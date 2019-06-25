<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="sottocategoria" type="model.bean.Sottocategoria" scope="request"/>
<main>
	<h1>
		<c:out value="${sottocategoria.nome}"/>
	</h1>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
