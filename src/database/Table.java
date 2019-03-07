package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Table implements Iterable<Record> {

	// Nomi delle colonne
	private String[] names;
	// Dimensioni massime raggiungibili dei VALORI delle colonne
	private int[] displaySizes;
	// Dimensioni minime raggiunte dalle colonne
	private int[] minSizes;
	// Dimensioni massime raggiungibili dalle colonne
	private int[] maxSizes;
	// Tutti i records
	private Record[] records;

	Table(ResultSet rs) throws SQLException {
		createHeader(rs.getMetaData());
		createRecords(rs);
		rs.close();
	}

	private void createHeader(ResultSetMetaData rsmd) throws SQLException {
		int count = rsmd.getColumnCount();
		if (count <= 0) {
			throw new SQLException("Tabella vuota.");
		}
		displaySizes = new int[count];
		names = new String[count];
		minSizes = new int[count];
		maxSizes = new int[count];
		for (int i = 0; i < count; i++) {
			displaySizes[i] = rsmd.getColumnDisplaySize(i + 1);
			names[i] = rsmd.getColumnName(i + 1);
			// Minima larghezza delle colonne
			minSizes[i] = names[i].length();
			// Massima larghezza delle colonne
			maxSizes[i] = Math.max(names[i].length(), displaySizes[i]);
		}
	}

	private void createRecords(ResultSet rs) throws SQLException {
		ArrayList<Record> recordsList = new ArrayList<>();
		while (rs.next()) {
			Record record = new Record(rs);
			int i = 0;
			for (Object cell : record) {
				if (cell == null) {
					cell = "NULL";
				}
				int len = cell.toString().length();
				if (len <= displaySizes[i]) {
					if (minSizes[i] < len) {
						minSizes[i] = len;
					}
				}
				i++;
			}
			recordsList.add(record);
		}
		records = recordsList.toArray(new Record[0]);
	}

	public void showMaximized() {
		show(false);
	}

	public void showMinimized() {
		show(true);
	}

	private void show(boolean minimize) {
		int[] sizes = minimize ? minSizes : maxSizes;
		putLine(sizes);
		showHeader(sizes);
		putLine(sizes);
		for (Record record : records) {
			record.show(sizes);
		}
		putLine(sizes);
	}

	static void print(Object obj, int size, boolean uppercase) {
		String str;
		if (obj == null) {
			str = "NULL";
		} else {
			str = obj + "";
		}
		if (str.length() > size) {
			str = str.substring(0, size);
//			return;
		}
		String res = str + " ".repeat(size - str.length());
//		(uppercase ? System.err : System.out).print(res);
		System.out.print(uppercase ? res.toUpperCase() : res);
	}

	private void putLine(int[] sizes) {
		for (int size : sizes) {
			System.out.print("+-");
			System.out.print("-".repeat(size));
			System.out.print("-");
		}
		System.out.println("+");
	}

	private void showHeader(int[] sizes) {
		for (int i = 0; i < names.length; i++) {
			System.out.print("| ");
			Table.print(names[i], sizes[i], true);
			System.out.print(" ");
		}
		System.out.println("|");
	}

	public Iterator<Record> iterator() {
		return Arrays.asList(records).iterator();
	}

	public Record get(int index) {
		return records[index];
	}

	public String[] getHeader() {
		return names;
	}

	public Object[][] getData() {
		Object[][] res = new Object[records.length][names.length];
		int i = 0;
		for (Record record : records) {
			int j = 0;
			for (Object item : record) {
				res[i][j] = item;
				j++;
			}
			i++;
		}
		return res;
	}

	public double[] getPreferredSizes() {
		double total = Arrays.stream(minSizes).reduce(0, (tot, x) -> tot + x);
		double[] res = new double[minSizes.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = minSizes[i] / total;
		}
		return res;
	}

	public int getCols() {
		return names.length;
	}

	public int getRows() {
		return records.length;
	}

}

