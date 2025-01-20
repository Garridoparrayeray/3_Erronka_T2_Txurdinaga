package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
//AMAITU GABE ETA IMPLEMENTATU GABE
public class PartiduakJolastu extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxSetakEtxekoak;
    private JComboBox<String> comboBoxSetakKampotarrak;
    private JButton btnGordeSetak;
    private JButton btnAtzera;
    private JComboBox<String> comboBoxKampotarrak;
    private JComboBox<String> comboBoxEtxekoak;
    private JButton btnKlasifikazioa;
    private JComboBox<String> comboBoxJornadas;
    private JComboBox<String> comboBoxPartidos;
    private ArrayList<String[]> datos; 
    private JLabel lblNewLabel;
    private ArrayList<String[]> datos2;
    
    public PartiduakJolastu(String[] jornadas, String[] partidos, String[] taldeak) {
        datos = new ArrayList<>();
        datos2 = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPartiduakJolastu = new JLabel("Partiduak Jolastu");
        lblPartiduakJolastu.setForeground(new Color(255, 255, 255));
        lblPartiduakJolastu.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblPartiduakJolastu.setHorizontalAlignment(SwingConstants.CENTER);
        lblPartiduakJolastu.setBounds(132, 10, 323, 78);
        contentPane.add(lblPartiduakJolastu);

        // ComboBox para jornadas
        comboBoxJornadas = new JComboBox<>(jornadas);
        comboBoxJornadas.setBounds(24, 100, 225, 21);
        comboBoxJornadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	comboBoxPartidos.setModel(new DefaultComboBoxModel<>(partidos));
            }
        });
        contentPane.add(comboBoxJornadas);

        JLabel lblJornadas = new JLabel("Jornadas:");
        lblJornadas.setForeground(new Color(255, 255, 255));
        lblJornadas.setBounds(24, 80, 157, 21);
        contentPane.add(lblJornadas);

        //Partiduen combobox
        comboBoxPartidos = new JComboBox<>(partidos);
        comboBoxPartidos.setBounds(334, 100, 225, 21);
        contentPane.add(comboBoxPartidos);

        JLabel lblPartidos = new JLabel("Partidos:");
        lblPartidos.setForeground(new Color(255, 255, 255));
        lblPartidos.setBounds(334, 80, 157, 21);
        contentPane.add(lblPartidos);

        // Combobox para Etxekoak y Kampotarrak
        comboBoxEtxekoak = new JComboBox<>(taldeak);
        comboBoxEtxekoak.setBounds(24, 160, 225, 21);
        contentPane.add(comboBoxEtxekoak);

        comboBoxKampotarrak = new JComboBox<>(taldeak);
        comboBoxKampotarrak.setBounds(334, 160, 225, 21);
        contentPane.add(comboBoxKampotarrak);

        JLabel lblEtxekoak = new JLabel("Etxekoak:");
        lblEtxekoak.setForeground(new Color(255, 255, 255));
        lblEtxekoak.setBounds(24, 140, 157, 21);
        contentPane.add(lblEtxekoak);

        JLabel lblKampotarrak = new JLabel("Kampotarrak:");
        lblKampotarrak.setForeground(new Color(255, 255, 255));
        lblKampotarrak.setBounds(334, 140, 157, 21);
        contentPane.add(lblKampotarrak);

        JLabel lblSetakExtekoak = new JLabel("Irabazitako setak");
        lblSetakExtekoak.setForeground(new Color(255, 255, 255));
        lblSetakExtekoak.setHorizontalAlignment(SwingConstants.CENTER);
        lblSetakExtekoak.setBounds(63, 200, 157, 21);
        contentPane.add(lblSetakExtekoak);

        JLabel lblSetakKampotarrak = new JLabel("Irabazitako setak:");
        lblSetakKampotarrak.setForeground(new Color(255, 255, 255));
        lblSetakKampotarrak.setHorizontalAlignment(SwingConstants.CENTER);
        lblSetakKampotarrak.setBounds(368, 200, 157, 21);
        contentPane.add(lblSetakKampotarrak);

        comboBoxSetakEtxekoak = new JComboBox<>(new String[]{"0", "1", "2", "3"});
        comboBoxSetakEtxekoak.setMaximumRowCount(6);
        comboBoxSetakEtxekoak.setBounds(104, 230, 71, 21);
        contentPane.add(comboBoxSetakEtxekoak);

        comboBoxSetakKampotarrak = new JComboBox<>(new String[]{"0", "1", "2", "3"});
        comboBoxSetakKampotarrak.setMaximumRowCount(6);
        comboBoxSetakKampotarrak.setBounds(410, 230, 71, 21);
        contentPane.add(comboBoxSetakKampotarrak);

        btnGordeSetak = new JButton("Gorde setak");
        btnGordeSetak.setBounds(43, 280, 177, 21);
        btnGordeSetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(comboBoxSetakEtxekoak.getSelectedItem().toString());
                int b = Integer.parseInt(comboBoxSetakKampotarrak.getSelectedItem().toString());
                boolean sonEquiposIguales = comboBoxEtxekoak.getSelectedItem().equals(comboBoxKampotarrak.getSelectedItem());
                boolean esEmpateEnSets = comboBoxSetakEtxekoak.getSelectedItem().equals(comboBoxSetakKampotarrak.getSelectedItem());
                boolean setshiru = a < 3 && b < 3;
                if (sonEquiposIguales) {
                    JOptionPane.showMessageDialog(null, "Ezin dira talde berdinak partidu batean egon", "Errorea", JOptionPane.ERROR_MESSAGE);
                } else if (esEmpateEnSets) {
                    JOptionPane.showMessageDialog(null, "Ezin dira berdinketan geratu", "Errorea", JOptionPane.ERROR_MESSAGE);
                } else if (setshiru) {
                    JOptionPane.showMessageDialog(null, "Taldeetatik bat 3 sets irabazi behar du", "Errorea", JOptionPane.ERROR_MESSAGE);
                } else {
                    String jornadaSeleccionada = (String) comboBoxJornadas.getSelectedItem();
                    String partidoSeleccionado = (String) comboBoxPartidos.getSelectedItem();
                    String selectedItem1 = (String) comboBoxEtxekoak.getSelectedItem();
                    String selectedItem2 = (String) comboBoxSetakEtxekoak.getSelectedItem();
                    String selectedItem3 = (String) comboBoxKampotarrak.getSelectedItem();
                    String selectedItem4 = (String) comboBoxSetakKampotarrak.getSelectedItem();
                    datos.add(new String[]{jornadaSeleccionada, partidoSeleccionado, selectedItem1, selectedItem2, selectedItem3, selectedItem4});
                    JOptionPane.showMessageDialog(null, "Datuak gordeta.");
                }
            }
        });
        contentPane.add(btnGordeSetak);

        btnAtzera = new JButton("Atzera");
        btnAtzera.setBounds(474, 400, 85, 21);
        btnAtzera.addActionListener(e -> {
            Menua m = new Menua();
            m.setVisible(true);
            dispose();
        });
        contentPane.add(btnAtzera);

        btnKlasifikazioa = new JButton("Klasifikazioa");
        btnKlasifikazioa.setBounds(354, 280, 177, 21);
        btnKlasifikazioa.addActionListener(e -> {
            Klasifikazioa ventanaTabla = new Klasifikazioa(datos, datos2);
            ventanaTabla.setVisible(true);
            dispose();
        });
        contentPane.add(btnKlasifikazioa);

        lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\IK_1DW3D\\Downloads\\blue-background-abstract-illustration-with-gradient-blur-design-free-vector_resized.jpg"));
        lblNewLabel.setBounds(0, 0, 598, 501);
        contentPane.add(lblNewLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //amaitu gabe
    }
}
