package main;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import objektuak.*;

public class TaldeenErabilpena {

    private static final String FILE_NAME = "taldeak.dat";
    private static final String jokalariaFile = "jokalariak.dat";
    private static final String jardunaldiakFile  = "jardunaldiak.dat";

    // Irakurri taldeak artxibotik
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

    //Gorde taldeak artxiboan
    public static void gordeTaldeak(List<Taldeak> taldeak) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(taldeak);
        } catch (IOException e) {
            System.err.println("Error saving teams: " + e.getMessage());
        }
    }

    // Kendu eta berritu taldeak fitxategian
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
    
    // Irakurri Jokalariak artxibotik
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

    //Gorde jokalariak artxiboan
    public static void gordeJokalariak(List<Jokalariak> jokalariak) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jokalariaFile))) {
            oos.writeObject(jokalariak);
        } catch (IOException e) {
            System.err.println("Error saving jokalariak: " + e.getMessage());
        }
    }

    // Kendu eta berritu jokalariak fitxategian
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
    
    public static void jardunaldiakGorde(List<Jornadas> jardunaldiak) {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jardunaldiakFile))) {
    		oos.writeObject(jardunaldiak);
    	} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errorea jardunaldiak gordetzeko orduan. Berriro saiatu mesedez.", "Jardunaldiak Gordetzean Errorea", JOptionPane.ERROR_MESSAGE);
			}
    }
}
