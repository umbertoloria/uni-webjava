<%@ page import="model.bean.CarrelloItem" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<%
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if (carrello != null) {
	%>
	<div id="cart_list">
		<%
			for (CarrelloItem carrelloItem : carrello) {
				Prodotto p = ProdottoDAO.doRetrieveByKey(carrelloItem.prodotto);
				assert p != null;
		%>
		<div>
			<img src="<%= p.immagine %>"/>
			<div>
				<label>
					<%= p.nome %>
				</label>
			</div>
			<span>
				<%= Formats.euro(p.prezzo) %> x <%= carrelloItem.quantita %>
			</span>
		</div>
		<%
			}
		%>
	</div>
	<%
	} else {
	%>
	<p> Nessun prodotto inserito </p>
	<%
		}
	%>
</main>
<%@ include file="parts/Footer.jsp" %>
