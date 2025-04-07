package com.ust.demo.exceptions;

public class FoodAlreadyFoundException extends RuntimeException {

	public FoodAlreadyFoundException(String message) {
		super(message);
	}
	
}
