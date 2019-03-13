package database;

import java.sql.SQLException;

public class DB {

	private static Database c;

	public static void init() {
		try {
			c = new Database("localhost", 3306, "root", "Ciaociao98.", "ecommerce");
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

}
