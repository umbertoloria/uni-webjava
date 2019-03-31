<%@ page isErrorPage="true" %>
<%@ page import="database.DB" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<div id="fullwrapper">
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
