package dao;

import database.Conn;
import database.Record;
import database.Table;

class DAO {

	static String[] take(String sql, Object... data) {
		Conn conn = Conn.hold();
		Table t = conn.query(sql, data);
		Conn.release(conn);
		Record r = t.get(0);
		String[] result = new String[r.getSize()];
		int i = 0;
		for (Object o : r) {
			result[i++] = o.toString();
		}
		return result;
	}

}
