<%
	String s = request.getParameter("q");
	if (s == null || s.trim().isEmpty()) {
		response.sendRedirect("./");
		return;
	}
%>
<%@ page import="database.DB" %>
<%@ page import="model.Prodotto" %>
<%@ page import="parts.Head" %>
<%@ page import="parts.Topbar" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%= Head.put() %>
<%= Topbar.put() %>
<%
	Prodotto[] prodotti = Prodotto.search(s);
	if (prodotti.length > 0) {
		out.println("<div id='dashboard'>");
		for (Prodotto prodotto : prodotti) {
			out.println(prodotto.makeBox());
		}
		out.println("</div>");
	} else {
		// TODO: Nessun prodotto.
	}
%>
