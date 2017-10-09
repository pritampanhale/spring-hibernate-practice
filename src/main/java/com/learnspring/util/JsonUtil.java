package com.learnspring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonUtil {

	
	public static String getJsonForm(Object obj) throws JsonProcessingException  {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
		
	}

	public static <T> T getObjectFromJson(String str, Class<T> clazz) throws Exception {
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(str, clazz);
	}
	

}
