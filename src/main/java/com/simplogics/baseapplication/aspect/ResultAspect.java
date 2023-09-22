package com.simplogics.baseapplication.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simplogics.baseapplication.annotation.APIResult;
import com.simplogics.baseapplication.dto.ResultDto;
import com.simplogics.baseapplication.exception.CustException;

@Aspect
@Component
public class ResultAspect {

	@Autowired
	private MessageSource messageSource;

	@Around("@annotation(com.simplogics.baseapplication.annotation.APIResult)")
	public Object onApiResponse(final ProceedingJoinPoint pjp) {
		final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		APIResult apiResult = method.getAnnotation(APIResult.class);
		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		ResultDto resultDto = null;
		RequestMethod[] requestMethod = requestMapping.method();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			final Object resultValue = pjp.proceed();

			if (resultValue == null) {
				resultDto = new ResultDto(true, retrieveMessage(apiResult.error_message()), resultValue);
				httpStatus = HttpStatus.I_AM_A_TEAPOT;
			} else {
				resultDto = new ResultDto(true, retrieveMessage(apiResult.message()), apiResult.message_code(),
						resultValue);
				httpStatus = getStatusForHttpType(requestMethod[0]);
			}

		} catch (Throwable e) {
			if (e instanceof CustException) {
				CustException exception = (CustException) e;
				resultDto = new ResultDto(false, retrieveMessage(exception.getMessage()), exception.getMessageCode(),
						null);
			} else if (e instanceof EmptyResultDataAccessException) {
				httpStatus = HttpStatus.NOT_FOUND;
				resultDto = new ResultDto(true, retrieveMessage("#data.not.found"), 0, null);
			} else if (e instanceof BadCredentialsException) {
				httpStatus = HttpStatus.UNAUTHORIZED;
				resultDto = new ResultDto(false, retrieveMessage("#invalid.credentials"), 0, null);
			} else {
				e.printStackTrace();
				resultDto = new ResultDto(false, retrieveMessage("#server.error"), 0, null);
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}

		return new ResponseEntity<Object>(resultDto, httpStatus);
	}

	private String retrieveMessage(String key) {
		if (key != null && key.startsWith("#")) {
			return messageSource.getMessage(key.replace("#", ""), null, key, LocaleContextHolder.getLocale());
		}
		return key;
	}

	private HttpStatus getStatusForHttpType(RequestMethod requestMethod) {
		HttpStatus status = null;
		switch (requestMethod) {
		case GET:
			status = HttpStatus.OK;
			break;
		case POST:
			status = HttpStatus.CREATED;
			break;
		case PUT:
			status = HttpStatus.CREATED;
			break;
		case PATCH:
			status = HttpStatus.OK;
			break;
		case DELETE:
			status = HttpStatus.OK;
			break;
		default:
			break;
		}
		return status;
	}
}
