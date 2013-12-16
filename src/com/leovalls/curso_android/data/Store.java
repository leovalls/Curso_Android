package com.leovalls.curso_android.data;

import java.util.List;

public class Store {
	
	private String nombre;
	private String direccion;
	private String telefono;
	private String horarios;
	private String website;
	private String email;
	private String ubicacionGeográfica;
	private int numFavoritos;
	private List<Comment> comentarios;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getHorarios() {
		return horarios;
	}
	public void setHorarios(String horarios) {
		this.horarios = horarios;
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
	public List<Comment> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comment> comentarios) {
		this.comentarios = comentarios;
	}
	public int getNumFavoritos() {
		return numFavoritos;
	}
	public void setNumFavoritos(int numFavoritos) {
		this.numFavoritos = numFavoritos;
	}
	public String getUbicacionGeográfica() {
		return ubicacionGeográfica;
	}
	public void setUbicacionGeográfica(String ubicacionGeográfica) {
		this.ubicacionGeográfica = ubicacionGeográfica;
	}
	
	

}
