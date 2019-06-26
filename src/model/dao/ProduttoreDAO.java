package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Produttore;

import java.util.ArrayList;

public class ProduttoreDAO extends DAO {

	public static Produttore doRetrieveByKey(int id) {
		String[] r = take("SELECT id, nome FROM produttori WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Produttore(Integer.parseInt(r[0]), r[1]);
	}

	public static Produttore[] getAll() {
		ArrayList<Produttore> produttori = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, nome FROM produttori ORDER BY nome");
		Conn.release(conn);
		for (Record record : t) {
			String[] r = record.asStringArray();
			produttori.add(new Produttore(Integer.parseInt(r[0]), r[1]));
		}
		return produttori.toArray(new Produttore[0]);
	}

}
