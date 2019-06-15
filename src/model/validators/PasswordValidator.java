package model.validators;

public class PasswordValidator {
	public final String nuova;
	public final String conferma;

	public PasswordValidator(String nuova, String conferma) {
		if (nuova.length() < 3) {
			this.nuova = "Password non valida: minimo 3 caratteri";
		} else if (nuova.length() > 16) {
			this.nuova = "Password non valida: massimo 16 caratteri";
		} else {
			this.nuova = null;
		}
		if (conferma.length() < 3) {
			this.conferma = "Password non valida: minimo 3 caratteri";
		} else if (conferma.length() > 16) {
			this.conferma = "Password non valida: massimo 16 caratteri";
		} else {
			this.conferma = null;
		}
	}

	public boolean wrongInput() {
		return nuova != null || conferma != null;
	}

}
