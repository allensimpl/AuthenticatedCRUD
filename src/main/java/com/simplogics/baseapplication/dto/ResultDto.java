package com.simplogics.baseapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto extends BaseDto{

	private static final long serialVersionUID = 4808834865591910669L;

	private String message;

	private boolean status;

	private int messageCode = 1;

	private Object data;

	public ResultDto(boolean status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ResultDto(boolean status, String message, int messageCode, Object data) {
		this.status = status;
		this.message = message;
		this.messageCode = messageCode;
		this.data = data;
	}
}
