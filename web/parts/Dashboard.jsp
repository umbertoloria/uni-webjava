<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="dashboard">
	<%
		for (Prodotto prod : (Prodotto[]) request.getAttribute("prodotti")) {
			Sottocategoria sottocat = SottocategoriaDAO.doRetrieveByKey(prod.sottocategoria);
			assert sottocat != null;
			Produttore produt = ProduttoreDAO.doRetrieveByKey(prod.produttore);
			assert produt != null;
	%>
	<div>
		<a href="prodotto.jsp?id=<%= prod.id %>" class="image">
			<img src="<%= prod.immagine %>" alt="Immagine del prodotto: '<%= prod.nome %>'"/>
		</a>
		<label>
			<a href="produttore.jsp?id=<%= produt.id %>">
				<%= produt.nome %>
			</a>
			<%= prod.nome %>
		</label>
		<span>
			<%= Formats.euro(prod.prezzo) %>
		</span>
		<a class="add_to_cart" onclick="addProdottoToCart(this, <%= prod.id %>, 1)"></a>
	</div>
	<%
		}
	%>
</div>
<!-- TODO: Implementare filtri. -->
