package model.bean;

public class OrdineHasProdotto {

	public final int prodotto;
	public final float prezzo;
	public final int quantita;

	public OrdineHasProdotto(int prodotto, float prezzo, int quantita) {
		this.prodotto = prodotto;
		this.prezzo = prezzo;
		this.quantita = quantita;
	}

}
