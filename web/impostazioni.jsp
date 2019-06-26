<%@ page import="model.bean.CartaCredito" %>
<%@ page import="model.bean.Indirizzo" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<div class="tabs">
		<ul class="tabs_header">
			<li><a>Cambia password</a></li>
			<li><a>Gestisci indirizzi</a></li>
			<li><a>Gestisci carte di credito</a></li>
		</ul>
		<div class="tabs_container">
			<div>
				<form action="servlet_cambiaPassword" method="post">
					<fieldset>
						<label>
							<span>Vecchia password</span>
							<input type="password" name="vecchia" placeholder="Pa$$w0rd"/>
						</label>
						<label>
							<span>Nuova password</span>
							<input type="password" name="nuova" placeholder="Pa$$w0rd"/>
						</label>
						<label>
							<span>Conferma password</span>
							<input type="password" name="conferma" placeholder="Pa$$w0rd"/>
						</label>
					</fieldset>
					<input type="submit" value="Cambia password"/>
					<div class="msg"></div>
				</form>
			</div>
			<div>
				<div class="card indirizzi">
					<%
						for (Indirizzo indirizzo : (Indirizzo[]) request.getAttribute("indirizzi")) {
					%>
					<div data-indirizzo-id="<%= indirizzo.id %>">
						<div>
							<a class="edit">
								<img src="images/edit.png" alt/>
							</a>
							<a class="drop">
								<img src="images/drop.png" alt/>
							</a>
						</div>
						<h2 data-indirizzo-nome="<%= indirizzo.nome %>">
							<%= indirizzo.nome %>
						</h2>
						<ul>
							<li data-indirizzo-indirizzo="<%= indirizzo.indirizzo %>">
								<%= indirizzo.indirizzo %>
							</li>
							<li data-indirizzo-citta="<%= indirizzo.citta%>">
								<%= indirizzo.citta%>
							</li>
							<li data-indirizzo-cap="<%= indirizzo.cap%>">
								<%= indirizzo.cap%>
							</li>
							<li data-indirizzo-provincia="<%= indirizzo.provincia%>">
								<%= indirizzo.provincia%>
							</li>
						</ul>
					</div>
					<%
						}
					%>
				</div>
				<form action="servlet_aggiungiIndirizzo" method="post" id="indirizzi_form">
					<fieldset>
						<label>
							<span>Nome</span>
							<input type="text" name="nome" placeholder="Casa"/>
						</label>
						<label>
							<span>Indirizzo</span>
							<input type="text" name="indirizzo" placeholder="Via Tal Dei Tali 59"/>
						</label>
						<label>
							<span>Città</span>
							<input type="text" name="citta" placeholder="Vallo di Java"/>
						</label>
						<label>
							<span>CAP</span>
							<input type="number" name="cap" placeholder="1996" min="00000" max="99999"/>
						</label>
						<label>
							<span>Provincia</span>
							<input type="text" name="provincia" placeholder="Orientata ad Oggetti"/>
						</label>
					</fieldset>
					<input type="submit" value="Aggiungi indirizzo"/>
					<div class="msg"></div>
				</form>
			</div>
			<div>
				<div class="card carte">
					<%
						for (CartaCredito carta : (CartaCredito[]) request.getAttribute("carte")) {
					%>
					<div data-carta-numero="<%= carta.numero %>">
						<div>
							<a class="drop">
								<img src="images/drop.png" alt/>
							</a>
						</div>
						<h2>
							<%= carta.numero %>
						</h2>
						<ul>
							<li>
								<%= carta.mese %> / <%= carta.anno %>
							</li>
							<li>
								<%= carta.cvv %>
							</li>
							<li>
								<%= Formats.euro(carta.saldo) %>
							</li>
						</ul>
					</div>
					<%
						}
					%>
				</div>
				<form action="servlet_aggiungiCarta" method="post">
					<fieldset>
						<label>
							<span>Carta di credito</span>
							<input type="text" name="numero" min="0000000000000000" max="9999999999999999"
							       placeholder="Numero della carta">
						</label>
						<label>
							<span>Mese</span>
							<select name="mese">
								<%
									for (String mese : Formats.mesi) {
										out.println("<option value='" + mese + "'>" + mese + "</option>");
									}
								%>
							</select>
						</label>
						<label>
							<span>Anno</span>
							<select name="anno">
								<%
									for (int anno = 2018; anno < 2026; anno++) {
										out.println("<option value='" + anno + "'>" + anno + "</option>");
									}
								%>
							</select>
						</label>
						<label>
							<span>CVV</span>
							<input type="text" name="cvv" placeholder="CVV"/>
						</label>
						<label>
							<span>Saldo</span>
							<input type="text" name="saldo" placeholder="Soldi sulla carta"/>
						</label>
					</fieldset>
					<input type="submit" value="Aggiungi carta"/>
					<div class="msg"></div>
				</form>
			</div>
		</div>
	</div>
