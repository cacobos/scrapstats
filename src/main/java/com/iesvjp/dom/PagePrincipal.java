package com.iesvjp.dom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PagePrincipal extends Base{

	
	By byEnlaceResultadosOro=By.cssSelector(".oro li:nth-child(3) a");
	By byEnlaceEstadisticasOro=By.cssSelector(".oro li:nth-child(2) a");
	By byEnlaceResultadosPlata=By.cssSelector(".plata li:nth-child(3) a");
	By byEnlaceEstadisticasPlata=By.cssSelector(".plata li:nth-child(2) a");
	By byEnlaceResultadosLF=By.cssSelector(".lf li:nth-child(3) a");
	By byEnlaceEstadisticasLF=By.cssSelector(".lf li:nth-child(2) a");
	
	public PagePrincipal(WebDriver driver) {
		super(driver);
		visit("http://competiciones.feb.es/estadisticas/");
		// TODO Auto-generated constructor stub
	}

	public PageResultadosCategoria visitResultadosOro() {
		driver.findElement(byEnlaceResultadosOro).click();		
		return new PageResultadosCategoria(driver);
	}
	
	public PageEstadisticasCategoria visitEstadisticasOro() {
		driver.findElement(byEnlaceEstadisticasOro).click();
		return new PageEstadisticasCategoria(driver);
	}
	
	public PageResultadosCategoria visitResultadosPlata() {
		driver.findElement(byEnlaceResultadosPlata).click();		
		return new PageResultadosCategoria(driver);
	}
	
	public PageEstadisticasCategoria visitEstadisticasPlata() {
		driver.findElement(byEnlaceEstadisticasPlata).click();
		return new PageEstadisticasCategoria(driver);
	}
	public PageResultadosCategoria visitResultadosLF() {
		driver.findElement(byEnlaceResultadosLF).click();		
		return new PageResultadosCategoria(driver);
	}
	
	public PageEstadisticasCategoria visitEstadisticasLF() {
		driver.findElement(byEnlaceEstadisticasLF).click();
		return new PageEstadisticasCategoria(driver);
	}
}
