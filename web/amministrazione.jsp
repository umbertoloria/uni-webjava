<%@ page contentType="text/html;charset=UTF-8" %>
<main>
	<form action="aggiungiProdotto" method="post" enctype="multipart/form-data">
		<fieldset>
			<label>
				<span>Immagine</span>
				<input type="file" name="file" placeholder="Carica l'immagine del prodotto..."/>
			</label>
		</fieldset>
		<input type="submit" value="Aggiungi prodotto"/>
		<div class="msg"></div>
	</form>
</main>
