<%@ page import="database.DB" %>
<%@ page import="model.Prodotto" %>
<%@ page import="parts.Topbar" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<html>
<head>
	<title>HomePage</title>
	<link rel="stylesheet" href="css/format.css"/>
	<link rel="stylesheet" href="css/topbar.css"/>
	<link rel="stylesheet" href="css/prodotto.css"/>
	<script type="text/javascript" src="js/Core.js"></script>
</head>
<body>
<%= Topbar.put() %>
<div id="prodotti">
	<%
		for (Prodotto prodotto : Prodotto.getPosts()) {
			out.println(prodotto.makeHTML());
		}
	%>
</div>
</body>
</html>
