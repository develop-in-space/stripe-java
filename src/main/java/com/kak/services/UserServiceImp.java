package com.kak.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kak.userapp.domain.User;

@Service
public class UserServiceImp implements UserService 
{
	private ArrayList<User> userList;
	
	@PostConstruct
	private void initData()
	{
		userList=new ArrayList<User>();
	}
	
	public int addUser(User user)
	{
		userList.add(user);
		return 1;
	}
}
