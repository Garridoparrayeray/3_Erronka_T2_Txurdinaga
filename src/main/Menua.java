package main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import objektuak.Taldeak;
import objektuak.Denboraldiak;
import objektuak.Jardunaldiak;
import objektuak.Jokalariak;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Menua klasea aplikazioaren menu nagusia da, eta botoiak eta ekintza desberdinak kudeatzen ditu.
 * Bertan, taldeak sortzeko, jokalariak sortzeko, partiduak jokatzeko, klasifikazioa ikusteko eta saioa itzitzeko aukera dago.
 * 
 * @author Talde 4
 * @version 1.0
 */
public class Menua extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnTaldeaSortu, btnJokalariaSortu, btnPartiduakJolastu, btnKlasifikazioa, btnSaioaItxi, btnGenerarJornadas;
    private List<Taldeak> listaTaldea = TaldeenErabilpena.irakurriTaldeak();
    private List<Jokalariak> listaJokalariak;
    private Jornadas jornadaTenp;
    
    /**
     * Aplikazioa abiarazteko metodo nagusia.
     * Menua leiho nagusia sortzen du eta ikusgai jarri.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	System.out.println();
                Menua frame = new Menua();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Menua klasearen eraikitzailea. Leiho nagusia sortzen du eta botoiak eta ekintzak hasieratzen ditu.
     */
    public Menua() {
    		IrteeraSarreraXML.LOG("Aplikazioan satu da");
    		// .dat fitxategietatik, gorde diren taldeak eta jokalariak irakurtzen ditu
        listaTaldea = TaldeenErabilpena.irakurriTaldeak();
        listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
        
        // Aplikazioaren hasierako leioaren interfaze grafikoa sortzeko partea: 
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
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnGenerarJornadas.setBounds(200, 303, 150, 21);
        contentPane.add(btnGenerarJornadas);
        
        EguneratuTaldeak();
        
    }

    /**
     * Egin behar diren botoiak erakutsi edo ez erakusteko metodoa.
     * 
     * @param erakutsi Botoiak erakutsi edo ez erabakitzen duen balioa.
     */
    public void botoiak_erakutsi(boolean erakutsi) {
        btnPartiduakJolastu.setVisible(erakutsi);
        btnKlasifikazioa.setVisible(erakutsi);
    }

    /**
     * Menuan dauden botoien ekintzak kudeatzen dituen metodoa.
     * Botoiak sakatzen direnean, aplikazioaren atal desberdinetara sartzen da.
     * 
     * @param ae Ekintza bakoitzaren gertakaria.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();

        if (o == btnTaldeaSortu) {
        	IrteeraSarreraXML.LOG("Taldeak Sortzeko atalean sartu da");
           TaldeakSortu JFrame = new TaldeakSortu(this);
           JFrame.setVisible(true);
           dispose();
           
        } else if (o == btnPartiduakJolastu) {
        	IrteeraSarreraXML.LOG("Partiduak Jolasteko atalean sartu da");
          Jornadas jornada = IrteeraSarreraXML.SarreraXML(); // Emaitzak sartu ahal diren partiduak aukeratzeko atala sortu ahal izateko. Jornadas-klase baten barruan dagoen informazioa behar du. Informazio hori XML-fitxategi batetik aterako da.
          jornadaTenp = jornada;
          PartiduakJolastu JFrameXML = new PartiduakJolastu(jornada);
        	JFrameXML.setVisible(true);
           dispose();
           
        } else if (o == btnKlasifikazioa) {
        	IrteeraSarreraXML.LOG("Klasifikazioa ikusteko atalean sartu da");
        	Jornadas jardunaldia = IrteeraSarreraXML.SarreraXML(); // Klasifikazioa egiteko, Jornadas-klase baten barruan dagoen informazioa behar du. Informazio hori XML-fitxategi batetik aterako da.
            Klasifikazioa JFrameKlasifikazioa = new Klasifikazioa(jardunaldia);
            JFrameKlasifikazioa.setVisible(true);
            dispose();
            
        } else if (o == btnSaioaItxi) {
        		IrteeraSarreraXML.LOG("Aplikaziotik atera da");
            System.exit(0);
        }
        else if (o == btnJokalariaSortu) {
        	JokalariaSortu JFrame2 = new JokalariaSortu(this);
          JFrame2.setVisible(true);
          dispose();
        }
    }
    
    /**
     * Taldeak eguneratzen dituen metodoa. Taldeak irakurtzen ditu eta botoiak bistaratzen ditu.
     */
    public void EguneratuTaldeak() {
    	IrteeraSarreraXML.LOG("Taldeak eguneratu egin dira");
    		listaTaldea = TaldeenErabilpena.irakurriTaldeak();
        botoiak_erakutsi(listaTaldea.size() >= 6); //Talde naikorik ez baldin badaude, ez da abiarazten botoiak
        
        btnJokalariaSortu.setVisible(listaTaldea.size() >= 1 ? true : false);
    }
    
    /**
     * Jokalariak eguneratzen dituen metodoa. Jokalariak irakurtzen ditu.
     */
    public void EguneratuJokalariak() {
    	IrteeraSarreraXML.LOG("Jokalariak eguneratu egin dira");
  		listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
  }
    
}