<%@ page import="model.Carrello" %>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<%
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if (carrello == null) {
			out.println("<h1>Il carrello è vuoto</h1>");
		} else {
	%>
	<h1>Carrello</h1>
	<div id="carrello_container">
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
					<a href="prodotto?id=<%= prodotto.id %>">
						<img src="immagine?id=<%= prodotto.immagine %>" alt/>
					</a>
					<a href="prodotto?id=<%= prodotto.id %>">
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
					out.println("<a href=\"login\">Accedi</a> per completare l'ordine");
				} else {
					Indirizzo[] indirizzi = (Indirizzo[]) request.getAttribute("indirizzi");
					CartaCredito[] carte = (CartaCredito[]) request.getAttribute("carte");
					if (indirizzi.length > 0 && carte.length > 0) {
			%>
			<label>
				<span>Dove vorresti che ti recapitassimo l'ordine?</span>
				<select name="indirizzo">
					<option selected disabled value="">Scegli un indirizzo...</option>
					<%
						for (Indirizzo indirizzo : indirizzi) {
							out.println("<option value=\"" + indirizzo.id + "\">" + indirizzo.nome + "</option>");
						}
					%>
				</select>
			</label>
			<label>
				<span>Su quale carta dovremo addebitarti l'ordine?</span>
				<select name="carta">
					<option selected disabled value="">Scegli una carta...</option>
					<%
						for (CartaCredito carta : carte) {
							out.println("<option value=\"" + carta.numero + "\">" + carta.numero + "</option>");
						}
					%>
				</select>
			</label>
			<input type="submit" value="Acquista" onclick="acquista(this)"/>
			<%
					} else if (indirizzi.length > 0) {
						out.println("<a href=\"impostazioni#3\">Aggiungi una carta</a> per completare l'ordine");
					} else {
						out.println("<a href=\"impostazioni#2\">Aggiungi un indirizzo</a> per completare l'ordine");
					}
				}
			%>
		</div>
	</div>
	<script>

		function acquista(btn) {
			btn = $(btn);
			const indirizzo = btn.parent().find("[name='indirizzo']").val();
			const carta = btn.parent().find("[name='carta']").val();
			ajaxPostRequest("servlet_acquista", "indirizzo=" + indirizzo + "&carta=" + carta, function (out) {
				error_manager(out);
			});
		}

		let inputTimeout;

		function updateCartSet(input, prodotto, quantita) {
			clearTimeout(inputTimeout);
			inputTimeout = setTimeout(function () {
				const div = $(input).parent().parent().parent();
				const dad = div.parent();
				setToCart(prodotto, quantita, function (cart_count, product_total, cart_total, cart_serial) {
					// Aggiornamento dati visibili
					$("#rightside li.carrello a label").html(cart_count);
					div.find("div:last-child div:first-child span").html(product_total);
					$("#cart_total").html(cart_total);
					// Sincronizzazione carrello
					const newserial = Serializator.set(dad.attr("data-serial"), prodotto, quantita);
					if (newserial === cart_serial) {
						dad.attr("data-serial", newserial);
					} else {
						location.reload();
					}
				});
			}, 300);
		}

		function updateCartDrop(input, prodotto) {
			const div = $(input).parent().parent().parent();
			const dad = div.parent();
			dropFromCart(prodotto, function (cart_count, cart_total, cart_serial) {
				// Aggiorna dati visibili
				$("#rightside li.carrello a label").html(cart_count);
				div.remove();
				$("#cart_total").html(cart_total);
				// Sincronizzazione carrello
				const newserial = Serializator.drop(dad.attr("data-serial"), prodotto);
				if (newserial === cart_serial) {
					dad.attr("data-serial", newserial);
				} else {
					location.reload();
				}
			});
		}

	</script>
	<%
		}
	%>
</main>
