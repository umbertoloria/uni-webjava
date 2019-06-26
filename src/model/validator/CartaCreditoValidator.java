package model.validator;

import util.Formats;

public final class CartaCreditoValidator {

	public final String numeroMsg, meseMsg, annoMsg, cvvMsg, saldoMsg;

	public CartaCreditoValidator(String numero, String mese, int anno, String cvv, float saldo) {
		if (numero.length() != 16) {
			numeroMsg = "Numero carta non valido: richieste 16 cifre";
		} else {
			numeroMsg = null;
		}
		boolean contained = false;
		for (String s : Formats.mesi) {
			if (mese.equals(s)) {
				contained = true;
				break;
			}
		}
		if (!contained) {
			meseMsg = "Mese non valido";
		} else {
			meseMsg = null;
		}
		if (anno < 2018 || anno > 2026) {
			annoMsg = "Anno non valido: minimo 2018 massimo 2026";
		} else {
			annoMsg = null;
		}
		if (cvv.length() != 4 && cvv.length() != 3) {
			cvvMsg = "CVV non valido: minimo 3 massimo 4 cifre";
		} else {
			boolean number = true;
			for (int i = 0; i < cvv.length(); i++) {
				if (!Character.isDigit(cvv.charAt(i))) {
					number = false;
					break;
				}
			}
			if (!number) {
				cvvMsg = "CVV non valido: minimo 3 massimo 4 cifre";
			} else {
				cvvMsg = null;
			}
		}
		if (saldo <= 0) {
			saldoMsg = "Saldo non positivo";
		} else {
			saldoMsg = null;
		}
	}

	public boolean wrongInput() {
		return numeroMsg != null || meseMsg != null || annoMsg != null || cvvMsg != null || saldoMsg != null;
	}

}
