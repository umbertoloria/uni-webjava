package model;

import aspects.Box;
import aspects.List;
import database.DB;
import database.Record;
import database.Table;
import utils.Formats;

import java.util.ArrayList;

public class Prodotto extends Model implements Box, List {

	private int id, sottocategoria;
	private String nome;
	private int produttore;
	private float prezzo;
	private String immagine, descrizione;

	public Prodotto(int id) {
		String[] r = take("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
				"FROM prodotti WHERE id = ?", id);
		this.id = Integer.parseInt(r[0]);
		sottocategoria = Integer.parseInt(r[1]);
		nome = r[2];
		produttore = Integer.parseInt(r[3]);
		prezzo = Float.parseFloat(r[4]);
		immagine = r[5];
		descrizione = r[6];
	}

	/**
	 Restituisce tutti i prodotti ordinati per nome.
	 */
	public static Prodotto[] getAll() {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Table t = DB.query("SELECT id FROM prodotti ORDER BY nome");
		for (Record record : t) {
			prodotti.add(new Prodotto((int) record.get(0)));
		}
		return prodotti.toArray(new Prodotto[0]);
	}

	/**
	 Ricerca tutti i prodotti con il nome fornito, ordinati per nome.
	 */
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
		String part = "<a class='prebox' href='sottocategoria.jsp?id=" + sottocategoria + "'>" +
				new Sottocategoria(sottocategoria).getNome() + "</a>";
		// TODO: Inserire nome produttore.
		return "<div>" + part + "<a class='box' href='prodotto.jsp?id=" + id + "'> " +
				"<header>" + nome + "</header>" +
				"<img src='" + immagine + "'/>" +
				"<span> " + Formats.euro(prezzo) + "</span></a>" +
				"</div>";
	}

	public String makeList() {
		return "<a href='prodotto.jsp?id=" + id + "'>" +
				"<img src='" + immagine + "'/>" +
				"<span>" + nome + "</span></a>";
	}

}
