package com.cooksys.locations.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HitByDate")
public class HitByDate {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "NUM")
	private int num;
	
	@Column(name = "DATE")
	private Date date;
	
	public HitByDate(int num) {
		this.num = num;
		this.date = new Date();
	}
	
	public HitByDate() {
		// Default constructor.
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
