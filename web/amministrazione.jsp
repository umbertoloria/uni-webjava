<%@ page import="model.bean.Produttore" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="model.bean.Utente" %>
<%@ page import="model.container.OrdineContainer" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<div class="tabs">
		<ul class="tabs_header">
			<li><a>Aggiungi un prodotto</a></li>
			<li><a>Vedi tutti gli utenti</a></li>
			<li><a>Vedi tutti gli ordini</a></li>
		</ul>
		<div class="tabs_container">
			<div>
				<form action="servlet_aggiungiProdotto" method="post" enctype="multipart/form-data"
				      onsubmit="return checkProdotto($(this))">
					<fieldset>
						<label>
							<span>Nome</span>
							<input type="text" name="nome" placeholder="Nome del prodotto"/>
						</label>
						<label>
							<span>Sottocategoria</span>
							<select name="sottocategoria">
								<%
									for (Sottocategoria sottocategoria : (Sottocategoria[]) request.getAttribute("sottocategorie")) {
										out.println("<option value='" + sottocategoria.id + "'>" + sottocategoria.nome + "</option>");
									}
								%>
							</select>
						</label>
						<label>
							<span>Produttore</span>
							<select name="produttore">
								<%
									for (Produttore produttore : (Produttore[]) request.getAttribute("produttori")) {
										out.println("<option value='" + produttore.id + "'>" + produttore.nome + "</option>");
									}
								%>
							</select>
						</label>
						<label>
							<span>Descrizione</span>
							<textarea name="descrizione" placeholder="Descrizione del prodotto"></textarea>
						</label>
						<label>
							<span>Prezzo</span>
							<input type="text" name="prezzo" placeholder="31,00"/>
						</label>
						<label>
							<span>Immagine</span>
							<input type="file" name="file" placeholder="Carica l'immagine del prodotto..."/>
						</label>
					</fieldset>
					<input type="submit" value="Aggiungi prodotto"/>
					<div class="msg"></div>
				</form>
			</div>
			<div>
				<%
					Utente[] utenti = (Utente[]) request.getAttribute("utenti");
					if (utenti != null && utenti.length > 1) {
				%>
				<table>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>E-Mail</th>
						<th>Tipo</th>
					</tr>
					<%
						for (Utente utente : utenti) {
					%>
					<tr>
						<td>
							<%= utente.id %>
						</td>
						<td>
							<%= utente.nome %>
						</td>
						<td>
							<%= utente.email %>
						</td>
						<td>
							<%= utente.tipo %>
						</td>
					</tr>
					<%
						}
					%>
				</table>
				<%
					}
				%>
			</div>
			<div>
				<%
					OrdineContainer[] ordini = (OrdineContainer[]) request.getAttribute("ordini");
					if (ordini != null && ordini.length > 1) {
				%>
				<table>
					<tr>
						<th>Utente</th>
						<th>E-Mail</th>
						<th>Totale</th>
						<th>Destinazione</th>
						<th>Pagamento</th>
						<th>Data</th>
					</tr>
					<%
						for (OrdineContainer ordine : ordini) {
					%>
					<tr>
						<td>
							<%= ordine.getUtente().nome %>
						</td>
						<td>
							<%= ordine.getUtente().email %>
						</td>
						<td>
							<%= Formats.euro(ordine.getTotale()) %>
						</td>
						<td>
							<%= ordine.getDestinazione() %>
						</td>
						<td>
							<%= ordine.getPagamento() %>
						</td>
						<td>
							<%= Formats.date(ordine.getMomento()) %>
						</td>
					</tr>
					<%
						}
					%>
				</table>
				<%
					}
				%>
			</div>
		</div>
	</div>
</main>
