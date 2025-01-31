package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import java.util.List;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.swing.*;
import java.awt.*;

import objektuak.Taldeak;
import objektuak.Partiduak;
import objektuak.Jardunaldiak;

public class PartiduakJolastu extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnJugar, btnJardunaldiakErakutsi;
    private JTextField textTaldeA, textTaldeB;
    
    public PartiduakJolastu(Jornadas jornada) {
    	
    	// Partiduak Jolasteko (Partiduen emaitzak sartzeko) leioaren interfaze grafikoa sortzeko partea: 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        Menua menua = new Menua();
        JButton btnBueltatu = new JButton("Atzera");
        btnBueltatu.setBounds(150, 250, 100, 66);
        btnBueltatu.setVisible(true);
        contentPane.add(btnBueltatu);
        btnBueltatu.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Menua eguneratzeko = new Menua();
            eguneratzeko.EguneratuTaldeak();
            menua.setVisible(true);
            dispose();
					}             
        });
        
        JButton btnGordeDatuak = new JButton("GORDE");
        btnGordeDatuak.setBounds(300, 250, 222, 66);
        btnGordeDatuak.setVisible(true);
        contentPane.add(btnGordeDatuak);
        btnGordeDatuak.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Menua eguneratzeko = new Menua();
						eguneratzeko.EguneratuTaldeak();
						IrteeraSarreraXML.IrteeraXML(jornada); // Automatikoki sortu den Jornada berria erabili ahal izateko, beharrezkoa da gordetzea. Hau, XML moduan exportatzeko partea da. 
						menua.setVisible(true);
						dispose();
					}             
        });

        List<Taldeak> listaTaldeak = jornada.getTaldeak();
        List<Jardunaldiak> jardunaldiak = jornada.getJardunaldiak();
      	JPanel jardunaldiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      	jardunaldiPanel.setBounds(50, 0, 500, 400);
    		contentPane.add(jardunaldiPanel);
    		
    		List<Partiduak> partiduak = jornada.getPartiduak();
    		int primariCounter = 0;
    		int counter = 0;
    		System.out.println(partiduak.size());
    		System.out.println(jardunaldiak.size());
    		System.out.println(listaTaldeak.size());
    		
    	/*
    	 * Jornada baten jardunaldien eta jardunaldi horien partiduen arabera, emaitzak sartu nahi duzun partidua menu desplegableekin aukeratu ahal izateko partea automatikoki sartzeko kodea:
    	 * Hau lortzeko, Jardunaldi bakoitzerako botoi bat sortu egingo du eta, ondoren, botoi bakoitzera menu desplegablea sortzen du, non, jardunaldiaren partidu bakoita, JMenuItem moduan automatiko sartuko du
    	 * Partidu bat aukeratzean, partidu horren emaitzak sartzeko JOptionPane bat agertuko da, eta puntuak sartu ondoren,, partidu objetu horretan, partiduaren puntuazioa gehituko dio.
    	 */
    		
    			while (counter < partiduak.size() && primariCounter < jardunaldiak.size()) {
    				Jardunaldiak jardunaldia = jardunaldiak.get(primariCounter);
    				JButton btnJardunaldia = new JButton(jardunaldia.getJardunaldi_deskribapena());
      			jardunaldiPanel.add(btnJardunaldia);
    				JPopupMenu menu = new JPopupMenu();	
      			btnJardunaldia.addActionListener(new ActionListener() {
  						
  						@Override
  						public void actionPerformed(ActionEvent e) {
  							menu.show(btnJardunaldia, btnJardunaldia.getX() / 12, btnJardunaldia.getY() - btnJardunaldia.getHeight());
  						}
  					});
      			
    				for (int i = 0; i < (listaTaldeak.size()) / 2; i++) {
    					if (counter != partiduak.size()) {
    						Partiduak partidua = partiduak.get(counter);
    						Taldeak taldeA = partidua.getEtxeko_taldea();
    						Taldeak taldeB = partidua.getKanpoko_taldea();
    						JMenuItem partiduakMenu = new JMenuItem(taldeA.getIzena() + " VS " + taldeB.getIzena());
    						partiduakMenu.addActionListener(new ActionListener() {
    							
    							@Override
    							public void actionPerformed(ActionEvent e) {
    								puntuazioaSartu(partidua);
    							}
    						});
        				menu.add(partiduakMenu);
        				counter++;
    					}
    				}
    				primariCounter++;
    			}
    }
    
    public void puntuazioaSartu (Partiduak partidua) {
    	JPanel puntuazioPanela = new JPanel(new GridLayout(4, 1));
  		JLabel eskaeraTaldeA = new JLabel(partidua.getEtxeko_taldea().getIzena() + " taldearen puntuazioa sartu: ");
  		JTextField puntuazioaSartu1 = new JTextField();
  		JLabel eskaeraTaldeB = new JLabel(partidua.getKanpoko_taldea().getIzena() + " taldearen puntuazioa sartu: ");
  		JTextField puntuazioaSartu2 = new JTextField();
  		
  		puntuazioPanela.add(eskaeraTaldeA);
  		puntuazioPanela.add(puntuazioaSartu1);
  		puntuazioPanela.add(eskaeraTaldeB);
  		puntuazioPanela.add(puntuazioaSartu2);
  		
  		JOptionPane.showMessageDialog(null, puntuazioPanela, "Emaitzak Sartzeko", JOptionPane.OK_CANCEL_OPTION);
  		
  		String puntuazioaA = puntuazioaSartu1.getText();
  		String puntuazioaB = puntuazioaSartu2.getText();
  		if (puntuazioaA.isEmpty()) {
  			JOptionPane.showMessageDialog(null, (partidua.getEtxeko_taldea().getIzena() + " taldeko puntuazioa sartu behar da. Berriro saiatu mesedez"), "Errorea", JOptionPane.ERROR_MESSAGE);
  		}
  		else if (puntuazioaB.isEmpty()) {
  			JOptionPane.showMessageDialog(null, (partidua.getKanpoko_taldea().getIzena() + " taldeko puntuazioa sartu behar da. Berriro saiatu mesedez"), "Errorea", JOptionPane.ERROR_MESSAGE);
  		}
  		else {
  				int puntuakTaldeA = Integer.valueOf(puntuazioaA);
  				int puntuakTaldeB = Integer.valueOf(puntuazioaB);
  				
  				partidua.setEtxekoTaldekoPuntuazioa(puntuakTaldeA);
  				partidua.setKanpokoTaldekoPuntuazioa(puntuakTaldeB);
  				
  				IrteeraSarreraXML.LOG(partidua.getEtxeko_taldea().getIzena() + " vs " + partidua.getKanpoko_taldea().getIzena() + " partiduaren emaitzak egoki gorde egin dira... :)"); 
  				JOptionPane.showMessageDialog(null, "Puntuak ondo sartu egin dira :)", "Konfirmazioa", JOptionPane.INFORMATION_MESSAGE);
  		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	// Etorkizun baterako...
    }
}