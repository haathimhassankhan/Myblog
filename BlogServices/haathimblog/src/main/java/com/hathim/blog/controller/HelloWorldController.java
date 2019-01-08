package com.hathim.blog.controller;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hathim.blog.model.User;
import com.hathim.blog.rest.UserServiceRest;
import com.hathim.blog.service.LoginRequest;
import com.hathim.blog.service.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.websystique.springmvc.dao.userDao;
 
@Controller
public class HelloWorldController {
	
	@Autowired
	userDao employeeDAO;
	
	UserServiceRest userServiceRest = new UserServiceRest();
	
	public static final ObjectWriter objectWriter = new ObjectMapper().writer();
 
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public ModelAndView sayHello(ModelMap model,HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	session.setAttribute("greeting", "Welcome to shopon");
        ModelAndView modelAndView = new ModelAndView("login");
        model.addAttribute("user",new User());
        return modelAndView;
    }
    
    @RequestMapping(value="/helloagain",method = RequestMethod.GET)
    public ModelAndView sayHelloAgain(ModelMap model,HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	ModelAndView modelAndView = new ModelAndView("login");
    	if(session.getAttribute("user")== null){
    		session.setAttribute("greeting", "You cannot access directly");
    	        model.addAttribute("user",new User());
    	}
    	else{
    		User user = (User)session.getAttribute("user");
    		return sayHelloAgain(model, user, req);
    	}
       
        return modelAndView;
    }
 
    @RequestMapping(value="/helloagain", method = RequestMethod.POST)
    public ModelAndView sayHelloAgain(ModelMap model,User user,HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	ModelAndView modelAndView = new ModelAndView("welcome");
    	modelAndView.addObject("user", user);
    	LoginRequest loginRequest = new LoginRequest();
    	loginRequest.setUsername(user.getUserName());
    	loginRequest.setPassword(user.getPassword());
        
        try {
		      user = userServiceRest.sendJsonPost(loginRequest, "http://localhost:9091/RestService/person/getUser");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if(user != null){
    		session.setAttribute("greeting", "Hi "+user.getName());
    		session.setAttribute("user", user);
    		}
    	else {
    		session.setAttribute("greeting", "Sorry, There is no such account exist");
    		modelAndView.setViewName("login");
    		}
        return modelAndView;
    }
    
    @RequestMapping(value="/logOut",method = RequestMethod.GET)
    public ModelAndView logOut(ModelMap model,HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	ModelAndView modelAndView = new ModelAndView("login");
    	if(session.getAttribute("user")== null){
    		session.setAttribute("greeting", "You cannot access directly");
    	        model.addAttribute("user",new User());
    	}
    	else{
    		req.getSession().invalidate();
    		model.addAttribute("user",new User());
    	}
       
        return modelAndView;
    }
    
    @RequestMapping(value="/signUp", method = RequestMethod.GET)
    public ModelAndView signUp(ModelMap model,HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	ModelAndView modelAndView = new ModelAndView("SignUp");
    	modelAndView.addObject("user", new User());
    	
    		User user = (User)session.getAttribute("user");
    	
        return modelAndView;
    }
    
    @RequestMapping(value="/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(ModelMap model,User user,HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	ArrayList list = null;
    	//model.addAttribute("greeting", "Hello World from Spring 4 MVC");
    	ModelAndView modelAndView = new ModelAndView("welcome");
    	//modelAndView.addObject("user", user);
    	list = employeeDAO.checkUserNameAlreadyExist(user.getUserName());
    	if(list != null && list.size() > 0){
    		session.setAttribute("greeting", "username already exist");
    		modelAndView.setViewName("SignUp");
    		modelAndView.addObject("user", user);
    		}
    	else {
    		perform(user);
    		user = new User();
    		modelAndView.addObject("user", user);
    		session.setAttribute("greeting", "Your account has been created");
    		modelAndView.setViewName("login");
    		}
    	//perform(user);
        return modelAndView;
    }
    
    public void perform(User user){
		employeeDAO.save(user);
    }
    
    public String invokePost(){
    	final String CONTENT_TYPE = "Content-Type";
    	CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        HttpPost post = new HttpPost("");
        post.addHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        return "";
    }
 
}