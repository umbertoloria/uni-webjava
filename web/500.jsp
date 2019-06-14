<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<article>
		<h1>
			Errore 500: <%= exception.getMessage() %>
		</h1>
		<pre><%
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			exception.printStackTrace(printWriter);
			out.println(stringWriter);
			printWriter.close();
			stringWriter.close();
		%></pre>
	</article>
</main>
<jsp:include page="parts/Footer.jsp"/>
