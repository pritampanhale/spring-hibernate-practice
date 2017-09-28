package com.learnspring.bean;

public class MyBean {

	private String beanName;
	
	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public MyBean(String beanName) {
		this.beanName = beanName;
	}
}
