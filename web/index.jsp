<%@ page import="database.DB" %>
<%@ page import="model.Prodotto" %>
<%@ page import="parts.Head" %>
<%@ page import="parts.Topbar" %>
<%@ page import="parts.Sidebar" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%= Head.put() %>
<%= Topbar.put() %>
<div id="wrapper">
	<div id="dashboard">
		<%
			for (Prodotto prodotto : Prodotto.getAll()) {
				out.println(prodotto.makeBox());
			}
		%>
	</div>
	<%= Sidebar.put() %>
</div>
