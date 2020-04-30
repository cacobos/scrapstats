package com.iesvjp.stats;

import javax.persistence.EntityManager;

import org.openqa.selenium.WebDriver;

import com.iesvjp.dom.PageResultadosEquipo;
import com.iesvjp.modelos.Equipo;

public class HiloGuardarNuevosPartidos extends Thread{

	WebDriver driver;
	PageResultadosEquipo nav;
	EntityManager em;
	boolean terminado;
	
	
	
	public HiloGuardarNuevosPartidos(WebDriver driver, Equipo e, EntityManager em) {
		super();
		this.driver = driver;
		this.nav =new PageResultadosEquipo(driver);
		nav.setEquipo(e);
		this.em = em;
		this.terminado=false;
	}



	@Override
	public void run() {
		nav.leerPartidosEquipo(em);
		terminado=true;
	}

	public boolean haTerminado() {
		return terminado;
	}
}
