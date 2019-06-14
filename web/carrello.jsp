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
	<!-- TODO: Aggiornare i dati periodicamente. -->
	<%
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if (carrello != null) {
	%>
	<div id="cart_list">
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

		function newInput(elem) {
			const div = elem.parent().parent().parent();
			const pid = div.attr("data-product-id");
			const val = elem.val();
			setToCart(pid, val, function (itemCarrello) {
				div.parent().find("div[data-product-id='" + itemCarrello.prodotto + "'] " +
					"div:last-child div:first-child span").html(itemCarrello.totale);
			}, function (count) {
				$("#rightside li.carrello a label").html(count);
			});
		}

		let inputTimeout;

		$("#cart_list div div:last-child div:last-child input[type='number']").change(function () {
			const input = $(this);
			clearTimeout(inputTimeout);
			inputTimeout = setTimeout(function () {
				newInput(input);
			}, 500);
		});

		$("#cart_list div div:last-child div:last-child a").click(function () {
			const div = $(this).parent().parent().parent();
			const dad = div.parent();
			const pid = div.attr("data-product-id");
			dropFromCart(pid, function (itemCarrello) {
				dad.find("div[data-product-id='" + itemCarrello.prodotto + "'] " +
					"div:last-child div:first-child span").html(itemCarrello.totale);
				dad.find("div[data-product-id='" + itemCarrello.prodotto + "'] " +
					"div:last-child div:last-child input[type='number']").val(itemCarrello.quantita);
			}, function (count) {
				div.remove();
				$("#rightside li.carrello a label").html(count);
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
