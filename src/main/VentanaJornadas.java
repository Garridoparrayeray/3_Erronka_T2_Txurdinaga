package main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import objektuak.*;

public class VentanaJornadas extends JFrame {
    private static final long serialVersionUID = 3655324882502558546L;
    private JTable table;
    private DefaultTableModel model;

    public VentanaJornadas(Jornadas jornadas) {
        setTitle("Gestión de Jornadas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Taula sortu
        model = new DefaultTableModel();
        model.addColumn("Jornada");
        model.addColumn("Partido");
        model.addColumn("Resultado");
        
        // Partiduak sortu eta jornadak ikusi
        List<Jardunaldiak> jardunaldiak = jornadas.getJardunaldiak();
        List<Partiduak> partiduak = jornadas.getPartiduak();
        boolean hurrengora;
        int counter = 0;
        for (Jardunaldiak jardunaldia : jardunaldiak) {
        	hurrengora = false;
        	String izena = jardunaldia.getJardunaldi_deskribapena();
        	while (hurrengora == false) {
        		if (partiduak.get(counter).getJardunaldiak().equals(jardunaldia)) {
        			model.addRow(new Object[]{izena, (partiduak.get(counter).getEtxeko_taldea() + " VS " + partiduak.get(counter).getKanpoko_taldea()), ""});
        		}
        		else {
        			hurrengora = true;
        		}
        		counter++;
        	}
        }
        
        /*for (int i = 0; i < jardunaldia.size() - 2; i++) {
        	String jornadaIzena = ja;
        	for (String partido : jornada) {
            model.addRow(new Object[]{jornadaIzena, partido, ""});
        	}
        }*/

        // taula berria
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botón para guardar los resultados
        JButton btnGuardar = new JButton("Guardar Resultados");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		List<Jornadas> jardunaldiak = TaldeenErabilpena.jardunaldiakIrakurri();
            		jardunaldiak.add(jornadas);
                TaldeenErabilpena.jardunaldiakGorde(jardunaldiak);
                VentanaJornadas JFrameEguneratua = new VentanaJornadas(jornadas);
                JFrameEguneratua.setVisible(true);
                dispose()	;
            }
        });
        getContentPane().add(btnGuardar, BorderLayout.SOUTH);
        
    }

    // Partiduetako emaitza gorde
    private void guardarResultados() {
        for (int i = 0; i < model.getRowCount(); i++) {
        	String jornada = (String) model.getValueAt(i, 0);
            String partido = (String) model.getValueAt(i, 1);
            String resultado = (String) model.getValueAt(i, 2);
            System.out.println(partido + " - Resultado: " + resultado);
        }
    }
}