</main>
<script>

	$(".card.indirizzi > div").click(function () {
		if ($(this).is("[disabled]") && !$(this).is("[data-wait]")) {
			const form = $("#indirizzi_form");
			form.find("[name=nome]").val("");
			form.find("[name=indirizzo]").val("");
			form.find("[name=citta]").val("");
			form.find("[name=cap]").val("");
			form.find("[name=provincia]").val("");
			form.find("input[type=hidden]").remove();
			form.find("[type='submit']").val("Aggiungi indirizzo");
			form.attr("action", "aggiungiIndirizzo");
			$(this).removeAttr("disabled");
		}
	});

	$(".card.indirizzi > div > div > a.edit").click(function (ev) {
		const div = $(this).parent().parent();
		if (!div.is("[disabled]")) {
			ev.stopPropagation();
			div.attr("disabled", "disabled");
			div.attr("data-wait", "");
			setTimeout(function () {
				div.removeAttr("data-wait");
			}, 500);
			div.siblings().removeAttr("disabled");
			const id = div.attr("data-indirizzo-id");
			const nome = div.find("[data-indirizzo-nome]").attr("data-indirizzo-nome");
			const indirizzo = div.find("[data-indirizzo-indirizzo]").attr("data-indirizzo-indirizzo");
			const citta = div.find("[data-indirizzo-citta]").attr("data-indirizzo-citta");
			const cap = div.find("[data-indirizzo-cap]").attr("data-indirizzo-cap");
			const provincia = div.find("[data-indirizzo-provincia]").attr("data-indirizzo-provincia");
			const form = $("#indirizzi_form");
			form.find("[name=nome]").val(nome);
			form.find("[name=indirizzo]").val(indirizzo);
			form.find("[name=citta]").val(citta);
			form.find("[name=cap]").val(cap);
			form.find("[name=provincia]").val(provincia);
			form.append("<input type='hidden' name='id' value='" + id + "'/>");
			form.find("[type='submit']").val("Modifica indirizzo");
			form.attr("action", "modificaIndirizzo");
		}
	});

	$(".card.indirizzi > div > div a.drop").click(function (ev) {
		const div = $(this).parent().parent();
		if (!div.is("[disabled]") && confirm("Sei sicuro di voler cancellare questo indirizzo?")) {
			ev.stopPropagation();
			const id = div.attr("data-indirizzo-id");
			ajaxPostRequest("rimuoviIndirizzo", "id=" + id, function (out) {
				error_manager(out, function () {
					div.animate({opacity: "0"}, 300);
					setTimeout(function () {
						div.remove();
					}, 500);
				});
			});
		}
	});

	$(".card.carte > div > div a.drop").click(function (ev) {
		const div = $(this).parent().parent();
		if (!div.is("[disabled]") && confirm("Sei sicuro di voler cancellare questa carta?")) {
			ev.stopPropagation();
			const num = div.attr("data-carta-numero");
			ajaxPostRequest("rimuoviCarta", "num=" + num, function (out) {
				error_manager(out, function () {
					div.animate({opacity: "0"}, 300);
					setTimeout(function () {
						div.remove();
					}, 500);
				});
			});
		}
	});

</script>
