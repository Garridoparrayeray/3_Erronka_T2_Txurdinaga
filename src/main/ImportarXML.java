package main;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import objektuak.*;

public class ImportarXML {

    // Método para importar el archivo XML y devolver el objeto Jornadas
    public Jornadas importFromXML(String filename) throws Exception {
        // Crear un parser XML
        FileInputStream fileInputStream = new FileInputStream(filename);
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(fileInputStream);
        
        // Normalizar el documento XML
        document.getDocumentElement().normalize();

        // Extraer el nodo raíz <Jornada>
        Element root = document.getDocumentElement();

        // Extraer los datos de Denboraldia (Temporada)
        Denboraldiak denboraldia = loadDenboraldia(root);

        // Crear la lista de Jornadas
        List<Jardunaldiak> jardunaldiak = loadJardunaldiak(root);
        
        List<Partiduak> partiduak = loadPartiduak(root);

        // Crear y devolver el objeto Jornadas
        Jornadas jornadasObj = new Jornadas();
        jornadasObj.setDenboraldiak(denboraldia);
        jornadasObj.setJardunaldiak(jardunaldiak);
        jornadasObj.setPartiduak(partiduak);
        return jornadasObj;
    }

    // Cargar la información de Denboraldia (Temporada) desde el XML
    private Denboraldiak loadDenboraldia(Element root) {
        NodeList denboraldiaList = root.getElementsByTagName("Denboraldia");
        Element denboraldiaElement = (Element) denboraldiaList.item(0);

        String izena = denboraldiaElement.getElementsByTagName("denboraldi_kod").item(0).getTextContent();
        LocalDate hasieraData = LocalDate.parse(denboraldiaElement.getElementsByTagName("hasiera_data").item(0).getTextContent());
        LocalDate amaieraData = LocalDate.parse(denboraldiaElement.getElementsByTagName("amaiera_data").item(0).getTextContent());

        return new Denboraldiak(izena, hasieraData, amaieraData);
    }

    // Cargar las jornadas desde el XML
    private List<Jardunaldiak> loadJardunaldiak(Element root) {
        List<Jardunaldiak> jardunaldiak = new ArrayList<>();

        NodeList jardunaldiakList = root.getElementsByTagName("jardunaldiak");
        for (int i = 0; i < jardunaldiakList.getLength(); i++) {
            Element jardunaldiakElement = (Element) jardunaldiakList.item(i);

            String jardunaldi_deskribapena = jardunaldiakElement.getElementsByTagName("jardunaldi_deskribapena").item(0).getTextContent();
            int partidu_kopurua = Integer.valueOf(jardunaldiakElement.getElementsByTagName("partidu_kopurua").item(0).getTextContent());
            Jardunaldiak jardunaldia = new Jardunaldiak(jardunaldi_deskribapena, partidu_kopurua);
            jardunaldiak.add(jardunaldia);
        }

        return jardunaldiak;
    }
    
    private List<Partiduak> loadPartiduak(Element root) {
    	List<Partiduak> partiduak = new ArrayList<Partiduak>();
    	List<Jardunaldiak> jardunaldiak = new ArrayList<>();

      NodeList jardunaldiakList = root.getElementsByTagName("jardunaldiak");
      for (int i = 0; i < jardunaldiakList.getLength(); i++) {
          Element jardunaldiakElement = (Element) jardunaldiakList.item(i);

          String jardunaldi_deskribapena = jardunaldiakElement.getElementsByTagName("jardunaldi_deskribapena").item(0).getTextContent();
          int partidu_kopurua = Integer.valueOf(jardunaldiakElement.getElementsByTagName("partidu_kopurua").item(0).getTextContent());
          Jardunaldiak jardunaldia = new Jardunaldiak(jardunaldi_deskribapena, partidu_kopurua);

          // Cargar los partidos de la jornada
          NodeList patiduakList = jardunaldiakElement.getElementsByTagName("patiduak");
          for (int j = 0; j < patiduakList.getLength(); j++) {
              Element patiduakElement = (Element) patiduakList.item(j);

              NodeList patiduaList = patiduakElement.getElementsByTagName("patidua");
              for (int k = 0; k < patiduaList.getLength(); k++) {
                  Element patiduaElement = (Element) patiduaList.item(k);

                  String etxekoTaldea = patiduaElement.getElementsByTagName("etxeko_taldea").item(0).getTextContent();
                  String kanpokoTaldea = patiduaElement.getElementsByTagName("kanpoko_taldea").item(0).getTextContent();
                  String zelaia = patiduaElement.getElementsByTagName("zelaia").item(0).getTextContent();
                  int jardunaldi_kod = Integer.valueOf(patiduaElement.getElementsByTagName("jardunaldia_kod").item(0).getTextContent());
                  
                  
                  
                  Partiduak partidua = new Partiduak(etxekoTaldea, kanpokoTaldea, zelaia, jardunaldia);
                  partiduak.add(partidua);
              }
          }
      }
    	
    	return partiduak;
    }
}
