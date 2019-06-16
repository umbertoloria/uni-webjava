<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Produttore" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Prodotto[] prodotti = (Prodotto[]) request.getAttribute("prodotti");
	for (Prodotto prod : prodotti) {
		Produttore produt = ProduttoreDAO.doRetrieveByKey(prod.produttore);
		assert produt != null;
%>
<a href='prodotto.jsp?id=<%= prod.id %>'>
	<img src='<%= prod.immagine %>'/>
	<span><b><%= produt.nome %></b> <%= prod.nome %></span>
</a>
<%
	}
%>