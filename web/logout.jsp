<%
    request.getSession().removeAttribute("utente");
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<!-- TODO: css -->
Arrivederci.
<script>
	setTimeout(function () {
		location.href = ".";
	}, 3000);
</script>
