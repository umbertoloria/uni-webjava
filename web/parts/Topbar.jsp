<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Sottocategoria" %>
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
						String[] indices = {"first", "second", "third", "fourth"};
						int index = 0;
						for (BreadcrumbItem bci : bc) {
							if (bci.link == null) {
								out.println("<a class='" + indices[index] + "'>" + bci.nome + "</a>");
							} else {
								out.println("<a class='" + indices[index] + "' href='" + bci.link + "'>" + bci.nome + "</a>");
							}
							index++;
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
			<div id="rightside">
				<label>
					<a href="login.jsp">
						<img idle="https://cdn0.iconfinder.com/data/icons/emoshen/1000/EMOTICON1_cnvrt-11-256.png"
						     hover="https://cdn0.iconfinder.com/data/icons/emoshen/1000/EMOTICON1_cnvrt-16-256.png"/>
					</a>
				</label>
				<label>
					<a href="logout.jsp">
						<img idle="https://cdn0.iconfinder.com/data/icons/emoshen/1000/EMOTICON1_cnvrt-13-256.png"
						     hover="https://cdn0.iconfinder.com/data/icons/emoshen/1000/EMOTICON1_cnvrt-18-256.png"/>
					</a>
				</label>
				<label>
					<a href="carrello.jsp">
						<img src="https://cdn2.iconfinder.com/data/icons/miscellaneous-31/60/bag-512.png"/>
					</a>
				</label>
			</div>
			<script>
				$("#rightside img").each(function () {
					$(this).attr("src", $(this).attr("idle"));
				}).hover(function () {
					$(this).attr("src", $(this).attr("hover"));
				}, function () {
					$(this).attr("src", $(this).attr("idle"));
				});
			</script>
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
