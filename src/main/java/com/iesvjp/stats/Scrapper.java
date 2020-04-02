package com.iesvjp.stats;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.iesvjp.dom.PagePrincipal;

public class Scrapper {

	WebDriver driver;
	PagePrincipal pagePrincipal;
	
	By byDesplegableJornada;
	By byEnlaceResultadosOro;
	public Scrapper() {
		inicializar();
	}

	private void inicializar() {
		pagePrincipal=new PagePrincipal(driver);
		driver=pagePrincipal.chromeDriverConnection();
		
		DesiredCapabilities caps=new DesiredCapabilities();
    	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    	driver=new ChromeDriver();
		
		byDesplegableJornada=By.cssSelector("select[name='jornadasDropDownList']");
		byEnlaceResultadosOro=By.cssSelector(".oro li:nth-child(3) a");
		
    	
    	
    	
    	//driver.close();
	}
	
	public void accederAResultadosLebOro() {
		driver.navigate().to("http://competiciones.feb.es/estadisticas/");
    	WebElement enlace= driver.findElement(byEnlaceResultadosOro);
    	enlace.click();
    	WebElement desplegable=driver.findElement(byDesplegableJornada);
    	Select desp= new Select(desplegable);
    	//desp.selectByIndex(1);
    	desplegable=driver.findElement(By.cssSelector("select[name='jornadasDropDownList']"));
    	desp= new Select(desplegable);
    	//desp.selectByIndex(23);
	}

}
