package com.madhav.exceptions;

public class ApiException extends Throwable{
	
	private String message;
	
	private Object obj;
	
	private Throwable e;
	
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message, Object obj) {
		super(message);
	}
	
	public ApiException(String message, Object obj, Throwable e) {
		super(message, e);
	}
	

}
