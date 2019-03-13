package parts;

public class Topbar {

	private static TopbarItem[] items = {
			new TopbarItem("Prodotti", "products.jsp"),
			new TopbarItem("Contatti", "contacts.jsp"),
	};

	public static String put() {
		StringBuilder res = new StringBuilder();
		res.append("<div id='topbar'><div class='wrapper'>");
		res.append("<header><a href='./'> MCommerce </a></header>");
		res.append("<form id='search_form' action='cerca.jsp' method='get'>");
		res.append("<input type='text' id='search_input' name='q' " +
				"autocomplete='off' placeholder='Cerca uno strumento...'/>");
		res.append("<input type='submit' value='Cerca'/>");
		res.append("<div class='results'></div>");
		res.append("</form>");
		res.append("<ul>");
		for (TopbarItem item : items) {
			res.append(item.makeHTML());
		}
		res.append("</ul>");
		res.append("</div></div>");
		return res.toString();
	}

	static class TopbarItem {

		private String name, link;

		TopbarItem(String name, String link) {
			this.name = name;
			this.link = link;
		}

		String makeHTML() {
			return "<li> <a href='" + link + "'> " + name + "</a> </li>";
		}

	}

}
