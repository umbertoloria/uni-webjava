<%@ page import="model.dao.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Categoria categoria;
	try {
		String s = request.getParameter("id");
		if (s == null || s.trim().isEmpty()) {
			throw new NumberFormatException();
		}
		categoria = CategoriaDAO.doRetrieveByKey(Integer.parseInt(s));
		if (categoria == null) {
			throw new NumberFormatException();
		}
	} catch (NumberFormatException e) {
		response.sendRedirect("./");
		return;
	}
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(categoria.nome);
	request.setAttribute("breadcrumb", breadcrumb);
	request.setAttribute("topbar_categoria", categoria.id);
	request.setAttribute("prodotti", ProdottoDAO.getFromCategoria(categoria));
%>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<div id="mosaic">
		<%
			for (Sottocategoria sottocategoria : SottocategoriaDAO.getAllOf(categoria.id)) {
				out.println("<a href='sottocategoria.jsp?id=" + sottocategoria.id + "'>");
				out.println("<img src='" + sottocategoria.immagine + "'/>");
				out.println("<div><span>" + sottocategoria.nome + "</span></div>");
				out.println("</a>");
			}
		%>
	</div>
	<h1>
		<%= categoria.nome %>
	</h1>
	<%@ include file="parts/Dashboard.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
