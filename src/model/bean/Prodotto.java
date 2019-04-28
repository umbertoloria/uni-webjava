package model.bean;

public final class Prodotto {

	public final int id, sottocategoria;
	public final String nome;
	public final int produttore;
	public final float prezzo;
	public final String immagine, descrizione;

	public Prodotto(int id, int sottocategoria, String nome, int produttore, float prezzo, String immagine,
	                String descrizione) {
		this.id = id;
		this.sottocategoria = sottocategoria;
		this.nome = nome;
		this.produttore = produttore;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.descrizione = descrizione;
	}

}
