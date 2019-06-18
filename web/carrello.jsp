<%@ page import="model.Carrello" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.IndirizzoDAO" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="util.Breadcrumb" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Carrello");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<%
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if (carrello == null) {
			out.println("<h1>Il carrello è vuoto</h1>");
		} else {
	%>
	<h1>Carrello</h1>
	<div style="display: grid; grid-template-columns: 2fr 1fr; grid-gap: 30px; width: 100%; align-items: flex-start;">
		<div id="carrello" data-serial="<%= carrello.serialize() %>">
			<%
				for (CarrelloItem carrelloItem : carrello) {
					Prodotto prodotto = ProdottoDAO.doRetrieveByKey(carrelloItem.prodotto);
					assert prodotto != null;
					Produttore produttore = ProduttoreDAO.doRetrieveByKey(prodotto.produttore);
					assert produttore != null;
			%>
			<div data-product-id="<%= prodotto.id %>">
				<div>
					<a href="prodotto.jsp?id=<%= prodotto.id %>">
						<img src="<%= prodotto.immagine %>" alt="Immagine prodotto"/>
					</a>
					<a href="prodotto.jsp?id=<%= prodotto.id %>">
						<b>
							<%= produttore.nome %>
						</b>
						<%= prodotto.nome %>
					</a>
				</div>
				<div>
					<div>
					<span class="price">
						<%= Formats.euro(prodotto.prezzo * carrelloItem.quantita) %>
					</span>
					</div>
					<div>
						<input type="number" min="1" value="<%= carrelloItem.quantita%>"
						       onchange="updateCartSet(this, <%= prodotto.id %>, value)"/>
						<a onclick="updateCartDrop(this, <%= prodotto.id %>)">Rimuovi</a>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<div id="buy_box">
			<label>
				<span>Quanto costa l'ordine</span>
				<span class="price" id="cart_total"><%= Formats.euro(carrello.getTotal()) %></span>
			</label>
			<%
				Utente u = (Utente) request.getSession().getAttribute("utente");
				if (u == null) {
					out.println("<a href=\"login.jsp\">Accedi</a> per poter completare l'ordine");
				} else {
			%>
			<label>
				<span>Dove vorresti che ti recapitassimo l'ordine?</span>
				<select name="indirizzo">
					<option disabled value="">Scegli un indirizzo...</option>
					<%
						Indirizzo[] indirizzi = IndirizzoDAO.getAllThoseOf(u);
						for (Indirizzo indirizzo : indirizzi) {
							out.println("<option value=\"" + indirizzo.id + "\">" + indirizzo.nome + "</option>");
						}
					%>
				</select>
			</label>
			<input type="submit" value="Acquista" onclick="ordina(this)"/>
			<%
				}
			%>
		</div>
	</div>
	<script>

		function ordina(btn) {
			btn = $(btn);
			const indirizzo = btn.prev().find("[name='indirizzo']").val();
			ajaxPostRequest("acquista", "indirizzo=" + indirizzo, function (out) {
				error_manager(JSON.parse(out), null, function (message) {
					notification("Problema: " + message);
				}, function (out) {
					overlay(out);
				}, function (url) {
					location.href = url;
				}, function () {
					location.reload();
				});
			});
		}

		let inputTimeout;

		function updateCartSet(input, prodotto, quantita) {
			clearTimeout(inputTimeout);
			inputTimeout = setTimeout(function () {
				const div = $(input).parent().parent().parent();
				const dad = div.parent();
				setToCart(prodotto, quantita, function (cart_count, product_total, cart_total) {
					// Aggiornamento serial
					dad.attr("data-serial", Serializator.set(dad.attr("data-serial"), prodotto, quantita));
					// Aggiornamento dati visibili
					$("#rightside li.carrello a label").html(cart_count);
					div.find("div:last-child div:first-child span").html(product_total);
					$("#cart_total").html(cart_total);
					// Sincronizzazione carrello
					aggiornaCarrello();
					// TODO: Forse, si potrebbe pensare di farsi arrivare direttamente qui anche il serial...
				}, function (error) {
					notification("Problema: " + error);
				});
			}, 300);
		}

		function updateCartDrop(input, prodotto) {
			const div = $(input).parent().parent().parent();
			const dad = div.parent();
			dropFromCart(prodotto, function (cart_count, cart_total) {
				// Aggiornamento serial
				dad.attr("data-serial", Serializator.drop(dad.attr("data-serial"), prodotto));
				// Aggiorna dati visibili
				$("#rightside li.carrello a label").html(cart_count);
				div.remove();
				$("#cart_total").html(cart_total);
				// Sincronizzazione carrello
				aggiornaCarrello();
				// TODO: Si potrebbe pensare come sopra...
			}, function (error) {
				notification("Problema: " + error);
			});
		}

	</script>
	<%
		}
	%>
</main>
<jsp:include page="parts/Footer.jsp"/>
