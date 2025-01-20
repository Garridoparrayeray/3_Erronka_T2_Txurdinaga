package objektuak;

import java.time.LocalDate;
import java.util.Objects;

public class Jardunaldiak{
	private int jardunaldi_kod;
	private String jardunaldi_deskribapena;
	private LocalDate hasiera_data;
	private LocalDate amaiera_data;
	private int partidu_kopurua;
	
	private int counter = 0;
	
	public Jardunaldiak() {
		this.jardunaldi_kod = counter++;
		this.jardunaldi_deskribapena = "";
		this.hasiera_data = null;
		this.amaiera_data = null;
		this.partidu_kopurua = 0;
	}
	
	public Jardunaldiak(int jardunaldi_kod, LocalDate hasiera_data, LocalDate amaiera_data) {
		this.jardunaldi_kod = jardunaldi_kod;
		this.jardunaldi_deskribapena = "";
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = 0;
	}
	
	public Jardunaldiak(int jardunaldi_kod, String jardunaldi_deskribapena, LocalDate hasiera_data, LocalDate amaiera_data, int partidu_kopurua) {
		this.jardunaldi_kod = jardunaldi_kod;
		this.jardunaldi_deskribapena = jardunaldi_deskribapena;
		this.hasiera_data = hasiera_data;
		this.amaiera_data = amaiera_data;
		this.partidu_kopurua = partidu_kopurua;
	}
	
	public Jardunaldiak (Jardunaldiak bestea) {
		this.jardunaldi_kod = bestea.jardunaldi_kod;
		this.jardunaldi_deskribapena = bestea.jardunaldi_deskribapena;
		this.hasiera_data = bestea.hasiera_data;
		this.amaiera_data = bestea.amaiera_data;
		this.partidu_kopurua = bestea.partidu_kopurua;
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

	public int getPartidu_kopurua() {
		return partidu_kopurua;
	}

	public void setPartidu_kopurua(int partidu_kopurua) {
		this.partidu_kopurua = partidu_kopurua;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
