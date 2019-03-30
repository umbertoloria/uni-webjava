<%@ page isErrorPage="true" %>
<%@ page import="database.DB" %>
<%@ page import="parts.Head" %>
<%@ page import="parts.Topbar" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%= Head.put() %>
<%= Topbar.put() %>
<div id="wrapper">
	<header>Errore 404</header>
	<p>La pagina richiesta non è stata trovata.</p>
</div>
<script type="text/javascript">
	setTimeout(function () {
		location.href = './';
	}, 3000);
</script>
<!-- TODO: Abbellire questa pagina -->
