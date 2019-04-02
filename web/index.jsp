<%@ page import="dao.ProdottoDAO" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<div id="wrapper">
	<div id="dashboard">
		<%
			for (Prodotto prodotto : ProdottoDAO.getAll()) {
				out.println(prodotto.makeBox());
			}
		%>
	</div>
	<%@ include file="parts/Sidebar.jsp" %>
</div>
