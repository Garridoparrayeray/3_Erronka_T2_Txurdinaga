package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

import objektuak.Denboraldiak;
import objektuak.Jardunaldiak;
import objektuak.Partiduak;
import objektuak.Taldeak;

public class Klasifikazioa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Klasifikazioa(Jornadas jornada) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Denboraldiak denboraldia = jornada.getDenboraldia();
		List<Jardunaldiak> jardunaldiak = jornada.getJardunaldiak();
		List<Partiduak> partiduak = jornada.getPartiduak();
		List<Taldeak> taldeak = jornada.getTaldeak();
		System.out.println(jardunaldiak.size());
		System.out.println(partiduak.size());
		System.out.println(taldeak.size());
		
		String[] zutabeak = {"Postua", "Taldea", "Zelaia", "Irabaziak", "Galduak", "Enpateak", "Irabazi %"};
		DefaultTableModel taulaModeloa = new DefaultTableModel(zutabeak, 0);
		
		ArrayList<ArrayList<String>> taldePuntuazioak = new ArrayList<ArrayList<String>>();
		List<Taldeak> taldeKopia = taldeak;
		
		
		/*
		 * Klasifikazioa ateratzeko, XML fitxategi batetik inportatu egin den datuan Jornadas klasean gorde egin dira. Klase horren informazioa, klasifikazioa ateratzeko erabiliko da.
		 * Klase horretatik, denboraldia, jardunaldiak, partiduak eta taldeak atera ahalko dira, eta informazio horrekin klasifikazioa ateratzen da.
		 */
		boolean amaiera = false;
		int irabaziakA;
		int galdutakA;
		int enpateakA;
		int kokapena;
		//int irabaziakB;
		//int galdutakB;
		for (Taldeak taldea : taldeKopia) {
			kokapena = 0;
			irabaziakA = 0;
			galdutakA = 0;
			enpateakA = 0;
			
			// Klasifikaziorako taldeen informazioa eta irabazi, galdu eta enpatatu egin duten partiduen kopurua gordeko duen lista desordenatu bat sortzen da.
			ArrayList<String> taldeInformazioa = new ArrayList<String>();
			for (Partiduak partidua : partiduak) {
				if ((partidua.getEtxeko_taldea().equals(taldea) && partidua.getEtxekoTaldekoPuntuazioa() > partidua.getKanpokoTaldekoPuntuazioa()) || (partidua.getKanpoko_taldea().equals(taldea) && partidua.getKanpokoTaldekoPuntuazioa() > partidua.getEtxekoTaldekoPuntuazioa())) {
					irabaziakA++;
				}
				else if ((partidua.getEtxeko_taldea().equals(taldea) && partidua.getEtxekoTaldekoPuntuazioa() < partidua.getKanpokoTaldekoPuntuazioa()) || (partidua.getKanpoko_taldea().equals(taldea) && partidua.getKanpokoTaldekoPuntuazioa() < partidua.getEtxekoTaldekoPuntuazioa())) {
					galdutakA++;
				}
				else if (partidua.getEtxeko_taldea().equals(taldea) || partidua.getKanpoko_taldea().equals(taldea)) {
					enpateakA++;
				}
			}
			taldeInformazioa.add(taldea.getIzena() + "");
			taldeInformazioa.add(taldea.getZelaia());
			taldeInformazioa.add(irabaziakA + "");
			taldeInformazioa.add(galdutakA + "");
			taldeInformazioa.add(enpateakA + "");
			taldeInformazioa.add((irabaziakA / (irabaziakA + galdutakA + enpateakA) * 100) + "%");
			taldePuntuazioak.add(taldeInformazioa);
		}
		
		// Ondoren, aurreko ArrayList-aren informazio berdina eukiko duen beste lista bat sortzen da, baina hau, ranking-aren posizio zenbakia eukiko duena. 
		ArrayList<ArrayList<String>> klasifikazioaOrdenatua = new ArrayList<ArrayList<String>>();
		
		boolean handiena;
		int puntuazioaA;
		int puntuazioaB;
		int ranking;
		for (ArrayList<String> informazioaD : taldePuntuazioak) {
			ranking = 1;
			puntuazioaA = Integer.valueOf(informazioaD.get(2));
			ArrayList<String> datuakSartzea = new ArrayList<String>();
			for (ArrayList<String> informazioaD2 : taldePuntuazioak) {
				puntuazioaB = Integer.valueOf(informazioaD2.get(2));
				if (puntuazioaA < puntuazioaB) {
					ranking++; // Bere puntuazioa handiagoa denean, ranking-ean posizioak igoko ditu :)...
				}
			}
			datuakSartzea.add(ranking + "");
			datuakSartzea.add(informazioaD.get(0));
			datuakSartzea.add(informazioaD.get(1));
			datuakSartzea.add(informazioaD.get(2));
			datuakSartzea.add(informazioaD.get(3));
			datuakSartzea.add(informazioaD.get(4));
			datuakSartzea.add(informazioaD.get(5));
			klasifikazioaOrdenatua.add(datuakSartzea);
		}
		
		JTable taula = new JTable(taulaModeloa);
		
		JScrollPane scrollPane = new JScrollPane(taula);
		scrollPane.setBounds(22, 22, 500, 366);
		contentPane.add(scrollPane);
		IrteeraSarreraXML.LOG("Klasifikazioa kargatu da :)"); 
		
		// Amaitzeko, ArrayList-ean dagoen ranking posizioaren zenbakiaren arabera ordenatu egingo ditu. 
		for (int i = 0; i < klasifikazioaOrdenatua.size(); i++) {
			for (ArrayList<String> taulanSartzea : klasifikazioaOrdenatua) {
				if (Integer.valueOf(taulanSartzea.get(0)) == (i + 1)) {
					int irabaziak = Integer.valueOf(taulanSartzea.get(3));
					int galdutak = Integer.valueOf(taulanSartzea.get(4));
					int enpateak = Integer.valueOf(taulanSartzea.get(5));
					Double erantzuna = ((1.0 * irabaziak) / ((irabaziak + galdutak + enpateak)) * 100.0);
					String[] datuInformazioa = {taulanSartzea.get(0), taulanSartzea.get(1), taulanSartzea.get(2), irabaziak + "", galdutak + "", enpateak + "", erantzuna + "%"};
					taulaModeloa.addRow(datuInformazioa);
				}
			}
		}
		Menua menua = new Menua();
    JButton btnBueltatu = new JButton("Atzera");
     btnBueltatu.setBounds(555, 88, 100, 66);
     btnBueltatu.setVisible(true);
     contentPane.add(btnBueltatu);
     btnBueltatu.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Menua eguneratzeko = new Menua();
						eguneratzeko.EguneratuTaldeak();
						menua.setVisible(true);
						dispose();
					}             
      });
 }
}