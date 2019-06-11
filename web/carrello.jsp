<%@ page import="model.bean.CarrelloItem" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Carrello");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<h1>Carrello</h1>
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
					<b><%= produt.nome %>
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
		$("#cart_list div div:last-child div:last-child input[type='number']").change(function () {
			const div = $(this).parent().parent().parent();
			const pid = div.attr("data-product-id");
			const val = $(this).val();
			if (val >= 1) {
				ajaxPostRequest("updateCart", "p=" + pid + "&q=" + val, function (out) {
					let totalCartCount = 0;
					$.each($.parseJSON(out), function (index, value) {
						totalCartCount += value.quantita;
						div.parent().find("div[data-product-id='" + value.prodotto + "'] " +
							"div:last-child div:first-child span").html(value.totale);
					});
					$("#rightside label.carrello a span").html(totalCartCount);
				});
			}
		});
		$("#cart_list div div:last-child div:last-child a").click(function () {
			const div = $(this).parent().parent().parent();
			const pid = div.attr("data-product-id");
			ajaxPostRequest("updateCart", "p=" + pid + "&q=0", function (out) {
				div.remove();
				let totalCartCount = 0;
				$.each($.parseJSON(out), function (index, value) {
					totalCartCount += value.quantita;
					div.parent().find("div[data-product-id='" + value.prodotto + "'] " +
						"div:last-child div:first-child span").html(value.totale);
				});
				$("#rightside label.carrello a span").html(totalCartCount);
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
<%@ include file="parts/Footer.jsp" %>
