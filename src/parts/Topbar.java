package parts;

import model.Categoria;
import model.Sottocategoria;

public class Topbar {

	private static TopbarItem[] items;

	public static String put() {
		if (items == null) {
			Categoria[] categorie = Categoria.getAll();
			items = new TopbarItem[categorie.length];
			int i = 0;
			for (Categoria categoria : categorie) {
				Sottocategoria[] sottocategorie = Sottocategoria.getAllOf(categoria.getId());
				TopbarItem[] subitems = new TopbarItem[sottocategorie.length];
				int j = 0;
				for (Sottocategoria sottocategoria : sottocategorie) {
					subitems[j++] = new TopbarItem(sottocategoria.getNome(),
							"sottocategoria.jsp?id=" + sottocategoria.getId(), null);
				}
				items[i++] = new TopbarItem(categoria.getNome(), "categoria.jsp?id=" + categoria.getId(), subitems);
			}
		}
		StringBuilder res = new StringBuilder();
		res.append("<div id='topbar'>");

		res.append("<div id='firstbar'>");
		res.append("<div class='wrapper'>");
		res.append("<header><a href='./'> MCommerce </a></header>");
		res.append("<form id='search_form' action='cerca.jsp' method='get'>");
		res.append("<input type='text' id='search_input' name='q' " +
				"autocomplete='off' placeholder='Cerca uno strumento...'/>");
		res.append("<input type='submit' value='Cerca'/>");
		res.append("<div class='results'></div>");
		res.append("</form>");
		res.append("</div>");
		res.append("</div>");

		res.append("<div id='lastbar'>");
		res.append("<div class='wrapper'>");
		res.append("<ul>");
		for (TopbarItem item : items) {
			res.append(item.makeHTML());
		}
		res.append("</ul>");
		res.append("</div>");
		res.append("</div>");
		res.append("</div>");
		return res.toString();
	}

	static private class TopbarItem {

		private String name, link;
		private TopbarItem[] children;

		TopbarItem(String name, String link, TopbarItem[] children) {
			this.name = name;
			this.link = link;
			this.children = children;
		}

		String makeHTML() {
			StringBuilder result = new StringBuilder("<li><a href='" + link + "'>" + name + "</a>");
			if (children != null) {
				result.append("<ul>");
				for (TopbarItem child : children) {
					result.append(child.makeHTML());
				}
				result.append("</ul>");
			}
			result.append("</li>");
			return result.toString();
		}

	}

}
