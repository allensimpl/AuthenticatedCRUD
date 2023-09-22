package com.simplogics.baseapplication.security.utils;

import java.io.Serializable;
import java.util.Random;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.simplogics.baseapplication.entity.User;
import com.simplogics.baseapplication.utils.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3643445498812695391L;

	@Autowired
	private JedisPool jedisPool;

	@Value("${redis.instance.key}")
	private String instanceKey;

	public String extractTokenValue(String token) {
		return getClaimsFromtoken(token, Claims::getSubject);
	}

	private <T> T getClaimsFromtoken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(Constants.SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	public String generateToken(User user) {
		return doGenerateToken(user.getId(), user.getRole().name());
	}

	private String doGenerateToken(Long id, String role) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			final String redisKey = createRedisKey(id);
			final String subjectData = new Gson().toJson(new Subject(id, role));
			jedis.setex(redisKey, Constants.ACCESS_TOKEN_VALIDITY_SECONDS, subjectData);
			Claims claims = Jwts.claims().setSubject(redisKey);
			return Jwts.builder().setClaims(claims).setIssuer(Constants.JWT_ISSUER)
					.signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY).compact();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public Subject getSubject(String redisKey) {
		if (!StringUtils.hasText(redisKey)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			final String subject = jedis.get(redisKey);
			return StringUtils.hasText(subject) ? new Gson().fromJson(subject, Subject.class) : null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	private String createRedisKey(Long id) {
		return new StringBuilder().append(instanceKey).append("-").append(getSaltString()).append("-USR-" + id)
				.toString();
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public void deleteSession(String redisKey) {
		Jedis jedis = jedisPool.getResource();
		jedis.del(redisKey);
		jedis.close();
	}
}
