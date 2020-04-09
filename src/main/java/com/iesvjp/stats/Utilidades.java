package com.iesvjp.stats;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.iesvjp.dom.PageEstadisticasOro;
import com.iesvjp.dom.PageJugador;
import com.iesvjp.dom.PagePartido;
import com.iesvjp.dom.PagePrincipal;
import com.iesvjp.dom.PageResultadosEquipo;
import com.iesvjp.modelos.Equipo;
import com.iesvjp.modelos.Jugador;
import com.iesvjp.modelos.Partido;

public class Utilidades {
	public static void abrirEnNewTab(WebDriver driver, WebElement enlace) {
		String keyString = Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString();
		enlace.sendKeys(keyString);
	}

	public static void guardarEnBaseDatos(EntityManager em, Equipo e) {
		Query q = em.createQuery("select count(e) from Equipo e where e.nombre = :nombre and e.temporada = :temporada")
				.setParameter("nombre", e.getNombre()).setParameter("temporada", e.getTemporada());
		if ((long)q.getSingleResult() == 0) {
			em.persist(e);
		}
	}

	public static void guardarEquiposLebOro() {
		WebDriver driver = null;
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stats");
		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		// Iniciamos una transacción
		em.getTransaction().begin();
		PagePrincipal navegador = new PagePrincipal(driver);
		List<Equipo> equipos = navegador.visitEstadisticasOro().getEquipos();
		for (int i = 0; i < equipos.size(); i++) {
			guardarEnBaseDatos(em, equipos.get(i));
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public static void guardarPartidosLebOro() {
		WebDriver driver = null;
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stats");
		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		// Iniciamos una transacción
		em.getTransaction().begin();

		List<Equipo> equipos = em.createQuery("select e from Equipo e").getResultList();
		for (int i = 0; i < equipos.size(); i++) {
			System.out.println("Guardando partido " + i + " de "+ (equipos.size()-1));
			PageResultadosEquipo pageEquipo = new PageResultadosEquipo(driver);
			pageEquipo.setEquipo(equipos.get(i));
			pageEquipo.leerPartidosEquipo(em);
		}
		
		guardarFasesPartidos(equipos, em);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	private static void guardarFasesPartidos(List<Equipo> equipos, EntityManager em) {
		WebDriver driver=null;
		PageResultadosEquipo nav;
		for (int i = 0; i < equipos.size(); i++) {
			System.out.println("Guardando fase de partido " + i + " de "+ (equipos.size()-1));
			nav=new PageResultadosEquipo(driver);
			 nav.setEquipo(equipos.get(i));
			 nav.leerPartidosEquipo(em);
		}
	}
	
	public static void mostrarEquipos() {
		WebDriver driver = null;
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stats");
		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		// Iniciamos una transacción
		em.getTransaction().begin();

		List<Equipo> equipos = em.createQuery("select e from Equipo e").getResultList();
		for (Equipo equipo : equipos) {
			System.out.println(equipo);
		}

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public static void guardarDatosPartidos() {
		WebDriver driver = null;
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stats");
		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		// Iniciamos una transacción
		em.getTransaction().begin();
		List<Partido> partidos = em.createQuery("select p from Partido p where p.equipo1=null").getResultList();
		for (int i = 0; i < partidos.size(); i++) {
			System.out.println("Guardando partido " + (i+1) + " de " + partidos.size());
			PagePartido pagePartido=new PagePartido(driver);
			pagePartido.guardarDatosPartido(partidos.get(i), em);
			pagePartido.cerar();
		}

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public static void guardarDatosJugadores() {
		WebDriver driver = null;
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stats");
		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		// Iniciamos una transacción
		em.getTransaction().begin();
		List<Jugador> jugadores= em.createQuery("select j from Jugador j where j.nombre=null and url!=null").getResultList();
		for (int i = 0; i < jugadores.size(); i++) {			
			System.out.println("Guardando jugador " + i + " de "+ (jugadores.size()-1));
			PageJugador pageJugador=new PageJugador(driver);
			Jugador jugador=jugadores.get(i);
			pageJugador.visit(jugador.getUrl());
			pageJugador.leerJugador(jugador, em);
			em.getTransaction().commit();
			em.getTransaction().begin();
		}	

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
