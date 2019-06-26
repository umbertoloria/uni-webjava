package model.dao;

import database.Conn;
import database.Record;
import database.Table;
import model.bean.CartaCredito;
import model.bean.Utente;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartaCreditoDAO extends DAO {

	public static CartaCredito doRetrieveByKey(String numero) {
		String[] r = take("SELECT numero, mese, anno, cvv, saldo, utente " +
				"FROM carte_credito WHERE numero = ?", numero);
		if (r == null) {
			return null;
		}
		return new CartaCredito(r[0], r[1], Integer.parseInt(r[2]), r[3], Float.parseFloat(r[4]),
				Integer.parseInt(r[5]));
	}

	/** Ricerca tutti le carte appartenenti all'utente fornito. */
	public static CartaCredito[] getAllThoseOf(Utente utente) {
		ArrayList<CartaCredito> carte = new ArrayList<>();
		Conn conn = Conn.hold();
		Table t = conn.query("SELECT numero, mese, anno, cvv, saldo, utente " +
				"FROM carte_credito WHERE utente = ? ORDER BY numero", utente.id);
		Conn.release(conn);
		fillIn(t, carte);
		return carte.toArray(new CartaCredito[0]);
	}

	private static void fillIn(Table t, ArrayList<CartaCredito> carte) {
		for (Record record : t) {
			String[] r = record.asStringArray();
			carte.add(new CartaCredito(r[0], r[1], Integer.parseInt(r[2]), r[3], Float.parseFloat(r[4]),
					Integer.parseInt(r[5])));
		}
	}

	public static boolean doSave(CartaCredito carta) {
		try {
			insert("INSERT INTO carte_credito SET numero = ?, mese = ?, anno = ?, cvv = ?, " +
							"saldo = ?, utente = ?", carta.numero, carta.mese, carta.anno, carta.cvv, carta.saldo,
					carta.utente);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean doRemoveByKey(String numero) {
		try {
			insert("DELETE FROM carte_credito WHERE numero = ?", numero);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean doUpdate(CartaCredito carta) {
		try {
			insert("UPDATE carte_credito SET saldo = ? WHERE numero = ?", carta.saldo, carta.numero);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
