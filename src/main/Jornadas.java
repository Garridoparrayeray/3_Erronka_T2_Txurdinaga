package main;

import java.awt.GridLayout;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import objektuak.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jornadas implements Serializable{
	private Denboraldiak denboraldiak = new Denboraldiak();
    private List<Jardunaldiak> jardunaldiak;
    private List<Partiduak> partiduak;
    
    public Jornadas() {
			this.denboraldiak = new Denboraldiak();
			this.jardunaldiak = new ArrayList<Jardunaldiak>();
			this.partiduak = new ArrayList<Partiduak>();
		}

    public Jornadas(List<Taldeak> taldeak) {
        if (taldeak.size() < 6) {
            throw new IllegalArgumentException("Deben haber al menos 6 equipos para generar la temporada.");
        }

        this.jardunaldiak = new ArrayList<>();
        this.partiduak = new ArrayList<>();

        generarCalendario(taldeak);
    }

    private void generarCalendario(List<Taldeak> taldeak) {
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
        boolean zenbakiakE = false;
        String konprobatzeko;
        char c;
       /*for (int i = 0; i < 3; i++) {
      	 konprobatzeko = taula.getValueAt(1, i).toString();
        	for (int i2 = 0; i2 < konprobatzeko.length(); i2++) {
        		c = konprobatzeko.charAt(i2);
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

        // Si hay un número impar de equipos, agregamos un equipo "descanso"
        Taldeak descanso = new Taldeak();
        if (numEquipos % 2 != 0) {
            taldeak.add(descanso);
            numEquipos++;
            tieneDescanso = true;
        }

        List<Taldeak> equipos = new ArrayList<>(taldeak);
        Taldeak equipoFijo = equipos.remove(0);  // Primer equipo fijo en la rotación

        int numJornadas = numEquipos - 1;

        // Crear jornadas de IDA
        for (int i = 0; i < numJornadas; i++) {
            Jardunaldiak jornada = new Jardunaldiak((i + 1) + ". Jardunaldia", numEquipos / 2);
            for (int j = 0; j < numEquipos / 2; j++) {
                Taldeak local = (j == 0) ? equipoFijo : equipos.get(j - 1);
                Taldeak visitante = equipos.get(numEquipos - 2 - j);

                if (!local.equals(descanso) && !visitante.equals(descanso)) {
                    Partiduak partido = new Partiduak(local.getIzena(), visitante.getIzena(), local.getZelaia(), jornada);
                    partiduak.add(partido);
                }
            }
            // Rotamos los equipos excepto el equipo fijo
            Collections.rotate(equipos, 1);
            jardunaldiak.add(jornada);
        }

        // Crear jornadas de VUELTA (invirtiendo local y visitante)
        for (int i = 0; i < numJornadas; i++) {
            Jardunaldiak jornada = new Jardunaldiak((numJornadas + i + 1) + ". Jardunaldia", numEquipos / 2);
            for (int j = 0; j < numEquipos / 2; j++) {
                Taldeak visitante = (j == 0) ? equipoFijo : equipos.get(j - 1);
                Taldeak local = equipos.get(numEquipos - 2 - j);

                if (!local.equals(descanso) && !visitante.equals(descanso)) {
                    Partiduak partido = new Partiduak(local.getIzena(), visitante.getIzena(), local.getZelaia(), jornada);
                    partiduak.add(partido);
                }
            }
            // Rotamos los equipos para la vuelta
            Collections.rotate(equipos, 1);
            jardunaldiak.add(jornada);
        }

        // Si se agregó un equipo descanso, eliminarlo de la lista original
        if (tieneDescanso) {
            taldeak.remove(taldeak.size() - 1);
        }
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

/*package main;

import java.awt.GridLayout;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

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
  		int counter = 1;
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
       /* for (int i = 0; i < 3; i++) {
        	konrpobatzeko = taula.getValueAt(i, 1).toString();
        	for (int i2 = 0; i2 < konrpobatzeko.length(); i2++) {
        		c = konrpobatzeko.charAt(i2);
        		if (!Character.isDigit(c)) {
        			zenbakiakE = true;
        		}
        	}
        }/*
        
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
    		
        int taldeKopurua = taldeak.size() - 1;
        
    		if (taldeKopurua + 1 % 2 != 0) {
    			taldeak.add(deskantzua);
    			taldeKopurua++;
    		}
    		
    		// Kanpoko eta etxeko taldeak simulatzeko, taldeMultzoa erabiliko da.
    		List<Taldeak> taldeMultzoa = taldeak;
    		counter = 1;
    		for (int i = 0; i < taldeKopurua - 1; i++) {
    			Jardunaldiak jardunaldia = new Jardunaldiak(((counter) + ". Jardunaldia"), taldeKopurua / 2);
    			listaJardunaldiak.add(jardunaldia);
    			for (int j = 0; j < taldeKopurua / 2; j++) {
    				Taldeak etxekoTaldea = taldeMultzoa.get(i);
    				Taldeak kanpokoTaldea = taldeMultzoa.get(taldeKopurua - i - j); //taldeKopurua - i - j
    				
    				if (!etxekoTaldea.equals(deskantzua) && !kanpokoTaldea.equals(deskantzua) && !etxekoTaldea.equals(kanpokoTaldea)) {
    					Partiduak partidua = new Partiduak(etxekoTaldea.getIzena(), kanpokoTaldea.getIzena(), etxekoTaldea.getZelaia(), jardunaldia);
    					listaPartiduak.add(partidua);
    				}
    			}
    			counter++;
    		}
    		
    		for (int i = 0; i < taldeKopurua - 1; i++) {
    			Jardunaldiak jardunaldia = new Jardunaldiak(((counter) + ". Jardunaldia"), (taldeKopurua / 2));
    			listaJardunaldiak.add(jardunaldia);
    			for (int j = 0; j < taldeKopurua / 2; j++) {
    				Taldeak kanpokoTaldea = taldeMultzoa.get(j);
    				Taldeak etxekoTaldea = taldeMultzoa.get(taldeKopurua - i - j);
    				
    				if ((!etxekoTaldea.equals(deskantzua) && !kanpokoTaldea.equals(deskantzua)) && !etxekoTaldea.equals(kanpokoTaldea)) {
    					Partiduak partidua = new Partiduak(kanpokoTaldea.getIzena(), etxekoTaldea.getIzena(), kanpokoTaldea.getZelaia(), jardunaldia);
    					listaPartiduak.add(partidua);
    				}
    			}
    			counter++;
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
}*/
