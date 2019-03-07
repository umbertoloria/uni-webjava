package database;

import java.sql.SQLException;

public class DB {

	private static Database c;

	public static void init() {
		try {
			c = new Database("localhost", 3306, "root", "ciaociao", "blog");
		} catch (SQLException e) {
		}
	}

	public static Table query(String sql) {
		try {
			return c.query(sql);
		} catch (SQLException e) {
			return null;
		}
	}

}
