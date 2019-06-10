package model.bean;

public class CarrelloItem {

	public final int utente;
	public final int prodotto;
	public final int quantita;

	public CarrelloItem(int prodotto, int quantita) {
		this.utente = -1;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public CarrelloItem(int utente, int prodotto, int quantita) {
		this.utente = utente;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

}
