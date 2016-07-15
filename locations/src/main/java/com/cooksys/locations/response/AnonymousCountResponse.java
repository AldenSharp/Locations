package com.cooksys.locations.response;

import com.cooksys.locations.entity.AreaCode;

public class AnonymousCountResponse {

	private int num;
	private int count;

	public AnonymousCountResponse(AreaCode areaCode) {
		num = areaCode.getNum();
		count = areaCode.getAnonymousCount();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
