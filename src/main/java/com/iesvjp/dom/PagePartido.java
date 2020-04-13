package com.iesvjp.dom;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.EntityManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.iesvjp.modelos.Equipo;
import com.iesvjp.modelos.Jugador;
import com.iesvjp.modelos.Lineapartido;
import com.iesvjp.modelos.Partido;

public class PagePartido extends Base {

	final static By byEquipoLocal = By.cssSelector("#equipoLocalHyperLink");
	final static By byEquipoVisitante = By.cssSelector("#equipoVisitanteHyperLink");
	final static By byTemporada = By.cssSelector("#paginaTitulo_temporadaLabel");
	final static By byCompeticion = By.cssSelector("#paginaTitulo_ligaLabel");
	final static By byTablaPuntosCuartos = By.cssSelector(".tablaResultadosCuartos");
	final static By byptosLocal = By.cssSelector("#resultadoLocalLabel");
	final static By byptosVisitante = By.cssSelector("#resultadoVisitanteLabel");

	final static By byFecha = By.cssSelector("#fechaLabel");

	final static By byt2Local = By.cssSelector("#jugadoresLocalDataGrid_Label21");
	final static By byt3Local = By.cssSelector("#jugadoresLocalDataGrid_Label23");
	final static By bytlLocal = By.cssSelector("#jugadoresLocalDataGrid_Label25");
	final static By byrbdLocal = By.cssSelector("#jugadoresLocalDataGrid_Label28");
	final static By byrboLocal = By.cssSelector("#jugadoresLocalDataGrid_Label27");
	final static By byasLocal = By.cssSelector("#jugadoresLocalDataGrid_Label29");
	final static By bybrLocal = By.cssSelector("#jugadoresLocalDataGrid_Label30");
	final static By bybpLocal = By.cssSelector("#jugadoresLocalDataGrid_Label31");
	final static By byfcLocal = By.cssSelector("#jugadoresLocalDataGrid_Label36");
	final static By byfrLocal = By.cssSelector("#jugadoresLocalDataGrid_Label35");
	final static By byvalLocal = By.cssSelector("#jugadoresLocalDataGrid_Label37");
	final static By by1qLocal = By.cssSelector("#primerParcialLocalLabel");
	final static By by2qLocal = By.cssSelector("#segundoParcialLocalLabel");
	final static By by3qLocal = By.cssSelector("#tercerParcialLocalLabel");
	final static By by4qLocal = By.cssSelector("#cuartoParcialLocalLabel");
	final static By by1qVisit = By.cssSelector("#primerParcialVisitanteLabel");
	final static By by2qVisit = By.cssSelector("#segundoParcialVisitanteLabel");
	final static By by3qVisit = By.cssSelector("#tercerParcialVisitanteLabel");
	final static By by4qVisit = By.cssSelector("#cuartoParcialVisitanteLabel");

	final static By byt2Visitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label45");
	final static By byt3Visitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label47");
	final static By bytlVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label52");
	final static By byrbdVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label56");
	final static By byrboVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label57");
	final static By byasVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label60");
	final static By bybrVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label62");
	final static By bybpVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label64");
	final static By byfcVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label73");
	final static By byfrVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label74");
	final static By byvalVisitante = By.cssSelector("#jugadoresVisitanteDataGrid_Label76");

	final static By byLineasLocal = By
			.cssSelector("#jugadoresLocalDataGrid .itemTabla, #jugadoresLocalDataGrid .itemAlternativoTabla ");
	final static By byLineasVisit = By
			.cssSelector("#jugadoresVisitanteDataGrid .itemTabla, #jugadoresVisitanteDataGrid .itemAlternativoTabla ");

	final static By byMinutosJugador = By.cssSelector("td:nth-child(4) span");
	final static By byNombreJugador = By.cssSelector("td:nth-child(3) a");
	final static By byPuntosJugador = By.cssSelector("td:nth-child(5) span");
	final static By byt2Jugador = By.cssSelector("td:nth-child(6) span");
	final static By byt3Jugador = By.cssSelector("td:nth-child(7) span");
	final static By bytlJugador = By.cssSelector("td:nth-child(9) span");
	final static By byrbdJugador = By.cssSelector("td:nth-child(10) td:nth-child(1) span");
	final static By byrboJugador = By.cssSelector("td:nth-child(10) td:nth-child(2) span");
	final static By byasJugador = By.cssSelector("td:nth-child(11) span");
	final static By bybrJugador = By.cssSelector("td:nth-child(12) span");
	final static By bybpJugador = By.cssSelector("td:nth-child(13) span");
	final static By byfcJugador = By.cssSelector("td:nth-child(16) td:nth-child(1) span");
	final static By byfrJugador = By.cssSelector("td:nth-child(16) td:nth-child(2) span");
	final static By byvalJugador = By.cssSelector("td:nth-child(17) span");
	final static By bymasmenosJugador = By.cssSelector("td:nth-child(18) span");

	public PagePartido(WebDriver driver) {
		super(driver);
	}

