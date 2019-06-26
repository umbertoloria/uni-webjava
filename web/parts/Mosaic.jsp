<%@ page import="model.bean.Sottocategoria" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="mosaic">
	<%
		for (Sottocategoria sottocategoria : (Sottocategoria[]) request.getAttribute("sottocategorie")) {
	%>
	<a href="sottocategoria?id=<%= sottocategoria.id %>">
		<img src="immagine?id=<%= sottocategoria.immagine %>" alt/>
		<div><span><%= sottocategoria.nome %></span></div>
	</a>
	<%
		}
	%>
</div>
