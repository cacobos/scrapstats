package com.iesvjp.dom;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Clase que gestiona la automatización de una página web y de la que heredarán
 * las clases que gestionarán cada página de nuestro proyecto
 * 
 * @author Carlos Cobos
 *
 */
public class Base {
	protected WebDriver driver;

	/**
	 * Constructor parametrizado
	 * @param driver
	 */
	public Base(WebDriver driver) {
		this.driver = driver;
		if (driver == null) {
			driver = chromeDriverConnection();
		}
	}

	/**
	 * Método que inicializa del WebDriver
	 * 
	 * @return el WebDriver inicializado
	 */
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("enable-automation");
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.addArguments("enable-features=NetworkServiceInProcess");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(3L, TimeUnit.SECONDS);
		
	
		return driver;
	}

	/**
	 * Método que busca un WebElement en una página web
	 * 
	 * @param locator el localizados del elemento que estamos buscando
	 * @return el WebElement encontrado
	 */
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	/**
	 * Método que busca una lista de WebElement en una página web
	 * 
	 * @param locator el localizados del elemento que estamos buscando
	 * @return la lista con los WebElement encontrados
	 */
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * Método que devuelve el texto de un WebElement
	 * 
	 * @param element el WebElement del que queremos obtener el texto
	 * @return el texto
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	/**
	 * Método que devuelve el texto de un WebElement a partir de un localizados
	 * 
	 * @param locator el localizador del WebElement del que queremos obtener el
	 *                texto
	 * @return el texto
	 */
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	/**
	 * Método que escribe texto en un WebElement
	 * 
	 * @param inputText el texto a escribir
	 * @param locator   el localizador del WebElement
	 */
	public void type(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}

	/**
	 * Método que hace click en un WebElement
	 * 
	 * @param locator el localizador del WebElement
	 */
	public void click(By locator) {
		driver.findElement(locator).click();
	}

	/**
	 * Método que indica si un WebElement se muestra en pantalla
	 * 
	 * @param locator el localizador del WebElement
	 * @return si se muestra
	 */
	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Método que abre un enlace el una nueva pestaña
	 * 
	 * @param enlace el WebElement con el enlace
	 */
	public void abrirEnNewTab(WebElement enlace) {
		String keyString = Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString();
		enlace.sendKeys(keyString);
	}

	/**
	 * Método que abre una url en una nueva pestaña
	 * 
	 * @param url la url que queremos abrir
	 */
	public void abrirUrlNewTab(String url) {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		Iterator<String> it = driver.getWindowHandles().iterator();
		String newTabInstance = "";
		while (it.hasNext()) {
			newTabInstance = it.next();
		}
		driver.switchTo().window(newTabInstance);
		driver.navigate().to(url);
	}

	/**
	 * Método que visita una url
	 * 
	 * @param url la url que queremos abrir
	 */
	public void visit(String url) {
		driver.get(url);
	}

	/**
	 * Cierra la pestaña actual
	 */
	public void cerar() {
		driver.close();
	}
}
