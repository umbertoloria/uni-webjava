<%@ page import="model.Carrello" %>
<%@ page import="model.bean.CarrelloItem" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
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
	<h1>Carrello</h1>
	<%
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if (carrello != null) {
	%>
	<div id="carrello" data-serial="<%= carrello.serialize() %>">
		<%
			for (CarrelloItem carrelloItem : carrello) {
				Prodotto prodot = ProdottoDAO.doRetrieveByKey(carrelloItem.prodotto);
				assert prodot != null;
				Produttore produt = ProduttoreDAO.doRetrieveByKey(prodot.produttore);
				assert produt != null;
		%>
		<div data-product-id="<%= prodot.id %>">
			<div>
				<a href="prodotto.jsp?id=<%= prodot.id %>">
					<img src="<%= prodot.immagine %>"/>
				</a>
				<a href="prodotto.jsp?id=<%= prodot.id %>">
					<b>
						<%= produt.nome %>
					</b>
					<%= prodot.nome %>
				</a>
			</div>
			<div>
				<div>
					<span>
						<%= Formats.euro(prodot.prezzo * carrelloItem.quantita) %>
					</span>
				</div>
				<div>
					<input type="number" min="1" value="<%= carrelloItem.quantita%>"/>
					<a>Rimuovi</a>
				</div>
			</div>
		</div>
		<%
			}
		%>
	</div>
	<script>

		let inputTimeout;

		$("#carrello div div:last-child div:last-child input[type='number']").change(function () {
			const input = $(this);
			clearTimeout(inputTimeout);
			inputTimeout = setTimeout(function () {
				const div = input.parent().parent().parent();
				const prodotto = div.attr("data-product-id");
				const quantita = input.val();
				setToCart(prodotto, quantita, function (count, newtotal) {
					// Aggiorna "cardinalità"
					$("#rightside li.carrello a label").html(count);
					// Aggiornamento "data-serial"
					const app = div.parent().attr("data-serial").split(";");
					app.pop();
					let newserial = "";
					$.each(app, function (index, prod) {
						if (prod.split(":")[0] === prodotto) {
							newserial += prod.split(":")[0] + ":" + quantita + ";";
						} else {
							newserial += prod + ";";
						}
					});
					div.parent().attr("data-serial", newserial);
					// Aggiornamento "totale"
					div.find("div:last-child div:first-child span").html(newtotal);
					// Sincronizzazione carrello.
					aggiornaCarrello();
				}, function (error) {
					notification("Problema: " + error);
				});
			}, 300);
		});

		$("#carrello div div:last-child div:last-child a").click(function () {
			const div = $(this).parent().parent().parent();
			const prodotto = div.attr("data-product-id");
			dropFromCart(prodotto, function (count) {
				// Aggiorna "cardinalità"
				$("#rightside li.carrello a label").html(count);
				// Aggiornamento "data-serial"
				const app = div.parent().attr("data-serial").split(";");
				app.pop();
				let newserial = "";
				$.each(app, function (index, prod) {
					if (prod.split(":")[0] !== prodotto) {
						newserial += prod + ";";
					}
				});
				div.parent().attr("data-serial", newserial);
				// Rimozione prodotto.
				div.remove();
				// Sincronizzazione carrello.
				aggiornaCarrello();
			}, function (error) {
				notification("Problema: " + error);
			});
		});

	</script>
	<%
	} else {
	%>
	<p> Nessun prodotto inserito </p>
	<%
		}
	%>
</main>
<jsp:include page="parts/Footer.jsp"/>
