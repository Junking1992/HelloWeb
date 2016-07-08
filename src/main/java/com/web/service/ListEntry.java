package com.web.service;

public class ListEntry {

	public String images;

	public String title;

	public String protagonist;

	public String country;

	public String source;

	public String date;

	public String url;

	@Override
	public String toString() {
		return "ListEntry [url=" + url + ", images=" + images + ", title=" + title + ", protagonist=" + protagonist
				+ ", country=" + country + ", source=" + source + ", date=" + date + "]";
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProtagonist() {
		return protagonist;
	}

	public void setProtagonist(String protagonist) {
		this.protagonist = protagonist;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
