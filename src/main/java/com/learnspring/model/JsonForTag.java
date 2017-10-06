package com.learnspring.model;

public class JsonForTag {

	private String id;
	private String tag;
	
	public JsonForTag(String id, String tag) {
		this.id = id;
		this.tag = tag;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
