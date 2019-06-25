<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<h1>Top 10</h1>
	<% request.setAttribute("prodotti", ProdottoDAO.getAll()); %>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
