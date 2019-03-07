package database;

import java.sql.*;

public class Database {

	private Connection conn;

	public Database(String url, int port, String usr, String pwd, String db) throws SQLException {
		conn = DriverManager.getConnection(
				makeURI(url, port, db), usr, pwd
		);
		if (conn == null) {
			throw new SQLException("Unable to connect to " + url);
		}
	}

	private String makeURI(String url, int port, String db) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ignored) {
		}
		String configs = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&" +
				"useLegacyDatetimeCode=false&serverTimezone=UTC";
		return "jdbc:mysql://" + url + ":" + port + "/" + db + configs;
	}

	public Table query(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return new Table(rs);
	}

	public void update(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

}
