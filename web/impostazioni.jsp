<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if (utente == null) {
		response.sendRedirect("./");
		return;
	}
%>
<%@ include file="parts/Head.jsp" %>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Impostazioni");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<%@ include file="parts/Topbar.jsp" %>
<main>
	<div class="tabs">
		<ul class="tabs_header">
			<li><a>Cambia Password</a></li>
			<li><a>Aggiorna Indirizzi</a></li>
			<li><a>Informazioni Personali</a></li>
		</ul>
		<div class="tabs_container">
			<div>Primo: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
				the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type
				and scrambled it to make a type specimen book. It has survived not only five centuries, but also the
				leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with
				the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
				publishing software like Aldus PageMaker including versions of Lorem Ipsum.
			</div>
			<div>Secondo: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
				been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of
				type and scrambled it to make a type specimen book. It has survived not only five centuries, but also
				the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s
				with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
				publishing software like Aldus PageMaker including versions of Lorem Ipsum.
			</div>
			<div>Terzo: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
				the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type
				and scrambled it to make a type specimen book. It has survived not only five centuries, but also the
				leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with
				the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
				publishing software like Aldus PageMaker including versions of Lorem Ipsum.
			</div>
		</div>
	</div>
</main>
<%@ include file="parts/Footer.jsp" %>
