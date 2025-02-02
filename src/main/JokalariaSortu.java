package main;
import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import objektuak.Taldeak;
import objektuak.Jokalariak;
import objektuak.Pertsona;
import main.Menua;

/**
 * Jokalariak sortzeko eta kudeatzeko leihoa.
 * Jokalari berriak gehitzeko, ezabatzeko eta aldaketak gordetzeko aukera ematen du.
 */
public class JokalariaSortu extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textJokalaria, textJokalariKodea, textJokalariaAbizena, textJokalariaRola, textTaldeKodea;
    private JButton btnGordeIzenak, btnKenduJokalaria, btnGordeAldaketak;
    private DefaultTableModel tableModel;
    private JTable tableJokalariak;
    private Menua menua;
    
    private List<Jokalariak> listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
    private List<Taldeak> listaTaldeak = TaldeenErabilpena.irakurriTaldeak();

    /**
     * Jokalariak sortzeko leihoa sortzen du.
     * @param menua Menu nagusia eguneratzeko erreferentzia.
     */
    public JokalariaSortu(Menua menua) {
    		// JokalariakSortu leioaren interfaze grafikoa sortzeko: 
        this.menua = menua;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 544, 384);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTaldeakSortu = new JLabel("Jokalariak Sortu");
        lblTaldeakSortu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTaldeakSortu.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblTaldeakSortu.setBounds(153, 20, 210, 37);
        contentPane.add(lblTaldeakSortu);

        JLabel lblJokalariKodea = new JLabel("Jokalariaren Kodea:");
        lblJokalariKodea.setBounds(21, 60, 160, 23);
        contentPane.add(lblJokalariKodea);

        textJokalariKodea = new JTextField();
        textJokalariKodea.setBounds(21, 80, 223, 19);
        contentPane.add(textJokalariKodea);
        textJokalariKodea.setColumns(10);
        
        JLabel lblJokalariIzena = new JLabel("Jokalariaren izena:");
        lblJokalariIzena.setBounds(21, 100, 140, 23);
        contentPane.add(lblJokalariIzena);

        textJokalaria = new JTextField();
        textJokalaria.setBounds(21, 120, 223, 19);
        contentPane.add(textJokalaria);
        textJokalaria.setColumns(10);
        
        JLabel lblJokalariaAbizena = new JLabel("Jokalariaren Abizena:");
        lblJokalariaAbizena.setBounds(21, 140, 140, 23);
        contentPane.add(lblJokalariaAbizena);

        textJokalariaAbizena = new JTextField();
        textJokalariaAbizena.setBounds(21, 160, 223, 19);
        contentPane.add(textJokalariaAbizena);
        textJokalariaAbizena.setColumns(10);
        
        JLabel lblJokalariaRola = new JLabel("Jokalariaren Rola:");
        lblJokalariaRola.setBounds(21, 180, 140, 23);
        contentPane.add(lblJokalariaRola);

        textJokalariaRola = new JTextField();
        textJokalariaRola.setBounds(21, 200, 223, 19);
        contentPane.add(textJokalariaRola);
        textJokalariaRola.setColumns(10);
        
        JLabel lblTaldeKodea = new JLabel("Talde Kodea:");
        lblTaldeKodea.setBounds(21, 220, 140, 23);
        contentPane.add(lblTaldeKodea);

        textTaldeKodea = new JTextField();
        textTaldeKodea.setBounds(21, 240, 223, 19);
        contentPane.add(textTaldeKodea);
        textTaldeKodea.setColumns(10);

        btnGordeIzenak = new JButton("Gorde Jokalaria");
        btnGordeIzenak.addActionListener(this);
        btnGordeIzenak.setBounds(21, 266, 132, 21);
        contentPane.add(btnGordeIzenak);

        tableModel = new DefaultTableModel(new String[]{"Jokalaria"}, 0);
        tableJokalariak = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableJokalariak);
        scrollPane.setBounds(324, 70, 196, 223);
        contentPane.add(scrollPane);

        btnKenduJokalaria = new JButton("Kendu jokalaria");
        btnKenduJokalaria.addActionListener(this);
        btnKenduJokalaria.setBounds(388, 303, 132, 21);
        contentPane.add(btnKenduJokalaria);

        btnGordeAldaketak = new JButton("Gorde aldaketak");
        btnGordeAldaketak.addActionListener(this);
        btnGordeAldaketak.setBounds(21, 303, 132, 21);
        contentPane.add(btnGordeAldaketak);

        JButton btnAtzera = new JButton("Atzera");
        btnAtzera.addActionListener(e -> {
            if (menua != null) {
                menua.EguneratuTaldeak(); // Taldeak eguneratzen ditu
            }
            Menua eguneratzeko = new Menua();
            eguneratzeko.EguneratuTaldeak();
            menua.setVisible(true);
            dispose();
        });
        btnAtzera.setBounds(222, 303, 85, 21);
        contentPane.add(btnAtzera);

        // jokalariak.dat fitxategian gordeta dauden jokalarien informazioa, taulan automatikoki jartzeko partea: 
        List<Jokalariak> jokalariak = TaldeenErabilpena.irakurriJokalariak();
        for (Jokalariak jokalaria : jokalariak) {
            tableModel.addRow(new Object[]{jokalaria.getJokalarikod() + " " +  jokalaria.getIzena() + " " + jokalaria.getAbizena() + " " + jokalaria.getJokalariRola()});
        }
    }
    
    /**
     * Ekintza bat exekutatzen du botoi bati sakatzen zaionean.
     * @param e Ekintza gertakaria.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnGordeIzenak) {
        	int jokalariKod = Integer.valueOf(textJokalariKodea.getText());
        	String izena = textJokalaria.getText();
        	String abizena = textJokalariaAbizena.getText();
        	String jokalariRola = textJokalariaRola.getText();
        	int taldeKod = Integer.valueOf(textTaldeKodea.getText());
        	boolean errepikatu = true;
          
        	if (jokalariKod <= 0) {
        	  JOptionPane.showMessageDialog(null, "Jokalari kodea ezin da negatiboa izan", "Errorea Jokalari kodean", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (izena.isEmpty()) {
        	  JOptionPane.showMessageDialog(null, "Izena ezin da hutsik egon", "Errorea izenan", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (abizena.isEmpty()) {
        	  JOptionPane.showMessageDialog(null, "Abizena ezin da hutsik egon", "Errorea Abizenan", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (jokalariRola.isEmpty()) {
        	  JOptionPane.showMessageDialog(null, "Jokalari rola ezin da hutsik egon", "Errorea izenan", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (taldeKod <= 0) {
        	  JOptionPane.showMessageDialog(null, "Talde kodea ezin da negatiboa izan", "Errorea Talde kodean", JOptionPane.ERROR_MESSAGE);
        	}
          
          /*
           * Parte honetan, aldagai berdina erabiltzen da, sartu den talde kodea existitzen dela eta sartutako jokalari_kod errepikatuta ez egotea egiaztatzeko erabiltzen da 
           * Jokalaria sortu ahal izateko, errepikatu aldagaiaren balioa, false izan behar da. Defektuz true izango da. Hau, da, aldatu egin beharko da.
          */
          for (Taldeak taldea : listaTaldeak) {  
          	if (taldea.getTaldekod() == taldeKod) { // Lehenengo, sartu den talde_kod-a, aplikazioan gordeta dauden talde bateko izatea egiaztatzen du. Taldea existitzen bada, errepikatu aldagaia false balioa hartuko du
          		errepikatu = false;
          	}
          }
          for (Jokalariak jokalaria : listaJokalariak) { // Ondoren, sartutako jokalari_kod, aplikazioan gordeta dauden beste jokalari batek, jokalari_kod berdina ez izatea egiaztatzen du. Sartutako jokalari_kod, aplikazioan gordeta dagoen beste jokalari baten jokalari_kod-arekin berdina badute, errepikatu aldagaia true balioa berriro hartuko du. Bestela, false balioarekin mantenduko da.
        		if (jokalaria.getJokalarikod() == jokalariKod) {
        			errepikatu = true;
        		}
        	}
          if (errepikatu == false) {
          	if (izena.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Ez duzu talderik sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
          } else if (isDuplicate(izena)) {
              JOptionPane.showMessageDialog(this, "Jokalari hau dagoeneko gehituta dago", "Errorea", JOptionPane.WARNING_MESSAGE);
          } else {
          		Jokalariak jokalaria = new Jokalariak(jokalariKod, izena, abizena, jokalariRola, taldeKod);
          		tableModel.addRow(new Object[]{jokalariKod + " " + izena + " " +  abizena + " " +  jokalariRola + " " +  taldeKod});
          		textJokalariKodea.setText("");
          		textJokalaria.setText("");
          		textJokalariaAbizena.setText("");
          		textJokalariaRola.setText("");
          		textTaldeKodea.setText("");
          		listaJokalariak.add(jokalaria);
          		IrteeraSarreraXML.LOG(jokalaria.getIzena() + " jokalaria sortu egin da"); 
          }
          }
          else {
          	JOptionPane.showMessageDialog(null, "Jokalaria ez da existitzen edo jokalari kodea errepikatuta dago. Berriro saiatu mesedez...", "Errorea", JOptionPane.ERROR_MESSAGE);
          }
        } else if (source == btnKenduJokalaria) {  
        	
          int[] selectedRows = tableJokalariak.getSelectedRows(); 
          /*
           * Jokalaria kentzeko hiru kasu begiratzen ditu. Jokalariak aukeratu ez badituzu, aukeratu duzun jokalaria, jokalariak.dat fitxategian oraindik gorde ez badzu eta, aukeratu duzun jokalaria, jokalariak.dat fitxategian gordeta badago
           * Jokalaria bat aukeratu ez baduzu, abisatzeko mesu bat agertuko da.
           * Aukeratu duzun jokalaria, jokalariak.dat-ean oraindik ez baduzu gorde, taulatik ezabatuko du bakarrik, jokalariak.dat eguneratu gabe.
           * Aukeratu duzun jokalariak, jokalariak.dat-ean gordeta badago, aurreko kasuaren berdina egingo du, baina kasu honetan, bai eguneratuko du jokalariak.dat, aukeratu duzun jokalaria, fitxategi horretatik ezabatzeko ere. 
           */
          
          if (selectedRows.length == 0) {
              JOptionPane.showMessageDialog(this, "Aukeratu jokalari bat kendu nahi baduzu.", "Errorea", JOptionPane.ERROR_MESSAGE);
          } else {
              for (int i = 0; i < selectedRows.length; i++) {
                  String jugadorData = tableModel.getValueAt(selectedRows[i], 0).toString();
                  int jokalariKod = 0;
                  String zenbakiaAteratzen = "";
                  
                  for (int j = 0; j < jugadorData.length(); j++) {
                      char c = jugadorData.charAt(j);
                      if (Character.isDigit(c)) {
                          zenbakiaAteratzen += c;
                      } else if (!Character.isDigit(c) && !zenbakiaAteratzen.isEmpty()) {
                          jokalariKod = Integer.parseInt(zenbakiaAteratzen);
                          break;
                      }
                  }

                  for (int j = 0; j < listaJokalariak.size(); j++) {
                      if (listaJokalariak.get(j).getJokalarikod() == jokalariKod) {
                          TaldeenErabilpena.JokalariakKendu(listaJokalariak, listaJokalariak.get(j));
                          tableModel.removeRow(selectedRows[i] - i);
                          break;
                      }
                  }
              }
              IrteeraSarreraXML.LOG("Sortutako jokalari bat ezabatu egin da"); 
              JOptionPane.showMessageDialog(this, "Jokalaria kendu da eta fitxategia eguneratu da!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
          }
      }
        else if (source == btnGordeAldaketak) {
        	// Sortu dituzun jokalariak, jokalariak.dat fitxategian gordetzeko atala:
            if (tableModel.getRowCount() < 0) {
                JOptionPane.showMessageDialog(this, "Gutxienez sei talde behar dira.", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else {
                TaldeenErabilpena.gordeJokalariak(listaJokalariak);
                JOptionPane.showMessageDialog(this, "Taldeak gorde dira!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
                if (menua != null) {
                    menua.EguneratuJokalariak(); // Eguneratu baina menu barruen
                }
                IrteeraSarreraXML.LOG("Jokalarietan diren aldaketak, jokalariak.dat fitxategian gorde egin dira."); 
                menua.setVisible(true);
                dispose();
            }
        }
    }
    /**
     * Izena errepikatuta dagoen ala ez egiaztatzen du.
     * @param izena Jokalariaren izena.
     * @return True izena errepikatuta badago, bestela false.
     */
    // Jokalariak errepikatuta ez egoteko
    private boolean isDuplicate(String izena) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(izena)) {
                return true;
            }
        }
        return false;
    }
}