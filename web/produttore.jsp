<%@ page import="model.bean.Produttore" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.ProduttoreDAO" %>
<%@ page import="util.Breadcrumb" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Produttore produttore;
	try {
		String s = request.getParameter("id");
		if (s == null || s.trim().isEmpty()) {
			throw new NumberFormatException();
		}
		produttore = ProduttoreDAO.doRetrieveByKey(Integer.parseInt(s));
		if (produttore == null) {
			throw new NumberFormatException();
		}
	} catch (NumberFormatException e) {
		response.sendRedirect("./");
		return;
	}
%>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(produttore.nome);
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<h1>
		Prodotti di <%= produttore.nome %>
	</h1>
	<% request.setAttribute("prodotti", ProdottoDAO.getFromProduttore(produttore)); %>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
<jsp:include page="parts/Footer.jsp"/>
