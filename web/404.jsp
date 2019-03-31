<%@ page isErrorPage="true" %>
<%@ page import="database.DB" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% DB.init(); %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<div id="fullwrapper">
	<header>Errore 404</header>
	<p>La pagina richiesta non è stata trovata.</p>
</div>
<script type="text/javascript">
	setTimeout(function () {
		location.href = './';
	}, 3000);
</script>
<!-- TODO: Abbellire questa pagina -->
