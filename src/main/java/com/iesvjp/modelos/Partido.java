package com.iesvjp.modelos;




import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Partido implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "fk_equipolocal", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Equipo equipoLocal;
	@JoinColumn(name = "equipovisitante", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Equipo equipoVistante;
	@Column
	private String competicion;
	@Column
	private String temporada;	
	@Column
	private String fase;
	@Column
	private String jornada;
	@Column
	private String url;
	@Column
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column
	private int prorroga;
	@Column
	private int ptoLocal;
	@Column
	private int ptoVisit;
	@Column
	private int punt1qLocal;
	@Column
	private int punt1qVisit;
	@Column
	private int punt2qLocal;
	@Column
	private int punt2qVisit;
	@Column
	private int punt3qLocal;
	@Column
	private int punt3qVisit;
	@Column
	private int punt4qLocal;
	@Column
	private int punt4qVisit;
	@Column
	private int t2aLocal;
	@Column
	private int t2iLocal;
	@Column
	private int t3aLocal;
	@Column
	private int t3iLocal;
	@Column
	private int tlaLocal;
	@Column
	private int tliLocal;
	@Column
	private int rbdLocal;
	@Column
	private int rboLocal;
	@Column
	private int asLocal;
	@Column
	private int brLocal;
	@Column
	private int bpLocal;
	@Column
	private int fcLocal;
	@Column
	private int frLocal;
	@Column
	private int valLocal;
	@Column
	private int t2aVisit;
	@Column
	private int t2iVisit;
	@Column
	private int t3aVisit;
	@Column
	private int t3iVisit;
	@Column
	private int tlaVisit;
	@Column
	private int tliVisit;
	@Column
	private int rbdVisit;
	@Column
	private int rboVisit;
	@Column
	private int asVisit;
	@Column
	private int brVisit;
	@Column
	private int bpVisit;
	@Column
	private int fcVisit;
	@Column
	private int frVisit;
	@Column
	private int valVisit;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}
	public Equipo getEquipoVistante() {
		return equipoVistante;
	}
	public void setEquipoVistante(Equipo equipoVistante) {
		this.equipoVistante = equipoVistante;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getProrroga() {
		return prorroga;
	}
	public void setProrroga(int prorroga) {
		this.prorroga = prorroga;
	}
	public int getPtoLocal() {
		return ptoLocal;
	}
	public void setPtoLocal(int ptoLocal) {
		this.ptoLocal = ptoLocal;
	}
	public int getPtoVisit() {
		return ptoVisit;
	}
	public void setPtoVisit(int ptoVisit) {
		this.ptoVisit = ptoVisit;
	}
	public int getPunt1qLocal() {
		return punt1qLocal;
	}
	public void setPunt1qLocal(int punt1qLocal) {
		this.punt1qLocal = punt1qLocal;
	}
	public int getPunt1qVisit() {
		return punt1qVisit;
	}
	public void setPunt1qVisit(int punt1qVisit) {
		this.punt1qVisit = punt1qVisit;
	}
	public int getPunt2qLocal() {
		return punt2qLocal;
	}
	public void setPunt2qLocal(int punt2qLocal) {
		this.punt2qLocal = punt2qLocal;
	}
	public int getPunt2qVisit() {
		return punt2qVisit;
	}
	public void setPunt2qVisit(int punt2qVisit) {
		this.punt2qVisit = punt2qVisit;
	}
	public int getPunt3qLocal() {
		return punt3qLocal;
	}
	public void setPunt3qLocal(int punt3qLocal) {
		this.punt3qLocal = punt3qLocal;
	}
	public int getPunt3qVisit() {
		return punt3qVisit;
	}
	public void setPunt3qVisit(int punt3qVisit) {
		this.punt3qVisit = punt3qVisit;
	}
	public int getPunt4qLocal() {
		return punt4qLocal;
	}
	public void setPunt4qLocal(int punt4qLocal) {
		this.punt4qLocal = punt4qLocal;
	}
	public int getPunt4qVisit() {
		return punt4qVisit;
	}
	public void setPunt4qVisit(int punt4qVisit) {
		this.punt4qVisit = punt4qVisit;
	}
	public int getT2aLocal() {
		return t2aLocal;
	}
	public void setT2aLocal(int t2aLocal) {
		this.t2aLocal = t2aLocal;
	}
	public int getT2iLocal() {
		return t2iLocal;
	}
	public void setT2iLocal(int t2iLocal) {
		this.t2iLocal = t2iLocal;
	}
	public int getT3aLocal() {
		return t3aLocal;
	}
	public void setT3aLocal(int t3aLocal) {
		this.t3aLocal = t3aLocal;
	}
	public int getT3iLocal() {
		return t3iLocal;
	}
	public void setT3iLocal(int t3iLocal) {
		this.t3iLocal = t3iLocal;
	}
	public int getTlaLocal() {
		return tlaLocal;
	}
	public void setTlaLocal(int tlaLocal) {
		this.tlaLocal = tlaLocal;
	}
	public int getTliLocal() {
		return tliLocal;
	}
	public void setTliLocal(int tliLocal) {
		this.tliLocal = tliLocal;
	}
	public int getRbdLocal() {
		return rbdLocal;
	}
	public void setRbdLocal(int rbdLocal) {
		this.rbdLocal = rbdLocal;
	}
	public int getRboLocal() {
		return rboLocal;
	}
	public void setRboLocal(int rboLocal) {
		this.rboLocal = rboLocal;
	}
	public int getAsLocal() {
		return asLocal;
	}
	public void setAsLocal(int asLocal) {
		this.asLocal = asLocal;
	}
	public int getBrLocal() {
		return brLocal;
	}
	public void setBrLocal(int brLocal) {
		this.brLocal = brLocal;
	}
	public int getBpLocal() {
		return bpLocal;
	}
	public void setBpLocal(int bpLocal) {
		this.bpLocal = bpLocal;
	}
	public int getFcLocal() {
		return fcLocal;
	}
	public void setFcLocal(int fcLocal) {
		this.fcLocal = fcLocal;
	}
	public int getFrLocal() {
		return frLocal;
	}
	public void setFrLocal(int frLocal) {
		this.frLocal = frLocal;
	}
	public int getValLocal() {
		return valLocal;
	}
	public void setValLocal(int valLocal) {
		this.valLocal = valLocal;
	}
	public int getT2aVisit() {
		return t2aVisit;
	}
	public void setT2aVisit(int t2aVisit) {
		this.t2aVisit = t2aVisit;
	}
	public int getT2iVisit() {
		return t2iVisit;
	}
	public void setT2iVisit(int t2iVisit) {
		this.t2iVisit = t2iVisit;
	}
	public int getT3aVisit() {
		return t3aVisit;
	}
	public void setT3aVisit(int t3aVisit) {
		this.t3aVisit = t3aVisit;
	}
	public int getT3iVisit() {
		return t3iVisit;
	}
	public void setT3iVisit(int t3iVisit) {
		this.t3iVisit = t3iVisit;
	}
	public int getTlaVisit() {
		return tlaVisit;
	}
	public void setTlaVisit(int tlaVisit) {
		this.tlaVisit = tlaVisit;
	}
	public int getTliVisit() {
		return tliVisit;
	}
	public void setTliVisit(int tliVisit) {
		this.tliVisit = tliVisit;
	}
	public int getRbdVisit() {
		return rbdVisit;
	}
	public void setRbdVisit(int rbdVisit) {
		this.rbdVisit = rbdVisit;
	}
	public int getRboVisit() {
		return rboVisit;
	}
	public void setRboVisit(int rboVisit) {
		this.rboVisit = rboVisit;
	}
	public int getAsVisit() {
		return asVisit;
	}
	public void setAsVisit(int asVisit) {
		this.asVisit = asVisit;
	}
	public int getBrVisit() {
		return brVisit;
	}
	public void setBrVisit(int brVisit) {
		this.brVisit = brVisit;
	}
	public int getBpVisit() {
		return bpVisit;
	}
	public void setBpVisit(int bpVisit) {
		this.bpVisit = bpVisit;
	}
	public int getFcVisit() {
		return fcVisit;
	}
	public void setFcVisit(int fcVisit) {
		this.fcVisit = fcVisit;
	}
	public int getFrVisit() {
		return frVisit;
	}
	public void setFrVisit(int frVisit) {
		this.frVisit = frVisit;
	}
	public int getValVisit() {
		return valVisit;
	}
	public void setValVisit(int valVisit) {
		this.valVisit = valVisit;
	}
	
	public String getCompeticion() {
		return competicion;
	}
	public void setCompeticion(String competicion) {
		this.competicion = competicion;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public String getJornada() {
		return jornada;
	}
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
