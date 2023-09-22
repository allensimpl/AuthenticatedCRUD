package com.simplogics.baseapplication.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.simplogics.baseapplication.annotation.EmailValidator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto extends BaseDto{

	private static final long serialVersionUID = 5251239011301311399L;

	@EmailValidator(message = "#user.invalid.email")
	private String email;
	
	@NotNull(message = "#invalid.password")
	@NotEmpty(message = "#invalid.password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
