<%@ page import="model.container.ProdottoContainer" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Integer minPrezzo;
	try {
		minPrezzo = Integer.parseInt(request.getParameter("min"));
		if (minPrezzo < 1 || minPrezzo > 1000) {
			throw new NumberFormatException();
		}
	} catch (NumberFormatException e) {
		minPrezzo = null;
	}
	Integer maxPrezzo;
	try {
		maxPrezzo = Integer.parseInt(request.getParameter("max"));
		if (maxPrezzo < 1 || maxPrezzo > 1000) {
			throw new NumberFormatException();
		}
	} catch (NumberFormatException e) {
		maxPrezzo = null;
	}
	ProdottoContainer[] pcs = (ProdottoContainer[]) request.getAttribute("prodotti");
	if (pcs == null || pcs.length == 0) {
		out.println("<h1>Nessun prodotto trovato</h1>");
	} else {
		String title = (String) request.getAttribute("title");
		if (title != null) {
			out.println("<h1>" + title + "</h1>");
		}
		Boolean filtri = (Boolean) request.getAttribute("filtri");
		if (filtri != null && filtri) {
//			out.println("<main style='display: flex;'>");
			out.println("<main>");
			out.println("<div id='dashboard'>");
		} else {
			out.println("<main id='dashboard'>");
		}
		for (ProdottoContainer p : pcs) {
%>
<div data-price="<%= p.prodotto_prezzo %>">
	<a href="prodotto?id=<%= p.prodotto_id %>" class="image">
		<img src="immagine?id=<%= p.prodotto_immagine %>" alt/>
	</a>
	<div>
		<label>
			<a href="produttore?id=<%= p.produttore_id %>">
				<%= p.produttore_nome %>
			</a>
			<%= p.prodotto_nome %>
		</label>
		<span>
				<%= Formats.euro(p.prodotto_prezzo) %>
			</span>
		<a class="add_to_cart" onclick="addProdottoFromDashboardToCart(this, <%= p.prodotto_id %>)"></a>
	</div>
</div>
<%
	}
	if (filtri != null && filtri) {
		out.println("</div>");
%>
<div id="filtri">
	<label>
		<span>Prezzo minimo <label></label></span>
		<input type="range" name="min" min="1" max="1000" value="<%= minPrezzo != null ? minPrezzo : 1 %>">
	</label>
	<label>
		<span>Prezzo massimo <label></label></span>
		<input type="range" name="max" min="1" max="1000" value="<%= maxPrezzo != null ? maxPrezzo : 1000 %>">
	</label>
	<input type="submit" value="Apply"/>
</div>
<%
		}
		out.println("</main>");
	}
%>
