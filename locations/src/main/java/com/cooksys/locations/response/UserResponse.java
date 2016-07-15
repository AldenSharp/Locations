package com.cooksys.locations.response;

import com.cooksys.locations.entity.User;

public class UserResponse {

	private String name;
	private int areaCode;

	public UserResponse(User user) {
		name = user.getName();
		areaCode = user.getNum();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

}
