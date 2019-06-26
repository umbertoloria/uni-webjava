package model.bean;

public class CartaCredito {

	public final String numero, mese, cvv;
	public final int anno, utente;
	public final float saldo;

	public CartaCredito(String numero, String mese, int anno, String cvv, float saldo, int utente) {
		this.numero = numero;
		this.mese = mese;
		this.anno = anno;
		this.cvv = cvv;
		this.saldo = saldo;
		this.utente = utente;
	}

	public String toString() {
		return numero + ", " + mese + " " + anno + ", " + cvv;
	}

}
