package com.web.api.entity;

public class Ip {

	public String area;
	public String location;

	public Ip() {
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Ip [area=" + area + ", location=" + location + "]";
	}
}
