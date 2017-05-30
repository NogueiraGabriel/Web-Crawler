package com.nogueiragabriel.crawler;

public class Company {
	private String name;
	private String phone;
	private String adress;
	
	public Company (String name, String phone, String adress){
		this.name = name;
		this.phone = phone;
		this.adress = adress;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhone() {
		return this.phone;
	} 
	
	public String getAdress() {
		return this.adress;
	}
}
