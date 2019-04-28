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
%>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Home", "./");
	breadcrumb.add(categoria.nome, "./categoria.jsp?id=" + categoria.id);
	breadcrumb.add(sottocategoria.nome);
	request.setAttribute("breadcrumb", breadcrumb);
%>
<%@ include file="parts/Breadcrumb.jsp" %>
<main>
	<%
		request.setAttribute("prodotti", ProdottoDAO.getFromSottocategoria(sottocategoria));
	%>
	<%@ include file="parts/boxes/Prodotto.jsp" %>
	<%@ include file="parts/Sidebar.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
