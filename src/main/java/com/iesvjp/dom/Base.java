package com.iesvjp.dom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	WebDriver driver;

	public Base(WebDriver driver) {
		this.driver = driver;
		if (driver == null) {
			driver = chromeDriverConnection();
		}
	}

	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public void type(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void abrirEnNewTab(WebElement enlace) {		
			String keyString =   Keys.CONTROL+Keys.SHIFT.toString()+Keys.ENTER.toString();
			enlace.sendKeys(keyString);
		
	}

	public void visit(String url) {
		driver.get(url);
	}
	
	public void cerar() {
		driver.close();
	}
}
