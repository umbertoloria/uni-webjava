<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="produttore" type="model.bean.Produttore" scope="request"/>
<main>
	<h1>
		Prodotti di <c:out value="${produttore.nome}"/>
	</h1>
	<jsp:include page="parts/Dashboard.jsp"/>
</main>
