package model;

import aspects.Box;
import aspects.List;
import database.DB;
import database.Record;
import database.Table;
import utils.Formats;

import java.util.ArrayList;

public class Prodotto extends Model implements Box, List {

	private int id;
	private String nome;
	private float prezzo;
	private int produttore;
	private String immagine, descrizione;

	public Prodotto(int id) {
		String[] r = take("SELECT id, nome, prezzo, produttore, immagine, descrizione FROM prodotti WHERE id = ?", id);
		if (r == null) {
			return;
		}
		this.id = Integer.parseInt(r[0]);
		nome = r[1];
		prezzo = Float.parseFloat(r[2]);
		produttore = Integer.parseInt(r[3]);
		immagine = r[4];
		descrizione = r[5];
	}

	public static Prodotto[] getPosts() {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Table t = DB.query("SELECT id FROM prodotti ORDER BY nome");
		for (Record record : t) {
			prodotti.add(new Prodotto((int) record.get(0)));
		}
		return prodotti.toArray(new Prodotto[0]);
	}

	public static Prodotto[] search(String query) {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		if (query.length() >= 3) {
			Table t = DB.query("SELECT id FROM prodotti WHERE nome LIKE ? ORDER BY nome", "%" + query + "%");
			for (Record record : t) {
				prodotti.add(new Prodotto((int) record.get(0)));
			}
		}
		return prodotti.toArray(new Prodotto[0]);
	}

	public String makeBox() {
		return "<a href='prodotto.jsp?id=" + id + "'>" +
				"<header>" + nome + "</header>" +
				"<span> " + Formats.euro(prezzo) + "</span>" +
				"<img src='" + immagine + "'/></a>";
	}

	public String makeList() {
		return "<a href='prodotto.jsp?id=" + id + "'>" +
				"<img src='" + immagine + "'/>" +
				"<span>" + nome + "</span></a>";
	}

}
