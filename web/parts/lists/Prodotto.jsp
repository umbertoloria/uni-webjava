<%@ page import="model.bean.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Prodotto[] prodotti = (Prodotto[]) request.getAttribute("prodotti");
	for (Prodotto prodotto : prodotti) {
%>
<a href='prodotto.jsp?id=<%= prodotto.id %>'>
	<img src='<%= prodotto.immagine %>' alt="Immagine del prodotto: <%= prodotto.nome %>"/>
	<span><%= prodotto.nome %> </span>
</a>
<%
	}
%>