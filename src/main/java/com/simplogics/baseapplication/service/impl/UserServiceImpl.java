package com.simplogics.baseapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.LoginDto;
import com.simplogics.baseapplication.dto.UserDto;
import com.simplogics.baseapplication.entity.User;
import com.simplogics.baseapplication.repository.UserRepository;
import com.simplogics.baseapplication.security.utils.JwtTokenUtil;
import com.simplogics.baseapplication.service.UserService;

@Service(value = "userService")
//TODO Auth not required for now
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public BaseDto login(LoginDto loginDto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		User user = userRepository.findUserByEmail(loginDto.getEmail()).get();

		return new UserDto(user, jwtTokenUtil.generateToken(user));
	}

	@Override
	public BaseDto findUserById(Long id) throws EmptyResultDataAccessException {
		return new UserDto(userRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("#data.not.found", 0)));
	}

}
