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
import org.openqa.selenium.support.ui.Select;

import com.iesvjp.modelos.Equipo;
import com.iesvjp.modelos.Partido;
import com.iesvjp.stats.Utilidades;

public class PageEstadisticasCategoria extends Base {

	private By byNombresEquipos = By.cssSelector("#estadisticasMediasDataGrid a");

	private By byEnlaceResultados = By.cssSelector("#rachasLinkButton");
	private By byTablaResultados = By.cssSelector("#rachasDataGrid");

	private Set<String> tabs;
	private List<Equipo> equipos;

	public PageEstadisticasCategoria(WebDriver driver) {
		super(driver);
		equipos = new ArrayList<Equipo>();
	}

	public void mostrarEquipos() {
		List<WebElement> equipos = driver.findElements(byNombresEquipos);
		for (int i = 0; i < equipos.size(); i++) {
			System.out.println(equipos.get(i).getText());
		}
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
		WebElement dropdown = findElement(By.cssSelector("#fasesGruposDropDownList"));
		Select selectFases = new Select(dropdown);
		List<WebElement> optionsFases = selectFases.getOptions();
		String oldTab = driver.getWindowHandle();
		for (int i = 0; i < optionsFases.size(); i++) {
		    driver.switchTo().window(oldTab);
		    dropdown = findElement(By.cssSelector("#fasesGruposDropDownList"));
			selectFases = new Select(dropdown);
			selectFases.selectByIndex(i);
			List<WebElement> webElementsEquipos = driver.findElements(byNombresEquipos);
			for (int j = 0; j < webElementsEquipos.size(); j++) {
				Utilidades.abrirEnNewTab(driver, webElementsEquipos.get(j));				
			}
		}

		tabs = driver.getWindowHandles();
		iterarTabsGetEquipos();
		
		return equipos;
	}

	private void iterarTabsGetEquipos() {
		By byTemporada = By.cssSelector("#paginaTitulo_temporadaLabel");
		By byCategoria = By.cssSelector("#paginaTitulo_ligaLabel");
		By byNombreEquipo = By.cssSelector("#paginaTitulo_tituloLabel");
		Iterator<String> it = tabs.iterator();
		String tab;
		while (it.hasNext()) {
			tab = it.next();
			driver.switchTo().window(tab);
			if (isPageEquipo()) {
				Equipo e = new Equipo();
				e.setCategoria(driver.findElement(byCategoria).getText());
				e.setTemporada(driver.findElement(byTemporada).getText());
				e.setNombre(driver.findElement(byNombreEquipo).getText());
				e.setUrl(driver.getCurrentUrl());
				equipos.add(e);
			}

			driver.close();
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
