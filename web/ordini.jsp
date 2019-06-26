<%@ page import="model.container.OrdineContainer" %>
<%@ page import="model.container.OrdineHasProdottoContainer" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<%
		OrdineContainer[] ordini = (OrdineContainer[]) request.getAttribute("ordini");
		if (ordini == null || ordini.length == 0) {
	%>
	<h1>Non hai ancora effettuato un ordine</h1>
	<%
	} else {
	%>
	<div id="ordini">
		<%
			for (OrdineContainer ordine : ordini) {
		%>
		<div class="ordine">
			<div class="ordine_items">
				<%
					for (OrdineHasProdottoContainer itema : ordine) {
				%>
				<a href="prodotto?id=<%= itema.prodotto_id %>">
					<img src="immagine?id=<%= itema.prodotto_immagine %>" alt/>
					<label>
						<b>
							<%= itema.produttore_nome %>
						</b>
						<%= itema.prodotto_nome %>
					</label>
					<span class="pr"><%= Formats.euro(itema.prezzo * itema.quantita) %></span>
					<span class="qu">x<%= itema.quantita %></span>
				</a>
				<%
					}
				%>
			</div>
			<div>
				<h2>
					Spedito a <%= ordine.getDestinazione() %>
				</h2>
				<h2>
					Spedito il <%= Formats.date(ordine.getMomento()) %>
				</h2>
				<h2>
					Acquistato con <%= ordine.getPagamento() %>
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
