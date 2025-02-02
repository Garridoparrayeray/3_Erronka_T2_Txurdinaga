package frogak;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.IrteeraSarreraXML;
import objektuak.*;

import java.awt.GridLayout;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

class JornadasFroga {

	@Test
	void JornadasSortzea() {
		List<Taldeak> taldeak = new ArrayList<Taldeak>();
		for (int i = 0; i < 7; i++) {
			taldeak.add(new Taldeak());
		}
		
		Denboraldiak denboraldia = new Denboraldiak();
		List<Jardunaldiak> jardunaldiakDatua = new ArrayList<Jardunaldiak>();
		List<Partiduak> partiduakDatua = new ArrayList<Partiduak>();
		
		// Denboraldiak sortzeko beharrezko datuak eskatzeko:
		int numEquipos = taldeak.size();
        boolean tieneDescanso = false;
        JPanel denboraldiPanela = new JPanel(new GridLayout(3, 2));
        JLabel izenaEskaera = new JLabel("Denboraldiaren Izena: ");
    	JTextField izenaSartu = new JTextField();
    	JLabel hasieraDataEskaera = new JLabel("Hasiera Data: ");
        JTable taula = new JTable(1, 3);
        JLabel amaieraDataEskaera = new JLabel("Amaiera Data: ");
        JTable taula2 = new JTable(1, 3);
        denboraldiPanela.add(izenaEskaera);
        denboraldiPanela.add(izenaSartu);
        denboraldiPanela.add(hasieraDataEskaera);
        denboraldiPanela.add(taula);
        denboraldiPanela.add(amaieraDataEskaera);
        denboraldiPanela.add(taula2);
        
        JOptionPane.showMessageDialog(null, denboraldiPanela, "Denboraldia Sartzeko", JOptionPane.OK_CANCEL_OPTION);
    	String urteaH = taula.getValueAt(0, 0) + "";
    	String hilabeteaH = taula.getValueAt(0, 1) + "";
    	String egunaH = taula.getValueAt(0, 2) + "";
    	String urteaA = taula2.getValueAt(0, 0) + "";
    	String hilabeteaA = taula2.getValueAt(0, 1) + "";
    	String egunaA = taula2.getValueAt(0, 2) + "";
    	String izena = izenaSartu.getText();
    	String hasieraData = urteaH + "-" + hilabeteaH + "-" + egunaH;
    	String amaieraData = urteaA + "-" + hilabeteaA + "-" + egunaA;
    	denboraldia.setDenboraldi_Izena(izena);
    	denboraldia.setHasiera_data(hasieraData);
    	denboraldia.setAmaiera_data(amaieraData);
    	
    	
    	// Sartutako datu guztiak ondo egotea egiaztatzen du. Efizientagoa izateko, for eta switch bat konbinatzen erabili egin ditugu.
    	boolean irtera = false;
    	boolean zenbakiaEz = false;
    	char c;
    	for (int i = 0; i < 7; i++) {
    		switch(i) {
    		case 0:
    			for (int i2 = 0; i2 < urteaH.length() ; i2++) {
    				c = urteaH.charAt(i2);
    				if (!Character.isDigit(c)) {
    					zenbakiaEz = true;
    				}
    			}
    			if (urteaH.isEmpty() || zenbakiaEz == true) {
    				irtera = true;
    				JOptionPane.showMessageDialog(null, "Hasiera datako urtearen eremua utzik dago, edo sartutako datua ez da zenbaki oso bat. Berriro saiatu mesdez.");
    			}
    		break;
    		case 1:
    			for (int i2 = 0; i2 < hilabeteaH.length() ; i2++) {
    				c = hilabeteaH.charAt(i2);
    				if (!Character.isDigit(c)) {
    					zenbakiaEz = true;
    				}
    			}
    			if (hilabeteaH.isEmpty() || zenbakiaEz == true) {
    				irtera = true;
    				JOptionPane.showMessageDialog(null, "Hasiera datako hilabetearen eremua utzik dago, edo sartutako datua ez da zenbaki oso bat. Berriro saiatu mesdez.");
    			}
    		break;
    		case 2:
    			for (int i2 = 0; i2 < egunaH.length() ; i2++) {
    				c = egunaH.charAt(i2);
    				if (!Character.isDigit(c)) {
    					zenbakiaEz = true;
    				}
    			}
    			if (egunaH.isEmpty() || zenbakiaEz == true) {
    				irtera = true;
    				JOptionPane.showMessageDialog(null, "Hasiera datako egunaren eremua utzik dago, edo sartutako datua ez da zenbaki oso bat. Berriro saiatu mesdez.");
    			}
    		break;
    		case 3:
    			for (int i2 = 0; i2 < urteaA.length() ; i2++) {
    				c = urteaA.charAt(i2);
    				if (!Character.isDigit(c)) {
    					zenbakiaEz = true;
    				}
    			}
    			if (urteaA.isEmpty() || zenbakiaEz == true) {
    				JOptionPane.showMessageDialog(null, "Amairera datako urtearen eremua utzik dago, edo sartutako datua ez da zenbaki oso bat. Berriro saiatu mesdez.");
    			}
    		break;
    		case 4:
    			for (int i2 = 0; i2 < hilabeteaA.length() ; i2++) {
    				c = hilabeteaA.charAt(i2);
    				if (!Character.isDigit(c)) {
    					zenbakiaEz = true;
    				}
    			}
    			if (hilabeteaA.isEmpty() || zenbakiaEz == true) {
    				irtera = true;
    				JOptionPane.showMessageDialog(null, "Amaiera datako hilabetearen eremua utzik dago, edo sartutako datua ez da zenbaki oso bat. Berriro saiatu mesdez.");
    			}
    		break;
    		case 5:
    			for (int i2 = 0; i2 < egunaA.length() ; i2++) {
    				c = egunaA.charAt(i2);
    				if (!Character.isDigit(c)) {
    					zenbakiaEz = true;
    				}
    			}
    			if (egunaA.isEmpty() || zenbakiaEz == true) {
    				irtera = true;
    				JOptionPane.showMessageDialog(null, "Amaiera datako egunaren eremua utzik dago, edo sartutako datua ez da zenbaki oso bat. Berriro saiatu mesdez.");
    			}
    		break;
    		case 6: 
    			if (izena.isEmpty()) {
    				irtera = true;
    			}
    			break;
    		}
    	}
    	if (irtera == true) {
    		return;
    	}
    	
    	/*
    	 * Jornadas automatikoki sortzeko, lehenengo, aplikazioan gordeta dauden talde kopurua begiratzen du. Bakoitiak badira, deskantzua taldea sortuko da, baina talde berri hau jolasten dituen partiduak ez dira gordeko
    	 * Beste alde batetik, talde fijo bat erabiliko da erreferentzia moduan. Buklearen buelta bakoitzean rotatzen egongo da. 
    	 * Taldeak automatikoki sortzeko, round-robin-eko algoritmo matematikoa erabili dugu
    	 */
    	
    	Taldeak deskantzua = new Taldeak();
    	if (taldeak.size() % 2 != 0) {
    		taldeak.add(deskantzua);
    		tieneDescanso = true;
    	}

    	List<Taldeak> listaTaldeak = new ArrayList<>(taldeak);
    	Taldeak taldeFijoa = listaTaldeak.remove(0);  
    	
    	int counter = 0;
    	int counter2 = 0;
    	for (int i = 0; i < (taldeak.size() - 1); i++) {
    		String jardunaldiHasieraData = "";
    		String jardunaldiAmaieraData = "";
    		
    		Jardunaldiak jardunaldia = new Jardunaldiak(((counter + 1)  + ". Jardunaldia"), jardunaldiHasieraData, jardunaldiAmaieraData, denboraldia);
    		
    		for (int j = 0; j < taldeak.size() / 2; j++) {
                Taldeak etxekoa = (j == 0) ? taldeFijoa : listaTaldeak.get(j - 1);
                Taldeak kanpokoa = listaTaldeak.get(numEquipos - 2 - j);

                if (!etxekoa.equals(deskantzua) && !kanpokoa.equals(deskantzua)) {
                    Partiduak partido = new Partiduak(etxekoa, kanpokoa, etxekoa.getZelaia(), jardunaldia);
                    partiduakDatua.add(partido);
                    counter2++;
                }
                if (j == (taldeak.size() / 2) - 1) {
                	//System.out.println(counter2);
                	jardunaldia.setPartidu_kopurua(counter2);
                	counter2 = 0;
                }
            }
    		Collections.rotate(listaTaldeak, 1);
    		jardunaldiakDatua.add(jardunaldia);
    		counter++;
    	}
    	
    	
    	for (int i = 0; i < (taldeak.size() - 1); i++) {
    		String jardunaldiHasieraData = "";
    		String jardunaldiAmaieraData = "";
    		
    		Jardunaldiak jardunaldia = new Jardunaldiak(((counter + 1)  + ". Jardunaldia"), jardunaldiHasieraData, jardunaldiAmaieraData, denboraldia);
    		
    		for (int j = 0; j < taldeak.size() / 2; j++) {
                Taldeak kanpokoa = (j == 0) ? taldeFijoa : listaTaldeak.get(j - 1);
                Taldeak etxekoa = listaTaldeak.get(numEquipos - 2 - j);

                if (!etxekoa.equals(deskantzua) && !kanpokoa.equals(deskantzua)) {
                    Partiduak partido = new Partiduak(etxekoa, kanpokoa, etxekoa.getZelaia(), jardunaldia);
                    partiduakDatua.add(partido);
                    counter2++;
                }
                if (j == (taldeak.size() / 2) - 1) {
                	jardunaldia.setPartidu_kopurua(counter2);
                	//System.out.println(counter2);
                	counter2 = 0;
                }
            }
    		Collections.rotate(listaTaldeak, 1);
    		jardunaldiakDatua.add(jardunaldia);
    		counter++;
    		if (tieneDescanso == true) {
    			taldeak.remove(taldeak.size() - 1);
    			tieneDescanso = false;
    		}
    	}
	}

}
