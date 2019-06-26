package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO extends DAO {

	public static Utente doRetrieveByKey(int id) {
		String[] r = take("SELECT id, email, password, nome, tipo FROM utenti WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Utente(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4]);
	}

	public static Utente doRetrieveByEmail(String email) {
		String[] r = take("SELECT id, email, password, nome, tipo FROM utenti WHERE email = ?", email);
		if (r == null) {
			return null;
		}
		return new Utente(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4]);
	}

	public static boolean doSave(Utente utente) {
		try {
			insert("INSERT INTO utenti SET email = ?, password = ?, nome = ?",
					utente.email, utente.password, utente.nome);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean updatePassword(Utente utente) {
		try {
			insert("UPDATE utenti SET password = ? WHERE id = ?", utente.password, utente.id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static Utente[] getAll() {
		ArrayList<Utente> utenti = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, email, password, nome, tipo FROM utenti ORDER BY nome");
		Conn.release(conn);
		for (Record r : t) {
			utenti.add(new Utente(Integer.parseInt(r.get(0).toString()), r.get(1).toString(), r.get(2).toString(),
					r.get(3).toString(), r.get(4).toString()));
		}
		return utenti.toArray(new Utente[0]);
	}

}
