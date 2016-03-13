package com.chaljaa.model;

public enum ContactType {
	MOBILE("MOBILE"),
	EMAIL("EMAIL"),
	EMERGENCY("EMERGENCY");
	
	String contactType;
	
	private ContactType(String contactType){
		this.contactType = contactType;
	}
	
	public String getContactType(){
		return contactType;
	}
	
}
