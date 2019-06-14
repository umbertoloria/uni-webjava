<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<article>
		<h1>Errore 404</h1>
		<p>La pagina richiesta non è stata trovata.</p>
	</article>
</main>
<jsp:include page="parts/Footer.jsp"/>
<script type="text/javascript">
	setTimeout(function () {
		location.href = './';
	}, 3000);
</script>
