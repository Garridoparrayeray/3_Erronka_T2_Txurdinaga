package objektuak;

import java.io.Serializable;
import java.util.Objects;

public class Taldeak implements Serializable{
	private String izena;
	private int taldekod;
	private String zelaia;
	private String herrialdea;
	private int incrementar = 20;
  

public Taldeak() {
	this.izena = "";
	this.taldekod = incrementar++;
	this.zelaia = "";
	this.herrialdea = "";
}

public Taldeak (int taldekod, String izena, String zelaia) {
	this.izena = izena;
	this.taldekod = taldekod;
	this.zelaia = zelaia;
	this.herrialdea = "";
}

public Taldeak (int taldeKod, String izena, String zelaia, String herrialdea) {
	this.taldekod = taldeKod;
	this.izena = izena;
	this.zelaia = zelaia;
	this.herrialdea = herrialdea;
}

public Taldeak (Taldeak beste) {
	this.izena = beste.izena;
	this.taldekod = beste.taldekod;
	this.zelaia = beste.zelaia;
	this.herrialdea = beste.herrialdea;
}

public String getIzena() {
	return izena;
}

public void setIzena(String izena) {
	this.izena = izena;
}

public int getTaldekod() {
	return taldekod;
}

public void setTaldekod(int taldekod) {
	this.taldekod = taldekod;
}

public String getHerrialdea() {
	return herrialdea;
}

public void setHerrialdea(String herrialdea) {
	this.herrialdea = herrialdea;
}

public int getIncrementar() {
	return incrementar;
}

public String getZelaia() {
	return zelaia;
}

public void setZelaia(String zelaia) {
	this.zelaia = zelaia;
}

public void setIncrementar(int incrementar) {
	this.incrementar = incrementar;
}

@Override
public int hashCode() {
	return Objects.hash(herrialdea, incrementar, izena, taldekod);
}

@Override
public String toString() {
	return "Taldeak [taldekod=" + taldekod + ", izena=" + izena + ", herrialdea=" + herrialdea + "]"
			+ incrementar + "]";
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Taldeak other = (Taldeak) obj;
	return Objects.equals(herrialdea, other.herrialdea) && incrementar == other.incrementar
			&& Objects.equals(izena, other.izena) && taldekod == other.taldekod;
}

public int compareTo(Taldeak o) {
	return Integer. compare(this.getTaldekod(), o.taldekod);
}	
}