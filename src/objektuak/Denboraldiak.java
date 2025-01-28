package objektuak;

import java.time.LocalDate;
import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.*;

public class Denboraldiak implements Serializable {
	private int denboraldi_kod;
	private String denboraldi_Izena;
	private LocalDate hasiera_data;
	private LocalDate amaiera_data;
	private int jardunaldi_kopurua;
	
	private List<Jornadas> listaJardunaldiak = TaldeenErabilpena.jardunaldiakIrakurri();
	
	public Denboraldiak() {
		this.denboraldi_kod = automatikoa();
		this.denboraldi_Izena = "";
		this.hasiera_data = null;
		this.amaiera_data = null;
		this.jardunaldi_kopurua = 0;
	}
	
	public Denboraldiak(String denboraldi_Izena, LocalDate hasiera_data, LocalDate amaiera_data) {
		this.denboraldi_kod = automatikoa();
		this.denboraldi_Izena = denboraldi_Izena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.jardunaldi_kopurua = 0;
	}
	
	public Denboraldiak (int denboraldi_kod, String denboraldi_Izena, LocalDate hasiera_data, LocalDate amaiera_data, int jardunaldi_kopurua) {
		this.denboraldi_kod = denboraldi_kod;
		this.denboraldi_Izena = denboraldi_Izena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.jardunaldi_kopurua = jardunaldi_kopurua;
	}
	
	public Denboraldiak (Denboraldiak bestea) {
		this.denboraldi_kod = bestea.denboraldi_kod;
		this.denboraldi_Izena = bestea.denboraldi_Izena;
		this.hasiera_data = bestea.hasiera_data;
		this.amaiera_data = bestea.amaiera_data;
		this.jardunaldi_kopurua = bestea.jardunaldi_kopurua;
	}
	
	public int automatikoa() {
		List<Denboraldiak> denboraldiak = new ArrayList<Denboraldiak>();
		for (Jornadas jardunaldia : listaJardunaldiak) {
			denboraldiak.add(jardunaldia.getDenboraldiak());
		}
		boolean errepikatua;
		boolean amaiera = false;
		int hasiera = 0;
		
		while (amaiera == false) {
			errepikatua = false;
			hasiera++;
			for (int i = 0; i < denboraldiak.size(); i++) {
				if (hasiera == denboraldiak.get(i).getDenboraldi_kod()) {
					errepikatua = true;
				}
			}
			if (errepikatua == false) {
				return hasiera;
			}
		}
		return 0;
	}
	
	public String toString() {
		return "Denboraldia {denboraldi kodea=" + denboraldi_kod + ", denboraldi izena=" + denboraldi_Izena + ", hasiera_data=" + hasiera_data + ", amaiera_data" + amaiera_data + ", jardunaldi kopurua=" + jardunaldi_kopurua + " }";
	}
	
	public int hashCode() {
		int emaitza = 37;
		emaitza += emaitza * 13 * Objects.hash(denboraldi_kod);
		return emaitza;
	}
	
	public boolean equals(Object bestea) {
		if (this == bestea) {
			return true;
		}
		else if (getClass() != bestea.getClass()) {
			return false;
		}
		else {
			Denboraldiak besteDenboraldia = (Denboraldiak) bestea;
			return denboraldi_kod == besteDenboraldia.denboraldi_kod;
		}
	}
	
	public int compareTo(Denboraldiak d) {
		return Integer.compare(this.denboraldi_kod, d.denboraldi_kod);
	}
	
	public int getDenboraldi_kod() {
		return denboraldi_kod;
	}

	public void setDenboraldi_kod(int denboraldi_kod) {
		this.denboraldi_kod = denboraldi_kod;
	}

	public String getDenboraldi_Izena() {
		return denboraldi_Izena;
	}

	public void setDenboraldi_Izena(String denboraldi_Izena) {
		this.denboraldi_Izena = denboraldi_Izena;
	}

	public LocalDate getHasiera_data() {
		return hasiera_data;
	}

	public void setHasiera_data(LocalDate hasiera_data) {
		this.hasiera_data = hasiera_data;
	}

	public LocalDate getAmaiera_data() {
		return amaiera_data;
	}

	public void setAmaiera_data(LocalDate amaiera_data) {
		this.amaiera_data = amaiera_data;
	}

	public int getJardunaldi_kopurua() {
		return jardunaldi_kopurua;
	}

	public void setJardunaldi_kopurua(int jardunaldi_kopurua) {
		this.jardunaldi_kopurua = jardunaldi_kopurua;
	}
}
