package com.ecommerce.application.controllers.exceptions;

import lombok.Data;

@Data
public class ControllerHandler {
	
	private int status;
	private String message;
}
