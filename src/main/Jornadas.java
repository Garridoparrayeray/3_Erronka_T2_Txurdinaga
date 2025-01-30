package main;

import objektuak.Denboraldiak;
import objektuak.Jardunaldiak;
import objektuak.Partiduak;
import objektuak.Taldeak;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.swing.*;
import java.awt.*;

public class Jornadas implements Serializable {
	private Denboraldiak denboraldia;
	private List<Jardunaldiak> jardunaldiak;
	private List<Partiduak> partiduak;
	private List<Taldeak> taldeak; 
	
	public Jornadas() {
		this.denboraldia = new Denboraldiak();
		this.jardunaldiak = new ArrayList<Jardunaldiak>();
		this.partiduak = new ArrayList<Partiduak>();
		this.taldeak = new ArrayList<Taldeak>();
	}
	
	public Jornadas(Denboraldiak denboraldiak, List<Jardunaldiak> jardunaldiak, List<Partiduak> partiduak, List<Taldeak> taldeak) {
		this.denboraldia = denboraldiak;
		this.jardunaldiak = jardunaldiak;
		this.partiduak = partiduak;
		this.taldeak = taldeak;
	}
	
	public Jornadas(List<Taldeak> taldeak) {
		Denboraldiak denboraldiDatua = new Denboraldiak();
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
    	//String hasieraData = urteaH + "-" + hilabeteaH + "-" + egunaH;
    	//String amaieraData = urteaA + "-" + hilabeteaA + "-" + egunaA;
    	String hasieraData = urteaH + "-" + hilabeteaH + "-" + egunaH;
    	String amaieraData = urteaA + "-" + hilabeteaA + "-" + egunaA;
    	denboraldiDatua.setDenboraldi_Izena(izena);
    	denboraldiDatua.setHasiera_data(hasieraData);
    	denboraldiDatua.setAmaiera_data(amaieraData);
    	
    	Taldeak deskantzua = new Taldeak();
    	if (taldeak.size() % 2 != 0) {
    		taldeak.add(deskantzua);
    		tieneDescanso = true;
    	}

    	List<Taldeak> listaTaldeak = new ArrayList<>(taldeak);
    	Taldeak equipoFijo = listaTaldeak.remove(0);  // ???
    	
    	// Puede dar error con el .size()
    	int counter = 0;
    	int counter2 = 0;
    	for (int i = 0; i < (taldeak.size() - 1); i++) {
    		String jardunaldiHasieraData = randomData();
    		String jardunaldiAmaieraData = randomData();
    		
    		Jardunaldiak jardunaldia = new Jardunaldiak(((counter + 1)  + ". Jardunaldia"), jardunaldiHasieraData, jardunaldiAmaieraData, denboraldia);
    		
    		for (int j = 0; j < taldeak.size() / 2; j++) {
                Taldeak etxekoa = (j == 0) ? equipoFijo : listaTaldeak.get(j - 1);
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
    		String jardunaldiHasieraData = randomData();
    		String jardunaldiAmaieraData = randomData();
    		
    		Jardunaldiak jardunaldia = new Jardunaldiak(((counter + 1)  + ". Jardunaldia"), jardunaldiHasieraData, jardunaldiAmaieraData, denboraldia);
    		
    		for (int j = 0; j < taldeak.size() / 2; j++) {
                Taldeak kanpokoa = (j == 0) ? equipoFijo : listaTaldeak.get(j - 1);
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
    	this.denboraldia = denboraldiDatua;
		this.jardunaldiak = jardunaldiakDatua;
		this.partiduak = partiduakDatua;
		this.taldeak = taldeak;
    	/*System.out.println("Número total de jornadas creadas: " + jardunaldiak.size());
    	for (Jardunaldiak j : jardunaldiak) {
    	    System.out.println(j.getJardunaldi_deskribapena() + " - Partidos: " + j.getPartidu_kopurua());
    	}*/
    	//System.out.println(jardunaldiak.size());
    	//System.out.println(partiduak.size());
	}
	
	public String randomData() {
		return "2012-06-12";
	}
	
	public void setDenboraldia(Denboraldiak denboraldia) {
		this.denboraldia = denboraldia;
	}
	
	public Denboraldiak getDenboraldia() {
		return this.denboraldia;
	}
	
	public void setJardunaldiak(List<Jardunaldiak> jardunaldiak) {
		this.jardunaldiak = jardunaldiak;
	}
	
	public void addJardunaldia (Jardunaldiak jardunaldia) {
		this.jardunaldiak.add(jardunaldia);
	}
	
	public List<Jardunaldiak> getJardunaldiak() {
		return this.jardunaldiak;
	}
	
	public void setPartiduak(List<Partiduak> partiduak) {
		this.partiduak = partiduak;
	}
	
	public void addPartiduak (Partiduak partidua) {
		this.partiduak.add(partidua);
	}
	
	public List<Partiduak> getPartiduak() {
		return this.partiduak;
	}
	
	// Behin denboraldi bat hasita, ezin dira taldeak aldatu
	public List<Taldeak> getTaldeak(){
		return this.taldeak;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jornadas implements Serializable{
	private Denboraldiak denboraldiak = new Denboraldiak();
    private List<Jardunaldiak> jardunaldiak;
    private List<Partiduak> partiduak;
    
    // Eraikitzaileak: 
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

    public void setDenboraldiak (Denboraldiak denboraldia) {
    	this.denboraldiak = denboraldia;
    }
    
    public Denboraldiak getDenboraldiak () {
    	return denboraldiak;
    }
    
    public void setJardunaldiak (List<Jardunaldiak> jardunaldiak) {
    	this.jardunaldiak = jardunaldiak;
    }
    
    public void addJardunaldiak (Jardunaldiak jardunaldia) {
    	jardunaldiak.add(jardunaldia);
    }
    
    public List<Jardunaldiak> getJardunaldiak () {
    	return jardunaldiak;
    }
    
    public void setPartiduak (List<Partiduak> partiduak) {
    	this.partiduak = partiduak;
    }
    
    public void addPartiduak (Partiduak partidua) {
    	partiduak.add(partidua);
    }
    
    public List<Partiduak> getPartiduak () {
    	return partiduak;
    }
}*/