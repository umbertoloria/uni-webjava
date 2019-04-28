package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Categoria;

import java.util.ArrayList;

public class CategoriaDAO extends DAO {

	public static Categoria doRetrieveByKey(int id) {
		String[] r = take("SELECT id, nome FROM categorie WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Categoria(Integer.parseInt(r[0]), r[1]);
	}

	public static Categoria[] getAll() {
		ArrayList<Categoria> categorie = new ArrayList<>();

		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, nome FROM categorie");
		Conn.release(conn);

		for (Record record : t) {
			String[] r = record.asStringArray();
			categorie.add(new Categoria(Integer.parseInt(r[0]), r[1]));
		}
		return categorie.toArray(new Categoria[0]);
	}

}
