package main;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import objektuak.*;

/**
 * TaldeenErabilpena klaseak taldeen, jokalarien eta jardunaldien datuak artxibotik irakurri, gorde eta eguneratzeko funtzioak eskaintzen ditu.
 * Fitxategiak erabiltzen ditu datuak gordetzeko eta irakurtzeko.
 */
public class TaldeenErabilpena {

    private static final String FILE_NAME = "taldeak.dat";
    private static final String jokalariaFile = "jokalariak.dat";
    private static final String jardunaldiakFile  = "jardunaldiak.dat";

    /**
     * Taldeak irakurtzen ditu "taldeak.dat" fitxategitik.
     * 
     * @return Taldeen zerrenda bat. Fitxategia ez badago edo irakurtzeko errore bat gertatzen bada, zerrenda huts bat itzultzen du.
     */
    // Irakurri taldeak .dat fitxategitik
    public static List<Taldeak> irakurriTaldeak() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {            	
            	return (List<Taldeak>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
            	JOptionPane.showMessageDialog(null, "Aukeratu taldea kendu nahi .", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Taldeak "taldeak.dat" fitxategian gordetzen ditu.
     * 
     * @param taldeak Talde guztien zerrenda.
     */
    //Gorde taldeak .dat fitxategian
    public static void gordeTaldeak(List<Taldeak> taldeak) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(taldeak);
        } catch (IOException e) {
            System.err.println("Error saving teams: " + e.getMessage());
        }
    }

    /**
     * Taldea "taldeak.dat" fitxategitik ezabatu eta taldeen zerrenda eguneratzen du.
     * 
     * @param taldeak Taldeen zerrenda.
     * @param taldea Ezabatu nahi den taldea.
     */
    // Kendu eta berritu taldeak .dat fitxategian
    public static void TaldeakKendu(List<Taldeak> taldeak, Taldeak taldea) {
    	taldeak.remove(taldea);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(taldeak);
            JOptionPane.showMessageDialog(null, "Taldeak kendu dira eta fitxategia eguneratu da!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errorea taldeak eguneratzean", "Errorea", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error updating teams: " + ex.getMessage());
        }
    }
    
    /**
     * Jokalariak irakurtzen ditu "jokalariak.dat" fitxategitik.
     * 
     * @return Jokalari guztien zerrenda. Fitxategia ez bada aurkitzen edo errore bat gertatzen bada, zerrenda huts bat itzultzen du.
     */
    // Irakurri Jokalariak .dat fitxategitik
    public static List<Jokalariak> irakurriJokalariak() {
        File file2 = new File(jokalariaFile);
        if (file2.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file2))) {            	
            	return (List<Jokalariak>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
            	JOptionPane.showMessageDialog(null, "Aukeratu taldea kendu nahi .", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Jokalariak "jokalariak.dat" fitxategian gordetzen ditu.
     * 
     * @param jokalariak Jokalari guztien zerrenda.
     */
    //Gorde jokalariak .dat fitxategian
    public static void gordeJokalariak(List<Jokalariak> jokalariak) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jokalariaFile))) {
            oos.writeObject(jokalariak);
        } catch (IOException e) {
            System.err.println("Error saving jokalariak: " + e.getMessage());
        }
    }

    /**
     * Jokalaria "jokalariak.dat" fitxategitik ezabatu eta jokalarien zerrenda eguneratzen du.
     * 
     * @param jokalariak Jokalari guztien zerrenda.
     * @param jokalaria Ezabatu nahi den jokalaria.
     */
    // Kendu eta berritu jokalariak .dat fitxategian
    public static void JokalariakKendu(List<Jokalariak> jokalariak, Jokalariak jokalaria) {
    	jokalariak.remove(jokalaria);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jokalariaFile))) {
            oos.writeObject(jokalariak);
            JOptionPane.showMessageDialog(null, "Taldeak kendu dira eta fitxategia eguneratu da!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errorea taldeak eguneratzean", "Errorea", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error updating teams: " + ex.getMessage());
        }
    }
    
    /**
     * Jardunaldiak irakurtzen ditu "jardunaldiak.dat" fitxategitik.
     * 
     * @return Jardunaldi guztien zerrenda. Fitxategia ez bada aurkitzen edo irakurtzeko errore bat gertatzen bada, zerrenda huts bat itzultzen du.
     */
    // Jardunaldiak irakurtzeko
    public static List<Jornadas> jardunaldiakIrakurri() {
    	File file3 = new File(jardunaldiakFile);
      if (file3.exists()) {
          try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file3))) {            	
          	return (List<Jornadas>) ois.readObject();
          } catch (IOException | ClassNotFoundException e) {
          	JOptionPane.showMessageDialog(null, "Jardunaldiak irakurtzeko orduan errorea ematen du. Berriro saiatu mesedez..", "Jardunaldiak Irakurtzeko Errorrea", JOptionPane.ERROR_MESSAGE);
          }
      }
    	return new ArrayList<Jornadas>();
    }
    
    /**
     * Jardunaldiak "jardunaldiak.dat" fitxategian gordetzen ditu.
     * 
     * @param jardunaldiak Jardunaldi guztien zerrenda.
     */
    // Jardunaldiak gordetzeko
    public static void jardunaldiakGorde(List<Jornadas> jardunaldiak) {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jardunaldiakFile))) {
    		oos.writeObject(jardunaldiak);
    	} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errorea jardunaldiak gordetzeko orduan. Berriro saiatu mesedez.", "Jardunaldiak Gordetzean Errorea", JOptionPane.ERROR_MESSAGE);
			}
    }
}