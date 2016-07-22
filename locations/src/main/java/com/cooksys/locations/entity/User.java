package com.cooksys.locations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = { "NAME" }))
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NUM")
	private int num;
	
	@Column(name = "TYPE")
	private String type;
	
	public User(String s) {
		this.type = s;
	}

	public User() {
		// Default constructor.
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
