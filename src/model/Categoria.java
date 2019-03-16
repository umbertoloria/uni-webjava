package model;

import database.DB;
import database.Record;
import database.Table;

import java.util.ArrayList;

public class Categoria extends Model {

	private int id;
	private String nome;

	public Categoria(int id) {
		String[] r = take("SELECT id, nome FROM categorie WHERE id = ?", id);
		if (r == null) {
			return;
		}
		this.id = Integer.parseInt(r[0]);
		nome = r[1];
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static Categoria[] getAll() {
		ArrayList<Categoria> categorie = new ArrayList<>();
		Table t = DB.query("SELECT id FROM categorie");
		for (Record record : t) {
			categorie.add(new Categoria((int) record.get(0)));
		}
		return categorie.toArray(new Categoria[0]);
	}

}
