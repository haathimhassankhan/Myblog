package com.journaldev.jaxrs.model;

public class LoginResponse {
	
	private String name;
	private String message;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

}
