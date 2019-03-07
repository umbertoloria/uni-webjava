<%@ page import="database.DB" %>
<%@ page import="model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<html>
<head>
	<title>HomePage</title>
</head>
<body>
<%
	for (Post post : Post.getPosts()) {
		out.println(post.makeHTML());
	}
%>
</body>
</html>
