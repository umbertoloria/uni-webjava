<%
	String s = request.getParameter("q");
	if (s == null || s.trim().isEmpty()) {
		response.sendRedirect("./");
		return;
	}
	String[] ss = s.split(" ");
	for (int i = 0; i < ss.length; i++) {
		ss[i] = ss[i].trim();
	}
%>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<!-- TODO: Messaggio nel caso di nessun risultato. -->
	<% request.setAttribute("prodotti", ProdottoDAO.search(ss)); %>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
<jsp:include page="parts/Footer.jsp"/>
