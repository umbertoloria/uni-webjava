<%
    request.getSession().removeAttribute("utente");
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="parts/Head.jsp" %>
<!-- TODO: css -->
Arrivederci scemo.
<script>
	setTimeout(function () {
		location.href = ".";
	}, 3000);
</script>
