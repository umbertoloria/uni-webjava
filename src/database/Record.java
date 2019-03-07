package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;

public class Record implements Iterable<Object> {

	private Object[] values;

	Record(ResultSet rs) throws SQLException {
		values = new Object[rs.getMetaData().getColumnCount()];
		for (int i = 0; i < values.length; i++) {
			values[i] = rs.getObject(i + 1);
		}
	}

	void show(int[] sizes) {
		int i = 0;
		for (Object value : values) {
			System.out.print("| ");
			Table.print(value, sizes[i++], false);
			System.out.print(" ");
		}
		System.out.println("|");
	}

	public Object get(int index) {
		return values[index];
	}

	public Iterator<Object> iterator() {
		return Arrays.asList(values).iterator();
	}

}
