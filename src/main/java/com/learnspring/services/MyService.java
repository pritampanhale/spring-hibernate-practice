package com.learnspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnspring.bean.MyBean;

@Service
public class MyService {

	@Autowired
	MyBean mybean;
	
	public String getSomething(){
		System.out.println(mybean.getBeanName());
		return "This is something";
	}
}
