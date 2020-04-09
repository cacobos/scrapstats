package com.iesvjp.dom;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.iesvjp.modelos.Jugador;

public class PageJugador extends Base {

	Jugador jugador;
	String urlJugador;

	By byFechaNacimiento = By.cssSelector("#fechaNacLabel");
	By byfoto = By.cssSelector("#componenteImage");
	By byPuesto = By.cssSelector("#puestoLabel");
	By byAltura = By.cssSelector("#alturaLabel");
	By byNombre=By.cssSelector("#nombreLabel");

	public PageJugador(WebDriver driver) {
		super(driver);
	}

	public void leerJugador(Jugador jugador, EntityManager em) {
		this.jugador = jugador;
		this.urlJugador = jugador.getUrl();
		visit(urlJugador);
		leerDatosJugador();
		driver.close();
	}

	private void leerDatosJugador() {
		asignarFechayLocalidad();
		try{
			jugador.setNombre(findElement(byNombre).getText());
		}catch (Exception e) {
			jugador.setNombre("");
		}
		try {
			jugador.setUrlFoto(findElement(byfoto).getAttribute("src"));
		}catch (Exception e) {
			jugador.setUrlFoto("");
		}
		try {
			jugador.setAltura(Integer.parseInt(findElement(byAltura).getText()));	
		}catch (Exception e) {
			jugador.setAltura(0);
		}
		try {
			jugador.setPuesto(findElement(byPuesto).getText());
		}catch (Exception e) {
			jugador.setPuesto("");
		}
	}

	private void asignarFechayLocalidad() {
		try {
			String[] div = findElement(byFechaNacimiento).getText().split(" ");
			jugador.setFechaNacimiento(dateFromString(div[0]));			
			try {				
				jugador.setCiudadNacimiento(div[1] + " " + div[2]);
			} catch (Exception e) {

			}
		} catch (Exception e) {

		}
	}

	private Date dateFromString(String txt) {
		String[] div = txt.split("/");
		return Date.valueOf(LocalDate.of(Integer.parseInt(div[2]), Integer.parseInt(div[1]), Integer.parseInt(div[0])));
	}
}
