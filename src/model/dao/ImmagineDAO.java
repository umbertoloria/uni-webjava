package model.dao;

import database.Conn;
import model.bean.Immagine;

import java.sql.*;

public class ImmagineDAO extends DAO {

	public static Immagine doRetrieveByKey(int id) {
		Conn c = Conn.hold();
		Connection co = c.getConnection();
		Immagine immagine = null;
		try {
			PreparedStatement stm = co.prepareStatement("SELECT id, data FROM immagini WHERE id = ?");
			stm.setString(1, "" + id);
			ResultSet set = stm.executeQuery();
			if (set.next()) {
				Blob b = set.getBlob(2);
				byte[] bb = b.getBytes(1, (int) b.length());
				immagine = new Immagine(set.getInt(1), bb);
			}
		} catch (SQLException ignore) {
		}
		Conn.release(c);
		return immagine;
	}

	public static Immagine doSave(Immagine immagine) {
		Conn c = Conn.hold();
		Connection co = c.getConnection();
		Immagine result = null;
		try {
			PreparedStatement stm = co.prepareStatement("INSERT INTO immagini SET data = ?",
					Statement.RETURN_GENERATED_KEYS);
			stm.setBlob(1, immagine.stream);
			if (stm.executeUpdate() == 1) {
				ResultSet rs = stm.getGeneratedKeys();
				rs.next();
				result = doRetrieveByKey(rs.getInt(1));
			}
		} catch (SQLException ignore) {
		}
		Conn.release(c);
		return result;
	}

}
