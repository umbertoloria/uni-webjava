package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Ordine;
import model.bean.OrdineHasProdotto;
import model.bean.Prodotto;
import model.bean.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO extends DAO {

	public static Ordine doRetrieveByKey(int id) {
		Conn c = Conn.hold();
		Table t = c.query("SELECT id, utente, destinazione, pagamento, momento FROM ordini WHERE id = ?", id);
		if (t.count() == 0) {
			return null;
		}
		int ordine_id, ordine_utente;
		String ordine_destinazione, ordine_pagamento, ordine_momento;
		{
			Record r = t.get(0);
			ordine_id = Integer.parseInt(r.get(0).toString());
			ordine_utente = Integer.parseInt(r.get(1).toString());
			ordine_destinazione = r.get(2).toString();
			ordine_pagamento = r.get(3).toString();
			ordine_momento = r.get(4).toString();
		}
		ArrayList<OrdineHasProdotto> ordineHasProdotti = new ArrayList<>();
		t = c.query("SELECT prodotto, prezzo, quantita FROM ordine_has_prodotti WHERE ordine = ?", id);
		for (Record r : t) {
			ordineHasProdotti.add(new OrdineHasProdotto(Integer.parseInt(r.get(0).toString()),
					Float.parseFloat(r.get(1).toString()), Integer.parseInt(r.get(2).toString())));
		}
		return new Ordine(ordine_id, ordine_utente, ordine_destinazione, ordine_pagamento, ordine_momento,
				ordineHasProdotti.toArray(new OrdineHasProdotto[0]));
	}

	public static Ordine[] getAllThoseOf(Utente utente) {
		ArrayList<Ordine> ordini = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id FROM ordini WHERE utente = ? ORDER BY momento DESC", utente.id);
		Conn.release(conn);
		for (Record r : t) {
			ordini.add(doRetrieveByKey(Integer.parseInt(r.get(0).toString())));
		}
		return ordini.toArray(new Ordine[0]);
	}

	public static boolean doSave(Ordine ordine) {
		try {
			int lastId = insertButGetLastId("INSERT INTO ordini SET utente = ?, destinazione = ?, pagamento = ?",
					ordine.utente, ordine.destinazione, ordine.pagamento);
			for (OrdineHasProdotto item : ordine) {
				insert("INSERT INTO ordine_has_prodotti SET ordine = ?, prodotto = ?, prezzo = ?, quantita = ?",
						lastId, item.prodotto, item.prezzo, item.quantita);
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean himHasAlreadyBoughtThis(Utente utente, Prodotto prodotto) {
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT * FROM ordini INNER JOIN ordine_has_prodotti ON " +
				"ordini.id = ordine_has_prodotti.ordine WHERE utente = ? and prodotto = ?", utente.id, prodotto.id);
		Conn.release(conn);
		return t.count() >= 1;
	}

	public static Ordine[] getAll() {
		ArrayList<Ordine> ordini = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id FROM ordini ORDER BY momento DESC");
		Conn.release(conn);
		for (Record r : t) {
			ordini.add(doRetrieveByKey(Integer.parseInt(r.get(0).toString())));
		}
		return ordini.toArray(new Ordine[0]);
	}

}
