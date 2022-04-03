package com.panamby.clientes.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.panamby.clientes.rest.exceptions.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object handleValidationErrors(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		List<String> messages = bindingResult.getAllErrors()
		.stream()
		.map(objectError -> objectError.getDefaultMessage())
		.collect(Collectors.toList());
	return new ApiErrors(messages);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity handleResponseStatusException(ResponseStatusException e) {
		String messageErro = e.getMessage();
		HttpStatus codigoStatus = e.getStatus();
		ApiErrors apiErrors = new ApiErrors(messageErro);
		return new ResponseEntity(apiErrors, codigoStatus);
	}
}
