package objektuak;

import java.time.LocalDate;
import java.util.Objects;
import java.io.*;

/**
 * Jokalariak klaseak jokalarien informazioa kudeatzen du, pertsona klasearen ondorengo gisa.
 * Klase honek jokalarien datuak, hala nola, identifikazioa, izena, abizena, jaiotze data eta rolak kudeatzen ditu.
 */
public class Jokalariak extends Pertsona implements Serializable, Comparable<Jokalariak>{
  private int jokalarikod;
  private String nan;
  private String izena;
  private String abizena;
  private LocalDate jaiotzedata ;
  private String jokalariRola;
  private int taldeKod;
  private static int incrementar = 0;
  
  /**
   * Jokalariak klasearen eraikitzailea, balio lehenetsiekin.
   * Jokalariaren hasierako balioak ezartzen ditu.
   */
  public Jokalariak() {
      this.jokalarikod = incrementar++;
      this.nan = "";
      this.izena = "";
      this.abizena = "";
      this.jaiotzedata = null;
      this.jokalariRola = "";
      this.taldeKod = 0;
  }
  
  /**
   * Jokalariaren objetu bat sortzen du, hasierako informazioarekin.
   * 
   * @param jokalarikod Jokalariaren kodea.
   * @param izena Jokalariaren izena.
   * @param abizena Jokalariaren abizena.
   * @param jokalariRola Jokalariaren rola edo posizioa.
   * @param taldeKod Jokalariaren taldearen kodea.
   */
  public Jokalariak(int jokalarikod, String izena, String abizena, String jokalariRola, int taldeKod) {   
  	super(izena, abizena);
  	this.jokalarikod = jokalarikod;
  	this.jokalariRola = jokalariRola;
  	this.taldeKod = taldeKod;
  }
  
  /**
   * Jokalariaren objetu bat sortzen du, hasierako informazio osoarekin.
   * 
   * @param jokalarikod Jokalariaren kodea.
   * @param nan Jokalariaren NAN.
   * @param izena Jokalariaren izena.
   * @param abizena Jokalariaren abizena.
   * @param jaiotzedata Jokalariaren jaiotze data.
   * @param jokalariRola Jokalariaren rola edo posizioa.
   * @param taldeKod Jokalariaren taldearen kodea.
   */
  public Jokalariak(String jokalarikod, String nan, String izena, String abizena, LocalDate jaiotzedata, String jokalariRola, int taldeKod) {
      super(izena, abizena, PertsonaMota.jokalari, nan, jaiotzedata);
      this.jokalarikod = incrementar++;
      this.jokalariRola = jokalariRola;
      this.taldeKod = taldeKod;
  }
  
  /**
   * Jokalariaren objektu baten kopia sortzen du.
   * 
   * @param beste Beste Jokalariaren objektu bat.
   */
  // Constructor de copia
  public Jokalariak(Jokalariak beste) {
      super(beste.getIzena(), beste.getAbizena(), PertsonaMota.jokalari, beste.getNAN(), beste.getJaiotzedata());
      this.jokalarikod = beste.jokalarikod;
      this.jokalariRola = beste.jokalariRola;
  }

  /**
   * Jokalariaren informazioa itzultzen du.
   * 
   * @return Jokalariaren informazioaren deskribapena.
   */
  @Override
  public String toString() {
      return "Jokalariak {" +  "jokalarikod=" + jokalarikod +", izena='" + getIzena() +  ", abizena='" + getAbizena() +  ", posizioa='" + jokalariRola + '}';
  }
  
  /**
   * Objektuaren hash kodea kalkulatzen du.
   * 
   * @return Objetoaren hash kodea.
   */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(jokalarikod, jokalariRola);
		return result;
	}

	/**
	   * Bi objektu konparatzen ditu berdin edo ez.
	   * 
	   * @param obj Konparatu nahi den beste objektua.
	   * @return True baldin eta objektuak berdinak badira, bestela False.
	   */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jokalariak other = (Jokalariak) obj;
		return jokalarikod == other.jokalarikod && Objects.equals(jokalariRola, other.jokalariRola);
	}

	/**
	   * Jokalariak konparatzen ditu, lehenengoa bigarrenarekin alderatuz.
	   * 
	   * @param o Konparatu nahi den beste Jokalaria.
	   * @return Negatibo bat, positibo bat edo 0, konparazioaren arabera.
	   */
	@Override
	public int compareTo(Jokalariak o) {
		return Integer. compare(this.getJokalarikod(), o.jokalarikod);
	}

	public int getJokalarikod() {
		return jokalarikod;
	}

	public void setJokalarikod(int jokalarikod) {
		this.jokalarikod = jokalarikod;
	}

	public String getNan() {
		return nan;
	}

	public void setNan(String nan) {
		this.nan = nan;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public LocalDate getJaiotzedata() {
		return jaiotzedata;
	}

	public void setJaiotzedata(LocalDate jaiotzedata) {
		this.jaiotzedata = jaiotzedata;
	}

	public String getJokalariRola() {
		return jokalariRola;
	}

	public void setJokalariRola(String jokalariRola) {
		this.jokalariRola = jokalariRola;
	}

	public int getTaldeKod() {
		return taldeKod;
	}

	public void setTaldeKod(int taldeKod) {
		this.taldeKod = taldeKod;
	}

	public static int getIncrementar() {
		return incrementar;
	}

	public static void setIncrementar(int incrementar) {
		Jokalariak.incrementar = incrementar;
	}


} 














/*public class Jokalariak {
	private String izena;
	private String abizena;
	private String nan;
	private int jokalarikod;
	private String posizioa;
	private int incrementar = 0;
	private ArrayList<Jokalariak> jokalariak;
	
public Jokalariak() {
	this.izena = "";
	this.abizena = "";
	this.nan = "";
	this.jokalarikod = incrementar++; 
	this.posizioa = "";
}

public Jokalariak(String izena, String abizena, String nan, int jokalarikod, String posizioa) {
	this.izena = izena;
	this.abizena = abizena;
	this.nan = nan;
	this.jokalarikod = incrementar++;
	this.posizioa = posizioa;
}
//KOPIA
public Jokalariak (Jokalariak beste) {
	this.izena = beste.izena;
	this.abizena = beste.abizena;
	this.nan = beste.nan;
	this.jokalarikod = beste.jokalarikod;
	this.posizioa = beste.posizioa;
}

//GETTERS AND SETTERS
public String getIzena() {
	return izena;
}

public void setIzena(String izena) {
	this.izena = izena;
}

public String getAbizena() {
	return abizena;
}

public void setAbizenak(String abizenak) {
	this.abizena = abizenak;
}

public String getNan() {
	return nan;
}

public void setNan(String nan) {
	this.nan = nan;
}

public int getJokalarikod() {
	return jokalarikod;
}

public void setJokalarikod(int jokalarikod) {
	this.jokalarikod = jokalarikod;
}

public String getPosizioa() {
	return posizioa;
}

public void setPosizioa(String posizioa) {
	this.posizioa = posizioa;
}

public int getIncrementar() {
	return incrementar;
}

public void setIncrementar(int incrementar) {
	this.incrementar = incrementar;
}

@Override
public String toString() {
	return "Jokalariak [izena=" + izena + ", abizena=" + abizena + ", nan=" + nan + ", jokalarikod=" + jokalarikod
			+ ", posizioa=" + posizioa + ", incrementar=" + incrementar + "]";
}

}*/