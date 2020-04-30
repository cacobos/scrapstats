package com.iesvjp.stats;

import javax.persistence.EntityManager;

import org.openqa.selenium.WebDriver;

import com.iesvjp.dom.PagePartido;
import com.iesvjp.modelos.Partido;

public class HiloGuardarPartidosVacios extends Thread{
	
	WebDriver driver;
	PagePartido pagePartido;
	Partido partido;
	EntityManager em;
	boolean terminado;
	
	
	public HiloGuardarPartidosVacios(Partido partido, EntityManager em) {
		super();
		this.driver = null;
		this.pagePartido = new PagePartido(driver);
		this.partido = partido;
		this.em=em;
		terminado=false;
	}



	@Override
	public void run() {
		pagePartido.guardarDatosPartido(partido, em);
		pagePartido.cerar();
		terminado=false;
	}
	
	public boolean isTerminado() {
		return terminado;
	}

}
