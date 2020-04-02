package com.iesvjp.modelos;



import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.Basic;
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
public class LineaPartido implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JoinColumn(name = "fk_jugador", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Jugador jugador;
	@JoinColumn(name = "fk_partido", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Partido partido;
	@JoinColumn(name = "fk_equipo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Equipo equipo;
	@Column
	@Basic
	private Time minutos;
	@Column
	private int puntos;
	@Column
	private int t2a;
	@Column
	private int t2i;
	@Column
	private int t3a;
	@Column
	private int t3i;
	@Column
	private int tla;
	@Column
	private int tli;
	@Column
	private int rbo;
	@Column
	private int rbd;
	@Column
	private int asist;
	@Column
	private int br;
	@Column
	private int bp;
	@Column
	private int fc;
	@Column
	private int fr;
	@Column
	private int val;
	@Column
	private int masMenos;

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Time getMinutos() {
		return minutos;
	}

	public void setMinutos(Time minutos) {
		this.minutos = minutos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getT2a() {
		return t2a;
	}

	public void setT2a(int t2a) {
		this.t2a = t2a;
	}

	public int getT2i() {
		return t2i;
	}

	public void setT2i(int t2i) {
		this.t2i = t2i;
	}

	public int getT3a() {
		return t3a;
	}

	public void setT3a(int t3a) {
		this.t3a = t3a;
	}

	public int getT3i() {
		return t3i;
	}

	public void setT3i(int t3i) {
		this.t3i = t3i;
	}

	public int getTla() {
		return tla;
	}

	public void setTla(int tla) {
		this.tla = tla;
	}

	public int getTli() {
		return tli;
	}

	public void setTli(int tli) {
		this.tli = tli;
	}

	public int getRbo() {
		return rbo;
	}

	public void setRbo(int rbo) {
		this.rbo = rbo;
	}

	public int getRbd() {
		return rbd;
	}

	public void setRbd(int rbd) {
		this.rbd = rbd;
	}

	public int getAsist() {
		return asist;
	}

	public void setAsist(int asist) {
		this.asist = asist;
	}

	public int getBr() {
		return br;
	}

	public void setBr(int br) {
		this.br = br;
	}

	public int getBp() {
		return bp;
	}

	public void setBp(int bp) {
		this.bp = bp;
	}

	public int getFc() {
		return fc;
	}

	public void setFc(int fc) {
		this.fc = fc;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getMasMenos() {
		return masMenos;
	}

	public void setMasMenos(int masMenos) {
		this.masMenos = masMenos;
	}
	
	
	
}
