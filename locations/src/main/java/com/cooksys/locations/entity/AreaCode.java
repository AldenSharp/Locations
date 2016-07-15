package com.cooksys.locations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AreaCode", uniqueConstraints = @UniqueConstraint(columnNames = { "NUM" }))
public class AreaCode {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "NUM")
	private int num;
	
	@Column(name = "ANONYMOUSCOUNT")
	private int anonymousCount;
	
	@Column(name = "REGISTEREDUSERCOUNT")
	private int registeredUserCount;
	
	@Column(name = "LOGGEDINUSERCOUNT")
	private int loggedInUserCount;
	
	public AreaCode(int num) {
		
		this.num = num;
		this.anonymousCount = 0;
		this.registeredUserCount = 0;
		this.loggedInUserCount = 0;
		
	}
	
	public AreaCode() {
		// Default constructor.
	}
	
	public int getTotalCount() {
		return loggedInUserCount + anonymousCount;
	}
	
	public double getConversionRate() {
		return (double) this.registeredUserCount / this.getTotalCount();
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

	public int getAnonymousCount() {
		return anonymousCount;
	}

	public void setAnonymousCount(int anonymousCount) {
		this.anonymousCount = anonymousCount;
	}
	
	public void incrementAnonymousCount() {
		this.anonymousCount++;
	}
	
	public void decrementAnonymousCount() {
		this.anonymousCount--;
	}

	public int getRegisteredUserCount() {
		return registeredUserCount;
	}

	public void setRegisteredUserCount(int registeredUserCount) {
		this.registeredUserCount = registeredUserCount;
	}
	
	public void incrementRegisteredUserCount() {
		this.registeredUserCount++;
	}
	
	public void decrementRegisteredUserCount() {
		this.registeredUserCount--;
	}

	public int getLoggedInUserCount() {
		return loggedInUserCount;
	}

	public void setLoggedInUserCount(int loggedInUserCount) {
		this.loggedInUserCount = loggedInUserCount;
	}
	
	public void incrementLoggedInUserCount() {
		this.loggedInUserCount++;
	}
	
	public void decrementLoggedInUserCount() {
		this.loggedInUserCount--;
	}

}
