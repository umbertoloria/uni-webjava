<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="util.Breadcrumb" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Prodotto prodotto;
	Produttore produttore;
	try {
		String s = request.getParameter("id");
		if (s == null || s.trim().isEmpty()) {
			throw new NumberFormatException();
		}
		prodotto = ProdottoDAO.doRetrieveByKey(Integer.parseInt(s));
		if (prodotto == null) {
			throw new NumberFormatException();
		}
		produttore = ProduttoreDAO.doRetrieveByKey(prodotto.produttore);
		if (produttore == null) {
			throw new NumberFormatException();
		}
	} catch (NumberFormatException e) {
		response.sendRedirect("./");
		return;
	}
%>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(produttore.nome);
	breadcrumb.add(prodotto.nome);
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<div id="prodotto">
		<div>
			<img src="<%=prodotto.immagine%>">
		</div>
		<div>
			<h2>
				<%= produttore.nome %> <%= prodotto.nome %>
			</h2>
			<p>
				<%= prodotto.descrizione %>
			</p>
		</div>
		<form>
			<p> Qui ci vanno le stelline </p>
			<a> Recensisci il prodotto </a>
			<p> Spedizione Gratuita </p>
			<p> Consegna in 24 Ore </p>
			<div>
				<input type="number" min="1" value="1"/>
				<span data-prezzo-unitario="<%= prodotto.prezzo %>">
					<%= Formats.euro(prodotto.prezzo) %>
				</span>
			</div>
			<input type="submit" value="Aggiungi al carrello">
		</form>
	</div>
	<script>

		$("#prodotto form").submit(function (event) {
			event.preventDefault();
			const quantita = $(this).find("input[type='number']").val();
			addToCart(<%= prodotto.id %>, quantita, function (count) {
				$("#rightside li.carrello a label").html(count);
			}, function () {
			});
		});

		$("#prodotto form input[type='number']").change(function () {
			const quantita = $(this).val();
			const span = $(this).next();
			const prezzo_unitario = span.attr("data-prezzo-unitario");
			span.html(euro_format(prezzo_unitario * quantita));
		});

		function euro_format(prezzo) {
			prezzo = "€ " + Math.round(prezzo * 100) / 100;
			prezzo = prezzo.replace(".", ",");
			if (prezzo.indexOf(",") === -1) {
				prezzo += ",00";
			}
			while (prezzo.indexOf(",") > prezzo.length - 3) {
				prezzo += "0";
			}
			return prezzo;
		}

	</script>
</main>
<jsp:include page="parts/Footer.jsp"/>
