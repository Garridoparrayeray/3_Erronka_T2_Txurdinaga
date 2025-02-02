package objektuak;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.Jornadas;
import main.TaldeenErabilpena;

/**
 * Partiduak klasea partidu baten informazioa kudeatzeko erabiltzen da.
 * Etxeko eta kanpoko taldeak, zelaia, puntuazioak, eta partidua burutzen den jardunaldia biltzen ditu.
 */
public class Partiduak implements Serializable {
		private int partidu_kod;
		private Taldeak etxeko_taldea;
		private Taldeak kanpoko_taldea;
		private String zelaia;
		private String partidudata;
		private int etxekoTaldekoPuntuazioa;
		private int kanpokoTaldekoPuntuazioa;
		
		private Jardunaldiak jardunaldia;
		
		private int counter = 1;
		
		/**
	     * Partidu bat sortzeko lehenengo konstruktorea. Balioak lehenetsiak dira.
	     */
		public Partiduak() {
			this.partidu_kod = automatikoa();
			this.etxeko_taldea = new Taldeak();
			this.kanpoko_taldea = new Taldeak();
			this.zelaia = "";
			this.partidudata = null;
			this.etxekoTaldekoPuntuazioa = 0;
			this.kanpokoTaldekoPuntuazioa = 0;
			this.jardunaldia = new Jardunaldiak();
		}
		
		/**
	     * Partidu bat sortzeko konstruktorea, non taldeak eta jardunaldia ezartzen diren.
	     * @param etxeko_taldea Etxeko taldea
	     * @param kanpoko_taldea Kanpoko taldea
	     * @param zelaia Partidua jokatzen den zelaia
	     * @param jardunaldia Partiduaren jardunaldia
	     */
		public Partiduak(Taldeak etxeko_taldea, Taldeak kanpoko_taldea, String zelaia, Jardunaldiak jardunaldia) {
			this.partidu_kod = automatikoa();;
			this.etxeko_taldea = etxeko_taldea;
			this.kanpoko_taldea = kanpoko_taldea;
			this.zelaia = zelaia;
			this.partidudata = null;
			this.etxekoTaldekoPuntuazioa = 0;
			this.kanpokoTaldekoPuntuazioa = 0;
			this.jardunaldia = jardunaldia;
		}
		
		/**
	     * Partidu bat sortzeko konstruktorea, non taldeak, puntuazioak eta jardunaldia ezartzen diren.
	     * @param partidu_kod Partiduaren kodea
	     * @param etxeko_taldea Etxeko taldea
	     * @param kanpoko_taldea Kanpoko taldea
	     * @param zelaia Partidua jokatzen den zelaia
	     * @param jardunaldia Partiduaren jardunaldia
	     */
		public Partiduak(int partidu_kod, Taldeak etxeko_taldea, Taldeak kanpoko_taldea, String zelaia, Jardunaldiak jardunaldia) {
			this.partidu_kod = partidu_kod;
			this.etxeko_taldea = etxeko_taldea;
			this.kanpoko_taldea = kanpoko_taldea;
			this.zelaia = zelaia;
			this.partidudata = null;
			this.etxekoTaldekoPuntuazioa = 0;
			this.kanpokoTaldekoPuntuazioa = 0;
			this.jardunaldia = jardunaldia;
		}
		
		/**
	     * Partidu bat sortzeko konstruktorea, non puntuazioak eta jardunaldia ere ezartzen diren.
	     * @param partidu_kod Partiduaren kodea
	     * @param etxeko_taldea Etxeko taldea
	     * @param kanpoko_taldea Kanpoko taldea
	     * @param zelaia Partidua jokatzen den zelaia
	     * @param etxekoTaldekoPuntuazioa Etxeko taldearen puntuazioa
	     * @param kanpokoTaldekoPuntuazioa Kanpoko taldearen puntuazioa
	     * @param jardunaldia Partiduaren jardunaldia
	     */
		public Partiduak(int partidu_kod, Taldeak etxeko_taldea, Taldeak kanpoko_taldea, String zelaia, int etxekoTaldekoPuntuazioa, int kanpokoTaldekoPuntuazioa, Jardunaldiak jardunaldia) {
			this.partidu_kod = partidu_kod;
			this.etxeko_taldea = etxeko_taldea;
			this.kanpoko_taldea = kanpoko_taldea;
			this.zelaia = zelaia;
			this.partidudata = null;
			this.etxekoTaldekoPuntuazioa = etxekoTaldekoPuntuazioa;
			this.kanpokoTaldekoPuntuazioa = kanpokoTaldekoPuntuazioa;
			this.jardunaldia = jardunaldia;
		}
		
		 /**
	     * Partidu bat sortzeko azkeneko konstruktorea, non data ere ezartzen den.
	     * @param partidu_kod Partiduaren kodea
	     * @param etxeko_taldea Etxeko taldea
	     * @param kanpoko_taldea Kanpoko taldea
	     * @param zelaia Partidua jokatzen den zelaia
	     * @param partidudata Partiduaren data
	     * @param etxekoTaldekoPuntuazioa Etxeko taldearen puntuazioa
	     * @param kanpokoTaldekoPuntuazioa Kanpoko taldearen puntuazioa
	     * @param jardunaldia Partiduaren jardunaldia
	     */
		public Partiduak(int partidu_kod, Taldeak etxeko_taldea, Taldeak kanpoko_taldea, String zelaia, String partidudata, int etxekoTaldekoPuntuazioa, int kanpokoTaldekoPuntuazioa, Jardunaldiak jardunaldia) {
			this.partidu_kod = partidu_kod;
			this.etxeko_taldea = etxeko_taldea;
			this.kanpoko_taldea = kanpoko_taldea;
			this.zelaia = zelaia;
			this.partidudata = partidudata;
			this.etxekoTaldekoPuntuazioa = etxekoTaldekoPuntuazioa;
			this.kanpokoTaldekoPuntuazioa = kanpokoTaldekoPuntuazioa;
			this.jardunaldia = jardunaldia;
		}
		
