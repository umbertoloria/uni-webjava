package dao;

import database.DB;
import database.Record;
import database.Table;
import model.Categoria;

import java.util.ArrayList;

public class CategoriaDAO extends DAO {

	public static Categoria doRetrieveByKey(int id) {
		String[] r = take("SELECT id, nome FROM categorie WHERE id = ?", id);
		return new Categoria(Integer.parseInt(r[0]), r[1]);
	}

	public static Categoria[] getAll() {
		ArrayList<Categoria> categorie = new ArrayList<>();
		Table t = DB.query("SELECT id FROM categorie");
		for (Record record : t) {
			categorie.add(doRetrieveByKey((int) record.get(0)));
		}
		return categorie.toArray(new Categoria[0]);
	}

}
