package com.leovalls.curso_android.data;

import java.util.List;

public class Photo {
	
	private String url;
	private String descripcion;
	private int numFavoritos;
	private List<Comment> comentarios;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getNumFavoritos() {
		return numFavoritos;
	}
	public void setNumFavoritos(int numFavoritos) {
		this.numFavoritos = numFavoritos;
	}
	public List<Comment> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comment> comentarios) {
		this.comentarios = comentarios;
	}

	
}
