package model.bean;

public final class Categoria {

	public final int id;
	public final String nome;

	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
