package objektuak;

import java.io.Serializable;
import java.util.Objects;

/**
 * Taldeak klaseak talderen bat modelatzen du, izena, taldekod, zelaia, herrialdea eta incrementar atributuekin.
 * Klaseak objektu bat sortu eta beste talde batekin alderatu ahal izateko zenbait metodo eskaintzen ditu.
 */
	public class Taldeak implements Serializable{
		private String izena;
		private int taldekod;
		private String zelaia;
		private String herrialdea;
		private int incrementar = 20;
		
		/**
	     * Ezarpen-lehena: taldekod automatikoki handituko den taldea sortzen du.
	     */
		public Taldeak() {
			this.izena = "";
			this.taldekod = incrementar++;
			this.zelaia = "";
			this.herrialdea = "";
		}

		/**
	     * Ezarpen-lehena: izen, taldekod eta zelaia dituzten talde bat sortzen du.
	     * @param taldekod Taldearen kodea
	     * @param izena Taldearen izena
	     * @param zelaia Taldearen zelaia
	     */
		public Taldeak (int taldekod, String izena, String zelaia) {
			this.izena = izena;
			this.taldekod = taldekod;
			this.zelaia = zelaia;
			this.herrialdea = "";
		}

		/**
	     * Ezarpen-lehena: taldekod, izen, zelaia eta herrialdearekin talde bat sortzen du.
	     * @param taldeKod Taldearen kodea
	     * @param izena Taldearen izena
	     * @param zelaia Taldearen zelaia
	     * @param herrialdea Taldearen herrialdea
	     */
		public Taldeak (int taldeKod, String izena, String zelaia, String herrialdea) {
			this.taldekod = taldeKod;
			this.izena = izena;
			this.zelaia = zelaia;
			this.herrialdea = herrialdea;
		}

		/**
	     * Kopia-kontruktorea: beste Taldeak objektu batetik taldea sortzen du.
	     * @param beste Beste Taldeak objektua
	     */
		public Taldeak (Taldeak beste) {
			this.izena = beste.izena;
			this.taldekod = beste.taldekod;
			this.zelaia = beste.zelaia;
			this.herrialdea = beste.herrialdea;
		}

		/**
	     * Taldearen izena lortzeko metodoa.
	     * @return Taldearen izena
	     */
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

		/**
	     * Hashcode metodoa, Taldeak objektuaren identifikazio bakarra sortzeko.
	     * @return Taldeak objektuaren hashcode
	     */
		@Override
		public int hashCode() {
			return Objects.hash(herrialdea, incrementar, izena, taldekod);
		}

		/**
	     * ToString metodoa, Taldeak objektuaren deskribapena itzultzeko.
	     * @return Taldeak objektuaren deskribapena
	     */
		@Override
		public String toString() {
			return "Taldeak [taldekod=" + taldekod + ", izena=" + izena + ", herrialdea=" + herrialdea + "]"+ incrementar + "]";
		}

		/**
	     * Eguera metodoa, bi Taldeak objektuak berdinak diren ala ez egiaztatzeko.
	     * @param obj Beste objektu bat
	     * @return True bada berdinak dira, bestela false
	     */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Taldeak other = (Taldeak) obj;
			return Objects.equals(herrialdea, other.herrialdea) && incrementar == other.incrementar && Objects.equals(izena, other.izena) && taldekod == other.taldekod;
		}

		/**
	     * Taldeak objektuak alderatzeko metodoa.
	     * @param o Taldeak objektu bat
	     * @return Taldearen taldekod-en arabera alderaketa
	     */
		public int compareTo(Taldeak o) {
			return Integer. compare(this.getTaldekod(), o.taldekod);
		}	
	}