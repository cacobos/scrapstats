package com.iesvjp.dom;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.iesvjp.modelos.Equipo;
import com.iesvjp.modelos.Lineapartido;
import com.iesvjp.modelos.Partido;

public class PageResultadosEquipo extends Base{

	Equipo equipo;
	String urlEquipo;
	List<Partido> partidos;
	
	By byEnlaceResultados=By.cssSelector("a#rachasLinkButton");
	By byEnlacesPartidos=By.cssSelector("#rachasDataGrid td:nth-child(3) a");
	By byDesplegablePartidos=By.cssSelector("#gruposDropDownList");
	By byFilasTablaResultado=By.cssSelector("#rachasDataGrid .itemTabla, #rachasDataGrid .itemAlternativoTabla");
	public PageResultadosEquipo(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo=equipo;
		this.urlEquipo=equipo.getUrl();			
	}

	public void leerPartidosEquipo(EntityManager em) {
		partidos=new ArrayList<Partido>();
		visit(urlEquipo);
		click(byEnlaceResultados);
		String oldTab=driver.getWindowHandle();
		
		//Recorremos todas las opciones del desplegable de fases
		WebElement desplegableFases=findElement(byDesplegablePartidos);
		Select selectFases=new Select(desplegableFases);
		List<WebElement> optionsFases=selectFases.getOptions();
		for (int i = 0; i < optionsFases.size(); i++) {
			driver.switchTo().window(oldTab);
			desplegableFases=findElement(byDesplegablePartidos);
			selectFases=new Select(desplegableFases);
			selectFases.selectByIndex(i);
			leerPartidosFase(em);
		}	
		cerar();
	}

	private void leerPartidosFase(EntityManager em) {
		String temporada=findElement(By.cssSelector("#paginaTitulo_temporadaLabel")).getText();
		String competicion=findElement(By.cssSelector("#paginaTitulo_ligaLabel")).getText();
		String fase=new Select(findElement(byDesplegablePartidos)).getFirstSelectedOption().getText();
		String fecha="";
		String url="";
		String jornada="";
		List<WebElement> filasPartidos=findElements(byFilasTablaResultado);
		for (int i = 0; i < filasPartidos.size(); i++) {
			url=filasPartidos.get(i).findElement(By.cssSelector("td:nth-child(3) a")).getAttribute("href");
			fecha=filasPartidos.get(i).findElement(By.cssSelector("td:nth-child(4) span")).getText();
			jornada=filasPartidos.get(i).findElement(By.cssSelector("td:nth-child(1) span")).getText();
			if(!existePartido(em, url)){
				Partido p=new Partido();
				p.setTemporada(temporada);
				p.setFase(fase);
				p.setFecha(dateFromString(fecha));
				p.setCompeticion(competicion);
				p.setJornada(jornada);
				p.setUrl(url);
				em.persist(p);
			}
		}
	}
	
	private Date dateFromString(String txt) {
		String[] div=txt.split("/");
		return Date.valueOf(LocalDate.of(Integer.parseInt(div[2]), Integer.parseInt(div[1]),Integer.parseInt(div[0])));
	}

	private boolean existePartido(EntityManager em, String url) {		
		return (long)em.createQuery("select count(p) from Partido p where p.url = :url").setParameter("url", url).getSingleResult()>0;
	}	

	
	
}
