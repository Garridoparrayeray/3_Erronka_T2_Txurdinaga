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
        
        // Beharrezkoa da:
        Denboraldiak denboraldia = jornadas.getDenboraldia();
        List<Jardunaldiak> jardunaldiak = jornadas.getJardunaldiak();
        List<Partiduak> partiduak = jornadas.getPartiduak();
        
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        int counter = 0;
        for (int i = 0; i < jardunaldiak.size(); i++) {
        	Jardunaldiak jardunaldia = jardunaldiak.get(i);
        	for (int i2 = 0; i2 < jardunaldia.getPartidu_kopurua(); i2++) {
        		Partiduak partidua = partiduak.get(counter);
        		model.addRow(new Object[]{partidua.getJardunaldiak().getJardunaldi_deskribapena(), (partidua.getEtxeko_taldea().getIzena() + " VS " + partidua.getKanpoko_taldea().getIzena()), ""});
        		counter++;
        	}
        	
        }

        // Jornadas exportatzeko optzioa
        JButton btnGuardar = new JButton("Guardar Resultados");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	IrteeraSarreraXML.IrteeraXML(jornadas);
            }
        });
        getContentPane().add(btnGuardar, BorderLayout.SOUTH);
    }
}