package model.bean;

public final class Prodotto {

	public final int id, sottocategoria, produttore, immagine;
	public final String nome, descrizione;
	public final float prezzo;

	public Prodotto(int id, int sottocategoria, String nome, int produttore, float prezzo, int immagine,
	                String descrizione) {
		this.id = id;
		this.sottocategoria = sottocategoria;
		this.nome = nome;
		this.produttore = produttore;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.descrizione = descrizione;
	}

	public Prodotto(int sottocategoria, String nome, int produttore, float prezzo, int immagine,
	                String descrizione) {
		this.id = -1;
		this.sottocategoria = sottocategoria;
		this.nome = nome;
		this.produttore = produttore;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.descrizione = descrizione;
	}

	public int getId() {
		return id;
	}

}
