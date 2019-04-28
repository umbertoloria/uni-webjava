<%@ page import="model.dao.CategoriaDAO" %>
<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
	<div id='firstbar'>
		<div>
			<div id="leftside">
				<a href='./'>
					<span></span>
					<label>MCommerce</label>
				</a>
			</div>
			<div id="rightside">
				<form id='search_form' action='cerca.jsp' method='get'>
					<input type='text' id='search_input' name='q' autocomplete='off'
					       placeholder='Cerca uno strumento...'/>
					<input type='submit' value='Cerca'/>
					<div class='results'></div>
				</form>
				<label>
					<a href="carrello.jsp">
						<%--<img src="https://cdn2.iconfinder.com/data/icons/e-commerce-glyph-2/614/8_-_Shopping_Cart-256.png"/>--%>
						<img src="https://cdn2.iconfinder.com/data/icons/flat-ui-4/100/Cart-256.png"/>
					</a>
				</label>
			</div>
		</div>
	</div>
	<div id='lastbar'>
		<nav>
			<ul>
				<%
					for (Categoria cat : CategoriaDAO.getAll()) {
						out.println("<li>");
						out.println("<a href='categoria.jsp?id=" + cat.id + "'>");
						out.println(cat.nome);
						out.println("</a>");
						Sottocategoria[] sottocats = SottocategoriaDAO.getAllOf(cat.id);
						if (sottocats.length > 0) {
							out.println("<ul>");
							for (Sottocategoria sottocat : sottocats) {
								out.println("<li>");
								out.println("<a href='sottocategoria.jsp?id=" + sottocat.id + "'>");
								out.println(sottocat.nome);
								out.println("</a>");
								out.println("</li>");
							}
							out.println("</ul>");
						}
						out.println("</li>");
					}
				%>
			</ul>
		</nav>
	</div>
</header>
