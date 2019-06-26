package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Prodotto;
import model.bean.Recensione;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneDAO extends DAO {

	public static Recensione doRetrieveByKey(int id) {
		String[] r = take("SELECT id, prodotto, voto, titolo, commento, utente, momento" +
				"FROM recensioni WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Recensione(Integer.parseInt(r[0]), Integer.parseInt(r[1]), Integer.parseInt(r[2]), r[3],
				r[4], Integer.parseInt(r[5]), r[6]);
	}

	/** Ricerca tutte le recensioni del prodotto fornito. */
	public static Recensione[] getFromProdotto(Prodotto prodotto) {
		ArrayList<Recensione> recensioni = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, prodotto, voto, titolo, commento, utente, momento " +
				"FROM recensioni WHERE prodotto = ?", prodotto.id);
		Conn.release(conn);
		for (Record record : t) {
			String[] r = record.asStringArray();
			recensioni.add(new Recensione(Integer.parseInt(r[0]), Integer.parseInt(r[1]), Integer.parseInt(r[2]), r[3],
					r[4], Integer.parseInt(r[5]), r[6]));
		}
		return recensioni.toArray(new Recensione[0]);
	}

	public static Recensione doRetrieveByUtenteAndProdotto(int utente, int prodotto) {
		String[] r = take("SELECT id, prodotto, voto, titolo, commento, utente, momento " +
				"FROM recensioni WHERE utente = ? AND prodotto = ?", utente, prodotto);
		if (r == null) {
			return null;
		}
		return new Recensione(Integer.parseInt(r[0]), Integer.parseInt(r[1]), Integer.parseInt(r[2]), r[3],
				r[4], Integer.parseInt(r[5]), r[6]);
	}

	public static boolean doSave(Recensione recensione) {
		try {
			insert("INSERT INTO recensioni SET prodotto = ?, voto = ?, titolo = ?,  commento = ?, " +
							"utente = ?", recensione.prodotto, recensione.voto, recensione.titolo,
					recensione.commento, recensione.utente);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
