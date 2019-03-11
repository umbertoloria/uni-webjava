package database;

import java.sql.SQLException;

public class DB {

	private static Database c;

	public static void init() {
		try {
			c = new Database("localhost", 3306, "root", "ciaociao", "ecommerce");
		} catch (SQLException ignored) {
		}
	}

	public static Table query(String sql, Object... data) {
		try {
			return c.query(sql, data);
		} catch (SQLException e) {
			return null;
		}
	}

//	public static Table stm(Object... d) {
//		String sql = (String) d[0];
//		int i = 0;
//		Object[] data = new Object[d.length - 1];
//		for (Object o : d) {
//			data[i++] = o;
//		}
//	}

}
