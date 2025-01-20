package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Klasifikazioa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableKlasifikazioa;
	private JButton btnAtzera;
	private static DefaultTableModel dtm;
	private JLabel lblNewLabel;
  private JTable table2;
  private JTextField textField;
  private JLabel lblNewLabel_2;
  private JButton btnNewButton_1;


	/**
	 * Create the frame.
	 */
	
	public Klasifikazioa(ArrayList<String[]> datos, ArrayList<String[]> datos2
) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGoiBurua = new JLabel("Klasifikazioak");
		lblGoiBurua.setForeground(new Color(255, 255, 255));
		lblGoiBurua.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoiBurua.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblGoiBurua.setBounds(241, 22, 210, 37);
		contentPane.add(lblGoiBurua);
		
		String[] columnNames = {"selectedItem1", "selectedItem2","selectedItem3","selectedItem4"};
		String[][] filas = {
        {"selectedItem1", "selectedItem2"},
        {"selectedItem3","selectedItem4"}
    };
		
		dtm = new DefaultTableModel(columnNames, 0);
		for (String[] fila : datos) {
			dtm.addRow(fila);
		}
  
		 DefaultTableModel dtm2 = new DefaultTableModel(filas, 0);
	    for (String[] fil : datos2) {
	        dtm2.addRow(fil);
	    }
	    
	    
		/*partidu jolastutako datuak hemen */
		tableKlasifikazioa = new JTable(dtm);
		tableKlasifikazioa.setBounds(36, 82, 384, 253);
		contentPane.add(tableKlasifikazioa);
		
		btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(303, 345, 85, 21);
		contentPane.add(btnAtzera);

		btnAtzera.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Menua m = new Menua();
		        m.setVisible(true);
		        dispose(); // Cierra la ventana actual
		    }
		});
		
		 table2 = new JTable(dtm2);
     table2.setBounds(451, 82, 201, 253);
     contentPane.add(table2);
     
     TableRowSorter<TableModel> sorter = new TableRowSorter<>(dtm2);
     table2.setRowSorter(sorter);
     
     textField = new JTextField();
     textField.setBounds(448, 53, 96, 19);
     contentPane.add(textField);
     textField.setColumns(10);
     
     JButton btnNewButton = new JButton("Sartu");
     btnNewButton.setBounds(567, 51, 85, 21);
     contentPane.add(btnNewButton);
     
     JLabel lblNewLabel_1 = new JLabel("Jolastutako pariduak");
     lblNewLabel_1.setForeground(new Color(255, 255, 255));
     lblNewLabel_1.setBounds(184, 59, 132, 21);
     contentPane.add(lblNewLabel_1);
     
     lblNewLabel_2 = new JLabel("Klasifikazioa");
     lblNewLabel_2.setForeground(new Color(255, 255, 255));
     lblNewLabel_2.setBounds(520, 30, 85, 13);
     contentPane.add(lblNewLabel_2);
     
     btnNewButton_1 = new JButton("Ordenatu");
     btnNewButton_1.setBounds(501, 345, 85, 21);
     contentPane.add(btnNewButton_1);
     btnNewButton_1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Establecer el orden descendente (de mayor a menor)
            	 sorter.setSortKeys(java.util.Collections.singletonList(new RowSorter.SortKey(1, SortOrder.DESCENDING)));             }
         });
     lblNewLabel = new JLabel("New label");
 		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\IK_1DW3D\\Downloads\\blue-background-abstract-illustration-with-gradient-blur-design-free-vector_resized.jpg"));
 		lblNewLabel.setBounds(0, 0, 696, 407);
 		contentPane.add(lblNewLabel);
     
     btnNewButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Obtener el texto del JTextField
                 String data = textField.getText().trim();

                 // Verificar que no esté vacío y que tenga al menos dos palabras
                 String[] words = data.split("\\s+"); // dividir por espacios

                 if (words.length >= 2) {
                     // Agregar las dos primeras palabras como una nueva fila en el JTable
                     dtm2.addRow(new Object[]{words[0], words[1]});
                     // Limpiar el JTextField
                     textField.setText("");
                 } else {
                     JOptionPane.showMessageDialog(null, "Por favor ingrese al menos dos palabras separadas por espacio.");
                 }
             }
         });
 }
}



