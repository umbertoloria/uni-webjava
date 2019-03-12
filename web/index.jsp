<%@ page import="database.DB" %>
<%@ page import="model.Prodotto" %>
<%@ page import="parts.Head" %>
<%@ page import="parts.Topbar" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%= Head.put() %>
<%= Topbar.put() %>
<div id="prodotti">
	<%
		for (Prodotto prodotto : Prodotto.getPosts()) {
			out.println(prodotto.makeHTML());
		}
	%>
</div>
</body>
</html>
