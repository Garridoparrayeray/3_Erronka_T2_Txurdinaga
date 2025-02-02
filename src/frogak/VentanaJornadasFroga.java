package frogak;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import objektuak.Denboraldiak;
import objektuak.Jardunaldiak;
import objektuak.Partiduak;

import main.Jornadas;

class VentanaJornadasFroga {

	@Test
	void testVentanaJornadas() {
		Jornadas jornadas = new Jornadas();
		Denboraldiak denboraldia = jornadas.getDenboraldia();
        List<Jardunaldiak> jardunaldiak = jornadas.getJardunaldiak();
        List<Partiduak> partiduak = jornadas.getPartiduak();
        
        DefaultTableModel model = new DefaultTableModel(new String[]{"Taldeak"}, 0);
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        int counter = 0;
        for (int i = 0; i < jardunaldiak.size(); i++) {
        	Jardunaldiak jardunaldia = jardunaldiak.get(i);
        	for (int i2 = 0; i2 < jardunaldia.getPartidu_kopurua(); i2++) {
        		Partiduak partidua = partiduak.get(counter);
        		model.addRow(new Object[]{partidua.getJardunaldiak().getJardunaldi_deskribapena(), (partidua.getEtxeko_taldea().getIzena() + " VS " + partidua.getKanpoko_taldea().getIzena()), ""});
        		counter++;
        	}
        }
	}

}
