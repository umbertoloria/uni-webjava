<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="prodotto" type="model.bean.Prodotto" scope="request"/>
<jsp:useBean id="produttore" type="model.bean.Produttore" scope="request"/>
<main id="prodotto">
	<div>
		<img src="immagine?id=<%= prodotto.immagine %>" alt="">
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
		<div>
			<input type="number" min="1" value="1"/>
			<span data-prezzo-unitario="<%= prodotto.prezzo %>" class="price">
					<%= Formats.euro(prodotto.prezzo) %>
				</span>
		</div>
		<input type="submit" value="Aggiungi al carrello">
	</form>
</main>
<script>

	$("#prodotto form").submit(function (event) {
		event.preventDefault();
		const quantita = $(this).find("input[type='number']").val();
		addToCart(<%= prodotto.id %>, quantita, function (cart_count) {
			$("#rightside li.carrello a label").html(cart_count);
		});
	});

	$("#prodotto form input[type='number']").change(function () {
		const quantita = $(this).val();
		const span = $(this).next();
		const prezzo_unitario = span.attr("data-prezzo-unitario");
		span.html(euro_format(prezzo_unitario * quantita));
	});

</script>
