package main;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.List;

import objektuak.*;

public class ExportarXML {

    // Método para exportar la información de la clase Jornadas a un archivo XML
    public void exportToXML(Jornadas jornadas, String filename) throws Exception {
        // Crear un documento XML vacío
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // Crear el nodo raíz <Jornada>
        Element root = document.createElement("Jornada");
        document.appendChild(root);

        // Exportar la información de Denboraldia (Temporada)
        Denboraldiak denboraldia = jornadas.getDenboraldiak();
        Element denboraldiaElement = document.createElement("Denboraldia");
        root.appendChild(denboraldiaElement);

        // Agregar los elementos dentro de <Denboraldia>
        Element denboraldiKod = document.createElement("denboraldi_kod");
        denboraldiKod.appendChild(document.createTextNode(denboraldia.getDenboraldi_kod() + ""));
        denboraldiaElement.appendChild(denboraldiKod);

        Element hasieraData = document.createElement("hasiera_data");
        hasieraData.appendChild(document.createTextNode(denboraldia.getHasiera_data() + ""));
        denboraldiaElement.appendChild(hasieraData);

        Element amaieraData = document.createElement("amaiera_data");
        amaieraData.appendChild(document.createTextNode(denboraldia.getAmaiera_data() + ""));
        denboraldiaElement.appendChild(amaieraData);

        // Exportar las jornadas
        Element jardunaldiakElement = document.createElement("jardunaldiak");
        denboraldiaElement.appendChild(jardunaldiakElement);

        for (Jardunaldiak jardunaldia : jornadas.getJardunaldiak()) {
            Element jardunaldiaElement = document.createElement("jardunaldia");
            jardunaldiakElement.appendChild(jardunaldiaElement);

            // Agregar datos de la jornada
            Element jardunaldiKod = document.createElement("jardunaldi_kod");
            jardunaldiKod.appendChild(document.createTextNode(jardunaldia.getJardunaldi_kod() + ""));
            jardunaldiakElement.appendChild(jardunaldiKod);

            Element jardunaldiDeskribapena = document.createElement("jardunaldi_deskribapena");
            jardunaldiDeskribapena.appendChild(document.createTextNode(jardunaldia.getJardunaldi_deskribapena()));
            jardunaldiakElement.appendChild(jardunaldiDeskribapena);

            Element partiduKopurua = document.createElement("partidu_kopurua");
            partiduKopurua.appendChild(document.createTextNode(String.valueOf(jornadas.getPartiduak().size())));
            jardunaldiakElement.appendChild(partiduKopurua);

            // Exportar los partidos
            Element patiduakElement = document.createElement("patiduak");
            jardunaldiakElement.appendChild(patiduakElement);

            for (Partiduak patidua : jornadas.getPartiduak()) {
                Element patiduaElement = document.createElement("patidua");
                patiduakElement.appendChild(patiduaElement);

                // Agregar los detalles del partido
                Element patiduaKod = document.createElement("patidua_kod");
                patiduaKod.appendChild(document.createTextNode(patidua.getPartidu_kod() + ""));
                patiduaElement.appendChild(patiduaKod);

                Element etxekoTaldea = document.createElement("etxeko_taldea");
                etxekoTaldea.appendChild(document.createTextNode(patidua.getEtxeko_taldea()));
                patiduaElement.appendChild(etxekoTaldea);
                
                Element kanpokoTaldea = document.createElement("kanpoko_taldea");
                kanpokoTaldea.appendChild(document.createTextNode(patidua.getKanpoko_taldea()));
                patiduaElement.appendChild(kanpokoTaldea);
                
                Element zelaia = document.createElement("zelaia");
                zelaia.appendChild(document.createTextNode(patidua.getZelaia()));
                patiduaElement.appendChild(zelaia);
                
                Element jardunaldiaKod = document.createElement("jardunaldia_kod");
                jardunaldiaKod.appendChild(document.createTextNode(patidua.getJardunaldiak().getJardunaldi_kod() + ""));
                patiduaElement.appendChild(jardunaldiaKod);
                
            }
        }

        // Crear el objeto Transformer para escribir el XML en un archivo
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // Definir el formato de salida (indentación)
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Crear el archivo de salida
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileWriter(filename));

        // Escribir el contenido en el archivo
        transformer.transform(source, result);
    }
}
