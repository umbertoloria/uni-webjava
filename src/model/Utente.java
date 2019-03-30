package model;

public class Utente extends Model {

	private int id;
	private String email, password, nome;

	public Utente(int id) {
		String[] r = take("SELECT id, email, password, nome FROM utenti WHERE id = ?", id);
		this.id = Integer.parseInt(r[0]);
		email = r[1];
		password = r[2];
		nome = r[3];
	}

}
