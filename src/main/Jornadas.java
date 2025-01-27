package main;

import java.awt.GridLayout;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import objektuak.*;

public class Jornadas implements Serializable{
		private Denboraldiak denboraldiak;
		private List<Jardunaldiak> jardunaldiak;
		private List<Partiduak> partiduak;
		
		public Jornadas() {
			this.denboraldiak = new Denboraldiak();
			this.jardunaldiak = new ArrayList<Jardunaldiak>();
			this.partiduak = new ArrayList<Partiduak>();
		}
		
    public Jornadas (List<Taldeak> taldeak) {
    	List<Jardunaldiak> listaJardunaldiak = new ArrayList<Jardunaldiak>();
    	List<Partiduak> listaPartiduak = new ArrayList<Partiduak>();
  		Taldeak deskantzua = new Taldeak();
  		int counter = 0;
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
        boolean zenbakiakE = false;
        String konrpobatzeko;
        char c;
       /* for (int i = 0; i < 3; i++) {
        	konrpobatzeko = taula.getValueAt(i, 1).toString();
        	for (int i2 = 0; i2 < konrpobatzeko.length(); i2++) {
        		c = konrpobatzeko.charAt(i2);
        		if (!Character.isDigit(c)) {
        			zenbakiakE = true;
        		}
        	}
        }*/
        
        if (zenbakiakE == false) {
        	String izena = izenaSartu.getText();
        	int urteaH = Integer.valueOf(taula.getValueAt(0, 0).toString());
        	int hilabeteaH = Integer.valueOf(taula.getValueAt(0, 1).toString());
        	int egunaH = Integer.valueOf(taula.getValueAt(0, 2).toString());
        	int urteaA = Integer.valueOf(taula2.getValueAt(0, 0).toString());
        	int hilabeteaA = Integer.valueOf(taula2.getValueAt(0, 1).toString());
        	int egunaA = Integer.valueOf(taula2.getValueAt(0, 2).toString());
        	LocalDate hasieraData = LocalDate.of(urteaH, hilabeteaH, egunaH);
        	LocalDate amaieraData = LocalDate.of(urteaA, hilabeteaA, egunaA);
        	//LocalDate hasieraData = LocalDate.of(urteaH, hilabeteaH, egunaH);
        	//LocalDate amaieraData = LocalDate.of(urteaA, hilabeteaA, egunaA);
        	Denboraldiak denboraldia = new Denboraldiak(izena, hasieraData, amaieraData);
        	this.denboraldiak = denboraldia;
        }
        else {
        	JOptionPane.showMessageDialog(null, "Datarako, bakarrik zenbaki osoak sartu ahal dira. Berriro saiatu mesedez.", "Datak Sartzeko Errorea", JOptionPane.ERROR_MESSAGE);
        }
    		
    		if (taldeak.size() % 2 != 0) {
    			taldeak.add(deskantzua);
    		}
    		
    		// Kanpoko eta etxeko taldeak simulatzeko, taldeMultzoa erabiliko da.
    		List<Taldeak> taldeMultzoa = taldeak;
    		
    		for (int i = 0; i < taldeak.size() - 1; i++) {
    			counter++;
    			Jardunaldiak jardunaldia = new Jardunaldiak();
    			jardunaldia.setJardunaldi_deskribapena(counter + ". Jardunaldia");
    			jardunaldia.setPartidu_kopurua(taldeak.size() / 2);
    			listaJardunaldiak.add(jardunaldia);
    			for (int j = 0; j < taldeak.size() / 2; i++) {
    				Taldeak etxekoTaldea = taldeMultzoa.get(j);
    				Taldeak kanpokoTaldea = taldeMultzoa.get(taldeak.size() -i - j);
    				
    				if (!etxekoTaldea.equals(deskantzua) && !kanpokoTaldea.equals(deskantzua)) {
    					Partiduak partidua = new Partiduak(etxekoTaldea.getIzena(), kanpokoTaldea.getIzena(), etxekoTaldea.getZelaia(), jardunaldia);
    					partiduak.add(partidua);
    				}
    			}
    		}
    		
    		for (int i = 0; i < taldeak.size() - 1; i++) {
    			counter++;
    			Jardunaldiak jardunaldia = new Jardunaldiak();
    			jardunaldia.setJardunaldi_deskribapena(counter + ". Jardunaldia");
    			jardunaldia.setPartidu_kopurua(taldeak.size() / 2);
    			listaJardunaldiak.add(jardunaldia);
    			for (int j = 0; j < taldeak.size() / 2; i++) {
    				Taldeak kanpokoTaldea = taldeMultzoa.get(j);
    				Taldeak etxekoTaldea = taldeMultzoa.get(taldeak.size() -i - j);
    				
    				if (!etxekoTaldea.equals(deskantzua) && !kanpokoTaldea.equals(deskantzua)) {
    					Partiduak partidua = new Partiduak(kanpokoTaldea.getIzena(), etxekoTaldea.getIzena(), kanpokoTaldea.getZelaia(), jardunaldia);
    					listaPartiduak.add(partidua);
    				}
    			}
    		}
    		this.jardunaldiak = listaJardunaldiak;
    		this.partiduak = listaPartiduak;
    }
    
    public void addDenboraldiak (Denboraldiak denboraldia) {
    	this.denboraldiak = denboraldia;
    }
    
    public Denboraldiak getDenboraldiak () {
    	return denboraldiak;
    }
    
    public void addJardunaldiak (Jardunaldiak jardunaldia) {
    	jardunaldiak.add(jardunaldia);
    }
    
    public List<Jardunaldiak> getJardunaldiak () {
    	return jardunaldiak;
    }
    
    public void addPartiduak (Partiduak partidua) {
    	partiduak.add(partidua);
    }
    
    public List<Partiduak> getPartiduak () {
    	return partiduak;
    }
}