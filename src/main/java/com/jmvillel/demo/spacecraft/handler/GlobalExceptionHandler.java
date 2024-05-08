package com.jmvillel.demo.spacecraft.handler;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jmvillel.demo.spacecraft.aspect.LoggerAspect;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	MessageSource messageSource;
	
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
	private static final Locale locale = LocaleContextHolder.getLocale();
	Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
  
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleDefaultException(Exception exception) {
		
		logger.error(exception.getMessage());

		return ErrorResponse
				.create(exception, 
						HttpStatus.BAD_REQUEST, 
						messageSource.getMessage("error.default", new Object[]{}, locale));
	}
  
	@ExceptionHandler(EntityNotFoundException.class)
	public ErrorResponse handleEntityNotFoundException(EntityNotFoundException exception) {
		ErrorResponse response = ErrorResponse
		.create(exception, 
				HttpStatus.BAD_REQUEST, 
				exception.getMessage());
		return response;
	}
	
	@ExceptionHandler(EntityExistsException.class)
	public ErrorResponse handleEntityExistsException(EntityExistsException exception) {
		ErrorResponse response = ErrorResponse
		.create(exception, 
				HttpStatus.BAD_REQUEST, 
				exception.getMessage());
		return response;
	}
}
