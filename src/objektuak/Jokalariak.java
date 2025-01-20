package objektuak;

import java.time.LocalDate;
import java.util.Objects;

public class Jokalariak extends Pertsona implements Comparable<Jokalariak> {
  private int jokalarikod;
  private String posizioa;
  private static int incrementar = 0;
  
  public Jokalariak() {
      super();
      this.jokalarikod = incrementar++;
      this.posizioa = "";
  }

  public Jokalariak(String izena, String abizena, String nan, LocalDate jaiotzedata, String posizioa) {
      super(izena, abizena, PertsonaMota.jokalari, nan, jaiotzedata);
      this.jokalarikod = incrementar++;
      this.posizioa = posizioa;
  }
  
  // Constructor de copia
  public Jokalariak(Jokalariak beste) {
      super(beste.getIzena(), beste.getAbizena(), PertsonaMota.jokalari, beste.getNAN(), beste.getJaiotzedata());
      this.jokalarikod = beste.jokalarikod;
      this.posizioa = beste.posizioa;
  }
  
  // GETTERS Y SETTERS
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

  public static int getIncrementar() {
      return incrementar;
  }

  public static void setIncrementar(int incrementar) {
      Jokalariak.incrementar = incrementar;
  }

  @Override
  public String toString() {
      return "Jokalariak {" +" izena='" + getIzena() + '\'' +  ", abizena='" + getAbizena() + '\'' + ", NAN='" + getNAN() + '\'' + ", jaiotzedata=" + getJaiotzedata() +  ", jokalarikod=" + jokalarikod +  ", posizioa='" + posizioa + '\'' + '}';
  }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(jokalarikod, posizioa);
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
		return jokalarikod == other.jokalarikod && Objects.equals(posizioa, other.posizioa);
	}

	@Override
	public int compareTo(Jokalariak o) {
		return Integer. compare(this.getJokalarikod(), o.jokalarikod);
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
