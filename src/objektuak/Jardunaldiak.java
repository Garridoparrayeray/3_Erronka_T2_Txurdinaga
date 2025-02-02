package objektuak;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import main.Jornadas;

import main.TaldeenErabilpena;

/**
 * Jardunaldiak klaseak jardunaldi baten informazioa kudeatzen du.
 * Jardunaldi bakoitzak kode bat, deskribapena, hasiera eta amaiera datak, partidu kopurua eta denboraldi bat izan dezake.
 * Klase honek jardunaldiak identifikatzen eta kudeatzen laguntzen du.
 */
public class Jardunaldiak  implements Serializable{
	private int jardunaldi_kod;
	private String jardunaldi_deskribapena;
	private String hasiera_data;
	private String amaiera_data;
	private int partidu_kopurua;
	private Denboraldiak denboraldia;
	
	/**
     * Jardunaldiak objektuaren eraikitzailea, balio lehenetsiekin.
     * Hasierako balioak ezartzen ditu.
     */
	public Jardunaldiak() {
		this.jardunaldi_kod = automatikoa();
		this.jardunaldi_deskribapena = "";
		this.hasiera_data = null;
		this.amaiera_data = null;
		this.partidu_kopurua = 0;
		this.denboraldia = new Denboraldiak();
	}
	
	/**
     * Jardunaldiak objektuaren eraikitzailea, denboraldia parametro gisa jasoz.
     * 
     * @param denboraldia Jardunaldiaren lotura duen denboraldia.
     */
	public Jardunaldiak(Denboraldiak denboraldia) {
		this.jardunaldi_kod = automatikoa();
		this.jardunaldi_deskribapena = "";
		this.hasiera_data = null;
		this.amaiera_data = null;
		this.partidu_kopurua = 0;
		this.denboraldia = denboraldia;
	}
	
	 /**
     * Jardunaldiak objektuaren eraikitzailea, deskribapena, hasiera eta amaiera data eta denboraldia parametro gisa jasoz.
     * 
     * @param jardunaldi_deskribapena Jardunaldiaren deskribapena.
     * @param hasiera_data Jardunaldiaren hasiera data.
     * @param amaiera_data Jardunaldiaren amaiera data.
     * @param denboraldia Jardunaldiaren lotura duen denboraldia.
     */
	public Jardunaldiak(String jardunaldi_deskribapena, String hasiera_data, String amaiera_data, Denboraldiak denboraldia) {
		this.jardunaldi_kod = automatikoa();
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = 0;
		this.denboraldia = denboraldia;
	}
	
	/**
     * Jardunaldiak objektuaren eraikitzailea, denboraldiaren informazioarekin.
     * 
     * @param jardunaldi_kod Jardunaldiaren kodea.
     * @param jardunaldi_deskribapena Jardunaldiaren deskribapena.
     * @param hasiera_data Jardunaldiaren hasiera data.
     * @param amaiera_data Jardunaldiaren amaiera data.
     * @param partidu_kopurua Jardunaldian egingo diren partidu kopurua.
     * @param denboraldia Jardunaldiaren lotura duen denboraldia.
     */
	public Jardunaldiak(int jardunaldi_kod, String jardunaldi_deskribapena, String hasiera_data, String amaiera_data, Denboraldiak denboraldia) {
		this.jardunaldi_kod = jardunaldi_kod;
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = 0;
		this.denboraldia = denboraldia;
	}
	
	/**
     * Jardunaldiak objektuaren eraikitzailea, beste jardunaldi baten informazioarekin.
     * 
     * @param bestea Beste jardunaldi batetik kopiatutako datuak.
     */
	public Jardunaldiak(int jardunaldi_kod, String jardunaldi_deskribapena, String hasiera_data, String amaiera_data, int partidu_kopurua, Denboraldiak denboraldia) {
		this.jardunaldi_kod = jardunaldi_kod;
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = partidu_kopurua;
		this.denboraldia = denboraldia;
	}
	
	/**
     * Jardunaldiaren kode automatiko bat sortzen du, beste kode batzuekin errepikapenik egon ez dadin.
     * 
     * @return Jardunaldiaren kode automatikoa.
     */
	public Jardunaldiak (Jardunaldiak bestea) {
		this.jardunaldi_kod = bestea.jardunaldi_kod;
		this.jardunaldi_deskribapena = bestea.jardunaldi_deskribapena;
		this.hasiera_data = bestea.hasiera_data;
		this.amaiera_data = bestea.amaiera_data;
		this.partidu_kopurua = bestea.partidu_kopurua;
		this.denboraldia = new Denboraldiak();
	}
	
	/**
     * Jardunaldiaren kode automatiko bat sortzen du, beste kode batzuekin errepikapenik egon ez dadin.
     * 
     * @return Jardunaldiaren kode automatikoa.
     */
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
	
	/**
     * Jardunaldiaren deskribapena itzultzen du.
     * 
     * @return Jardunaldiaren informazioa.
     */
	public String toString() {
		return "Jardunaldia {jardunaldi kodea=" + jardunaldi_kod + ", jardunaldi deskribapena=" + jardunaldi_deskribapena + ", hasiera_data" + hasiera_data + ", amaiera data=" + amaiera_data + ", partidu kopurua" + partidu_kopurua + " }";
	}
	
	/**
     * Objetoaren hash kodea kalkulatzen du.
     * 
     * @return Objetoaren hash kodea.
     */
	public int hashCode () {
		int emaitza = 37;
		emaitza = emaitza * 13 * Objects.hash(jardunaldi_kod);
		return emaitza;
	}
	
	/**
     * Beste objektu batekin konparatzen du jardunaldiak berdinak diren edo ez.
     * 
     * @param bestea Konparatu nahi den beste objektua.
     * @return True baldin eta objektuak berdinak badira, bestela False.
     */
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

	/**
     * Jardunaldiak konparatzen ditu, lehenengoa bigarrenarekin alderatuz.
     * 
     * @param j Konparatu nahi den beste jardunaldi.
     * @return Negatibo bat, positibo bat edo 0, konparazioaren arabera.
     */
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