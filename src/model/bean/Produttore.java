package model.bean;

public final class Produttore {

	public final int id;
	public final String nome;

	public Produttore(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
