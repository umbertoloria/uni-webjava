<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<form action="servlet_login" method="post">
		<fieldset>
			<label>
				<span>E-Mail</span>
				<input type="email" name="email" placeholder="mario.rossi@libero.pizza"/>
			</label>
			<label>
				<span>Password</span>
				<input type="password" name="password" placeholder="Pa$$w0rd"/>
			</label>
		</fieldset>
		<input type="submit" value="Accedi"/>
		<div class="msg"></div>
	</form>
	<a href="registrazione">Non hai un account? Creane uno</a>
</main>
