package com.learnspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnspring.model.Emp;
import com.learnspring.model.Employee;
import com.learnspring.services.EmployeeServiceImpl;
import com.learnspring.services.MyService;

@Controller
public class MyController {
	
	private MyService myService;
	private EmployeeServiceImpl empService;

	@Autowired
	public MyController(MyService myServicem, EmployeeServiceImpl empservice) {
		this.myService = myServicem;
		this.empService = empservice;
	}

	@RequestMapping(value = "/hello")
	public @ResponseBody String testMethod(){
		String something = myService.getSomething();
		System.out.println("In controller " + something);
		return "hello annotation";
	}
	
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody String saveEmployee(@RequestBody String emp){
		
		System.out.println("In controller ");
		empService.saveEmployee();
		return "hello annotation";
	}
	
	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getAllEmployee(){
		
		List<Employee> findAllEmployees = empService.findAllEmployees();
		return findAllEmployees.toString();
	}
}

