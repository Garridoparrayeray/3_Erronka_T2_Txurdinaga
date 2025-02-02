package frogak;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import main.IrteeraSarreraXML;
import main.Menua;
import main.TaldeenErabilpena;
import objektuak.Jokalariak;
import objektuak.Taldeak;

/*
 * Kodea, JUnit proba motentzako moldatu da. Berdina egiten du baina interfaze grafiko gabe JUnit es emateko erroreak. 
 * Probetan erabiltzailerik ez daudenez, interfaze grafikoekin JUnit arazoak ematen ditu
 */

class JokalariaSortuFroga {

	@Test
	void testJokalariaSortu() {
		int jokalariKod = 1;
    	String izena = "Alejandro";
    	String abizena = "Magno";
    	String jokalariRola = "Puntako-Jokalaria";
    	int taldeKod = 6;
    	boolean errepikatu = true;
    	
    	Jokalariak jokalaria = new Jokalariak(jokalariKod, izena, abizena, jokalariRola, taldeKod);
	}
	
	@Test
	void testJokalariaEzabatu() {
		List<Jokalariak> listaJokalariak = new ArrayList<Jokalariak>();
		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Jokalaria"}, 0);
        JTable tableJokalariak = new JTable(tableModel);
		
		int[] selectedRows = tableJokalariak.getSelectedRows(); 
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
	}
}
