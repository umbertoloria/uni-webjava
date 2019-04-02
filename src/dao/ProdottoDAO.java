package dao;

import database.Conn;
import database.Record;
import database.Table;
import model.Prodotto;

import java.util.ArrayList;

public class ProdottoDAO extends DAO {

	public static Prodotto doRetrieveByKey(int id) {
		String[] r = take("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
				"FROM prodotti WHERE id = ?", id);
		return new Prodotto(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], Integer.parseInt(r[3]),
				Float.parseFloat(r[4]), r[5], r[6]);
	}

	/**
	 Restituisce tutti i prodotti ordinati per nome.
	 */
	public static Prodotto[] getAll() {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
				"FROM prodotti ORDER BY nome");
		Conn.release(conn);
		fillIn(t, prodotti);
		return prodotti.toArray(new Prodotto[0]);
	}

	/**
	 Ricerca tutti i prodotti con il nome fornito, ordinati per nome.
	 */
	public static Prodotto[] search(String query) {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		if (query.length() >= 3) {
			Conn conn = Conn.hold();
			Table t = conn.query("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
					"FROM prodotti WHERE nome LIKE ? ORDER BY nome", "%" + query + "%");
			Conn.release(conn);
			fillIn(t, prodotti);
		}
		return prodotti.toArray(new Prodotto[0]);
	}

	private static void fillIn(Table t, ArrayList<Prodotto> prodotti) {
		for (Record record : t) {
			String[] r = record.asStringArray();
			prodotti.add(new Prodotto(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], Integer.parseInt(r[3]),
					Float.parseFloat(r[4]), r[5], r[6]));
		}
	}

}
