package com.leovalls.curso_android.model;

import java.util.List;

public class Store {
	
	private String id;
	private String name;
	private String description;
	private String address;
	private String phone;
	private String horary;
	private String website;
	private String email;
	private String latitude;
	private String longitude;
	private int numFavorites;
	private List<Comment> comments;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHorary() {
		return horary;
	}
	public void setHorary(String horary) {
		this.horary = horary;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getNumFavorites() {
		return numFavorites;
	}
	public void setNumFavorites(int numFavorites) {
		this.numFavorites = numFavorites;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	

}
