package com.continenantal.heat.webservices.restfulwebservices.beans;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String msg) {
		message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	
}
