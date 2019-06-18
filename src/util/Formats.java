package util;

public class Formats {

	// FIXME: forse con 10.11 non funge più lol
	public static String euro(float prezzo) {
		int euro = (int) prezzo;
		int centesimi = (int) ((prezzo - euro) * 100);
		String result = "€ " + euro;
		if (centesimi == 0) {
			result += ",00";
		} else {
			result += "," + centesimi;
		}
		return result;
	}

}
