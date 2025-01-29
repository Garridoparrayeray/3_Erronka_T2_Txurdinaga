package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import objektuak.*; // Asegúrate de que estas clases estén importadas correctamente
import main.ExportarXML;
import main.ImportarXML;

public class IrteeraSarreraXML extends JFrame {

    private JTable tablaClasificacion;
    private DefaultTableModel modeloTabla;
    private Jornadas jornadas; // Variable para manejar el objeto Jornadas

    public IrteeraSarreraXML() {
        // Crear la interfaz con botones y una tabla para mostrar la clasificación
        JButton btnExportar = new JButton("Exportar a XML");
        btnExportar.setBounds(50, 100, 200, 50);
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    exportarXML();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el archivo XML: " + ex.getMessage());
                }
            }
        });

        JButton btnImportar = new JButton("Importar XML");
        btnImportar.setBounds(50, 180, 200, 50);
        btnImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    importarXML();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al importar el archivo XML: " + ex.getMessage());
                }
            }
        });

        // Crear el modelo para la tabla de clasificación
        String[] columnNames = {"Puesto", "Equipo", "Partidos Ganados", "Partidos Perdidos", "Puntos"};
        modeloTabla = new DefaultTableModel(null, columnNames);
        tablaClasificacion = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClasificacion);
        scrollPane.setBounds(300, 100, 300, 200);

        setTitle("Exportar e Importar Jornadas");
        setSize(650, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(btnExportar);
        add(btnImportar);
        add(scrollPane);
    }

    public void exportarXML() throws Exception {
        // Usar JFileChooser para seleccionar la ubicación y nombre del archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo XML");
        fileChooser.setSelectedFile(new File("jornadas.xml"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return; // Si el usuario cancela, no hacemos nada
        }

        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getName().endsWith(".xml")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".xml"); // Añadir extensión .xml si no tiene
        }

        // Crear el objeto ExportarXML y exportar los datos
        ExportarXML exportarXML = new ExportarXML();
        exportarXML.exportToXML(jornadas, fileToSave.getAbsolutePath());

        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(null, "Archivo XML creado con éxito.");
    }

    public Jornadas importarXML() throws Exception {
        // Usar JFileChooser para seleccionar el archivo XML
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo XML");

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return new Jornadas(); // Si el usuario cancela, no hacemos nada
        }

        File fileToOpen = fileChooser.getSelectedFile();
        if (!fileToOpen.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no existe.");
            return new Jornadas();
        }

        // Crear el objeto ImportarXML y obtener los datos
        ImportarXML importarXML = new ImportarXML();
        jornadas = importarXML.importFromXML(fileToOpen.getAbsolutePath());

        // Limpiar la tabla antes de insertar nuevos datos
        modeloTabla.setRowCount(0);

        // Agregar las filas de la tabla (clasificación)
        for (Jardunaldiak jardunaldi : jornadas.getJardunaldiak()) {
            modeloTabla.addRow(new Object[]{jardunaldi.getJardunaldi_kod(), jardunaldi.getJardunaldi_deskribapena(), jardunaldi.getPartidu_kopurua()});
        }

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Archivo XML importado con éxito.");
        return importarXML.importFromXML(fileToOpen.toString());
    }
}
