<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="model.dao.CategoriaDAO" %>
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="util.Breadcrumb" %>
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
%>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add(categoria.nome);
	request.setAttribute("breadcrumb", breadcrumb);
	request.setAttribute("topbar_categoria", categoria.id);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<div id="mosaic">
		<%
			for (Sottocategoria sottocategoria : SottocategoriaDAO.getAllThoseOf(categoria.id)) {
		%>
		<a href="sottocategoria.jsp?id=<%= sottocategoria.id %>">
			<img src="immagine?id=<%= sottocategoria.immagine %>" alt/>
			<div><span><%= sottocategoria.nome %></span></div>
		</a>
		<%
			}
		%>
	</div>
	<h1>
		<%= categoria.nome %>
	</h1>
	<% request.setAttribute("prodotti", ProdottoDAO.getFromCategoria(categoria)); %>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
<jsp:include page="parts/Footer.jsp"/>
