package model;

public final class Utente {

	public final int id;
	public final String email, password, nome;

	public Utente(int id, String email, String password, String nome) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
	}

}
