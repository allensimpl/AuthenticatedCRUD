package com.simplogics.baseapplication.security.filters;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.security.service.impl.UserDetailServiceImpl;
import com.simplogics.baseapplication.security.utils.JwtTokenUtil;
import com.simplogics.baseapplication.security.utils.Subject;
import com.simplogics.baseapplication.service.UserService;
import com.simplogics.baseapplication.utils.Constants;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@WebFilter(urlPatterns = "/secure/**")
@Order(3)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {

		String header = req.getHeader(Constants.AUTH_HEADER_KEY);
		String redisKey = null;
		String authToken = null;
		String url = req.getRequestURI();

		if (!url.contains("/secure/")) {
			filterChain.doFilter(req, res);
			return;
		}
		if (header != null && header.startsWith(Constants.TOKEN_PREFIX)) {
			authToken = header.replace(Constants.TOKEN_PREFIX, "");
			try {
				redisKey = jwtTokenUtil.extractTokenValue(authToken);
				Subject subject = jwtTokenUtil.getSubject(redisKey);
				if (subject != null) {
					try {
						
						UserDetailServiceImpl userDetailServiceImpl = (UserDetailServiceImpl) userDetailsService;

						userService.findUserById(subject.getId());

						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								subject, null, userDetailServiceImpl.getAuthoritiesFromRoleString(subject.getRole()));
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					} catch (EmptyResultDataAccessException e) {
						jwtTokenUtil.deleteSession(redisKey);
						throw new UnsupportedJwtException(e.getMessage());
					}
				} else {
					sendErrorWithStatus(HttpStatus.UNAUTHORIZED.value(), "Session expired", res);
					return;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				sendErrorWithStatus(HttpStatus.FORBIDDEN.value(), "Unauthorized", res);
				return;
			} catch (ExpiredJwtException e) {
				sendErrorWithStatus(HttpStatus.UNAUTHORIZED.value(), "Session expired", res);
				return;
			} catch (SignatureException e) {

				sendErrorWithStatus(HttpStatus.FORBIDDEN.value(), "Invalid signature", res);
				return;
			} catch (UnsupportedJwtException | MalformedJwtException e) {
				e.printStackTrace();
				sendErrorWithStatus(HttpStatus.FORBIDDEN.value(), "Unauthorized", res);
				return;
			}
		} else {
			System.out.println("the header is null________________________________________");
			sendErrorWithStatus(HttpStatus.FORBIDDEN.value(), "Unauthorized", res);
			return;
		}

		try {
			filterChain.doFilter(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			sendErrorWithStatus(HttpStatus.FORBIDDEN.value(), "Unauthorized", res);
		}

	}

	private void sendErrorWithStatus(int status, String message, HttpServletResponse res) {
		res.setStatus(status);
		try {
			Writer writer = res.getWriter();
			writer.write(new Gson().toJson(new ResultDto(false, message, null)));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
