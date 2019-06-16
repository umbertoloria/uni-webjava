package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Indirizzo;
import model.bean.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public class IndirizzoDAO extends DAO {

	public static Indirizzo doRetrieveByKey(int id) {
		String[] r = take("SELECT id, nome, indirizzo, citta, cap, provincia, utente " +
				"FROM indirizzi WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Indirizzo(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4], r[5], Integer.parseInt(r[6]));
	}

	public static Indirizzo doRetrieveByNomeAndUtente(String nome, int utente) {
		String[] r = take("SELECT id, nome, indirizzo, citta, cap, provincia, utente " +
				"FROM indirizzi WHERE nome = ? AND utente = ?", nome, utente);
		if (r == null) {
			return null;
		}
		return new Indirizzo(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4], r[5], Integer.parseInt(r[6]));
	}

	/** Ricerca tutti gli indirizzi appartenenti all'utente fornito. */
	public static Indirizzo[] getAllThoseOf(Utente utente) {
		// TODO: Predere quello più usato, aggiornando la documentazione.
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, nome, indirizzo, citta, cap, provincia, utente " +
				"FROM indirizzi WHERE utente = ? ORDER BY nome", utente.id);
		Conn.release(conn);
		fillIn(t, indirizzi);
		return indirizzi.toArray(new Indirizzo[0]);
	}

	private static void fillIn(Table t, ArrayList<Indirizzo> indirizzi) {
		for (Record record : t) {
			String[] r = record.asStringArray();
			indirizzi.add(new Indirizzo(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4], r[5], Integer.parseInt(r[6])));
		}
	}

	public static boolean doSave(Indirizzo indirizzo) {
		try {
			insert("INSERT INTO indirizzi SET nome = ?, indirizzo = ?, citta = ?, cap = ?, " +
							"provincia = ?, utente = ?", indirizzo.nome, indirizzo.indirizzo, indirizzo.citta,
					indirizzo.cap, indirizzo.provincia, indirizzo.utente);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean doRemoveByKey(int id) {
		try {
			insert("DELETE FROM indirizzi WHERE id = ?", id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
