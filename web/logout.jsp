<%
	request.getSession().removeAttribute("utente");
	// TODO: Cancellare anche "age" relativo all'user, non ancora implementato...
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<script>
	overlay("Arrivederci");
	setTimeout(function () {
		location.href = ".";
	}, 3000);
</script>
