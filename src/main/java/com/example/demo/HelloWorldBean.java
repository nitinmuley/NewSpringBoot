package com.example.demo;

public class HelloWorldBean {

	//this 
	private String message;
   public HelloWorldBean() {
	   
   }
	public HelloWorldBean(String message) {
		this.message=message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}
