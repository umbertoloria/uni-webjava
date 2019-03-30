package parts;

public class Sidebar {

	private static Sidebox sidebox = new Sidebox("Utenza", new Sideitem[]{
			new Sideitem("Accesso", "accesso.jsp"),
			new Sideitem("Registrazione", "registrazione.jsp"),
	});

	public static String put() {
		StringBuilder res = new StringBuilder();
		res.append("<div id='sidebar'>");
		res.append(sidebox.makeHTML());
		// TODO: Aggiungi credits.
		res.append("</div>");
		return res.toString();
	}

	static class Sidebox {

		private String title;
		private Sideitem[] items;

		Sidebox(String title, Sideitem[] items) {
			this.title = title;
			this.items = items;
		}

		String makeHTML() {
			StringBuilder result = new StringBuilder();
			result.append("<div class='sidebox'>");
			result.append("<header>");
			result.append(title);
			result.append("</header>");
			result.append("<ul>");
			for (Sideitem item : items) {
				result.append(item.makeHTML());
			}
			result.append("</ul>");
			result.append("</div>");
			return result.toString();
		}

	}

	static class Sideitem {

		private String name, link;

		Sideitem(String name, String link) {
			this.name = name;
			this.link = link;
		}

		String makeHTML() {
			return "<li><a href='" + link + "'>" + name + "</a></li>";
		}

	}

}
