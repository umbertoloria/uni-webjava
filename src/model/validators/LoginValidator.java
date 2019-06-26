package model.validators;

public final class LoginValidator {

	public final String emailMsg, passwordMsg;

	public LoginValidator(String email, String password) {
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
	}

	public boolean wrongInput() {
		return emailMsg != null || passwordMsg != null;
	}

}
