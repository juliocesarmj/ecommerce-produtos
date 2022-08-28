package com.ecommerce.application.controllers.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProdutoHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Object> body = new HashMap<>();
		body.put("status", status.value());
		
		List<String> erros = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage).collect(Collectors.toList());
		
		body.put("erros", erros);
		
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ControllerHandler> handleIllegalArgumentException(IllegalArgumentException i) {
		ControllerHandler erro = new ControllerHandler();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		erro.setStatus(status.value());
		erro.setMessage(i.getMessage());
		
		return ResponseEntity.status(status).body(erro);
	}
}
