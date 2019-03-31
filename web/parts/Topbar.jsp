<%@ page import="dao.CategoriaDAO" %>
<%@ page import="dao.SottocategoriaDAO" %>
<%@ page import="model.Categoria" %>
<%@ page import="model.Sottocategoria" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id='topbar'>
	<div id='firstbar'>
		<div>
			<header><a href='./'> MCommerce </a></header>
			<form id='search_form' action='cerca.jsp' method='get'>
				<input type='text' id='search_input' name='q' autocomplete='off'
				       placeholder='Cerca uno strumento...'/>
				<input type='submit' value='Cerca'/>
				<div class='results'></div>
			</form>
		</div>
	</div>
	<div id='lastbar'>
		<div>
			<ul>
				<%
					Categoria[] categorie = CategoriaDAO.getAll();
					for (Categoria categoria : categorie) {
						out.println("<li>");
						out.println("<a href='categoria.jsp?id=" + categoria.id + "'>");
						out.println(categoria.nome);
						out.println("</a>");
						Sottocategoria[] sottocategorie = SottocategoriaDAO.getAllOf(categoria.id);
						if (sottocategorie.length > 0) {
							out.println("<ul>");
							for (Sottocategoria sottocategoria : sottocategorie) {
								out.println("<li>");
								out.println("<a href='sottocategoria.jsp?id=" + sottocategoria.id + "'>");
								out.println(sottocategoria.nome);
								out.println("</a>");
								out.println("</li>");
							}
							out.println("</ul>");
						}
						out.println("</li>");
					}
				%>
			</ul>
		</div>
	</div>
</div>
