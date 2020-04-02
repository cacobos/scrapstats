package com.iesvjp.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

@Entity
public class Equipo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String nombre;
	@Column
	private String categoria;
	@Column
	private String url;
	@Column
	private String temporada;
	// private List<LineaPartido> lineasPartido;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", categoria=" + categoria + ", temporada=" + temporada + "]";
	}

	public static List<Equipo> findAllEquipos(EntityManager em) {
		return em.createQuery("select e from Equipo e").getResultList();
	}
}
