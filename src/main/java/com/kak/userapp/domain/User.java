package com.kak.userapp.domain;

public class User 
{
	String name,address;
	long phone;
	
	public User(String name,String address,long phone)
	{
		this.name=name;
		this.address=address;
		this.phone=phone;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getAddress()
	{
		return this.address;
	}
	public void setPhone(long phone)
	{
		 this.phone=phone;
	}
	public long getPhone()
	{
		return this.phone;
	}
}
