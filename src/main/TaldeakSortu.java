package main;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaldeakSortu extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textTaldeak;
    private JButton btnGordeIzenak, btnKenduTaldea, btnGordeAldaketak;
    private DefaultTableModel tableModel;
    private JTable tableTaldeak;
    private Menua menua;

    public TaldeakSortu(Menua menua) {
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

        JLabel lblTaldeenIzena = new JLabel("Taldearen izena:");
        lblTaldeenIzena.setBounds(21, 70, 102, 23);
        contentPane.add(lblTaldeenIzena);

        textTaldeak = new JTextField();
        textTaldeak.setBounds(21, 92, 223, 19);
        contentPane.add(textTaldeak);
        textTaldeak.setColumns(10);

        btnGordeIzenak = new JButton("Gorde izena");
        btnGordeIzenak.addActionListener(this);
        btnGordeIzenak.setBounds(21, 121, 132, 21);
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
            menua.setVisible(true);
            dispose();
        });
        btnAtzera.setBounds(222, 303, 85, 21);
        contentPane.add(btnAtzera);

        // Irakurri taldeak erabiltzeko
        List<String> taldeak = TaldeenErabilpena.irakurriTaldeak();
        for (String taldea : taldeak) {
            tableModel.addRow(new Object[]{taldea});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnGordeIzenak) {
            String izena = textTaldeak.getText().trim();
            if (izena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ez duzu talderik sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else if (isDuplicate(izena)) {
                JOptionPane.showMessageDialog(this, "Talde hau dagoeneko gehituta dago", "Errorea", JOptionPane.WARNING_MESSAGE);
            } else {
                tableModel.addRow(new Object[]{izena});
                textTaldeak.setText("");
            }
        } else if (source == btnKenduTaldea) {
            int[] selectedRows = tableTaldeak.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "Aukeratu taldea kendu nahi baduzu.", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    tableModel.removeRow(selectedRows[i]);
                }
                List<String> taldeak = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    taldeak.add((String) tableModel.getValueAt(i, 0));
                }
                TaldeenErabilpena.gordeTaldeak(taldeak);
                JOptionPane.showMessageDialog(this, "Taldeak kendu dira eta fitxategia eguneratu da!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (source == btnGordeAldaketak) {
            if (tableModel.getRowCount() < 6) {
                JOptionPane.showMessageDialog(this, "Gutxienez sei talde behar dira.", "Errorea", JOptionPane.ERROR_MESSAGE);
            } else {
                List<String> taldeak = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    taldeak.add((String) tableModel.getValueAt(i, 0));
                }
                TaldeenErabilpena.gordeTaldeak(taldeak);
                JOptionPane.showMessageDialog(this, "Taldeak gorde dira!", "Informazioa", JOptionPane.INFORMATION_MESSAGE);
                if (menua != null) {
                    menua.EguneratuTaldeak(); // Eguneratu baina menu barruen
                }
                menua.setVisible(true);
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
