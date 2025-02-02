package frogak;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import objektuak.Taldeak;

import java.util.*;

class TaldeakSortuFroga {

	@Test
	void testTaldeakGorde() {
		List<Taldeak> listaTaldeak = new ArrayList<Taldeak>();
		for (int b = 0; b < 7; b++) {
			listaTaldeak.add(new Taldeak());
		}
		
		String izena = "CV Teruel";
		int taldeKod = 1;
		String zelaia = "Pabellon Los Planos";
		boolean errepikatu = false;
            
		for (Taldeak taldea : listaTaldeak) {
			if (taldea.getTaldekod() == taldeKod) {
				errepikatu = true;
			}
		}
		if (errepikatu == false) {
			Taldeak taldea = new Taldeak(taldeKod, izena, zelaia);
		}
	}
}
