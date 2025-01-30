package main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import objektuak.Taldeak;
import main.Menua;

public class TaldeakSortu extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textTaldeak, textTaldeakKodea, textTaldeakZelaia;
    private JButton btnGordeIzenak, btnKenduTaldea, btnGordeAldaketak;
    private DefaultTableModel tableModel;
    private JTable tableTaldeak;
    private Menua menua;
    
    private List<Taldeak> listaTaldeak = TaldeenErabilpena.irakurriTaldeak();

    public TaldeakSortu(Menua menua) {
    	System.out.print(listaTaldeak.size());
    	//EREDU GRAFIKOA. MAIN BAKARRA MENUAN EZ EDUKITZEKO 18 MAIN
        this.menua = menua;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 544, 384);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTaldeakSortu = new JLabel("Taldeak Sortu");
        lblTaldeakSortu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTaldeakSortu.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblTaldeakSortu.setBounds(153, 23, 210, 37);
        contentPane.add(lblTaldeakSortu);

        JLabel lblTaldeenKodea = new JLabel("Taldearen Kodea:");
        lblTaldeenKodea.setBounds(21, 70, 102, 23);
        contentPane.add(lblTaldeenKodea);

        textTaldeakKodea = new JTextField();
        textTaldeakKodea.setBounds(21, 92, 223, 19);
        contentPane.add(textTaldeakKodea);
        textTaldeakKodea.setColumns(10);
        
        JLabel lblTaldeenIzena = new JLabel("Taldearen izena:");
        lblTaldeenIzena.setBounds(21, 114, 102, 23);
        contentPane.add(lblTaldeenIzena);

        textTaldeak = new JTextField();
        textTaldeak.setBounds(21, 136, 223, 19);
        contentPane.add(textTaldeak);
        textTaldeak.setColumns(10);
        
        JLabel lblTaldeenHerrialdea = new JLabel("Taldearen Zelaia:");
        lblTaldeenHerrialdea.setBounds(21, 158, 132, 23);
        contentPane.add(lblTaldeenHerrialdea);

        textTaldeakZelaia = new JTextField();
        textTaldeakZelaia.setBounds(21, 180, 223, 19);
        contentPane.add(textTaldeakZelaia);
        textTaldeakZelaia.setColumns(10);

        btnGordeIzenak = new JButton("Gorde Taldea");
        btnGordeIzenak.addActionListener(this);
        btnGordeIzenak.setBounds(21, 202, 132, 21);
        contentPane.add(btnGordeIzenak);

        tableModel = new DefaultTableModel(new String[]{"Taldeak"}, 0);
        tableTaldeak = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableTaldeak);
        scrollPane.setBounds(324, 70, 196, 223);
        contentPane.add(scrollPane);

        btnKenduTaldea = new JButton("Kendu taldea");
        btnKenduTaldea.addActionListener(this);
        btnKenduTaldea.setBounds(388, 303, 132, 21);
        contentPane.add(btnKenduTaldea);

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
        List<Taldeak> taldeak = TaldeenErabilpena.irakurriTaldeak();
        for (Taldeak taldea : taldeak) {
            tableModel.addRow(new Object[]{taldea});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnGordeIzenak) {
            String izena = textTaldeak.getText().trim();
            int taldeKod = Integer.valueOf(textTaldeakKodea.getText().trim());
            String zelaia = textTaldeakZelaia.getText().trim();
            boolean errepikatu = false;
            
            for (Taldeak taldea : listaTaldeak) {
            	if (taldea.getTaldekod() == taldeKod) {
            		errepikatu = true;
            	}
            }
            if (errepikatu == false) {
            	if (izena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ez duzu talderik sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else if (isDuplicate(izena)) {
                JOptionPane.showMessageDialog(this, "Talde hau dagoeneko gehituta dago", "Errorea", JOptionPane.WARNING_MESSAGE);
            } else {
            		Taldeak taldea = new Taldeak(taldeKod, izena, zelaia);
                tableModel.addRow(new Object[]{taldeKod + " " + izena + " " + zelaia});
                textTaldeak.setText("");
                textTaldeakKodea.setText("");
                textTaldeakZelaia.setText("");
                listaTaldeak.add(taldea);
            }
            }
            else {
            	JOptionPane.showMessageDialog(null, "Talde kodea errepikatuta dago. Berriro saiatu mesedez...", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == btnKenduTaldea) {
            int[] selectedRows = tableTaldeak.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "Aukeratu taldea kendu nahi baduzu.", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else {
                List<String> taldeak = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                	taldeak.add(tableModel.getValueAt(i, 0).toString());
                }
                
                String txt = tableModel.getValueAt(tableTaldeak.getSelectedRow(), 0).toString();
                int taldeKod = 0;
                String zenbakiaAteratzen = "";
                for (int i = 0; i < txt.length(); i++) {
                	char c = txt.charAt(i);
                	if (Character.isDigit(c)) {
                		zenbakiaAteratzen += c;
                	}
                	else if (!Character.isDigit(c)) {
                		taldeKod = Integer.valueOf(zenbakiaAteratzen);
                		i = txt.length() * 10;
                	}
                }
                
                for (int i = 0; i < listaTaldeak.size(); i++) {
                	if (listaTaldeak.get(i).getTaldekod() == taldeKod) {
                		taldeKod = i;
                	}
                }
                
                TaldeenErabilpena.TaldeakKendu(listaTaldeak, listaTaldeak.get(taldeKod));
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
                
                TaldeenErabilpena.gordeTaldeak(listaTaldeak);
                JOptionPane.showMessageDialog(this, "Taldeak gorde dira!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
                if (menua != null) {
                    menua.EguneratuTaldeak(); // Eguneratu baina menu barruen
                }
                menua.setVisible(true);
                Menua eguneratzeko = new Menua();
                eguneratzeko.EguneratuTaldeak();
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