package main;

import java.util.ArrayList;
import java.util.List;

public class Jornadas {

    public static List<List<String>> generarJornadas(List<String> equipos) {
        List<List<String>> jornadas = new ArrayList<>();
        int numeroEquipos = equipos.size();

        if (numeroEquipos < 2) {
            throw new IllegalArgumentException("Se necesitan al menos dos equipos para generar jornadas..");
        }

        boolean esImpar = (numeroEquipos % 2 != 0);
        if (esImpar) {
            equipos.add("Descanso");
            numeroEquipos++;
        }

        // Kopia bat sortu aurkariak sartzeko
        List<String> equiposAux = new ArrayList<>(equipos);
        int numJornadas = numeroEquipos - 1;

        // Joango partiduak
        for (int i = 0; i < numJornadas; i++) {
            List<String> jornada = new ArrayList<>();
            for (int j = 0; j < numeroEquipos / 2; j++) {
                String equipoLocal = equiposAux.get(j);
                String equipoVisitante = equiposAux.get(numeroEquipos - 1 - j);

                if (!equipoLocal.equals("Descanso") && !equipoVisitante.equals("Descanso")) {
                    jornada.add(equipoLocal + " vs " + equipoVisitante);
                }
            }
            jornadas.add(jornada);

            // Taldeak biratu lehena izan ezik
            String primerEquipo = equiposAux.remove(0);
            equiposAux.add(equiposAux.size() - 1, primerEquipo);
        }

        // Etorriko partiduak
        List<List<String>> jornadasVuelta = new ArrayList<>();
        for (List<String> jornada : jornadas) {
            List<String> jornadaVuelta = new ArrayList<>();
            for (String partido : jornada) {
                String[] equiposPartido = partido.split(" vs ");
                jornadaVuelta.add(equiposPartido[1] + " vs " + equiposPartido[0]);
            }
            jornadasVuelta.add(jornadaVuelta);
        }
        jornadas.addAll(jornadasVuelta);

        return jornadas;
    }
}
