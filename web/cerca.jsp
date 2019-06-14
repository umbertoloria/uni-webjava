<%
	String s = request.getParameter("q");
	if (s == null || s.trim().isEmpty()) {
		response.sendRedirect("./");
		return;
	}
%>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<!-- TODO: Messaggio nel caso di nessun risultato. -->
	<% request.setAttribute("prodotti", ProdottoDAO.search(s)); %>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
<jsp:include page="parts/Footer.jsp"/>
