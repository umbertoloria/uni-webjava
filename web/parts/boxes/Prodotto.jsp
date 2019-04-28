<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="util.Formats" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="dashboard">
	<%
		for (Prodotto prod : (Prodotto[]) request.getAttribute("prodotti")) {
			Sottocategoria sottocat = SottocategoriaDAO.doRetrieveByKey(prod.sottocategoria);
			Produttore produt = ProduttoreDAO.doRetrieveByKey(prod.produttore);
	%>
	<div>
		<div class="prebox">
			<a href="produttore.jsp?id=<%= produt.id %>">
				<%= produt.nome %>
			</a>
			<a href="sottocategoria.jsp?id=<%= sottocat.id %>">
				<%= sottocat.nome %>
			</a>
		</div>
		<a class="box" href="prodotto.jsp?id=<%= prod.id %>">
			<h1>
				<%= prod.nome %>
			</h1>
			<img src="<%= prod.immagine %>" alt="Immagine del prodotto: '<%= prod.nome %>'"/>
			<span>
			<%= Formats.euro(prod.prezzo) %>
		</span>
		</a>
	</div>
	<%
		}
	%>
</div>
