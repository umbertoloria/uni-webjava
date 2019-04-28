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
<%@ page import="model.dao.ProdottoDAO" %>
<%@ page import="model.bean.Categoria" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Home", "./");
	breadcrumb.add(categoria.nome);
	request.setAttribute("breadcrumb", breadcrumb);
%>
<%@ include file="parts/Breadcrumb.jsp" %>
<main>
	<%
		request.setAttribute("prodotti", ProdottoDAO.getFromCategoria(categoria));
	%>
	<%@ include file="parts/boxes/Prodotto.jsp" %>
	<%@ include file="parts/Sidebar.jsp" %>
</main>
<%@ include file="parts/Footer.jsp" %>
