package com.learnspring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.learnspring.bean.MyBean;

@EnableWebMvc
@Configuration
@ComponentScan("com")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public MyBean myBean(){
		return new MyBean("Pritam's Bean");
	}
}
