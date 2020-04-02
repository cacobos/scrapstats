package com.iesvjp.dom;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.iesvjp.modelos.Equipo;

public class PageResultadosEquipo extends Base{

	Equipo equipo;
	String urlEquipo;
	
	By byEnlaceResultados=By.cssSelector("a#rachasLinkButton");
	By byEnlacesPartidos=By.cssSelector("#rachasDataGrid td:nth-child(3) a");
	public PageResultadosEquipo(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo=equipo;
		this.urlEquipo=equipo.getUrl();			
	}

	public void leerPartidosEquipo(EntityManager em) {
		visit(urlEquipo);
		click(byEnlaceResultados);
		List<WebElement> enlacesPartidos=driver.findElements(byEnlacesPartidos);
		for (int i = 0; i < enlacesPartidos.size(); i++) {
			abrirEnNewTab(enlacesPartidos.get(i));
		}		
		iterarTabsPartidos(em);
	}

	private void iterarTabsPartidos(EntityManager em) {
		Set<String> tabs=driver.getWindowHandles();
			By byTemporada=By.cssSelector("#paginaTitulo_temporadaLabel");
			By byCategoria=By.cssSelector("#paginaTitulo_ligaLabel");
			By byNombreEquipo=By.cssSelector("#paginaTitulo_tituloLabel");
			Iterator<String> it = tabs.iterator();
			String tab;
			while (it.hasNext()) {
				tab = it.next();
				driver.switchTo().window(tab);
				if (isPagePartido()) {
					PagePartido p=new PagePartido(driver);
					p.guardarDatosPartido(em);
				}
				driver.close();
			}
		
	}

	private boolean isPagePartido() {
		try {
			driver.findElement(By.cssSelector("#jugadoresLocalPanel"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
