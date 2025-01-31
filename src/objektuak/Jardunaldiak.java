package objektuak;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import main.Jornadas;

import main.TaldeenErabilpena;

public class Jardunaldiak  implements Serializable{
	private int jardunaldi_kod;
	private String jardunaldi_deskribapena;
	private String hasiera_data;
	private String amaiera_data;
	private int partidu_kopurua;
	private Denboraldiak denboraldia;
	
	public Jardunaldiak() {
		this.jardunaldi_kod = automatikoa();
		this.jardunaldi_deskribapena = "";
		this.hasiera_data = null;
		this.amaiera_data = null;
		this.partidu_kopurua = 0;
		this.denboraldia = new Denboraldiak();
	}
	
	public Jardunaldiak(Denboraldiak denboraldia) {
		this.jardunaldi_kod = automatikoa();
		this.jardunaldi_deskribapena = "";
		this.hasiera_data = null;
		this.amaiera_data = null;
		this.partidu_kopurua = 0;
		this.denboraldia = denboraldia;
	}
	
	public Jardunaldiak(String jardunaldi_deskribapena, String hasiera_data, String amaiera_data, Denboraldiak denboraldia) {
		this.jardunaldi_kod = automatikoa();
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = 0;
		this.denboraldia = denboraldia;
	}
	
	public Jardunaldiak(int jardunaldi_kod, String jardunaldi_deskribapena, String hasiera_data, String amaiera_data, Denboraldiak denboraldia) {
		this.jardunaldi_kod = jardunaldi_kod;
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = 0;
		this.denboraldia = denboraldia;
	}
	
	public Jardunaldiak(int jardunaldi_kod, String jardunaldi_deskribapena, String hasiera_data, String amaiera_data, int partidu_kopurua, Denboraldiak denboraldia) {
		this.jardunaldi_kod = jardunaldi_kod;
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = partidu_kopurua;
		this.denboraldia = denboraldia;
	}
	
	public Jardunaldiak (Jardunaldiak bestea) {
		this.jardunaldi_kod = bestea.jardunaldi_kod;
		this.jardunaldi_deskribapena = bestea.jardunaldi_deskribapena;
		this.hasiera_data = bestea.hasiera_data;
		this.amaiera_data = bestea.amaiera_data;
		this.partidu_kopurua = bestea.partidu_kopurua;
		this.denboraldia = new Denboraldiak();
	}
	
	public int automatikoa() {
		List<Jornadas> jornadas = TaldeenErabilpena.jardunaldiakIrakurri();
		List<List<Jardunaldiak>> jardunaldiak = new ArrayList<List<Jardunaldiak>>();
		
		
		for (Jornadas jornada : jornadas) {
			jardunaldiak.add(jornada.getJardunaldiak());
		}
		boolean errepikatua;
		boolean amaiera = false;
		int hasiera = 0;
			for (List<Jardunaldiak> listaJardunaldia : jardunaldiak) {
				errepikatua = false;
				hasiera++;
				for (Jardunaldiak jardunaldia : listaJardunaldia) {
					if (hasiera == jardunaldia.getJardunaldi_kod()) {
						errepikatua = true;
					}
				}
				if (errepikatua == false) {
					return hasiera;
				}
		}
		return 10;
	}
	
	public String toString() {
		return "Jardunaldia {jardunaldi kodea=" + jardunaldi_kod + ", jardunaldi deskribapena=" + jardunaldi_deskribapena + ", hasiera_data" + hasiera_data + ", amaiera data=" + amaiera_data + ", partidu kopurua" + partidu_kopurua + " }";
	}
	
	public int hashCode () {
		int emaitza = 37;
		emaitza = emaitza * 13 * Objects.hash(jardunaldi_kod);
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
		Jardunaldiak besteJardunaldia = (Jardunaldiak) bestea;
		return jardunaldi_kod == besteJardunaldia.jardunaldi_kod;
	}
}

	public int compareTo (Jardunaldiak j) {
		return Integer.compare(this.jardunaldi_kod, j.jardunaldi_kod);
	}

	public int getJardunaldi_kod() {
		return jardunaldi_kod;
	}

	public void setJardunaldi_kod(int jardunaldi_kod) {
		this.jardunaldi_kod = jardunaldi_kod;
	}

	public String getJardunaldi_deskribapena() {
		return jardunaldi_deskribapena;
	}

	public void setJardunaldi_deskribapena(String jardunaldi_deskribapena) {
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
	}

	public String getHasiera_data() {
		return hasiera_data;
	}

	public void setHasiera_data(String hasiera_data) {
		this.hasiera_data = hasiera_data;
	}

	public String getAmaiera_data() {
		return amaiera_data;
	}

	public void setAmaiera_data(String amaiera_data) {
		this.amaiera_data = amaiera_data;
	}

	public int getPartidu_kopurua() {
		return partidu_kopurua;
	}

	public void setPartidu_kopurua(int partidu_kopurua) {
		this.partidu_kopurua = partidu_kopurua;
	}
	
	public void setDenboradia (Denboraldiak denboraldia) {
		this.denboraldia = denboraldia;
	}
	
	public Denboraldiak getDenboraldiak() {
		return denboraldia;
	}
}