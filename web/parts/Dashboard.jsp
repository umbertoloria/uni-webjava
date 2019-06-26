<%@ page import="model.container.ProdottoContainer" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	ProdottoContainer[] pcs = (ProdottoContainer[]) request.getAttribute("prodotti");
	if (pcs == null || pcs.length == 0) {
		out.println("<h1>Nessun prodotto trovato</h1>");
	} else {
		String title = (String) request.getAttribute("title");
		if (title != null) {
			out.println("<h1>" + title + "</h1>");
		}
		out.println("<div id=\"dashboard\">");
		for (ProdottoContainer p : pcs) {
%>
<div>
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
		// TODO: Implementare filtri.
		out.println("</div>");
	}
%>
