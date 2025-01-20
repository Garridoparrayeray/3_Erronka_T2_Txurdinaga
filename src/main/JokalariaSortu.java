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

public class JokalariaSortu extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textJokalaria, textJokalariKodea, textJokalariaAbizena, textJokalariaRola, textTaldeKodea;
    private JButton btnGordeIzenak, btnKenduJokalaria, btnGordeAldaketak;
    private DefaultTableModel tableModel;
    private JTable tableTaldeak;
    private Menua menua;
    
    private List<Jokalariak> listaJokalariak = TaldeenErabilpena.irakurriJokalariak();
    private List<Taldeak> listaTaldeak = TaldeenErabilpena.irakurriTaldeak();

    public JokalariaSortu(Menua menua) {
    	System.out.print(listaJokalariak.size());
    	//EREDU GRAFIKOA. MAIN BAKARRA MENUAN EZ EDUKITZEKO 18 MAIN
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
        tableTaldeak = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableTaldeak);
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
                menua.EguneratuTaldeak(); // TALDEAK EGUNERATU
            }
            Menua eguneratzeko = new Menua();
            eguneratzeko.EguneratuTaldeak();
            menua.setVisible(true);
            dispose();
        });
        btnAtzera.setBounds(222, 303, 85, 21);
        contentPane.add(btnAtzera);

        // Irakurri taldeak erabiltzeko
        List<Jokalariak> jokalariak = TaldeenErabilpena.irakurriJokalariak();
        for (Jokalariak jokalaria : jokalariak) {
            tableModel.addRow(new Object[]{jokalaria.getJokalarikod() + " " +  jokalaria.getIzena() + " " + jokalaria.getAbizena() + " " + jokalaria.getJokalariRola()});
        }
    }

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
          

          for (Taldeak taldea : listaTaldeak) {
          	if (taldea.getTaldekod() == taldeKod) {
          		errepikatu = false;
          	}
          }
          for (Jokalariak jokalaria : listaJokalariak) {
        		if (jokalaria.getJokalarikod() == jokalariKod) {
        			errepikatu = true;
        		}
        	}
          if (errepikatu == false) {
          	if (izena.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Ez duzu talderik sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
          } else if (isDuplicate(izena)) {
              JOptionPane.showMessageDialog(this, "Talde hau dagoeneko gehituta dago", "Errorea", JOptionPane.WARNING_MESSAGE);
          } else {
          		Pertsona pertsona = new Pertsona(izena, abizena);
          		Jokalariak jokalaria = new Jokalariak(pertsona, jokalariKod, izena, abizena, jokalariRola, taldeKod);
          		tableModel.addRow(new Object[]{jokalariKod + " " + izena + " " +  abizena + " " +  jokalariRola + " " +  taldeKod});
              textJokalariKodea.setText("");
              textJokalaria.setText("");
              textJokalariaAbizena.setText("");
              textJokalariaRola.setText("");
              textTaldeKodea.setText("");
              listaJokalariak.add(jokalaria);
          }
          }
          else {
          	JOptionPane.showMessageDialog(null, "Taldea ez da existitzen edo jokalari kodea errepikatuta dago. Berriro saiatu mesedez...", "Errorea", JOptionPane.ERROR_MESSAGE);
          }
        } else if (source == btnKenduJokalaria) {
            int[] selectedRows = tableTaldeak.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "Aukeratu taldea kendu nahi baduzu.", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else {
                List<String> jokalariak = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                	jokalariak.add(tableModel.getValueAt(i, 0).toString());
                }
                
                String txt = tableModel.getValueAt(tableTaldeak.getSelectedRow(), 0).toString();
                int jokalariKod = 0;
                String zenbakiaAteratzen = "";
                for (int i = 0; i < txt.length(); i++) {
                	char c = txt.charAt(i);
                	if (Character.isDigit(c)) {
                		zenbakiaAteratzen += c;
                	}
                	else if (!Character.isDigit(c)){
                		jokalariKod = Integer.valueOf(zenbakiaAteratzen);
                		i = txt.length() * 10;
                	}
                }
                
                for (int i = 0; i < listaJokalariak.size(); i++) {
                	if (listaJokalariak.get(i).getJokalarikod() == jokalariKod) {
                		jokalariKod = i;
                	}
                }
                
                TaldeenErabilpena.JokalariakKendu(listaJokalariak, listaJokalariak.get(jokalariKod));
                JOptionPane.showMessageDialog(this, "Taldeak kendu dira eta fitxategia eguneratu da!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
                tableModel.removeRow(tableTaldeak.getSelectedRow());
            }
        } else if (source == btnGordeAldaketak) {
            if (tableModel.getRowCount() < 0) {
                JOptionPane.showMessageDialog(this, "Gutxienez sei talde behar dira.", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else {
                // List<String> taldeak = new ArrayList<>();
            		// for (int i = 0; i < tableModel.getRowCount(); i++) {
            		//     taldeak.add((String) tableModel.getValueAt(i, 0));
            		// }
                
                TaldeenErabilpena.gordeJokalariak(listaJokalariak);
                JOptionPane.showMessageDialog(this, "Taldeak gorde dira!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
                if (menua != null) {
                    menua.EguneratuJokalariak(); // Eguneratu baina menu barruen
                }
                menua.setVisible(true);
                //Menua eguneratzeko = new Menua();
                //eguneratzeko.EguneratuTaldeak();
                dispose();
            }
        }
    }
    //taldeak ez ipintzeko bi aldiz 
    private boolean isDuplicate(String izena) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(izena)) {
                return true;
            }
        }
        return false;
    }
}
