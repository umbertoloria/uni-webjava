package model.dao;

import database.Conn;
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
		return t.get(0).asStringArray();
	}

	static void insert(String sql, Object... data) throws SQLException {
		Conn conn = Conn.hold();
		try {
			conn.update(sql, data);
		} finally {
			Conn.release(conn);
		}
	}

	static int insertButGetLastId(String sql, Object... data) throws SQLException {
		int id;
		Conn conn = Conn.hold();
		try {
			id = conn.updateButGetLastId(sql, data);
		} finally {
			Conn.release(conn);
		}
		return id;
	}

}
