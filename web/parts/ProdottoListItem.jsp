<%@ page import="model.container.ProdottoContainer" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	for (ProdottoContainer p : (ProdottoContainer[]) request.getAttribute("prodotti")) {
%>
<a href='prodotto?id=<%= p.prodotto_id %>'>
	<img src='immagine?id=<%= p.prodotto_immagine %>' alt/>
	<span><b><%= p.produttore_nome %></b> <%= p.prodotto_nome %></span>
</a>
<%
	}
%>
