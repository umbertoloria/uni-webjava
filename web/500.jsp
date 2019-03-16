<%@ page isErrorPage="true" %>
<%@ page import="database.DB" %>
<%@ page import="parts.Head" %>
<%@ page import="parts.Topbar" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%= Head.put() %>
<%= Topbar.put() %>
<div class="wrapper">
	<header>Errore 500: <%= exception.getMessage() %>
	</header>
	<pre><%
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		exception.printStackTrace(printWriter);
		out.println(stringWriter);
		printWriter.close();
		stringWriter.close();
	%></pre>
</div>
<script type="text/javascript">
	setTimeout(function () {
		location.href = './';
	}, 3000);
</script>
<!-- TODO: Abbellire questa pagina -->
