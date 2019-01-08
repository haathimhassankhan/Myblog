package com.websystique.springmvc.dao;

import java.util.ArrayList;

import com.hathim.blog.model.User;

public interface userDao {
	
	public void save(User user);
	
	public ArrayList fetch(User user);
	
	public ArrayList checkUserNameAlreadyExist(String username); 
	
}
