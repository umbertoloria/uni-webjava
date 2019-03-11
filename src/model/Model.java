package model;

import database.DB;
import database.Record;
import database.Table;

public abstract class Model {

	protected String[] take(String sql, Object data) {
		Table t = DB.query(sql, data);
		if (t == null) {
			return null;
		}
		Record r = t.get(0);
		String[] result = new String[r.getSize()];
		for (int i = 0; i < result.length; i++) {
			result[i] = r.get(i).toString();
		}
		return result;
	}

	public abstract String makeHTML();

}
