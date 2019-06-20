<%@ page import="model.bean.Ordine" %>
<%@ page import="model.bean.Utente" %>
<%@ page import="model.dao.OrdineDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if (utente == null) {
		response.sendRedirect("./");
		return;
	}
%>
<jsp:include page="parts/Head.jsp"/>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<div id="ordini">
		<%
			Ordine[] ordini = OrdineDAO.getAllThoseOf(utente);
			for (Ordine ordine : ordini) {
				request.setAttribute("ordine", ordine);
		%>
		<jsp:include page="parts/Ordine.jsp"/>
		<%
			}
		%>
	</div>
</main>
<jsp:include page="parts/Footer.jsp"/>