	public void guardarDatosPartido(Partido p, EntityManager em) {
		visit(p.getUrl());
		String nomEquipoLocal = findElement(byEquipoLocal).getText();
		String nomEquipoVisitante = findElement(byEquipoVisitante).getText();
		String temporada = findElement(byTemporada).getText();
		Equipo equipoLocal = (Equipo) em
				.createQuery("select e from Equipo e where e.nombre = :nombre and e.temporada= :temporada")
				.setParameter("nombre", nomEquipoLocal).setParameter("temporada", temporada).getSingleResult();
		Equipo equipoVisitante = (Equipo) em
				.createQuery("select e from Equipo e where e.nombre = :nombre and e.temporada = :temporada")
				.setParameter("nombre", nomEquipoVisitante).setParameter("temporada", temporada).getSingleResult();
		Date fecha = dateFromString(findElement(byFecha).getText());
		if (!estaCompletoPartido(em, equipoLocal, equipoVisitante, fecha)) {

			p.setEquipo1(equipoLocal);
			p.setEquipo2(equipoVisitante);
			p.setFecha(fecha);
			p.setCompeticion(findElement(byCompeticion).getText());
			p.setTemporada(temporada);
			p.setUrl(driver.getCurrentUrl());

			p.setPtoLocal(Integer.parseInt(findElement(byptosLocal).getText()));
			p.setPtoVisit(Integer.parseInt(findElement(byptosVisitante).getText()));

			p.setPunt1qLocal(Integer.parseInt(findElement(by1qLocal).getText()));
			p.setPunt2qLocal(Integer.parseInt(findElement(by2qLocal).getText()));
			p.setPunt3qLocal(Integer.parseInt(findElement(by3qLocal).getText()));
			p.setPunt4qLocal(Integer.parseInt(findElement(by4qLocal).getText()));
			p.setPunt1qVisit(Integer.parseInt(findElement(by1qVisit).getText()));
			p.setPunt2qVisit(Integer.parseInt(findElement(by2qVisit).getText()));
			p.setPunt3qVisit(Integer.parseInt(findElement(by3qVisit).getText()));
			p.setPunt4qVisit(Integer.parseInt(findElement(by4qVisit).getText()));

			p.setProrroga(findElements(By.cssSelector(".tablaResultadosCuartos tr:nth-child(2) td")).size() - 1);
			try {
				p.setT2aLocal(Integer.parseInt(getAciertos(findElement(byt2Local).getText())));
				p.setT2iLocal(Integer.parseInt(getIntentos(findElement(byt2Local).getText())));
				p.setT3aLocal(Integer.parseInt(getAciertos(findElement(byt3Local).getText())));
				p.setT3iLocal(Integer.parseInt(getIntentos(findElement(byt3Local).getText())));
				p.setTlaLocal(Integer.parseInt(getAciertos(findElement(bytlLocal).getText())));
				p.setTliLocal(Integer.parseInt(getIntentos(findElement(bytlLocal).getText())));
				p.setAsLocal(Integer.parseInt(findElement(byasLocal).getText()));
				p.setBrLocal(Integer.parseInt(findElement(bybrLocal).getText()));
				p.setBpLocal(Integer.parseInt(findElement(bybpLocal).getText()));
				p.setFcLocal(Integer.parseInt(findElement(byfcLocal).getText()));
				p.setFrLocal(Integer.parseInt(findElement(byfrLocal).getText()));
				p.setValLocal(Integer.parseInt(findElement(byvalLocal).getText()));
				p.setRbdLocal(Integer.parseInt(findElement(byrbdLocal).getText()));
				p.setRboLocal(Integer.parseInt(findElement(byrboLocal).getText()));

				p.setT2aVisit(Integer.parseInt(getAciertos(findElement(byt2Visitante).getText())));
				p.setT2iVisit(Integer.parseInt(getIntentos(findElement(byt2Visitante).getText())));
				p.setT3aVisit(Integer.parseInt(getAciertos(findElement(byt3Visitante).getText())));
				p.setT3iVisit(Integer.parseInt(getIntentos(findElement(byt3Visitante).getText())));
				p.setTlaVisit(Integer.parseInt(getAciertos(findElement(bytlVisitante).getText())));
				p.setTliVisit(Integer.parseInt(getIntentos(findElement(bytlVisitante).getText())));
				p.setAsVisit(Integer.parseInt(findElement(byasVisitante).getText()));
				p.setBrVisit(Integer.parseInt(findElement(bybrVisitante).getText()));
				p.setBpVisit(Integer.parseInt(findElement(bybpVisitante).getText()));
				p.setFcVisit(Integer.parseInt(findElement(byfcVisitante).getText()));
				p.setFrVisit(Integer.parseInt(findElement(byfrVisitante).getText()));
				p.setValVisit(Integer.parseInt(findElement(byvalVisitante).getText()));

				p.setRbdVisit(Integer.parseInt(findElement(byrbdVisitante).getText()));
				p.setRboVisit(Integer.parseInt(findElement(byrboVisitante).getText()));

				persistirLineas(em, equipoLocal, equipoVisitante, p);

				em.getTransaction().commit();
				em.getTransaction().begin();
			} catch (Exception e) {

			}
		}
	}

