package model;

import database.DB;
import database.Record;
import database.Table;

import java.util.ArrayList;

public class Prodotto extends Model {

	private int id;
	private String nome;
	private float prezzo;
	private int produttore;
	private String immagine;

	public Prodotto(int id) {
		String[] r = take("SELECT id, nome, prezzo, produttore, immagine FROM prodotti WHERE id = ?", id);
		if (r == null) {
			return;
		}
		this.id = Integer.parseInt(r[0]);
		nome = r[1];
		prezzo = Float.parseFloat(r[2]);
		produttore = Integer.parseInt(r[3]);
		immagine = r[4];
	}

	public static Prodotto[] getPosts() {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Table t = DB.query("SELECT id FROM prodotti ORDER BY nome");
		for (Record record : t) {
			prodotti.add(new Prodotto((int) record.get(0)));
		}
		return prodotti.toArray(new Prodotto[0]);
	}

	public String makeHTML() {
		return "<a href='prodotto.jsp?id=" + id + "' class='prodotto'>" +
				"<header>" + nome + "</header>" +
				"<label>" + prezzo + "</label>" +
				"<img src='" + immagine + "'/></a>";
	}

}
