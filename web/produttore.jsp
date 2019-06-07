<%@ page import="model.dao.ProdottoDAO" %>
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
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(produttore.nome);
	request.setAttribute("breadcrumb", breadcrumb);
	request.setAttribute("prodotti", ProdottoDAO.getFromProduttore(produttore));
%>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<h1>
		Prodotti di <%= produttore.nome %>
	</h1>
	<%@ include file="parts/Dashboard.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
