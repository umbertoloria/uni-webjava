package model.bean;

public final class Sottocategoria {

	public final int id, categoria;
	public final String nome, immagine;

	public Sottocategoria(int id, int categoria, String nome, String immagine) {
		this.id = id;
		this.categoria = categoria;
		this.nome = nome;
		this.immagine = immagine;
	}

}
