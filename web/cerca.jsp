<%@ page import="model.bean.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<%
		Prodotto[] prodotti = (Prodotto[]) request.getAttribute("prodotti");
		if (prodotti == null || prodotti.length == 0) {
	%>
	<h1>Nessun prodotto trovato</h1>
	<%
	} else {
	%>
	<jsp:include page="parts/Dashboard.jsp"/>
	<%
		}
	%>
</main>
