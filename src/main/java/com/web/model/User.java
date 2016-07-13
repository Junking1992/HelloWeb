package com.web.model;

import org.springframework.stereotype.Repository;

@Repository
public class User {
	public String userName;
	public String passWord;
	public String name;
	public String ip;
	public String loginTime;
	public String address;

	public User() {
		super();
	}

	public User(String userName, String passWord, String name) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", passWord=" + passWord + ", name=" + name + ", ip=" + ip + ", loginTime=" + loginTime + ", address=" + address + "]";
	}

}
