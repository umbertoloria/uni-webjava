<%@ page import="util.Breadcrumb" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Login");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<form action="login" method="post">
		<fieldset>
			<label>
				<span>E-Mail</span>
				<input type="email" name="email" placeholder="mario.rossi@libero.pizza" class="input"/>
			</label>
			<label>
				<span>Password</span>
				<input type="password" name="password" placeholder="Pa$$w0rd" class="input"/>
			</label>
		</fieldset>
		<input type="submit" value="Accedi" class="button"/>
		<div class="msg"></div>
	</form>
	<a href="registrazione.jsp">Non hai un account? Creane uno</a>
</main>
<jsp:include page="parts/Footer.jsp"/>
