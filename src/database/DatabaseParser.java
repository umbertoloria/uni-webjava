package database;

public class DatabaseParser {

	public static boolean lonelyQuery(String input) {
		int n = 0;
		for (char c : input.toCharArray()) {
			if (c == ';') {
				n++;
				if (n > 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean returnsSomething(String query) {
		// select
		// show
		// alter table
		// create table
		// create view
		// delete
		// insert
		// update
		String low = query.toLowerCase();
		boolean select = low.contains("select");
		boolean insert = low.contains("insert");
		boolean show = low.contains("show");
		boolean view = low.contains("view");
		return show || (select && !insert && !view);
	}

}