		/**
	     * Partiduaren automatikoki sortutako kodea ematen du, dagoeneko sortutako partidu kodeak saihestuz.
	     * @return Partidu kode automatikoa
	     */
		public Partiduak(Partiduak bestea) {
			this.partidu_kod = bestea.partidu_kod;
			this.etxeko_taldea = bestea.etxeko_taldea;
			this.kanpoko_taldea = bestea.kanpoko_taldea;
			this.zelaia = bestea.zelaia;
			this.partidudata = bestea.partidudata;
			this.etxekoTaldekoPuntuazioa = bestea.etxekoTaldekoPuntuazioa;
			this.kanpokoTaldekoPuntuazioa = bestea.kanpokoTaldekoPuntuazioa;
			this.jardunaldia = bestea.jardunaldia;
		}
		
		/**
	     * Partidua bezala adierazteko metodoa.
	     * @return Partiduaren informazio guztia
	     */
		public int automatikoa() {
			List<Jornadas> jornadas = TaldeenErabilpena.jardunaldiakIrakurri();
			List<List<Partiduak>> partiduak = new ArrayList<List<Partiduak>>();
			
			
			for (Jornadas jornada : jornadas) {
				partiduak.add(jornada.getPartiduak());
			}
			boolean errepikatua;
			boolean amaiera = false;
			int hasiera = 0;
			for (List<Partiduak> listaPartiduak : partiduak) {
				errepikatua = false;
				hasiera++;
				for (Partiduak partidua : listaPartiduak) {
					if (hasiera == partidua.getPartidu_kod()) {
						errepikatua = true;
					}
				}
				if (errepikatua == false) {
					return hasiera;
				}
			}
			counter++;
			return counter;
		}
		
		/**
	     * Partidua bezala adierazteko metodoa.
	     * @return Partiduaren informazio guztia
	     */
		public String toString() {
			return "Partidua {partidu kodea=" + partidu_kod + "etxeko taldea=" + etxeko_taldea + ", kanpoko taldea=" + kanpoko_taldea + "zelaia=" + zelaia + ", etxeko taldearen puntuazioa=" + etxekoTaldekoPuntuazioa + ", kanpoko taldearen puntuazioa=" + kanpokoTaldekoPuntuazioa + " }";
		}
		
		/**
	     * Partiduaren hash kodea ematen du.
	     * @return Partiduaren hash kodea
	     */
		public int hashCode() {
			int emaitza = 37;
			emaitza += emaitza * 13 * Objects.hash(partidu_kod);
			return emaitza;
		}
		
		/**
	     * Partidu baten berdintasunaren ebaluazioa egiten du.
	     * @param bestea Bestelako Partidu bat
	     * @return Berdin badira egia, bestela faltsua
	     */
		public boolean equals(Object bestea) {
			if (this == bestea) {
				return true;
			}
			else if (getClass() != bestea.getClass()) {
				return false;
			}
			else {
				Partiduak bestePartidua = (Partiduak) bestea;
				return partidu_kod == bestePartidua.partidu_kod;
			}
		}
		
		/**
	     * Partiduak konparatzeko metodoa.
	     * @param p Beste Partidu bat
	     * @return Partiduaren konparazioa
	     */
		public int compareTo(Partiduak p) {
			return Integer.compare(partidu_kod, p.partidu_kod);
		}

		public int getPartidu_kod() {
			return partidu_kod;
		}

		public void setPartidu_kod(int partidu_kod) {
			this.partidu_kod = partidu_kod;
		}

		public Taldeak getEtxeko_taldea() {
			return etxeko_taldea;
		}

		public void setEtxeko_taldea(Taldeak etxeko_taldea) {
			this.etxeko_taldea = etxeko_taldea;
		}

		public Taldeak getKanpoko_taldea() {
			return kanpoko_taldea;
		}

		public void setKanpoko_taldea(Taldeak kanpoko_taldea) {
			this.kanpoko_taldea = kanpoko_taldea;
		}

		public String getZelaia() {
			return zelaia;
		}

		public void setZelaia(String zelaia) {
			this.zelaia = zelaia;
		}

		public String getPartidudata() {
			return partidudata;
		}

		public void setPartidudata(String partidudata) {
			this.partidudata = partidudata;
		}

		public int getEtxekoTaldekoPuntuazioa() {
			return etxekoTaldekoPuntuazioa;
		}

		public void setEtxekoTaldekoPuntuazioa(int etxekoTaldekoPuntuazioa) {
			this.etxekoTaldekoPuntuazioa = etxekoTaldekoPuntuazioa;
		}

		public int getKanpokoTaldekoPuntuazioa() {
			return kanpokoTaldekoPuntuazioa;
		}

		public void setKanpokoTaldekoPuntuazioa(int kanpokoTaldekoPuntuazioa) {
			this.kanpokoTaldekoPuntuazioa = kanpokoTaldekoPuntuazioa;
		}

		public int getCounter() {
			return counter;
		}

		public void setCounter(int counter) {
			this.counter = counter;
		}
		
		public void setJardunaldia(Jardunaldiak jardunaldia) {
			this.jardunaldia = jardunaldia;
		}
		
		public Jardunaldiak getJardunaldiak() {
			return jardunaldia;
		}
}