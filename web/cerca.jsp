<%
	String s = request.getParameter("q");
	if (s == null || s.trim().isEmpty()) {
		response.sendRedirect("./");
		return;
	}
%>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<%
		// TODO: Nessun prodotto.
		request.setAttribute("prodotti", ProdottoDAO.search(s));
	%>
	<%@ include file="parts/boxes/Prodotto.jsp" %>
	<%@ include file="parts/Sidebar.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
