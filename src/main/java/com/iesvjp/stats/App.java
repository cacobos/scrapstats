package com.iesvjp.stats;

import java.awt.EventQueue;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.iesvjp.dom.PageEstadisticasCategoria;
import com.iesvjp.dom.PagePrincipal;
import com.iesvjp.dom.PageResultadosEquipo;
import com.iesvjp.modelos.Equipo;

/**
 * Hello world!
 *
 */
public class App {
	
	static WebDriver driver;

	public static void main(String[] args) {
		/*Utilidades.guardarEquiposLebOro();
		System.out.println("Guardados equipos oro");
		Utilidades.guardarEquiposLebPlata();
		System.out.println("Guardados equipos plata");
		Utilidades.guardarEquiposLF();
		System.out.println("Guardados equipos lf");*/
		Utilidades.guardarPartidos();
		Utilidades.guardarDatosJugadores();
	}
}
