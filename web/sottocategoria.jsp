<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="model.dao.CategoriaDAO" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="util.Breadcrumb" %>
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
%>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(categoria.nome, "./categoria.jsp?id=" + categoria.id);
	breadcrumb.add(sottocategoria.nome);
	request.setAttribute("breadcrumb", breadcrumb);
	request.setAttribute("topbar_sottocategoria", sottocategoria.id);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<h1>
		<%= sottocategoria.nome %>
	</h1>
	<% request.setAttribute("prodotti", ProdottoDAO.getFromSottocategoria(sottocategoria)); %>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
<jsp:include page="parts/Footer.jsp"/>
