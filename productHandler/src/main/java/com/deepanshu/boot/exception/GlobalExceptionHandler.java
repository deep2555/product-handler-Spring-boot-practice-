package com.deepanshu.boot.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.deepanshu.boot.DTO.ExceptionResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoryAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponseDto> handleCategoryAlreadyExistException(CategoryAlreadyExistsException ex,
			WebRequest webRequest) {

		ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(webRequest.getDescription(false),
				HttpStatus.CONFLICT, ex.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDto);
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ExceptionResponseDto> handleCategoryNotfoundException(CategoryNotFoundException ex,
			WebRequest webRequest) {

		ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(webRequest.getDescription(false),
				HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDto);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponseDto> handleGlobalException(Exception ex,
			WebRequest webRequest) {

		ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDto);
	}
}
