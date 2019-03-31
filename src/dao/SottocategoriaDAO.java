package dao;

import database.DB;
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
		Table t = DB.query("SELECT id FROM sottocategorie WHERE categoria = ?", categoria);
		for (Record record : t) {
			sottocategorie.add(doRetrieveByKey((int) record.get(0)));
		}
		return sottocategorie.toArray(new Sottocategoria[0]);
	}

}
