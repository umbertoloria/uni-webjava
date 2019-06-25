package model.bean;

public final class Sottocategoria {

	public final int id, categoria, immagine;
	public final String nome;

	public Sottocategoria(int id, int categoria, String nome, int immagine) {
		this.id = id;
		this.categoria = categoria;
		this.nome = nome;
		this.immagine = immagine;
	}

}
