package objektuak;

import java.time.LocalDate;
import java.util.Objects;
import java.io.*;

public class Jokalariak extends Pertsona implements Serializable, Comparable<Jokalariak>{
  private int jokalarikod;
  private String nan;
  private String izena;
  private String abizena;
  private LocalDate jaiotzedata ;
  private String jokalariRola;
  private int taldeKod;
  private static int incrementar = 0;
  
  public Jokalariak() {
      this.jokalarikod = incrementar++;
      this.nan = "";
      this.izena = "";
      this.abizena = "";
      this.jaiotzedata = null;
      this.jokalariRola = "";
      this.taldeKod = 0;
  }
  
  public Jokalariak(int jokalarikod, String izena, String abizena, String jokalariRola, int taldeKod) {   
  	super(izena, abizena);
  	this.jokalarikod = jokalarikod;
  	this.jokalariRola = jokalariRola;
  	this.taldeKod = taldeKod;
}

  public Jokalariak(String jokalarikod, String nan, String izena, String abizena, LocalDate jaiotzedata, String jokalariRola, int taldeKod) {
      super(izena, abizena, PertsonaMota.jokalari, nan, jaiotzedata);
      this.jokalarikod = incrementar++;
      this.jokalariRola = jokalariRola;
      this.taldeKod = taldeKod;
  }
  
  // Constructor de copia
  public Jokalariak(Jokalariak beste) {
      super(beste.getIzena(), beste.getAbizena(), PertsonaMota.jokalari, beste.getNAN(), beste.getJaiotzedata());
      this.jokalarikod = beste.jokalarikod;
      this.jokalariRola = beste.jokalariRola;
  }

  @Override
  public String toString() {
      return "Jokalariak {" +  "jokalarikod=" + jokalarikod +", izena='" + getIzena() + '\'' +  ", abizena='" + getAbizena() + '\'' + ", NAN='" + getNAN() + '\'' + ", jaiotzedata=" + getJaiotzedata() +  ", posizioa='" + jokalariRola + '\'' + '}';
  }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(jokalarikod, jokalariRola);
		return result;
	}

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