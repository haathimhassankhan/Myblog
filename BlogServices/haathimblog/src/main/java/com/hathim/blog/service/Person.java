package com.hathim.blog.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
public class Person {
	
	private String namee;
	private int age;
	private int id;

	public String getName() {
		return namee;
	}

	public void setName(String name) {
		this.namee = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id+"::"+namee+"::"+age;
	}

}
