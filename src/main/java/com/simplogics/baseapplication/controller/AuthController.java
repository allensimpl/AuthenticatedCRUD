package com.simplogics.baseapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simplogics.baseapplication.annotation.APIResult;
import com.simplogics.baseapplication.dto.LoginDto;
import com.simplogics.baseapplication.service.UserService;

@Controller
@RequestMapping("${spring.data.rest.base-path}")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/login")
	@APIResult(message = "#message.success", error_message = "#message.failed")
	public Object getIndex(@RequestBody @Valid LoginDto loginDto) {
		return userService.login(loginDto);
	}
}
