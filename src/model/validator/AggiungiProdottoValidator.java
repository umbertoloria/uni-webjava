package model.validator;

import util.RegularExpressions;

public class AggiungiProdottoValidator {

	public final String nomeMsg, descrizioneMsg, prezzoMsg;

	public AggiungiProdottoValidator(String nome, String descrizione, String prezzo) {
		if (nome.length() < 3) {
			nomeMsg = "Nome non valido: minimo 3 caratteri";
		} else if (nome.length() > 50) {
			nomeMsg = "Nome non valido: massimo 50 caratteri";
		} else {
			nomeMsg = null;
		}
		if (descrizione.length() < 3) {
			descrizioneMsg = "Descrizione non valida: minimo 10 caratteri";
		} else {
			descrizioneMsg = null;
		}
		if (!prezzo.matches(RegularExpressions.PREZZO)) {
			prezzoMsg = "Prezzo non valido";
		} else {
			prezzoMsg = null;
		}
	}

	public boolean wrongInput() {
		return nomeMsg != null || descrizioneMsg != null || prezzoMsg != null;
	}

}
