package objektuak;

import java.util.Objects;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Pertsona klaseak pertsonaren datuak modelatzen ditu, hala nola izena, abizena, mota, NAN eta jaiotze data.
 * Klase honek ere PertsonaMota enum-a barne hartzen du, pertsonaren mota zehazteko (entrenatzailea, epailea edo jokalari).
 */
public class Pertsona implements Serializable{
    private String izena;
    private String abizena;
    private PertsonaMota mota;
    private String NAN;
    private LocalDate jaiotzedata;

    /**
     * Ezarpen-lehena: pertsona bat sortzen du datu hutsekin.
     */
    public Pertsona() {
        this.izena = "";
        this.abizena = "";
        this.mota = null;
        this.NAN = "";
        this.jaiotzedata = null;
    }
    
    /**
     * Ezarpen-lehena: izen eta abizenekin pertsona bat sortzen du.
     * @param izena Pertsonaren izena
     * @param abizena Pertsonaren abizena
     */
    public Pertsona(String izena, String abizena ) {
    	this.izena = izena;
    	this.abizena = izena;
    	this.mota = null;
    	this.NAN = "";
    	this.jaiotzedata = null;
    }

    /**
     * Ezarpen-lehena: izen, abizena, mota, NAN eta jaiotze datarekin pertsona bat sortzen du.
     * @param izena Pertsonaren izena
     * @param abizena Pertsonaren abizena
     * @param mota Pertsonaren mota (entrenatzailea, epailea, jokalari)
     * @param NAN Pertsonaren NAN
     * @param jaiotzedata Pertsonaren jaiotze data
     */
    public Pertsona(String izena, String abizena, PertsonaMota mota, String NAN, LocalDate jaiotzedata) {
        this.izena = izena;
        this.abizena = abizena;
        this.mota = mota;
        this.NAN = NAN;
        this.jaiotzedata = jaiotzedata;
    }

    /* GETTER ETA SETTERAK */

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

    public PertsonaMota getMota() {
        return mota;
    }

    public void setMota(PertsonaMota mota) {
        this.mota = mota;
    }

    public String getNAN() {
        return NAN;
    }

    public void setNAN(String nAN) {
        NAN = nAN;
    }

    public LocalDate getJaiotzedata() {
        return jaiotzedata;
    }

    public void setJaiotzedata(LocalDate jaiotzedata) {
        this.jaiotzedata = jaiotzedata;
    }

    /* HASHCODE, EQUALS ETA TOSTRING */

    @Override
    public int hashCode() {
        return Objects.hash(izena, abizena, mota, NAN, jaiotzedata);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pertsona other = (Pertsona) obj;
        return Objects.equals(izena, other.izena) &&
                Objects.equals(abizena, other.abizena) &&
                mota == other.mota &&
                Objects.equals(NAN, other.NAN) &&
                Objects.equals(jaiotzedata, other.jaiotzedata);
    }

    @Override
    public String toString() {
        return "Pertsona{" +
                "izena='" + izena + '\'' +
                ", abizena='" + abizena + '\'' +
                ", mota=" + mota +
                ", NAN='" + NAN + '\'' +
                ", jaiotzedata=" + jaiotzedata +
                '}';
    }

    /* ENUM PERTSONAMOTA */
    public enum PertsonaMota {
        entrenatzailea,
        epailea,
        jokalari;
    }
}