package com.cooksys.locations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cooksys.locations.model.AppLocation;

@Entity
@Table(name = "Location", uniqueConstraints = @UniqueConstraint(columnNames = { "TITLE" }))
public class Location {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "NUM")
	private int num;

	public Location(AppLocation appLocation) {

		title = appLocation.getTitle();
		num = appLocation.getNum();

	}
	
	public Location() {
		// Default constructor.
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
