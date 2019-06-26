package model.bean;

public final class Recensione {

	public final int id, prodotto, voto, utente;
	public final String titolo, commento, momento;

	public Recensione(int id, int prodotto, int voto, String titolo, String commento, int utente, String momento) {
		if (voto < 1 || voto > 5) {
			throw new RuntimeException();
		}
		this.id = id;
		this.prodotto = prodotto;
		this.voto = voto;
		this.titolo = titolo;
		this.commento = commento;
		this.utente = utente;
		this.momento = momento;
	}

	public Recensione(int prodotto, int voto, String titolo, String commento, int utente) {
		if (voto < 1 || voto > 5) {
			throw new RuntimeException();
		}
		this.id = -1;
		this.prodotto = prodotto;
		this.voto = voto;
		this.titolo = titolo;
		this.commento = commento;
		this.utente = utente;
		this.momento = null;
	}

	public int getVoto() {
		return voto;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getCommento() {
		return commento;
	}

}
