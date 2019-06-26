package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.Categoria;
import model.bean.Prodotto;
import model.bean.Produttore;
import model.bean.Sottocategoria;

import java.sql.*;
import java.util.ArrayList;

public class ProdottoDAO extends DAO {

	public static Prodotto doRetrieveByKey(int id) {
		String[] r = take("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
				"FROM prodotti WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Prodotto(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], Integer.parseInt(r[3]),
				Float.parseFloat(r[4]), Integer.parseInt(r[5]), r[6]);
	}

	/** Restituisce tutti i prodotti ordinati per nome. */
	public static Prodotto[] getTop12() {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione, " +
				"IF(totale IS NULL, 0, totale) totale FROM (SELECT id, sottocategoria, nome, produttore, " +
				"prodotti.prezzo, immagine, descrizione, SUM(quantita) totale FROM prodotti LEFT JOIN " +
				"ordine_has_prodotti ON prodotti.id = ordine_has_prodotti.prodotto GROUP BY prodotti.id) t " +
				"ORDER BY totale DESC LIMIT 12");
		Conn.release(conn);
		fillIn(t, prodotti);
		return prodotti.toArray(new Prodotto[0]);
	}

	/** Ricerca tutti i prodotti con il nome fornito, ordinati per nome. */
	public static Prodotto[] search(String[] words) {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Conn conn = Conn.hold();
		Object[] matches = new Object[words.length];
		for (int i = 0; i < words.length; i++) {
			matches[i] = "%" + words[i] + "%";
		}
		Table t = conn.query("SELECT * FROM (SELECT prodotti.id, sottocategoria, prodotti.nome, produttore, " +
				"prezzo, prodotti.immagine, descrizione, CONCAT(prodotti.nome, ' ', produttori.nome) full " +
				"FROM prodotti JOIN produttori ON prodotti.produttore = produttori.id) t WHERE" +
				" full LIKE ? AND".repeat(words.length - 1) + " full LIKE ? ORDER BY nome", matches);
		Conn.release(conn);
		fillIn(t, prodotti);
		return prodotti.toArray(new Prodotto[0]);
	}

	/** Ricerca tutti i prodotti appartenenti alla categoria fornita, ordinati per nome. */
	public static Prodotto[] getFromCategoria(Categoria categoria) {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT prodotti.id, sottocategoria, prodotti.nome, produttore, prezzo, " +
				"prodotti.immagine, descrizione FROM prodotti JOIN sottocategorie ON sottocategorie.id = " +
				"prodotti.sottocategoria WHERE sottocategorie.categoria = ? ORDER BY nome", categoria.id);
		Conn.release(conn);
		fillIn(t, prodotti);
		return prodotti.toArray(new Prodotto[0]);
	}

	/** Ricerca tutti i prodotti appartenenti alla sottocategoria fornita, ordinati per nome. */
	public static Prodotto[] getFromSottocategoria(Sottocategoria sottocategoria) {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
				"FROM prodotti WHERE sottocategoria = ? ORDER BY nome", sottocategoria.id);
		Conn.release(conn);
		fillIn(t, prodotti);
		return prodotti.toArray(new Prodotto[0]);
	}

	/** Ricerca tutti i prodotti venduti dal produttore fornito, ordinati per nome. */
	public static Prodotto[] getFromProduttore(Produttore produttore) {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT id, sottocategoria, nome, produttore, prezzo, immagine, descrizione " +
				"FROM prodotti WHERE produttore = ? ORDER BY nome", produttore.id);
		Conn.release(conn);
		fillIn(t, prodotti);
		return prodotti.toArray(new Prodotto[0]);
	}

	private static void fillIn(Table t, ArrayList<Prodotto> prodotti) {
		for (Record record : t) {
			String[] r = record.asStringArray();
			prodotti.add(new Prodotto(Integer.parseInt(r[0]), Integer.parseInt(r[1]), r[2], Integer.parseInt(r[3]),
					Float.parseFloat(r[4]), Integer.parseInt(r[5]), r[6]));
		}
	}

	public static Prodotto doSave(Prodotto prodotto) {
		Conn c = Conn.hold();
		Connection co = c.getConnection();
		Prodotto result = null;
		try {
			PreparedStatement stm = co.prepareStatement("INSERT INTO prodotti SET sottocategoria = ?, nome = ?, " +
							"produttore = ?, prezzo = ?, immagine = ?, descrizione = ?",
					Statement.RETURN_GENERATED_KEYS);
			stm.setInt(1, prodotto.sottocategoria);
			stm.setString(2, prodotto.nome);
			stm.setInt(3, prodotto.produttore);
			stm.setFloat(4, prodotto.prezzo);
			stm.setInt(5, prodotto.immagine);
			stm.setString(6, prodotto.descrizione);
			if (stm.executeUpdate() == 1) {
				ResultSet rs = stm.getGeneratedKeys();
				rs.next();
				result = doRetrieveByKey(rs.getInt(1));
			}
		} catch (SQLException ignore) {
		}
		Conn.release(c);
		return result;
	}

}
