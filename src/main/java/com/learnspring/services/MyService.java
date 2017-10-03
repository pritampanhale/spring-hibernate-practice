package com.learnspring.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnspring.bean.MyBean;
import com.learnspring.dao.DatabaseChangeLogDao;
import com.learnspring.model.Databasechangelog;
import com.learnspring.model.DatabasechangelogPK;
import com.learnspring.util.JsonUtil;

@Service
@Transactional
public class MyService {

	@Autowired
	MyBean mybean;
	
	@Autowired
	private DatabaseChangeLogDao changeLogDao;
	
	public String getSomething(){
		System.out.println(mybean.getBeanName());
		return "This is something";
	}
	
	public String getDatabaseChangeLogDetails() throws JsonProcessingException {
		List<Databasechangelog> databaseChangeLogDetails = changeLogDao.getDatabaseChangeLogDetails();
		String jsonForm = JsonUtil.getJsonForm(databaseChangeLogDetails);
		return jsonForm;
		
	}
	
	public void getChangeLogDetails(DatabasechangelogPK pk){
		Databasechangelog changeLogDetails = changeLogDao.getChangeLogDetails(pk);
	}
	
	public void readChangeSetInformation(DatabasechangelogPK pk) throws Exception{
		String baseProjectLocation = "C:/Users/pritam.panhale/workspace/testMaven/";
		FileReader fr = new FileReader(new File(baseProjectLocation+pk.getFilename()));
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		while( (str = br.readLine())!=null){
			System.out.println(str);
		}
	}
}
