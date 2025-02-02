package frogak;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import main.IrteeraSarreraXML;

import objektuak.*;

class PartiduakJolastuFroga {

	@Test
	void testPuntuakSartu() {
		Partiduak partidua = new Partiduak();
		JPanel puntuazioPanela = new JPanel(new GridLayout(4, 1));
  		JLabel eskaeraTaldeA = new JLabel(partidua.getEtxeko_taldea().getIzena() + " taldearen puntuazioa sartu: ");
  		JTextField puntuazioaSartu1 = new JTextField();
  		JLabel eskaeraTaldeB = new JLabel(partidua.getKanpoko_taldea().getIzena() + " taldearen puntuazioa sartu: ");
  		JTextField puntuazioaSartu2 = new JTextField();
  		
  		puntuazioPanela.add(eskaeraTaldeA);
  		puntuazioPanela.add(puntuazioaSartu1);
  		puntuazioPanela.add(eskaeraTaldeB);
  		puntuazioPanela.add(puntuazioaSartu2);
  		
  		JOptionPane.showMessageDialog(null, puntuazioPanela, "Emaitzak Sartzeko", JOptionPane.OK_CANCEL_OPTION);
  		
  		String puntuazioaA = puntuazioaSartu1.getText();
  		String puntuazioaB = puntuazioaSartu2.getText();
  		if (puntuazioaA.isEmpty()) {
  			JOptionPane.showMessageDialog(null, (partidua.getEtxeko_taldea().getIzena() + " taldeko puntuazioa sartu behar da. Berriro saiatu mesedez"), "Errorea", JOptionPane.ERROR_MESSAGE);
  		}
  		else if (puntuazioaB.isEmpty()) {
  			JOptionPane.showMessageDialog(null, (partidua.getKanpoko_taldea().getIzena() + " taldeko puntuazioa sartu behar da. Berriro saiatu mesedez"), "Errorea", JOptionPane.ERROR_MESSAGE);
  		}
  		else {
  				int puntuakTaldeA = Integer.valueOf(puntuazioaA);
  				int puntuakTaldeB = Integer.valueOf(puntuazioaB);
  				
  				partidua.setEtxekoTaldekoPuntuazioa(puntuakTaldeA);
  				partidua.setKanpokoTaldekoPuntuazioa(puntuakTaldeB);
  				
  		}
	}

}
