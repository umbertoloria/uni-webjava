<%@ page import="util.Breadcrumb" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Registrazione");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<form action="servlet_registrazione" method="post">
		<fieldset>
			<label>
				<span>Nome</span>
				<input type="text" name="nome" placeholder="Mario Rossi" class="input"/>
			</label>
			<label>
				<span>E-Mail</span>
				<input type="text" name="email" placeholder="mario.rossi@libero.pizza" class="input"/>
			</label>
			<label>
				<span>Password</span>
				<input type="password" name="password" placeholder="Pa$$w0rd" class="input"/>
			</label>
			<label>
				<span>Conferma password</span>
				<input type="password" name="password2" placeholder="Pa$$w0rd" class="input"/>
			</label>
		</fieldset>
		<input type="submit" value="Registrati" class="button"/>
		<div class="msg"></div>
	</form>
</main>
<jsp:include page="parts/Footer.jsp"/>
