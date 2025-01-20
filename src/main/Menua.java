package main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Menua extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnTaldeaSortu;
    private JButton btnPartiduakJolastu;
    private JButton btnKlasifikazioa;
    private JButton btnSaioaItxi;
    private JButton btnGenerarJornadas;
    private List<String> taldeak = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Menua frame = new Menua();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Menua() {
    	//TALDERIK BADAGO EDO EZ FITXATEGIAN
        taldeakIrakurri();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 578, 402);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnTaldeaSortu = new JButton("Taldeak sortu");
        btnTaldeaSortu.setBackground(Color.white);
        btnTaldeaSortu.setForeground(Color.BLACK);
        btnTaldeaSortu.addActionListener(this);
        btnTaldeaSortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnTaldeaSortu.setBounds(23, 108, 175, 46);
        contentPane.add(btnTaldeaSortu);

        btnPartiduakJolastu = new JButton("Partiduak jolastu");
        btnPartiduakJolastu.setBackground(Color.white);
        btnPartiduakJolastu.setForeground(Color.BLACK);
        btnPartiduakJolastu.addActionListener(this);
        btnPartiduakJolastu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnPartiduakJolastu.setBounds(360, 108, 175, 46);
        contentPane.add(btnPartiduakJolastu);

        btnKlasifikazioa = new JButton("Klasifikazioa");
        btnKlasifikazioa.setBackground(Color.white);
        btnKlasifikazioa.setForeground(Color.BLACK);
        btnKlasifikazioa.addActionListener(this);
        btnKlasifikazioa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnKlasifikazioa.setBounds(191, 208, 179, 46);
        contentPane.add(btnKlasifikazioa);

        btnSaioaItxi = new JButton("Saioa itxi");
        btnSaioaItxi.setBackground(Color.white);
        btnSaioaItxi.setForeground(Color.BLACK);
        btnSaioaItxi.addActionListener(this);
        btnSaioaItxi.setBounds(444, 315, 100, 29);
        contentPane.add(btnSaioaItxi);

        JLabel lblGoiBurua = new JLabel("Boleibol Federazioa");
        lblGoiBurua.setForeground(new Color(248, 248, 255));
        lblGoiBurua.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblGoiBurua.setHorizontalAlignment(SwingConstants.CENTER);
        lblGoiBurua.setBounds(171, 25, 229, 39);
        contentPane.add(lblGoiBurua);

        btnGenerarJornadas = new JButton("Jornadak sortu");
        if(taldeak.size() >= 6){
            botoiak_erakutsi(true);
        }else{
            botoiak_erakutsi(false);
        }
        btnGenerarJornadas.addActionListener(e -> {
            if (taldeak.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Gutxienez sei talde egon behar dira.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                List<List<String>> jornadas = Jornadas.generarJornadas(taldeak);
                StringBuilder resultado = new StringBuilder("Jornadas generadas:\n\n");
                for (int i = 0; i < jornadas.size(); i++) {
                    resultado.append("Jornada ").append(i + 1).append(":\n");
                    for (String partido : jornadas.get(i)) {
                        resultado.append("  - ").append(partido).append("\n");
                    }
                    resultado.append("\n");
                }
                JOptionPane.showMessageDialog(this, resultado.toString(), "Jornadas", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnGenerarJornadas.setBounds(200, 303, 150, 21);
        contentPane.add(btnGenerarJornadas);
        
    }
   
    private void taldeakIrakurri(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("taldeak.dat"))) {
        List<String> loadedTaldeak = (List<String>) ois.readObject();
        this.taldeak = loadedTaldeak;
        } catch (IOException | ClassNotFoundException e) {
       // Fitxategia ez badago edo irakurtzean errore bat badago, hutsik dagoen zerrenda abiaraziko dugu
        	this.taldeak = new ArrayList<>();
        }
    }

    public void botoiak_erakutsi(boolean erakutsi) {
        btnPartiduakJolastu.setVisible(erakutsi);
        btnKlasifikazioa.setVisible(erakutsi);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();

        if (o == btnTaldeaSortu) {
           TaldeakSortu JFrame = new TaldeakSortu(this);
           JFrame.setVisible(true);
           dispose();
        } else if (o == btnPartiduakJolastu) {
            JOptionPane.showMessageDialog(this, "Ez daude oraindik partiduak gordeta!", "Errorea", JOptionPane.WARNING_MESSAGE);
        } else if (o == btnKlasifikazioa) {
            JOptionPane.showMessageDialog(this, "Lehengo sartu behar dituzu datuak", "Errorea", JOptionPane.ERROR_MESSAGE);
        } else if (o == btnSaioaItxi) {
            System.exit(0);
        }
    }
    public void EguneratuTaldeak() {
        taldeak = TaldeenErabilpena.irakurriTaldeak();
        botoiak_erakutsi(taldeak.size() >= 6); //ez badaude talde naikorik ez da abiarazten botoiak
    }
    
}
