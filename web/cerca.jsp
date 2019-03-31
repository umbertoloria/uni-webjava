<%
	String s = request.getParameter("q");
	if (s == null || s.trim().isEmpty()) {
		response.sendRedirect("./");
		return;
	}
%>
<%@ page import="dao.ProdottoDAO" %>
<%@ page import="database.DB" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<div id="wrapper">
	<%
		Prodotto[] prodotti = ProdottoDAO.search(s);
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
	<%@ include file="parts/Sidebar.jsp" %>
</div>
