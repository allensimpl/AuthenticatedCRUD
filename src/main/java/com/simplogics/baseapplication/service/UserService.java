package com.simplogics.baseapplication.service;

import org.springframework.dao.EmptyResultDataAccessException;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.LoginDto;

public interface UserService {

	BaseDto login(LoginDto loginDto);

	BaseDto findUserById(Long id) throws EmptyResultDataAccessException;
}
