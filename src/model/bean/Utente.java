package model.bean;

public final class Utente {

	public final int id;
	public final String email, password, nome, tipo;

	public Utente(int id, String email, String password, String nome, String tipo) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Utente(String email, String password, String nome) {
		this.id = -1;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.tipo = "Normale";
	}

}