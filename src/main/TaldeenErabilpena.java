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
    
    public static List<List<String>> jardunaldiakIrakurri() {
    	File file3 = new File(jardunaldiakFile);
    	if (file3.exists()) {
    		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file3))) {
    			return (List<List<String>>) ois.readObject();
    		} catch (IOException | ClassNotFoundException e) {
    			JOptionPane.showMessageDialog(null, "Jardunaldiak irakurtzeko orduan errorea eman du.", "Errorea", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	return new ArrayList<List<String>>();
    }
    
    public static void jardunaldiakGorde(List<List<String>> jardunaldiak) {
    	File file3 = new File(jardunaldiakFile);
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file3))) {
    		oos.writeObject(jardunaldiak);
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, "Jardunaldiak gordetzeko orduan errorea eman du.", "Errorea", JOptionPane.ERROR_MESSAGE);
    	}
    }

    public static  List<Partiduak> partiduakIrakurri() {
    	return new ArrayList<Partiduak>();
    }
    public static void partiduakGorde() {
    	
    }
    
    public static List<Denboraldiak> denboraldiakIrakurri() {
    	return new ArrayList<Denboraldiak>();
    }
    public static void denboraldiakGorde() {
    	
    }
    
}
