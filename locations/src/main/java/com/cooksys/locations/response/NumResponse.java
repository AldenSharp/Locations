package com.cooksys.locations.response;

import com.cooksys.locations.entity.AreaCode;

public class NumResponse {

	private int num;
	private String label;
	private int hitsPastWeek;
	private int hitsPastMonth;
	private int hitsPastYear;
	private int hitsAllTime;
	private int registeredUsers;
	private int loggedInUsers;
	private double conversionRate;
	
	public NumResponse(AreaCode areaCode, int hitsPastWeek, int hitsPastMonth, int hitsPastYear) {
		this.num = areaCode.getNum();
		this.label = areaCode.getLabel();
		this.hitsPastWeek = hitsPastWeek;
		this.hitsPastMonth = hitsPastMonth;
		this.hitsPastYear = hitsPastYear;
		this.hitsAllTime = areaCode.getAnonymousCount();
		this.registeredUsers = areaCode.getRegisteredUserCount();
		this.loggedInUsers = areaCode.getLoggedInUserCount();
		this.conversionRate = areaCode.getConversionRate();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getHitsPastWeek() {
		return hitsPastWeek;
	}

	public void setHitsPastWeek(int hitsPastWeek) {
		this.hitsPastWeek = hitsPastWeek;
	}

	public int getHitsPastMonth() {
		return hitsPastMonth;
	}

	public void setHitsPastMonth(int hitsPastMonth) {
		this.hitsPastMonth = hitsPastMonth;
	}

	public int getHitsPastYear() {
		return hitsPastYear;
	}

	public void setHitsPastYear(int hitsPastYear) {
		this.hitsPastYear = hitsPastYear;
	}

	public int getHitsAllTime() {
		return hitsAllTime;
	}

	public void setHitsAllTime(int hitsAllTime) {
		this.hitsAllTime = hitsAllTime;
	}
	
	public int getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(int registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public int getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(int loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}
	
}
