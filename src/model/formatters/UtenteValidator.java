package model.formatters;

import model.bean.Utente;

public final class UtenteValidator {

	public final String nome, email, password;

	public UtenteValidator(Utente u, boolean validateNome) {
		if (validateNome) {
			if (u.nome.length() < 3) {
				nome = "Nome non valido: minimo 3 caratteri";
			} else if (u.nome.length() > 40) {
				nome = "Nome non valido: massimo 40 caratteri";
			} else {
				nome = null;
			}
		} else {
			nome = null;
		}
		if (u.email.length() < 3) {
			email = "E-Mail non valida: minimo 3 caratteri";
		} else if (u.email.length() > 40) {
			email = "E-Mail non valida: massimo 40 caratteri";
		} else {
			email = null;
		}
		if (u.password.length() < 3) {
			password = "Password non valida: minimo 3 caratteri";
		} else if (u.password.length() > 16) {
			password = "Password non valida: massimo 16 caratteri";
		} else {
			password = null;
		}
	}

	public boolean empty() {
		return nome == null && email == null && password == null;
	}

}
