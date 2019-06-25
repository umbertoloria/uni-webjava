<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="categoria" scope="request" type="model.bean.Categoria"/>
<main>
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
	<h1>
		<c:out value="${categoria.nome}"/>
	</h1>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
