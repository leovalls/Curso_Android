package com.leovalls.curso_android.model;

import java.util.List;

public class Photo {
	
	private String id;
	private String url;
	private byte[] image;
	private String description;
	private int numFavorites;
	private List<Comment> comments;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
