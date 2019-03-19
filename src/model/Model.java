package model;

import database.DB;
import database.Record;
import database.Table;

abstract class Model {

	String[] take(String sql, Object... data) {
		Table t = DB.query(sql, data);
		Record r = t.get(0);
		String[] result = new String[r.getSize()];
		int i = 0;
		for (Object o : r) {
			result[i++] = o.toString();
		}
		return result;
	}

}
