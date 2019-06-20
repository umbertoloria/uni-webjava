<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="dashboard">
	<%
		for (Prodotto prodotto : (Prodotto[]) request.getAttribute("prodotti")) {
			Sottocategoria sottocat = SottocategoriaDAO.doRetrieveByKey(prodotto.sottocategoria);
			assert sottocat != null;
			Produttore produttore = ProduttoreDAO.doRetrieveByKey(prodotto.produttore);
			assert produttore != null;
	%>
	<div>
		<a href="prodotto.jsp?id=<%= prodotto.id %>" class="image">
			<img src="<%= prodotto.immagine %>" alt="Immagine prodotto"/>
		</a>
		<div>
			<label>
				<a href="produttore.jsp?id=<%= produttore.id %>">
					<%= produttore.nome %>
				</a>
				<%= prodotto.nome %>
			</label>
			<span>
				<%= Formats.euro(prodotto.prezzo) %>
			</span>
			<a class="add_to_cart" onclick="addProdottoFromDashboardToCart(this, <%= prodotto.id %>)"></a>
		</div>
	</div>
	<%
		}
	%>
</div>
<!-- TODO: Implementare filtri. -->
