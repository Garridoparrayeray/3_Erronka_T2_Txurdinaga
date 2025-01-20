package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objektuak.Taldeak;

public class Jornadas implements Serializable{
	private static List<List<String>> sortutakoJardunaldia;

    public static List<List<String>> generarJornadas(List<Taldeak> taldeak) {
        List<List<String>> jornadas = new ArrayList<>();
        int numeroEquipos = taldeak.size();

        if (numeroEquipos < 6) {
            throw new IllegalArgumentException("Se necesitan al menos dos equipos para generar jornadas.");
        }

        boolean esImpar = (numeroEquipos % 2 != 0);
        Taldeak descanso = new Taldeak();
        if (esImpar) {
        	taldeak.add(descanso);
            numeroEquipos++;
        }

        // Kopia bat sortu aurkariak sartzeko
        List<Taldeak> equiposAux = new ArrayList<>(taldeak);
        int numJornadas = numeroEquipos - 1;

        // Joango partiduak
        List<String> jornadaIzena = new ArrayList<String>();
        int counter = 0;
        for (int i = 0; i < numJornadas; i++) {
            List<String> jornada = new ArrayList<>();
            counter++;
            jornadaIzena.add((counter) + ". Jardunaldia.");
            for (int j = 0; j < numeroEquipos / 2; j++) {
                Taldeak equipoLocal = equiposAux.get(j);
                Taldeak equipoVisitante = equiposAux.get(numeroEquipos - 1 - j);

                if (!equipoLocal.equals(descanso) && !equipoVisitante.equals(descanso)) {
                    jornada.add(equipoLocal + " vs " + equipoVisitante);
                }
            }
            jornadas.add(jornada);

            // Taldeak biratu lehena izan ezik
            Taldeak primerEquipo = equiposAux.remove(0);
            equiposAux.add(equiposAux.size() - 1, primerEquipo);
        }

        // Etorriko partiduak
        List<List<String>> jornadasVuelta = new ArrayList<>();
        for (List<String> jornada : jornadas) {
            List<String> jornadaVuelta = new ArrayList<>();
            
            counter++;
            jornadaIzena.add(counter + ". Jardunaldia.");
            for (String partido : jornada) {
                String[] equiposPartido = partido.split(" vs ");
                jornadaVuelta.add(equiposPartido[1] + " vs " + equiposPartido[0]);
            }
            jornadasVuelta.add(jornadaVuelta);
        }
        jornadas.addAll(jornadasVuelta);
        jornadas.add(jornadaIzena);
        
        sortutakoJardunaldia = jornadas;
        return jornadas;
    }
    
    public List<List<String>>getJardunaldia () {
    	return sortutakoJardunaldia;
    }
    
}
