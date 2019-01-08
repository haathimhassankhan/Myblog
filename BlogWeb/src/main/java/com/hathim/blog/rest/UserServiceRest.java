package com.hathim.blog.rest;

import java.io.BufferedReader;

import com.websystique.springmvc.dao.userDao;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hathim.blog.model.User;
import com.hathim.blog.service.LoginRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserServiceRest {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@Autowired
	userDao employeeDAO;

	  public static final ObjectWriter objectWriter = new ObjectMapper().writer();
	public  static void main(String[] args) {
		UserServiceRest uv = new UserServiceRest();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Hathim");
		loginRequest.setPassword("Hathim");
		
		try {
			uv.sendJsonPost(loginRequest,"http://localhost:8090/RestService/person/getUser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendGet() throws Exception {
		URL urlObj = new URL("http://localhost:8090/RestService/person/99/getDummy");
		HttpURLConnection con =  (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine  = in.readLine()) != null){
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
		String age = response.toString().split("\\<age>")[1];
		age = age.split("\\</age>")[0];
		System.out.println(age);
	}
	
	public User sendJsonPost(LoginRequest loginRequest,String url) throws Exception,ClientProtocolException {
		User person = null;
		try{
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		String jsonRequest = objectWriter.writeValueAsString(loginRequest);
		StringEntity input = new StringEntity(jsonRequest);
		input.setContentType("application/json");
		post.setEntity(input);
		post.addHeader("Content-Type",ContentType.APPLICATION_JSON.getMimeType());
		HttpResponse response = client.execute(post);
		if(response.getStatusLine().getStatusCode() == 200){
			
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            ObjectMapper mapper = new ObjectMapper();
            person = mapper.readValue(reader.readLine(), User.class);
            }

		}
		catch(Exception  e) {
			System.out.println(e.getMessage());
		}
		return person;

	}

}
