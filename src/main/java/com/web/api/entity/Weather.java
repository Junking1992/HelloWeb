package com.web.api.entity;

public class Weather {
	
	public String reason;

	public Weather() {

	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Weather [reason=" + reason + "]";
	}

}
