<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	request.setAttribute("prodotti", ProdottoDAO.getAll());
%>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<h1>Top 10</h1>
	<%@ include file="parts/Dashboard.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
