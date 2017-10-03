package com.learnspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnspring.model.DatabasechangelogPK;
import com.learnspring.model.Employee;
import com.learnspring.services.EmployeeServiceImpl;
import com.learnspring.services.MyService;
import com.learnspring.util.JsonUtil;

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
	public @ResponseBody String getAllEmployee() throws JsonProcessingException {
		
		List<Employee> findAllEmployees = empService.findAllEmployees();
		return JsonUtil.getJsonForm(findAllEmployees);
	}
	
	@RequestMapping(value = "/getChangeLogDetails", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getDatabaseChangeLogDetails() throws JsonProcessingException {
		
		 return myService.getDatabaseChangeLogDetails();
		
	}
	
	@RequestMapping(value = "/viewChangeLogDetails", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody String viewChangeLogDetails(@RequestBody String emp) throws Exception{ 

		DatabasechangelogPK objectFromJson = JsonUtil.getObjectFromJson(emp, DatabasechangelogPK.class);
		myService.getChangeLogDetails(objectFromJson);
		myService.readChangeSetInformation(objectFromJson);
		
		return "hello annotation";
	}
}

