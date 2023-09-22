package com.simplogics.baseapplication.aspect;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.simplogics.baseapplication.dto.ResultDto;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//			errors.add(error.getDefaultMessage());
			addErrorMessage(error, errors);
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			addErrorMessage(error, errors);
		}
		ResultDto result = new ResultDto(false, errors.isEmpty() ? "Method Argument Not Valid" : errors.get(0), 0);
		return new ResponseEntity<>(result, status);
	}

	private void addErrorMessage(ObjectError error, List<String> errors) {
		String errorMessage = retrieveMessage(error.getDefaultMessage());
		errors.add(errorMessage != null ? errorMessage : error.getDefaultMessage());
	}
	
	private String retrieveMessage(String key) {
		if (key != null && key.startsWith("#")) {
			return messageSource.getMessage(key.replace("#", ""), null, key, LocaleContextHolder.getLocale());
		}
		return key;
	}
}
