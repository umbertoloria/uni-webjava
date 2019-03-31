package dao;

import model.Utente;

public class UtenteDAO extends DAO {

	public static Utente doRetrieveByKey(int id) {
		String[] r = take("SELECT id, email, password, nome FROM utenti WHERE id = ?", id);
		return new Utente(Integer.parseInt(r[0]), r[1], r[2], r[3]);
	}

}
