package model;

import database.DB;
import database.Record;
import database.Table;

import java.util.ArrayList;

public class Sottocategoria extends Model {

	private int id, categoria;
	private String nome;

	public Sottocategoria(int id) {
		String[] r = take("SELECT id, categoria, nome FROM sottocategorie WHERE id = ?", id);
		if (r == null) {
			return;
		}
		this.id = Integer.parseInt(r[0]);
		categoria = Integer.parseInt(r[1]);
		nome = r[2];
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static Sottocategoria[] getAllOf(int categoria) {
		ArrayList<Sottocategoria> sottocategorie = new ArrayList<>();
		Table t = DB.query("SELECT id FROM sottocategorie WHERE categoria = ?", categoria);
		for (Record record : t) {
			sottocategorie.add(new Sottocategoria((int) record.get(0)));
		}
		return sottocategorie.toArray(new Sottocategoria[0]);
	}

}
