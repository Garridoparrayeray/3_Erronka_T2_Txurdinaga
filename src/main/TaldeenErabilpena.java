package main;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TaldeenErabilpena {

    private static final String FILE_NAME = "taldeak.dat";

    // Irakurri taldeak artxibotik
    public static List<String> irakurriTaldeak() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (List<String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading teams: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }

    //Gorde taldeak artxiboan
    public static void gordeTaldeak(List<String> taldeak) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(taldeak);
        } catch (IOException e) {
            System.err.println("Error saving teams: " + e.getMessage());
        }
    }

    // Kendu eta berritu taldeak fitxategian
    public static void TaldeakKendu(List<String> taldeak) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(taldeak);
            JOptionPane.showMessageDialog(null, "Taldeak kendu dira eta fitxategia eguneratu da!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errorea taldeak eguneratzean", "Errorea", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error updating teams: " + ex.getMessage());
        }
    }
}
