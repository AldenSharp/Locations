package com.cooksys.locations.response;

public class MethodResponse {
	
	String info;
	Boolean error;
	
	public MethodResponse(String info, Boolean error) {
		this.info = info;
		this.error = error;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}
	
}
