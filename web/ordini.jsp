<%@ page import="model.bean.Ordine" %>
<%@ page import="model.bean.OrdineHasProdotto" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<%
		Ordine[] ordini = (Ordine[]) request.getAttribute("ordini");
		if (ordini == null || ordini.length == 0) {
	%>
	<h1>Non hai ancora effettuato un ordine</h1>
	<%
	} else {
	%>
	<div id="ordini">
		<%
			for (Ordine ordine : ordini) {
		%>
		<div class="ordine">
			<div class="ordine_items">
				<%
					for (OrdineHasProdotto item : ordine) {
						Prodotto prodotto = ProdottoDAO.doRetrieveByKey(item.prodotto);
						assert prodotto != null;
						Produttore produttore = ProduttoreDAO.doRetrieveByKey(prodotto.produttore);
						assert produttore != null;
				%>
				<a href="prodotto?id=<%= item.prodotto %>">
					<img src="immagine?id=<%= prodotto.immagine %>" alt/>
					<label>
						<b>
							<%= produttore.nome %>
						</b>
						<%= prodotto.nome %>
					</label>
					<span class="pr"><%= Formats.euro(item.prezzo * item.quantita) %></span>
					<span class="qu">x<%= item.quantita %></span>
				</a>
				<%
					}
				%>
			</div>
			<div>
				<h2>
					Spedito a <%= ordine.destinazione %>
				</h2>
				<h2>
					Spedito il <%= Formats.date(ordine.momento) %>
				</h2>
				<h2>
					Acquistato con <%= ordine.pagamento %>
				</h2>
				<span class="price">
			<%= Formats.euro(ordine.getTotale()) %>
		</span>
			</div>
		</div>
		<%
			}
		%>
	</div>
	<%
		}
	%>
</main>
