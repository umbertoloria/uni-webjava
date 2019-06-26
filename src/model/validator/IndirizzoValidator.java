package model.validator;

import model.bean.Indirizzo;

public final class IndirizzoValidator {

	public final String nome, indirizzo, citta, cap, provincia;

	public IndirizzoValidator(Indirizzo i) {
		if (i.nome.length() < 3) {
			nome = "Nome non valido: minimo 3 caratteri";
		} else if (i.nome.length() > 100) {
			nome = "Nome non valido: massimo 100 caratteri";
		} else {
			nome = null;
		}
		if (i.indirizzo.length() < 3) {
			indirizzo = "Indirizzo non valido: minimo 3 caratteri";
		} else if (i.nome.length() > 100) {
			indirizzo = "Indirizzo non valido: massimo 100 caratteri";
		} else {
			indirizzo = null;
		}
		if (i.citta.length() < 3) {
			citta = "Città non valida: minimo 3 caratteri";
		} else if (i.citta.length() > 50) {
			citta = "Città non valida: massimo 50 caratteri";
		} else {
			citta = null;
		}
		if (i.cap.length() != 5) {
			cap = "CAP non valido: sempre 5 caratteri numerici";
		} else {
			int j;
			for (j = 0; j < i.cap.length(); j++) {
				if (!Character.isDigit(i.cap.charAt(j))) {
					break;
				}
			}
			if (j < i.cap.length()) {
				cap = "CAP non valido: sempre 5 caratteri numerici";
			} else {
				cap = null;
			}
		}
		if (i.provincia.length() < 3) {
			provincia = "Provincia non valida: minimo 3 caratteri";
		} else if (i.provincia.length() > 50) {
			provincia = "Provincia non valida: massimo 50 caratteri";
		} else {
			provincia = null;
		}
	}

	public boolean wrongInput() {
		return nome != null || indirizzo != null || citta != null || cap != null || provincia != null;
	}

}
