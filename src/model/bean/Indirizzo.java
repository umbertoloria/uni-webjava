package model.bean;

public final class Indirizzo {

	public final int id;
	public final String nome, indirizzo, citta, cap, provincia;
	public final int utente;

	public Indirizzo(int id, String nome, String indirizzo, String citta, String cap, String provincia, int utente) {
		this.id = id;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.provincia = provincia;
		this.utente = utente;
	}

	public Indirizzo(String nome, String indirizzo, String citta, String cap, String provincia, int utente) {
		this.id = -1;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.provincia = provincia;
		this.utente = utente;
	}

	public String toString() {
		return indirizzo + ", " + citta + ", " + cap + ", " + provincia;
	}

}
