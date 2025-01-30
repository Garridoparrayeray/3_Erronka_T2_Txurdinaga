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
    
    public PartiduakJolastu() {
        // Configuración de la ventana principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        List<Taldeak> listaTaldeak = TaldeenErabilpena.irakurriTaldeak();
        
        List<Jornadas> listaJornadas = TaldeenErabilpena.jardunaldiakIrakurri();
  			Jornadas jornada = listaJornadas.get(listaJornadas.size() - 1);
        
        List<Jardunaldiak> jardunaldiak = jornada.getJardunaldiak();
      	JPanel jardunaldiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      	jardunaldiPanel.setBounds(50, 0, 500, 400);
    		contentPane.add(jardunaldiPanel);
    		
    		List<Partiduak> partiduak = jornada.getPartiduak();
    		int primariCounter = 0;
    		int counter = 0;
    		System.out.println(partiduak.size());
    		System.out.println(jardunaldiak.size());
    			while (counter < partiduak.size() - 1 || primariCounter < jardunaldiak.size() - 1) {
    				Jardunaldiak jardunaldia = jardunaldiak.get(primariCounter);
    				JButton btnJardunaldia = new JButton(jardunaldia.getJardunaldi_deskribapena());
      			jardunaldiPanel.add(btnJardunaldia);
    				JPopupMenu menu = new JPopupMenu();	
        		//menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
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
        				JMenuItem partiduakMenu = new JMenuItem(taldeA + " VS " + taldeB);
        				partiduakMenu.addActionListener(new ActionListener() {
    							
    							@Override
    							public void actionPerformed(ActionEvent e) {
    								puntuazioaSartu(partiduakMenu, taldeA.getIzena(), taldeB.getIzena());
    							}
    						});
        				menu.add(partiduakMenu);
        				counter++;
    					}
    				}
    				primariCounter++;
    			}
    }
    
    public void puntuazioaSartu (JMenuItem partidua, String taldeA, String taldeB) {
    	JPanel puntuazioPanela = new JPanel(new GridLayout(4, 1));
  		JLabel eskaeraTaldeA = new JLabel(taldeA + " taldearen puntuazioa sartu: ");
  		JTextField puntuazioaSartu1 = new JTextField();
  		JLabel eskaeraTaldeB = new JLabel(taldeB + " taldearen puntuazioa sartu: ");
  		JTextField puntuazioaSartu2 = new JTextField();
  		
  		puntuazioPanela.add(eskaeraTaldeA);
  		puntuazioPanela.add(puntuazioaSartu1);
  		puntuazioPanela.add(eskaeraTaldeB);
  		puntuazioPanela.add(puntuazioaSartu2);
  		
  		JOptionPane.showMessageDialog(null, puntuazioPanela, "Emaitzak Sartzeko", JOptionPane.OK_CANCEL_OPTION);
  		
  		String puntuazioaA = puntuazioaSartu1.getText();
  		String puntuazioaB = puntuazioaSartu2.getText();
  		if (puntuazioaA.isEmpty()) {
  			JOptionPane.showMessageDialog(null, (taldeA + " taldeko puntuazioa sartu behar da. Berriro saiatu mesedez"), "Errorea", JOptionPane.ERROR_MESSAGE);
  		}
  		else if (puntuazioaB.isEmpty()) {
  			JOptionPane.showMessageDialog(null, (taldeB + " taldeko puntuazioa sartu behar da. Berriro saiatu mesedez"), "Errorea", JOptionPane.ERROR_MESSAGE);
  		}
  		else {
  			boolean ezDiraZenbakiak = false;
  			char c;
  			for (int i = 0; i < puntuazioaA.length(); i++) {
  				c = puntuazioaA.charAt(i);
  				if (!Character.isDigit(c)) {
  					ezDiraZenbakiak = true;
  				}
  			}
  			for (int i2 = 0; i2 < puntuazioaB.length(); i2++) {
  				c = puntuazioaA.charAt(i2);
  				if (!Character.isDigit(c)) {
  					ezDiraZenbakiak = true;
  				}
  			}
  			if (ezDiraZenbakiak == false) {
  				
  			}
  			else {
  				JOptionPane.showMessageDialog(null, "Puntuazioko datuak, zenbaki osoak izatea derrigorrezkoa da. Saiatu berriro mesedez");
  			}
  		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
        // Acción cuando se hace clic en el botón "Jugar"
       /*  if (e.getSource() == btnJugar) {
        	String taldea1 = textTaldeA.getText();
        	String taldea2 = textTaldeB.getText();
        	int counter = 0;
        	
         if (!taldea1.isEmpty() && !taldea2.isEmpty()) {
        	 for (Taldeak taldea : listaTaldeak) {
        		 if (taldea1.equals(taldea.getIzena())) {
        			 counter++;
        		 }
        		 if (taldea2.equals(taldea.getIzena())) {
        			 counter++;
        		 }
        	 }
        	 
        	 if (counter == 2) {
        		 JPanel datuakSartu = new JPanel(new GridLayout(4, 1));
        		 JLabel eskaera1 = new JLabel(taldea1 + " taldearen puntuazioa sartu: ");
        		 JTextField puntuazioaSartu1 = new JTextField();
        		 JLabel eskaera2 = new JLabel(taldea2 + " taldearen puntuazioa sartu: ");
        		 JTextField puntuazioaSartu2= new JTextField();
        		 
        		 datuakSartu.add(eskaera1);
        		 datuakSartu.add(puntuazioaSartu1);
        		 datuakSartu.add(eskaera2);
        		 datuakSartu.add(puntuazioaSartu2);
        		 JOptionPane.showMessageDialog(null, datuakSartu, "Emaitzak Sartzeko", JOptionPane.OK_CANCEL_OPTION);
        		 
        		 boolean interruptorea = false;
        		 String puntuazioa1 = puntuazioaSartu1.getText().trim();
        		 String puntuazioa2 = puntuazioaSartu2.getText().trim();
        		 char c;
        		 
        		 for (int i = 0; i < puntuazioa1.length(); i++) {
        			 c = puntuazioa1.charAt(i);
        			 if (!Character.isDigit(c)) {
        				 interruptorea = true;
        			 }
        		 }
        		 for (int i2 = 0; i2 < puntuazioa2.length(); i2++) {
        			 c = puntuazioa1.charAt(i2);
        			 if (!Character.isDigit(c)) {
        				 interruptorea = true;
        			 }
        		 }
        		 
        		 if (interruptorea == false) {
        			 
        		 }
        		 else {
        			 JOptionPane.showMessageDialog(null, "Puntuazioan, bakarri zenbakiak sartu ahal dira. Berriro saiatu mesedez", "Errorea", JOptionPane.ERROR_MESSAGE);
        		 }
        	 }
        	 else {
        		 
        	 }
         }
         else {
        	 if (taldea1.isEmpty()) {
        		 JOptionPane.showMessageDialog(null, "A taldearen izenaren eremua utxik dago. A taldearen izena sartu eta berriro saiatu mesedez.", "Errorea", JOptionPane.ERROR_MESSAGE);
        	 }
        	 else {
        		 JOptionPane.showMessageDialog(null, "B taldearen izenaren eremua utxik dago. B taldearen izena sartu eta berriro saiatu mesedez.", "Errorea", JOptionPane.ERROR_MESSAGE);
        	 }
         }
        } 
        else if (e.getSource() == btnJardunaldiakErakutsi) {
        	List<List<String>> jardunaldiIrakurria = TaldeenErabilpena.jardunaldiakIrakurri();
        	VentanaJornadas JFrameJardunaldia = new VentanaJornadas(jardunaldiIrakurria);
        	JFrameJardunaldia.setVisible(true);
        }*/
    }
}