package main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaJornadas extends JFrame {
    private static final long serialVersionUID = 3655324882502558546L;
    private JTable table;
    private DefaultTableModel model;

    public VentanaJornadas(List<List<String>> jornadas) {
        setTitle("Gestión de Jornadas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Taula sortu
        model = new DefaultTableModel();
        model.addColumn("Partido");
        model.addColumn("Resultado");

        // Partiduak sortu eta jornadak ikusi
        for (List<String> jornada : jornadas) {
            for (String partido : jornada) {
                model.addRow(new Object[]{partido, ""});
            }
        }

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
                guardarResultados();
            }
        });
        getContentPane().add(btnGuardar, BorderLayout.SOUTH);
    }

    // Partiduetako emaitza gorde
    private void guardarResultados() {
        for (int i = 0; i < model.getRowCount(); i++) {
            String partido = (String) model.getValueAt(i, 0);
            String resultado = (String) model.getValueAt(i, 1);
            System.out.println(partido + " - Resultado: " + resultado);
        }
    }

}