	private void persistirLineas(EntityManager em, Equipo equipoLocal, Equipo equipoVisitante, Partido partido) {
		java.util.List<WebElement> lineasLocal = findElements(byLineasLocal);
		for (int i = 0; i < lineasLocal.size(); i++) {
			persistirLinea(em, equipoLocal, partido, lineasLocal.get(i));
		}
		java.util.List<WebElement> lineasVisit = findElements(byLineasVisit);
		for (int i = 0; i < lineasVisit.size(); i++) {
			persistirLinea(em, equipoVisitante, partido, lineasVisit.get(i));
		}
	}

	private void persistirLinea(EntityManager em, Equipo equipo, Partido partido, WebElement linea) {
		String urlJug = linea.findElement(byNombreJugador).getAttribute("href");
		Lineapartido lineaPartido = new Lineapartido();
		Jugador jug;
		if (existeJugador(em, urlJug)) {
			jug = (Jugador) em.createQuery("select j from Jugador j where j.url = :url").setParameter("url", urlJug)
					.getResultList().get(0);
		} else {
			jug = new Jugador();
			jug.setUrl(urlJug);
			jug.setNombre(linea.findElement(byNombreJugador).getText());
			em.persist(jug);
		}
		lineaPartido.setEquipo(equipo);
		lineaPartido.setJugador(jug);
		lineaPartido.setPartido(partido);
		if (!existeLineaPartido(em, lineaPartido)) {
			lineaPartido.setMinutos(timeFromString(linea.findElement(byMinutosJugador).getText()));
			lineaPartido.setPuntos(Integer.parseInt(linea.findElement(byPuntosJugador).getText()));
			lineaPartido.setT2a(Integer.parseInt(getAciertos(linea.findElement(byt2Jugador).getText())));
			lineaPartido.setT2i(Integer.parseInt(getAciertos(linea.findElement(byt2Jugador).getText())));
			lineaPartido.setT3a(Integer.parseInt(getAciertos(linea.findElement(byt3Jugador).getText())));
			lineaPartido.setT3i(Integer.parseInt(getAciertos(linea.findElement(byt3Jugador).getText())));
			lineaPartido.setTla(Integer.parseInt(getAciertos(linea.findElement(bytlJugador).getText())));
			lineaPartido.setTli(Integer.parseInt(getAciertos(linea.findElement(bytlJugador).getText())));
			lineaPartido.setRbd(Integer.parseInt(linea.findElement(byrbdJugador).getText()));
			lineaPartido.setRbo(Integer.parseInt(linea.findElement(byrboJugador).getText()));
			lineaPartido.setAsist(Integer.parseInt(linea.findElement(byasJugador).getText()));
			lineaPartido.setBr(Integer.parseInt(linea.findElement(bybrJugador).getText()));
			lineaPartido.setBp(Integer.parseInt(linea.findElement(bybrJugador).getText()));
			lineaPartido.setFc(Integer.parseInt(linea.findElement(byfcJugador).getText()));
			lineaPartido.setFr(Integer.parseInt(linea.findElement(byfrJugador).getText()));
			lineaPartido.setVal(Integer.parseInt(linea.findElement(byvalJugador).getText()));
			lineaPartido.setMasMenos(Integer.parseInt(linea.findElement(bymasmenosJugador).getText()));

			em.persist(lineaPartido);
		}
	}

	private boolean existeLineaPartido(EntityManager em, Lineapartido lineaPartido) {
		return (long) em
				.createQuery("select count(l) from Lineapartido l where l.partido = :partido and l.jugador = :jugador")
				.setParameter("partido", lineaPartido.getPartido()).setParameter("jugador", lineaPartido.getJugador())
				.getSingleResult() > 0;
	}

	private boolean existeJugador(EntityManager em, String urlJug) {
		return (long) (em.createQuery("select count(j) from Jugador j where j.url = :url").setParameter("url", urlJug)
				.getSingleResult()) > 0;
	}

	private boolean estaCompletoPartido(EntityManager em, Equipo equipoLocal, Equipo equipoVisitante, Date fecha) {
		return (long) (em.createQuery(
				"select count(p) from Partido p where equipo1 = :equipoLocal and equipo2 = :equipoVisitante and fecha = :fecha")
				.setParameter("equipoLocal", equipoLocal).setParameter("equipoVisitante", equipoVisitante)
				.setParameter("fecha", fecha).getSingleResult()) > 0;
	}

	private String getAciertos(String txt) {
		return txt.split(" ")[0].split("/")[0];
	}

	private String getIntentos(String txt) {
		return txt.split(" ")[0].split("/")[1];
	}

	private Date dateFromString(String txt) {
		String[] div = txt.split("/");
		return Date.valueOf(LocalDate.of(Integer.parseInt(div[2]), Integer.parseInt(div[1]), Integer.parseInt(div[0])));
	}

	private Time timeFromString(String txt) {
		String[] div = txt.split(":");
		return Time.valueOf(LocalTime.of(0, Integer.parseInt(div[0]), Integer.parseInt(div[1])));
	}
}
