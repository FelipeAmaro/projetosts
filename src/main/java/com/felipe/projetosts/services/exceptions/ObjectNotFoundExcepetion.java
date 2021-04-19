package com.felipe.projetosts.services.exceptions;

public class ObjectNotFoundExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExcepetion(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundExcepetion(String msg, Throwable cause) {
		super(msg, cause);
	}
}
