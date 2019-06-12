<%@ page import="model.Carrello" %>
<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="model.bean.Utente" %>
<%@ page import="model.dao.CategoriaDAO" %>
<%@ page import="model.dao.SottocategoriaDAO" %>
<%@ page import="util.Breadcrumb" %>
<%@ page import="util.BreadcrumbItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<header>
	<div>
		<div id="firstbar">
			<div id="leftside">
				<a href="./">
					MCommerce
				</a>
				<%
					Breadcrumb bc = (Breadcrumb) request.getAttribute("breadcrumb");
					if (bc != null) {
						for (BreadcrumbItem bci : bc) {
							if (bci.link == null) {
								out.println("<a>" + bci.nome + "</a>");
							} else {
								out.println("<a href='" + bci.link + "'>" + bci.nome + "</a>");
							}
						}
					}
				%>
			</div>
			<div id="centerside">
				<form id="search_form" action="cerca.jsp" method="get">
					<input type="text" id="search_input" name="q" autocomplete="off"
					       placeholder="Cerca uno strumento..."/>
					<input type="submit" value=""/>
					<div class="results"></div>
				</form>
			</div>
			<ul id="rightside">
					<%
					// TODO: Sincronizzare i dati sulla sessione con quelli sul DB.
					Utente user = (Utente) request.getSession().getAttribute("utente");
					if (user != null) {
				%>
				<li>
					<a>
						Account
					</a>
					<ul>
						<li><a href="impostazioni.jsp">Impostazioni</a></li>
						<li><a href="logout.jsp">Logout</a></li>
					</ul>
				</li>
					<%
				} else {
				%>
				<li>
					<a href="login.jsp">
						Login
					</a>
				</li>
					<%
					}
					int carrelloCount = 0;
					// TODO: refreshare ogni tanto il numero di prodotti nel carrello.
					{
						Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
						if (carrello != null) {
							carrelloCount = carrello.getCount();
						}
					}
				%>
				<li class="carrello">
					<a href="carrello.jsp">
						Carrello
						<label>
							<%= carrelloCount %>
						</label>
					</a>
				</li>
		</div>
		<%--<script>
			$("#rightside img").each(function () {
				$(this).attr("src", $(this).attr("idle"));
			}).hover(function () {
				$(this).attr("src", $(this).attr("hover"));
			}, function () {
				$(this).attr("src", $(this).attr("idle"));
			});
		</script>--%>
	</div>
	</div>
	<div>
		<nav>
			<ul>
				<%
					Integer activeCategoria = (Integer) request.getAttribute("topbar_categoria");
					Integer activeSottocategoria = (Integer) request.getAttribute("topbar_sottocategoria");
					for (Categoria cat : CategoriaDAO.getAll()) {
						if (activeCategoria != null && activeCategoria == cat.id) {
							out.println("<li class='active'>");
						} else {
							out.println("<li>");
						}
						out.println("<a href='categoria.jsp?id=" + cat.id + "'>");
						out.println(cat.nome);
						out.println("</a>");
						Sottocategoria[] sottocats = SottocategoriaDAO.getAllOf(cat.id);
						if (sottocats.length > 0) {
							out.println("<ul>");
							for (Sottocategoria sottocat : sottocats) {
								if (activeSottocategoria != null && activeSottocategoria == sottocat.id) {
									out.println("<li class='active'>");
								} else {
									out.println("<li>");
								}
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
