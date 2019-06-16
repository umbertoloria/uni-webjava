<%@ page import="model.bean.Indirizzo" %>
<%@ page import="model.bean.Utente" %>
<%@ page import="model.dao.IndirizzoDAO" %>
<%@ page import="util.Breadcrumb" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if (utente == null) {
		response.sendRedirect("./");
		return;
	}
%>
<jsp:include page="parts/Head.jsp"/>
<%
	Breadcrumb breadcrumb = new Breadcrumb();
	breadcrumb.add("Impostazioni");
	request.setAttribute("breadcrumb", breadcrumb);
%>
<jsp:include page="parts/Topbar.jsp"/>
<main>
	<div class="tabs">
		<ul class="tabs_header">
			<li><a>Cambia password</a></li>
			<li><a>Gestisci indirizzi</a></li>
			<li><a>Informazioni personali</a></li>
		</ul>
		<div class="tabs_container">
			<div>
				<form action="cambiaPassword" method="post">
					<fieldset>
						<label>
							<span>Vecchia password</span>
							<input type="password" name="vecchia" placeholder="Pa$$w0rd" class="input"/>
						</label>
						<label>
							<span>Nuova password</span>
							<input type="password" name="nuova" placeholder="Pa$$w0rd" class="input"/>
						</label>
						<label>
							<span>Conferma password</span>
							<input type="password" name="conferma" placeholder="Pa$$w0rd" class="input"/>
						</label>
					</fieldset>
					<input type="submit" value="Cambia password" class="button"/>
					<div class="msg"></div>
				</form>
			</div>
			<div>
				<div id="indirizzi">
					<%
						for (Indirizzo indirizzo : IndirizzoDAO.getAllThoseOf(utente)) {
					%>
					<div data-indirizzo-id="<%= indirizzo.id %>">
						<div>
							<a href="modificaIndirizzo.jsp?id=<%= indirizzo.id %>">
								<img src="images/edit.png"/>
							</a>
							<a>
								<img src="images/drop.png"/>
							</a>
						</div>
						<h3>
							<%= indirizzo.nome %>
						</h3>
						<ul>
							<li>
								<%= indirizzo.indirizzo %>
							</li>
							<li>
								<%= indirizzo.citta%>
							</li>
							<li>
								<%= indirizzo.cap%>
							</li>
							<li>
								<%= indirizzo.provincia%>
							</li>
						</ul>
					</div>
					<%
						}
					%>
				</div>
				<script>
					$("#indirizzi a:last-child").click(function () {
						const div = $(this).parent().parent();
						const id = div.attr("data-indirizzo-id");
						ajaxPostRequest("rimuoviIndirizzo", "id=" + id, function (out) {
							if (out === 'logout') {
								location.href = 'logout.jsp';
							} else if (out === 'done') {
								div.animate({opacity: "0"}, 300);
								setTimeout(function () {
									div.remove();
								}, 500);
							} else {
								location.reload();
							}
						});
					});
				</script>
				<form action="aggiungiIndirizzo" method="post">
					<fieldset>
						<label>
							<span>Nome</span>
							<input type="text" name="nome" placeholder="Casa" class="input"/>
						</label>
						<label>
							<span>Indirizzo</span>
							<input type="text" name="indirizzo" placeholder="Via Tal Dei Tali 59" class="input"/>
						</label>
						<label>
							<span>Città</span>
							<input type="text" name="citta" placeholder="Vallo di Java" class="input"/>
						</label>
						<label>
							<span>CAP</span>
							<input type="number" name="cap" placeholder="1996" class="input" min="00000" max="99999"/>
						</label>
						<label>
							<span>Provincia</span>
							<input type="text" name="provincia" placeholder="Orientata ad Oggetti" class="input"/>
						</label>
					</fieldset>
					<input type="submit" value="Aggiungi indirizzo" class="button"/>
					<div class="msg"></div>
				</form>
			</div>
			<div>Terzo: Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
				the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type
				and scrambled it to make a type specimen book. It has survived not only five centuries, but also the
				leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with
				the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop
				publishing software like Aldus PageMaker including versions of Lorem Ipsum.
			</div>
		</div>
	</div>
</main>
<jsp:include page="parts/Footer.jsp"/>
