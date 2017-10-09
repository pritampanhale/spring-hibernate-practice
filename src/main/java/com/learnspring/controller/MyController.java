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
import com.learnspring.model.JsonForTag;
import com.learnspring.model.Tag;
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
		String readChangeSetInformation = myService.readChangeSetInformation(objectFromJson);
		JsonForTag tag = new JsonForTag("1", readChangeSetInformation);
		
		return JsonUtil.getJsonForm(tag);
	}
	
	
	@RequestMapping(value = "/tagChangeSet", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody String tagChangeSet(@RequestBody String req) throws Exception{ 

		Tag tag = JsonUtil.getObjectFromJson(req, Tag.class);
		myService.updateTag(tag);
		System.out.println("Tag updated successfully");
		return "updated";
	}
}

