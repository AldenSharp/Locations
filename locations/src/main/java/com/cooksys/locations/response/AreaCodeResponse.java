package com.cooksys.locations.response;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.locations.entity.Location;

public class AreaCodeResponse {

	private int num;
	private List<String> locationTitles;

	public AreaCodeResponse(int num, List<Location> locations) {
		this.num = num;
		this.locationTitles = new ArrayList<String>();
		for (Location location : locations) {
			locationTitles.add(location.getTitle());
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<String> getLocationTitles() {
		return locationTitles;
	}

	public void setLocationTitles(List<String> locationTitles) {
		this.locationTitles = locationTitles;
	}

}
