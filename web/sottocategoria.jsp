<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Sottocategoria sottocategoria;
	Categoria categoria;
	try {
		String s = request.getParameter("id");
		if (s == null || s.trim().isEmpty()) {
			throw new NumberFormatException();
		}
		sottocategoria = SottocategoriaDAO.doRetrieveByKey(Integer.parseInt(s));
		if (sottocategoria == null) {
			throw new NumberFormatException();
		}
		categoria = CategoriaDAO.doRetrieveByKey(sottocategoria.categoria);
		if (categoria == null) {
			throw new NumberFormatException();
		}
	} catch (NumberFormatException e) {
		response.sendRedirect("./");
		return;
	}
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(categoria.nome, "./categoria.jsp?id=" + categoria.id);
	breadcrumb.add(sottocategoria.nome);
	request.setAttribute("topbar_sottocategoria", sottocategoria.id);
	request.setAttribute("breadcrumb", breadcrumb);
	request.setAttribute("prodotti", ProdottoDAO.getFromSottocategoria(sottocategoria));
%>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<h1>
		<%= sottocategoria.nome %>
	</h1>
	<%@ include file="parts/Dashboard.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
