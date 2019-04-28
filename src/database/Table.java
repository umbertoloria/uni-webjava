package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Table implements Iterable<Record> {

	private Record[] records;

	Table(ResultSet rs) throws SQLException {
		ArrayList<Record> recordsList = new ArrayList<>();
		while (rs.next()) {
			recordsList.add(new Record(rs));
		}
		records = recordsList.toArray(new Record[0]);
		rs.close();
	}

	public Iterator<Record> iterator() {
		return Arrays.asList(records).iterator();
	}

	public Record get(int index) {
		return records[index];
	}

	public int count() {
		return records.length;
	}

}
