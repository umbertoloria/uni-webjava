package model.validator;

public final class RegistrazioneValidator {

	public final String nomeMsg, emailMsg, passwordMsg, password2Msg;

	public RegistrazioneValidator(String nome, String email, String password, String password2) {
		if (nome.length() < 3) {
			nomeMsg = "Nome non valido: minimo 3 caratteri";
		} else if (nome.length() > 40) {
			nomeMsg = "Nome non valido: massimo 40 caratteri";
		} else {
			nomeMsg = null;
		}
		if (email.length() < 3) {
			emailMsg = "E-Mail non valida: minimo 3 caratteri";
		} else if (email.length() > 40) {
			emailMsg = "E-Mail non valida: massimo 40 caratteri";
		} else if (!email.contains("@")) {
			emailMsg = "E-Mail malformata";
		} else {
			emailMsg = null;
		}
		if (password.length() < 3) {
			passwordMsg = "Password non valida: minimo 3 caratteri";
		} else if (password.length() > 16) {
			passwordMsg = "Password non valida: massimo 16 caratteri";
		} else {
			passwordMsg = null;
		}
		if (!password.equals(password2)) {
			password2Msg = "Le password devono coincidere";
		} else {
			password2Msg = null;
		}
	}

	public boolean wrongInput() {
		return nomeMsg != null || emailMsg != null || passwordMsg != null || password2Msg != null;
	}

}
