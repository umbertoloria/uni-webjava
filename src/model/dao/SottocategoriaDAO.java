package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Sottocategoria;

import java.util.ArrayList;

public class SottocategoriaDAO extends DAO {

	public static Sottocategoria doRetrieveByKey(int id) {
		String[] r = take("SELECT id, categoria, nome, immagine FROM sottocategorie WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], r[3]);
	}

	public static Sottocategoria[] getAllThoseOf(int categoria) {
		ArrayList<Sottocategoria> sottocategorie = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, categoria, nome, immagine " +
				"FROM sottocategorie WHERE categoria = ?", categoria);
		Conn.release(conn);
		for (Record record : t) {
			String[] r = record.asStringArray();
			sottocategorie.add(new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], r[3]));
		}
		return sottocategorie.toArray(new Sottocategoria[0]);
	}

}
