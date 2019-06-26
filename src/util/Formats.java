package util;

public class Formats {

	public static String euro(float prezzo) {
		StringBuilder r = new StringBuilder("€ ");
		String val = Math.round(prezzo * 100f) / 100f + "";
		r.append(val.replace('.', ','));
		if (r.toString().indexOf(',') == -1) {
			r.append(",00");
		}
		while (r.toString().indexOf(',') > r.toString().length() - 3) {
			r.append('0');
		}
		return r.toString();
	}

	public static final String[] mesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Agosto",
			"Settembre", "Ottobre", "Novembre", "Dicembre"};

	public static String date(String datetime) {
		if (datetime == null) {
			return null;
		} else {
			String date = datetime.substring(0, 10);
			String[] comps = date.split("-");
			int giorno = Integer.parseInt(comps[2]);
			int mese = Integer.parseInt(comps[1]);
			int anno = Integer.parseInt(comps[0]);
			return giorno + " " + mesi[mese - 1] + " " + anno;
		}
	}

}
