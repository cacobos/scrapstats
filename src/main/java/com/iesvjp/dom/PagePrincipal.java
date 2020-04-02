package com.iesvjp.dom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PagePrincipal extends Base{

	
	By byEnlaceResultadosOro=By.cssSelector(".oro li:nth-child(3) a");
	By byEnlaceEstadisticasOro=By.cssSelector(".oro li:nth-child(2) a");
	
	public PagePrincipal(WebDriver driver) {
		super(driver);
		visit("http://competiciones.feb.es/estadisticas/");
		// TODO Auto-generated constructor stub
	}

	public PageResultadosOro visitResultadosOro() {
		driver.findElement(byEnlaceResultadosOro).click();		
		return new PageResultadosOro(driver);
	}
	
	public PageEstadisticasOro visitEstadisticasOro() {
		driver.findElement(byEnlaceEstadisticasOro).click();
		return new PageEstadisticasOro(driver);
	}
	
	
}
