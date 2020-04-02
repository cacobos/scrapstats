package com.iesvjp.dom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.iesvjp.modelos.Equipo;
import com.iesvjp.modelos.Partido;
import com.iesvjp.stats.Utilidades;

public class PageEstadisticasOro extends Base {

	private By byNombresEquipos = By.cssSelector("#estadisticasMediasDataGrid a");

	private By byEnlaceResultados = By.cssSelector("#rachasLinkButton");
	private By byTablaResultados = By.cssSelector("#rachasDataGrid");

	private Set<String> tabs;
	
	private List<Equipo> equipos;

	public PageEstadisticasOro(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void mostrarEquipos() {
		List<WebElement> equipos = driver.findElements(byNombresEquipos);
		for (int i = 0; i < equipos.size(); i++) {
			System.out.println(equipos.get(i).getText());
		}
	}

	public void getResultadosLebOro() {
		List<WebElement> equipos = driver.findElements(byNombresEquipos);
		for (int i = 0; i < equipos.size(); i++) {
			Utilidades.abrirEnNewTab(driver, equipos.get(i));
			if (isPageEquipo()) {
				System.out.println(driver.findElement(byEnlaceResultados).getText());
				driver.findElement(byEnlaceResultados).click();
			}
		}
		tabs = driver.getWindowHandles();
		iterarTabsGetResultados();
	}

	private void iterarTabsGetResultados() {
		Iterator<String> it = tabs.iterator();
		String tab;
		while (it.hasNext()) {
			tab = it.next();
			driver.switchTo().window(tab);
			if (isPageEquipo()) {
				System.out.println(driver.findElement(byEnlaceResultados).getText());
				driver.findElement(byEnlaceResultados).click();
			} else if (isPageResultados()) {
				mostrarResultados();
				driver.close();
			}
			
		}
		iterarTabsGetResultados();
	}

	private boolean isPageEquipo() {
		try {
			driver.findElement(byEnlaceResultados);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Equipo> getEquipos() {
		equipos=new ArrayList<Equipo>();
		List<WebElement> webElementsEquipos = driver.findElements(byNombresEquipos);
		for (int i = 0; i < webElementsEquipos.size(); i++) {
			Utilidades.abrirEnNewTab(driver, webElementsEquipos.get(i));
			if (isPageEquipo()) {
				System.out.println(driver.findElement(byEnlaceResultados).getText());
				driver.findElement(byEnlaceResultados).click();
			}
		}
		tabs = driver.getWindowHandles();
		iterarTabsGetEquipos();
		return equipos;
	}

	private void iterarTabsGetEquipos() {
		By byTemporada=By.cssSelector("#paginaTitulo_temporadaLabel");
		By byCategoria=By.cssSelector("#paginaTitulo_ligaLabel");
		By byNombreEquipo=By.cssSelector("#paginaTitulo_tituloLabel");
		Iterator<String> it = tabs.iterator();
		String tab;
		while (it.hasNext()) {
			tab = it.next();
			driver.switchTo().window(tab);
			if (isPageEquipo()) {
				Equipo e=new Equipo();
				e.setCategoria(driver.findElement(byCategoria).getText());
				e.setTemporada(driver.findElement(byTemporada).getText());
				e.setNombre(driver.findElement(byNombreEquipo).getText());
				e.setUrl(driver.getCurrentUrl());				
				equipos.add(e);
				driver.close();
			}
			
		}
	}

	private boolean isPageResultados() {
		try {
			driver.findElement(byTablaResultados);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void mostrarResultados() {
		List<WebElement> filas = driver.findElement(byTablaResultados)
				.findElements(By.cssSelector(".itemTabla, .itemAlternativoTabla"));
		for (int i = 0; i < filas.size(); i++) {
			System.out.println(filas.get(i).findElement(By.cssSelector("td:nth-child(3) a")).getText());
		}
	}
}
