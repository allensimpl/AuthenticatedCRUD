package com.simplogics.baseapplication.dto;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.simplogics.baseapplication.annotation.EmailValidator;
import com.simplogics.baseapplication.entity.User;
import com.simplogics.baseapplication.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

	private static final long serialVersionUID = 4935627812808934657L;

	private String firstName;

	private String lastName;

	@EmailValidator(message = "{user.invalid.email}")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonInclude(Include.NON_NULL)
	private String token;

	private UserRole role;

	public UserDto(User user) {
		BeanUtils.copyProperties(user, this, "id");

	}

	public UserDto(User user, String token) {
		BeanUtils.copyProperties(user, this, "id");
		this.token = token;
	}

}
