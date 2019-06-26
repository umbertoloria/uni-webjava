package model.validators;

public final class RecensioneValidator {

	public final String votoMsg, titoloMsg, commentoMsg;

	public RecensioneValidator(int voto, String titolo, String commento) {
		if (voto < 1 || voto > 5) {
			votoMsg = "Valore non valido: minimo 1 massimo 5 stelle";
		} else {
			votoMsg = null;
		}
		if (titolo.length() < 3) {
			titoloMsg = "Titolo non valido: minimo 3 caratteri";
		} else if (titolo.length() > 100) {
			titoloMsg = "Titolo non valido: massimo 100 caratteri";
		} else {
			titoloMsg = null;
		}
		if (commento.length() < 3) {
			commentoMsg = "Commento non valido: minimo 3 caratteri";
		} else {
			commentoMsg = null;
		}
	}

	public boolean wrongInput() {
		return votoMsg != null || titoloMsg != null || commentoMsg != null;
	}

}
