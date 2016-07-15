package com.cooksys.locations.response;

import com.cooksys.locations.entity.AreaCode;

public class ConversionRateResponse {

	private int num;
	private double rate;

	public ConversionRateResponse(AreaCode areaCode) {
		num = areaCode.getNum();
		rate = areaCode.getConversionRate();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
