<%@ page import="model.bean.Utente" %>
<%@ page import="util.Breadcrumb" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if (utente == null) {
		response.sendRedirect("./");
		return;
	}
%>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Amministrazione");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<form action="aggiungiProdotto" method="post" enctype="multipart/form-data">
		<fieldset>
			<label>
				<span>Immagine</span>
				<input type="file" name="file" placeholder="Carica l'immagine del prodotto..."/>
			</label>
		</fieldset>
		<input type="submit" value="Aggiungi prodotto"/>
		<div class="msg"></div>
	</form>
</main>
<jsp:include page="parts/Footer.jsp"/>
