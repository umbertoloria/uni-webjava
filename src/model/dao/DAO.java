package model.dao;

import database.Conn;
import database.Record;
import database.Table;

import java.sql.SQLException;

class DAO {

	static String[] take(String sql, Object... data) {
		Conn conn = Conn.hold();
		Table t = conn.query(sql, data);
		Conn.release(conn);
		if (t.count() == 0) {
			return null;
		}
		Record r = t.get(0);
		String[] result = new String[r.getSize()];
		int i = 0;
		for (Object o : r) {
			result[i++] = o.toString();
		}
		return result;
	}

	static void insert(String sql, Object... data) throws SQLException {
		Conn conn = Conn.hold();
		try {
			conn.update(sql, data);
		} finally {
			Conn.release(conn);
		}
	}

}
