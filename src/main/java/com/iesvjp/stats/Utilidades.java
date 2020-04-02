package com.iesvjp.stats;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.iesvjp.dom.PagePrincipal;
import com.iesvjp.dom.PageResultadosEquipo;
import com.iesvjp.modelos.Equipo;

public class Utilidades {
	public static void abrirEnNewTab(WebDriver driver, WebElement enlace) {
		String keyString = Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString();
		enlace.sendKeys(keyString);
	}

	public static void guardarEnBaseDatos(EntityManager em, Equipo e) {
		Query q = em.createQuery("select count(e) from Equipo e where e.nombre = :nombre and e.temporada = :temporada")
				.setParameter("nombre", e.getNombre()).setParameter("temporada", e.getTemporada());
		if (q.getMaxResults() == 0) {
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

		List<Equipo> equipos = Equipo.findAllEquipos(em);

		for (Equipo equipo : equipos) {
			PageResultadosEquipo pageEquipo = new PageResultadosEquipo(driver);
			pageEquipo.setEquipo(equipo);
			pageEquipo.leerPartidosEquipo(em);
		}
		
		guardarFasesPartidos(equipos, em);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	private static void guardarFasesPartidos(List<Equipo> equipos, EntityManager em) {
		WebDriver driver=null;
		for (Equipo equipo : equipos) {
			new PageResultadosEquipo(driver);
		}
	}
}
