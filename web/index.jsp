<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="util.Breadcrumb" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Home");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<%@ include file="parts/Breadcrumb.jsp" %>
<main>
	<%
		// TODO: Nessun prodotto.
		request.setAttribute("prodotti", ProdottoDAO.getAll());
	%>
	<%@ include file="parts/boxes/Prodotto.jsp" %>
	<%@ include file="parts/Sidebar.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
