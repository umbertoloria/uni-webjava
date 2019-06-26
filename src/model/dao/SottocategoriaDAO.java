package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Categoria;
import model.bean.Sottocategoria;

import java.util.ArrayList;

public class SottocategoriaDAO extends DAO {

	public static Sottocategoria doRetrieveByKey(int id) {
		String[] r = take("SELECT id, categoria, nome, immagine FROM sottocategorie WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], Integer.parseInt(r[3]));
	}

	public static Sottocategoria[] getAllThoseOf(Categoria categoria) {
		ArrayList<Sottocategoria> sottocategorie = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, categoria, nome, immagine " +
				"FROM sottocategorie WHERE categoria = ?", categoria.id);
		Conn.release(conn);
		for (Record record : t) {
			String[] r = record.asStringArray();
			sottocategorie.add(new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2],
					Integer.parseInt(r[3])));
		}
		return sottocategorie.toArray(new Sottocategoria[0]);
	}


	public static Sottocategoria[] getFirstMoreProvided(int count) {
		ArrayList<Sottocategoria> sottocategorie = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT sottocategorie.id, sottocategorie.categoria, sottocategorie.nome, " +
				"sottocategorie.immagine, count(prodotti.id) a FROM sottocategorie " +
				"LEFT JOIN prodotti ON prodotti.sottocategoria = sottocategorie.id GROUP BY sottocategorie.id " +
				"ORDER BY a DESC LIMIT " + count);
		Conn.release(conn);
		for (Record record : t) {
			String[] r = record.asStringArray();
			sottocategorie.add(new Sottocategoria(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2],
					Integer.parseInt(r[3])));
		}
		return sottocategorie.toArray(new Sottocategoria[0]);
	}

}
