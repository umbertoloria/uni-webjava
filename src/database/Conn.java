package database;

import java.sql.*;
import java.util.ArrayList;

public class Conn {

	private static ArrayList<Conn> cs = new ArrayList<>();

	public synchronized static Conn hold() {
		if (cs.size() == 0) {
			try {
				cs.add(new Conn("localhost", 3306, "root", "Ciaociao98.", "ecommerce"));
				// pass antonio = "Ciaociao98."
				// pass umberto = "ciaociao"
				// pass mario = "ciao"
			} catch (SQLException ignored) {
				System.out.println("Impossibile connettersi al db.");
			}
		}
		Conn conn = cs.get(0);
		cs.remove(0);
		return conn;
	}

	public synchronized static void release(Conn conn) {
		if (conn != null) {
			cs.add(conn);
		}
	}

	private Connection conn;

	public Connection getConnection() {
		return conn;
	}

	private Conn(String url, int port, String usr, String pwd, String db) throws SQLException {
		conn = DriverManager.getConnection(makeURI(url, port, db), usr, pwd);
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

	public Table query(String query, Object... data) {
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			int i = 1;
			for (Object d : data) {
				stmt.setString(i++, d.toString());
			}
			ResultSet rs = stmt.executeQuery();
			return new Table(rs);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void update(String query, Object... data) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(query);
		int i = 1;
		for (Object d : data) {
			stmt.setString(i++, d.toString());
		}
		stmt.executeUpdate();
	}

	public int updateButGetLastId(String query, Object... data) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int i = 1;
		for (Object d : data) {
			stmt.setString(i++, d.toString());
		}
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}

}
