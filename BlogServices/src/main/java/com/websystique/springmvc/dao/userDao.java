package com.websystique.springmvc.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;

import com.journaldev.jaxrs.model.LoginRequest;
import com.journaldev.jaxrs.model.User;





public interface userDao {
	
	public void save(User user);
	
	public String fetch(LoginRequest loginRequest);
	
	public ArrayList checkUserNameAlreadyExist(String username); 
	
	public ArrayList fetch(String query);
	
}
