package frogak;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import objektuak.*;
import main.*;

/*
 * Froga guztiak egiteko, interfaze grafiko gabe egiteko moldatu egin dira. 
 * Adibidez, inportatzeko edo exportatzeko interfaze grafikoa, edo parametroak frogan jartzen badituzu, 
 * errorea emango du, froga egiteko orduan aplikazioa emango zizkion datuak faltatzen zaiolako. Azkenean frogak aislatuak dira.
 */
class IrteeraSarreraXMLFroga {
	
	@Test
	void testIrteeraXML() {
		Jornadas jornada = new Jornadas();
		File fileToSave = new File("jornadas.xml");
		Denboraldiak denboraldia = jornada.getDenboraldia();
		List<Jardunaldiak> jardunaldiak = jornada.getJardunaldiak();
		List<Partiduak> partiduak = jornada.getPartiduak();
		
		DocumentBuilderFactory fabricaEpica = DocumentBuilderFactory.newInstance(); 
		try {
			DocumentBuilder herramienta = fabricaEpica.newDocumentBuilder(); 
			Document xmlDokumentua = herramienta.newDocument(); 
			
			Element root = xmlDokumentua.createElement("jornadas"); 
			xmlDokumentua.appendChild(root);
			
			Element xmlDenboraldia = xmlDokumentua.createElement("denboraldia");
			root.appendChild(xmlDenboraldia);
			
			Element denboraldi_kod = xmlDokumentua.createElement("denboraldi_kod");
			denboraldi_kod.appendChild(xmlDokumentua.createTextNode(denboraldia.getDenboraldi_kod() + ""));
			Element hasiera_dataD = xmlDokumentua.createElement("denboraldi_hasiera_data");
			hasiera_dataD.appendChild(xmlDokumentua.createTextNode(denboraldia.getHasiera_data()));
			Element amaiera_dataD = xmlDokumentua.createElement("denboraldi_amaiera_data");
			amaiera_dataD.appendChild(xmlDokumentua.createTextNode(denboraldia.getAmaiera_data()));
			
			xmlDenboraldia.appendChild(denboraldi_kod);
			xmlDenboraldia.appendChild(hasiera_dataD);
			xmlDenboraldia.appendChild(amaiera_dataD);
			
			Element xmlJardunaldiak = xmlDokumentua.createElement("jardunaldiak");
			root.appendChild(xmlJardunaldiak);
			
			int counter = 0;
	        for (int i = 0; i < jardunaldiak.size(); i++) {
	        	Jardunaldiak jardunaldia = jardunaldiak.get(i);
	        	Element xmlJardunaldia = xmlDokumentua.createElement("jardunaldia");
	        	xmlJardunaldiak.appendChild(xmlJardunaldia);
	        	
	        	Element jardunaldi_kod = xmlDokumentua.createElement("jardunaldi_kod");
	        	jardunaldi_kod.appendChild(xmlDokumentua.createTextNode(jardunaldia.getJardunaldi_kod() + ""));
	        	Element jardunaldi_deskribapena = xmlDokumentua.createElement("jardunaldi_deskribapena");
	        	jardunaldi_deskribapena.appendChild(xmlDokumentua.createTextNode(jardunaldia.getJardunaldi_deskribapena()));
	        	Element hasiera_dataJ = xmlDokumentua.createElement("jardunaldi_hasiera_data");
	        	hasiera_dataJ.appendChild(xmlDokumentua.createTextNode(jardunaldia.getHasiera_data()));
	        	Element amaiera_dataJ = xmlDokumentua.createElement("jardunaldi_amaiera_data");
	        	amaiera_dataJ.appendChild(xmlDokumentua.createTextNode(jardunaldia.getAmaiera_data()));
	        	Element partidu_kopurua = xmlDokumentua.createElement("partidu_kopurua");
	        	partidu_kopurua.appendChild(xmlDokumentua.createTextNode(jardunaldia.getPartidu_kopurua() + ""));
	        	Element xmlPartiduak = xmlDokumentua.createElement("partiduak");
	        	
	        	xmlJardunaldia.appendChild(jardunaldi_kod);
	        	xmlJardunaldia.appendChild(jardunaldi_deskribapena);
	        	xmlJardunaldia.appendChild(hasiera_dataJ);
	        	xmlJardunaldia.appendChild(amaiera_dataJ);
	        	xmlJardunaldia.appendChild(partidu_kopurua);
	        	xmlJardunaldia.appendChild(xmlPartiduak);
	        	
	        	for (int i2 = 0; i2 < jardunaldia.getPartidu_kopurua(); i2++) {
	        		Partiduak partidua = partiduak.get(counter);
	        		
	        		Element xmlPartidua = xmlDokumentua.createElement("partidua");
	        		xmlPartiduak.appendChild(xmlPartidua);
	        		
	        		Element partidu_kod = xmlDokumentua.createElement("partidu_kod");
	        		partidu_kod.appendChild(xmlDokumentua.createTextNode(partidua.getPartidu_kod() + ""));
	        		xmlPartidua.appendChild(partidu_kod);
	        		
	        		Element xmlEtxekoTaldea = xmlDokumentua.createElement("etxeko_taldea");
	        		Element talde_kodE = xmlDokumentua.createElement("talde_kod");
	        		talde_kodE.appendChild(xmlDokumentua.createTextNode(partidua.getEtxeko_taldea().getTaldekod() + ""));
	        		Element izenaE = xmlDokumentua.createElement("izena");
	        		izenaE.appendChild(xmlDokumentua.createTextNode(partidua.getEtxeko_taldea().getIzena()));
	        		Element zelaiaE = xmlDokumentua.createElement("zelaia");
	        		zelaiaE.appendChild(xmlDokumentua.createTextNode(partidua.getEtxeko_taldea().getZelaia()));
	        		Element herriaE = xmlDokumentua.createElement("herria");
	        		herriaE.appendChild(xmlDokumentua.createTextNode(partidua.getEtxeko_taldea().getHerrialdea()));
	        		Element puntuazioaE = xmlDokumentua.createElement("puntuazioa");
	        		puntuazioaE.appendChild(xmlDokumentua.createTextNode(partidua.getEtxekoTaldekoPuntuazioa() + ""));
	        		
	        		xmlEtxekoTaldea.appendChild(talde_kodE);
	        		xmlEtxekoTaldea.appendChild(izenaE);
	        		xmlEtxekoTaldea.appendChild(zelaiaE);
	        		xmlEtxekoTaldea.appendChild(herriaE);
	        		xmlEtxekoTaldea.appendChild(puntuazioaE);
	        		xmlPartidua.appendChild(xmlEtxekoTaldea);
	        		
	        		Element xmlKanpokoTaldea = xmlDokumentua.createElement("kanpoko_taldea");
	        		Element talde_kodK = xmlDokumentua.createElement("talde_kod");
	        		talde_kodK.appendChild(xmlDokumentua.createTextNode(partidua.getKanpoko_taldea().getTaldekod() + ""));
	        		Element izenaK = xmlDokumentua.createElement("izena");
	        		izenaK.appendChild(xmlDokumentua.createTextNode(partidua.getKanpoko_taldea().getIzena()));
	        		Element zelaiaK = xmlDokumentua.createElement("zelaia");
	        		zelaiaK.appendChild(xmlDokumentua.createTextNode(partidua.getKanpoko_taldea().getZelaia()));
	        		Element herriaK = xmlDokumentua.createElement("herria");
	        		herriaK.appendChild(xmlDokumentua.createTextNode(partidua.getKanpoko_taldea().getHerrialdea()));
	        		Element puntuazioaK = xmlDokumentua.createElement("puntuazioa");
	        		puntuazioaK.appendChild(xmlDokumentua.createTextNode(partidua.getKanpokoTaldekoPuntuazioa() + ""));
	        		
	        		xmlKanpokoTaldea.appendChild(talde_kodK);
	        		xmlKanpokoTaldea.appendChild(izenaK);
	        		xmlKanpokoTaldea.appendChild(zelaiaK);
	        		xmlKanpokoTaldea.appendChild(herriaK);
	        		xmlKanpokoTaldea.appendChild(puntuazioaK);
	        		xmlPartidua.appendChild(xmlKanpokoTaldea);
	        		
	        		Element zelaiaP = xmlDokumentua.createElement("partidu_zelaia");
	        		zelaiaP.appendChild(xmlDokumentua.createTextNode(partidua.getEtxeko_taldea().getZelaia()));
	        		xmlPartidua.appendChild(zelaiaP);
	        		
	        		counter++;
	        	}
	        }	
			
	        TransformerFactory autobotsFabrica = TransformerFactory.newInstance(); // Transformatzeko tresnak dituen objetua
        	try {
        		Transformer optimusPrime = autobotsFabrica.newTransformer(); // Transformatzeko objetua
        		optimusPrime.setOutputProperty(OutputKeys.INDENT, "yes"); // Guk errazago irakurtzeko da.
        		
        		DOMSource DOOM = new DOMSource(xmlDokumentua); // transformatu nahi duzun dokumentua aukeratzeko (ordenagailuari abisatzeko hori zehazki transformatu nahi duzula)
        		StreamResult STEAM = new StreamResult(fileToSave); // xml Dokumentuaren irteera helmuga ordenagailuari adierazteko da
        		System.out.println("Ondo generatu/sortu da :)..."); // Ondo atera dela mezua :)
        		try {
        			optimusPrime.transform(DOOM, STEAM);
        		} catch (TransformerException OP2) {
        			JOptionPane.showMessageDialog(null, "TransformerException motatako errorea eman du.", "Errorea", JOptionPane.ERROR_MESSAGE);
        		}
        	} catch (TransformerConfigurationException OP) {// OP = Optimus Prime XD :)
        		JOptionPane.showMessageDialog(null, "TransformerConfigurationException motatako errorea eman du.", "Errorea", JOptionPane.ERROR_MESSAGE);
        	}
			//try {
				
			//} catch (IOException | SAXException ios) {
				//JOptionPane.showMessageDialog(null, "Aplikazioa ondo porta mesdez :(", "Errorea", JOptionPane.ERROR_MESSAGE);
			//}
		} catch(ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, "ParserConfigurationException", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Test
	void SarreraXML() {
		File xmlFile = new File("jornadas.xml");
		Jornadas jornada = new Jornadas();
		Denboraldiak denboraldia = new Denboraldiak();
		List<Jardunaldiak> jardunaldiak = new ArrayList<Jardunaldiak>();
		List<Partiduak> partiduak = new ArrayList<Partiduak>();
		List<Taldeak> taldeak = new ArrayList<Taldeak>();
		
		DocumentBuilderFactory fabricaEpica = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder herramientaEpica = fabricaEpica.newDocumentBuilder();
			File xmlFitxategia = xmlFile;
			try {
				Document xmlDokumentua = herramientaEpica.parse(xmlFitxategia);
				xmlDokumentua.getDocumentElement().normalize();
				Element root = xmlDokumentua.getDocumentElement();
				Element xmlDenboraldia = (Element) root.getElementsByTagName("denboraldia").item(0); // Denboraldia bere jornadas-en barruan dagoen lehenengo etiqueta da :)
				int denboraldi_kod = Integer.valueOf(xmlDenboraldia.getElementsByTagName("denboraldi_kod").item(0).getTextContent());
				String hasieraDataD = xmlDenboraldia.getElementsByTagName("denboraldi_hasiera_data").item(0).getTextContent();
				String amaieraDataD = xmlDenboraldia.getElementsByTagName("denboraldi_amaiera_data").item(0).getTextContent();
				denboraldia.setDenboraldi_kod(denboraldi_kod);
				denboraldia.setHasiera_data(hasieraDataD);
				denboraldia.setAmaiera_data(amaieraDataD);
				
				Element xmlJardunaldiak = (Element) root.getElementsByTagName("jardunaldiak").item(0);
				NodeList nodeListJardunaldiak = xmlJardunaldiak.getElementsByTagName("jardunaldia");
				for (int i = 0; i < nodeListJardunaldiak.getLength() ; i++) {
					Element xmlJardunaldia = (Element) nodeListJardunaldiak.item(i);
					int jardunaldi_kod = Integer.valueOf(xmlJardunaldia.getElementsByTagName("jardunaldi_kod").item(0).getTextContent());
					String jardunaldi_deskribapena = xmlJardunaldia.getElementsByTagName("jardunaldi_deskribapena").item(0).getTextContent();
					String hasieraDataJ = xmlJardunaldia.getElementsByTagName("jardunaldi_hasiera_data").item(0).getTextContent();
					String amaieraDataJ = xmlJardunaldia.getElementsByTagName("jardunaldi_amaiera_data").item(0).getTextContent();
					Jardunaldiak jardunaldia = new Jardunaldiak(jardunaldi_kod, jardunaldi_deskribapena, hasieraDataJ, amaieraDataJ, denboraldia);
					
					Element xmlPartiduak = (Element) xmlJardunaldia.getElementsByTagName("partiduak").item(0);
					NodeList nodeListPartiduak = xmlPartiduak.getElementsByTagName("partidua");
					for (int i2 = 0; i2 < nodeListPartiduak.getLength(); i2++) {
						Element xmlPartidua = (Element) nodeListPartiduak.item(i2);
						int partidu_kod = Integer.valueOf(xmlPartidua.getElementsByTagName("partidu_kod").item(0).getTextContent());
						Element xmlEtxekoTaldea = (Element) xmlPartidua.getElementsByTagName("etxeko_taldea").item(0);
						int talde_kodE = Integer.valueOf(xmlEtxekoTaldea.getElementsByTagName("talde_kod").item(0).getTextContent());
						String izenaE = xmlEtxekoTaldea.getElementsByTagName("izena").item(0).getTextContent();
						String zelaiaE = xmlEtxekoTaldea.getElementsByTagName("zelaia").item(0).getTextContent();
						String herriaE = xmlEtxekoTaldea.getElementsByTagName("herria").item(0).getTextContent();
						int puntuazioaE = Integer.valueOf(xmlEtxekoTaldea.getElementsByTagName("puntuazioa").item(0).getTextContent());
						Taldeak etxekoTaldea = new Taldeak(talde_kodE, izenaE, zelaiaE, herriaE);
						boolean errepikatuta = false;
						for (Taldeak talde : taldeak) {
							if (talde.equals(etxekoTaldea)) {
								errepikatuta = true;
							}
						}
						if (errepikatuta == false) {
							taldeak.add(etxekoTaldea);
						}
						Element xmlKanpokoTaldea = (Element) xmlPartidua.getElementsByTagName("kanpoko_taldea").item(0);
						int talde_kodK = Integer.valueOf(xmlKanpokoTaldea.getElementsByTagName("talde_kod").item(0).getTextContent());
						String izenaK = xmlKanpokoTaldea.getElementsByTagName("izena").item(0).getTextContent();
						String zelaiaK = xmlKanpokoTaldea.getElementsByTagName("zelaia").item(0).getTextContent();
						String herriaK = xmlKanpokoTaldea.getElementsByTagName("herria").item(0).getTextContent();
						int puntuazioaK = Integer.valueOf(xmlKanpokoTaldea.getElementsByTagName("puntuazioa").item(0).getTextContent());
						Taldeak kanpokoTaldea = new Taldeak(talde_kodK, izenaK, zelaiaK, herriaK);
						errepikatuta = false;
						for (Taldeak talde : taldeak) {
							if (talde.equals(kanpokoTaldea)) {
								errepikatuta = true;
							}
						}
						if (errepikatuta == false) {
							taldeak.add(kanpokoTaldea);
						}
						
						String partidu_zelaia = etxekoTaldea.getZelaia();
						Partiduak partidua = new Partiduak(partidu_kod, etxekoTaldea, kanpokoTaldea, partidu_zelaia, puntuazioaE, puntuazioaK, jardunaldia);
						partiduak.add(partidua);
					}
					jardunaldia.setPartidu_kopurua(nodeListPartiduak.getLength());
					jardunaldiak.add(jardunaldia);
				}
				
				boolean errepikatua;
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres guardar los equipos?", "Confirmar Guardado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
				if (respuesta == JOptionPane.YES_OPTION) {
					
          List<Taldeak> listaTaldeak = TaldeenErabilpena.irakurriTaldeak();
          for (int i = 0; i < taldeak.size(); i++) {
          	errepikatua = false;
          	Taldeak taldea = taldeak.get(i);
          	for (int i2 = 0; i2 < listaTaldeak.size(); i2++) {
          		Taldeak taldea2 = listaTaldeak.get(i2);
          		if (taldea.equals(taldea2)) {
          			errepikatua = true;
          		}
          	}
          	if (errepikatua == false) {
          		listaTaldeak.add(taldea);
          	}
          }
          TaldeenErabilpena.gordeTaldeak(listaTaldeak);
          
      } else if (respuesta == JOptionPane.NO_OPTION) {
          JOptionPane.showMessageDialog(null, "Los equipos no fueron guardados.");
      } else {
          JOptionPane.showMessageDialog(null, "Operación cancelada.");
      }
			
			} catch (IOException | SAXException IS) {
				JOptionPane.showMessageDialog(null, "IOException edo SAXException motatako errore bat gertatu da....", "Errorea", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (ParserConfigurationException PCE) {
			JOptionPane.showMessageDialog(null, "ParserConfigurationException", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
		jornada.setDenboraldia(denboraldia);
		jornada.setJardunaldiak(jardunaldiak);
		jornada.setPartiduak(partiduak);
		jornada.setTaldeak(taldeak);
	}
	
	@Test
	void LOG() {
		String gertaera = ("gertaera bat");
		String logFileName = "log.txt";
		File logFile = new File(logFileName);
		
		if (logFile.exists()) {
			try {
				List<String> txtLineak = Files.readAllLines(Paths.get(logFileName));
				txtLineak.add(gertaera);
				try (FileWriter fw = new FileWriter(logFile, true); PrintWriter pw = new PrintWriter(fw)) {
					for (String textua : txtLineak) {
						pw.println(textua);
					}
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, "Idazteko orduan, IOException errorea eman du. Berriro saiatu mesdez...", "IOException Errorea", JOptionPane.ERROR_MESSAGE);
				} 
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Irakurtzeko orduan, IOException errorea eman du. Berriro saiatu mesdez...", "IOException Errorea", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			try (FileWriter fw = new FileWriter(logFile, true); PrintWriter pw = new PrintWriter(fw)) {
				pw.println(gertaera);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Idazteko orduan, IOException errorea eman du. Berriro saiatu mesdez...", "IOException Errorea", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
