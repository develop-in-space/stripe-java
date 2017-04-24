package com.kak.userapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.kak.services.UserService;
import com.kak.userapp.domain.User;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import com.stripe.net.RequestOptions.RequestOptionsBuilder;


// URL :- http://localhost:8080/userapp/usernameAndPassword  
// URL : http://localhost:8080/userapp/displayUser.html

@Controller
public class UserController
{
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/usernameAndPassword",method=RequestMethod.GET)
	public String userForm()
	{
		return "enterUsernameAndPassword";
	}
			
	/*@RequestMapping(value="/displayUser",method=RequestMethod.GET)
	public ModelAndView displayUsernameAndPassword(User user)
	{
		userService.addUser(user);
		ModelAndView modelView=new ModelAndView("viewUserInfo");
		return modelView;
	}*/
	
	@RequestMapping(value="/displayUser",method=RequestMethod.POST)
	public ModelAndView displayUsernameAndPassword(String name,String address,String city,String state,String zipcode,
			String country,long phone,String amount,String cardNumber,String cvc,String exp)
	{
		//Stripe.apiKey="sk_test_anVBqFXUs6571YJivnAtjrVh";
		
		RequestOptions requestOptions=(new RequestOptionsBuilder()).setApiKey("sk_test_oucOPKcuDTOEchEjR0pPqtyK").build();
		
		//RequestOptions requestOptions = RequestOptions.builder().setIdempotencyKey("xdqMR13rYBZvRbUw").build();
		
		ModelAndView modelView=new ModelAndView("viewUserInfo");	
		modelView.addObject("name",name);
		modelView.addObject("address",address);
		modelView.addObject("city",city);
		modelView.addObject("state",state);
		modelView.addObject("zipcode",zipcode);
		modelView.addObject("country",country);
		modelView.addObject("phone",phone);
		modelView.addObject("amount",amount);
		modelView.addObject("cardNumber",cardNumber);
		modelView.addObject("cvc",cvc);
		modelView.addObject("expiration",exp);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name",name);
		map.put("address",address);
		map.put("city",city);
		map.put("state",state);
		map.put("zipcode",zipcode);
		map.put("country",country);
		map.put("phone",phone);
		map.put("amount",amount);
		Map<String,Object> cardMap=new HashMap<String,Object>();
		cardMap.put("cardNumber",cardNumber);
		cardMap.put("cvc",cvc);
		cardMap.put("expiration",exp);
		map.put("card",cardMap);
		
		try
		{
			Charge charge=Charge.create(map,requestOptions);
			Logger logger=Logger.getLogger(UserController.class);
			logger.info(charge);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return modelView;
	}
}
