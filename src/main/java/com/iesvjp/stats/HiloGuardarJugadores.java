package com.iesvjp.stats;

import javax.persistence.EntityManager;

import org.openqa.selenium.WebDriver;

import com.iesvjp.dom.PageJugador;
import com.iesvjp.modelos.Jugador;

public class HiloGuardarJugadores extends Thread{

	PageJugador pageJugador;
	Jugador jugador;
	EntityManager em;
	WebDriver driver;
	boolean terminado;
	
	public HiloGuardarJugadores(Jugador jugador, EntityManager em) {
		super();
	terminado=false;
		this.driver = null;
		this.pageJugador=new PageJugador(driver);
		this.jugador = jugador;
		this.em = em;
		
	}

	@Override
	public void run() {
		pageJugador.visit(jugador.getUrl());
		pageJugador.leerJugador(jugador, em);
		terminado=true;
	}
	
	public boolean isTerminado() {
		return terminado;
	}
}
