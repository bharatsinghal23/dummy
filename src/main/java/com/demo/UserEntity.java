package com.demo;


import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the "mstUser" database table.
 * 
 */

public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private Long userId;

	@Autowired
	private Timestamp createdOn;

	@Autowired
	private Timestamp expirationDate;

	@Autowired
	private Boolean isActive;
	
	@Autowired
    private boolean accountNonLocked = true;
	
	@Autowired
    private boolean is2FAEnble = false;
	
	@Autowired
	private Boolean isDeleted;

	@JsonIgnore
	private String password;

	@Autowired
	private String userName;
	@Autowired
	private String mobile;
	
	@Autowired	private Boolean isPasswordChanged;
	
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsPasswordChanged() {
		return isPasswordChanged;
	}

	public void setIsPasswordChanged(Boolean isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}
	
	public boolean getis2FAEnble() {
		return is2FAEnble;
	}

	public void setis2FAEnble(boolean is2FAEnble) {
		this.is2FAEnble = is2FAEnble;
	}
	
	public UserEntity() {
	}
	public UserEntity(Long userId, Timestamp createdOn, Timestamp expirationDate, Boolean isActive,
		      boolean accountNonLocked, Boolean isDeleted, String password, String userName, String mobile,
		      Boolean isPasswordChanged) {
		    this.userId = userId;
		    this.createdOn = createdOn;
		    this.expirationDate = expirationDate;
		    this.isActive = isActive;
		    this.accountNonLocked = accountNonLocked;
		    this.isDeleted = isDeleted;
		    this.password = password;
		    this.userName = userName;
		    this.mobile = mobile;
		    this.isPasswordChanged = isPasswordChanged;
		  }
}
