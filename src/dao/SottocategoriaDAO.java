package dao;

import database.Conn;
import database.Record;
import database.Table;
import model.Sottocategoria;

import java.util.ArrayList;

public class SottocategoriaDAO extends DAO {

	public static Sottocategoria doRetrieveByKey(int id) {
		String[] r = take("SELECT id, categoria, nome FROM sottocategorie WHERE id = ?", id);
		return new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2]);
	}

	public static Sottocategoria[] getAllOf(int categoria) {
		ArrayList<Sottocategoria> sottocategorie = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, categoria, nome FROM sottocategorie WHERE categoria = ?", categoria);
		Conn.release(conn);
		for (Record record : t) {
			String[] r = record.asStringArray();
			sottocategorie.add(new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2]));
		}
		return sottocategorie.toArray(new Sottocategoria[0]);
	}

}
