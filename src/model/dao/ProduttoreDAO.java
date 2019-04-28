package model.dao;

import model.bean.Produttore;
import model.bean.Utente;

import java.sql.SQLException;

public class ProduttoreDAO extends DAO {

	public static Produttore doRetrieveByKey(int id) {
		String[] r = take("SELECT id, nome FROM produttori WHERE id = ?", id);
		if (r == null) {
			return null;
		}
		return new Produttore(Integer.parseInt(r[0]), r[1]);
	}

}
