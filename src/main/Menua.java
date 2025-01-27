package main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import objektuak.Taldeak;
import objektuak.Jardunaldiak;
import objektuak.Jokalariak;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Menua extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnTaldeaSortu, btnJokalariaSortu, btnPartiduakJolastu, btnKlasifikazioa, btnSaioaItxi, btnGenerarJornadas;
    private List<Taldeak> listaTaldea = TaldeenErabilpena.irakurriTaldeak();
    private List<Jokalariak> listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
    
    private List<Jornadas> listaJardunaldiak = new ArrayList<Jornadas>();

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
        listaTaldea = TaldeenErabilpena.irakurriTaldeak();
        listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
        
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
        
        btnJokalariaSortu = new JButton("Jokalariak sortu");
        btnJokalariaSortu.setBackground(Color.white);
        btnJokalariaSortu.setForeground(Color.BLACK);
        btnJokalariaSortu.addActionListener(this);
        btnJokalariaSortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnJokalariaSortu.setBounds(23, 208, 175, 46);
        contentPane.add(btnJokalariaSortu);

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
        btnKlasifikazioa.setBounds(360, 208, 179, 46);
        contentPane.add(btnKlasifikazioa);

        btnSaioaItxi = new JButton("Saioa itxi");
        btnSaioaItxi.setBackground(Color.white);
        btnSaioaItxi.setForeground(Color.BLACK);
        btnSaioaItxi.addActionListener(this);
        btnSaioaItxi.setBounds(444, 315, 100, 29);
        contentPane.add(btnSaioaItxi);

        JLabel lblGoiBurua = new JLabel("Boleibol Federazioa");
        lblGoiBurua.setForeground(Color.BLACK);
        lblGoiBurua.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblGoiBurua.setHorizontalAlignment(SwingConstants.CENTER);
        lblGoiBurua.setBounds(171, 25, 229, 39);
        contentPane.add(lblGoiBurua);

        btnGenerarJornadas = new JButton("Jornadak sortu");
        if(listaTaldea.size() >= 6){
            botoiak_erakutsi(true);
        }else{
            botoiak_erakutsi(false);
        }
        btnGenerarJornadas.addActionListener(e -> {
            if (listaTaldea.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Gutxienez sei talde egon behar dira.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
            	Jornadas jardunaldia = new Jornadas(listaTaldea);
            	VentanaJornadas JFrameJoranadas = new VentanaJornadas(jardunaldia);
            	JFrameJoranadas.setVisible(true);
                /*StringBuilder resultado = new StringBuilder("Jornadas generadas:\n\n");
                for (int i = 0; i < jornadas.size(); i++) {
                    resultado.append("Jornada ").append(i + 1).append(":\n");
                    for (String partido : jornadas.get(i)) {
                        resultado.append("  - ").append(partido).append("\n");
                    }
                    resultado.append("\n");
                }*/
                //JOptionPane.showMessageDialog(this, resultado.toString(), "Jornadas", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnGenerarJornadas.setBounds(200, 303, 150, 21);
        contentPane.add(btnGenerarJornadas);
        
        EguneratuTaldeak();
        
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
            PartiduakJolastu JFrame3 = new PartiduakJolastu();
            JFrame3.setVisible(true);
            dispose();
        } else if (o == btnKlasifikazioa) {
            JOptionPane.showMessageDialog(this, "Lehengo sartu behar dituzu datuak", "Errorea", JOptionPane.ERROR_MESSAGE);
        } else if (o == btnSaioaItxi) {
            System.exit(0);
        }
        else if (o == btnJokalariaSortu) {
        	JokalariaSortu JFrame2 = new JokalariaSortu(this);
          JFrame2.setVisible(true);
          dispose();
        }
    }
    public void EguneratuTaldeak() {
    		listaTaldea = TaldeenErabilpena.irakurriTaldeak();
        botoiak_erakutsi(listaTaldea.size() >= 6); //ez badaude talde naikorik ez da abiarazten botoiak
        
        btnJokalariaSortu.setVisible(listaTaldea.size() >= 1 ? true : false);
    }
    
    public void EguneratuJokalariak() {
  		listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
  }
    
}
