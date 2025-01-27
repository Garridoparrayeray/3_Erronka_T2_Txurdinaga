package objektuak;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import main.Jornadas;
import main.TaldeenErabilpena;


public class Partiduak {
		private int partidu_kod;
		private String etxeko_taldea;
		private String kanpoko_taldea;
		private String zelaia;
		private LocalDate partidudata;
		private int etxekoTaldekoPuntuazioa;
		private int kanpokoTaldekoPuntuazioa;
		
		private Jardunaldiak jardunaldia;
		
		private int counter = 0;
		
		public Partiduak() {
			this.partidu_kod = automatikoa();
			this.etxeko_taldea = "";
			this.kanpoko_taldea = "";
			this.zelaia = "";
			this.partidudata = null;
			this.etxekoTaldekoPuntuazioa = 0;
			this.kanpokoTaldekoPuntuazioa = 0;
			this.jardunaldia = new Jardunaldiak();
		}
		
		public Partiduak(String etxeko_taldea, String kanpoko_taldea, String zelaia, Jardunaldiak jardunaldia) {
			this.partidu_kod = automatikoa();;
			this.etxeko_taldea = etxeko_taldea;
			this.kanpoko_taldea = kanpoko_taldea;
			this.zelaia = zelaia;
			this.partidudata = null;
			this.etxekoTaldekoPuntuazioa = 0;
			this.kanpokoTaldekoPuntuazioa = 0;
			this.jardunaldia = jardunaldia;
		}
		
		public Partiduak(int partidu_kod, String etxeko_taldea, String kanpoko_taldea, String zelaia, LocalDate partidudata, int etxekoTaldekoPuntuazioa, int kanpokoTaldekoPuntuazioa, Jardunaldiak jardunaldia) {
			this.partidu_kod = partidu_kod;
			this.etxeko_taldea = etxeko_taldea;
			this.kanpoko_taldea = kanpoko_taldea;
			this.zelaia = zelaia;
			this.partidudata = partidudata;
			this.etxekoTaldekoPuntuazioa = etxekoTaldekoPuntuazioa;
			this.kanpokoTaldekoPuntuazioa = kanpokoTaldekoPuntuazioa;
			this.jardunaldia = new Jardunaldiak();
		}
		
		public Partiduak(Partiduak bestea) {
			this.partidu_kod = bestea.partidu_kod;
			this.etxeko_taldea = bestea.etxeko_taldea;
			this.kanpoko_taldea = bestea.kanpoko_taldea;
			this.zelaia = bestea.zelaia;
			this.partidudata = bestea.partidudata;
			this.etxekoTaldekoPuntuazioa = bestea.etxekoTaldekoPuntuazioa;
			this.kanpokoTaldekoPuntuazioa = bestea.kanpokoTaldekoPuntuazioa;
			this.jardunaldia = new Jardunaldiak();
		}
		
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
			return 0;
		}
		
		public String toString() {
			return "Partidua {partidu kodea=" + partidu_kod + "etxeko taldea=" + etxeko_taldea + ", kanpoko taldea=" + kanpoko_taldea + "zelaia=" + zelaia + ", etxeko taldearen puntuazioa=" + etxekoTaldekoPuntuazioa + ", kanpoko taldearen puntuazioa=" + kanpokoTaldekoPuntuazioa + " }";
		}
		
		public int hashCode() {
			int emaitza = 37;
			emaitza += emaitza * 13 * Objects.hash(partidu_kod);
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
				Partiduak bestePartidua = (Partiduak) bestea;
				return partidu_kod == bestePartidua.partidu_kod;
			}
		}
		
		public int compareTo(Partiduak p) {
			return Integer.compare(partidu_kod, p.partidu_kod);
		}

		public int getPartidu_kod() {
			return partidu_kod;
		}

		public void setPartidu_kod(int partidu_kod) {
			this.partidu_kod = partidu_kod;
		}

		public String getEtxeko_taldea() {
			return etxeko_taldea;
		}

		public void setEtxeko_taldea(String etxeko_taldea) {
			this.etxeko_taldea = etxeko_taldea;
		}

		public String getKanpoko_taldea() {
			return kanpoko_taldea;
		}

		public void setKanpoko_taldea(String kanpoko_taldea) {
			this.kanpoko_taldea = kanpoko_taldea;
		}

		public String getZelaia() {
			return zelaia;
		}

		public void setZelaia(String zelaia) {
			this.zelaia = zelaia;
		}

		public LocalDate getPartidudata() {
			return partidudata;
		}

		public void setPartidudata(LocalDate partidudata) {
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
