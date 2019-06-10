<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<article>
		<h1>Errore 404</h1>
		<p>La pagina richiesta non è stata trovata.</p>
	</article>
</main>
<%@ include file="parts/Footer.jsp" %>
<script type="text/javascript">
	setTimeout(function () {
		location.href = './';
	}, 3000);
</script>
