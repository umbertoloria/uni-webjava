<%@ page import="model.bean.Produttore" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<form action="servlet_aggiungiProdotto" method="post" enctype="multipart/form-data">
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
</main>
