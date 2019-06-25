<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Sottocategoria" %>
<%@ page import="util.Breadcrumb" %>
<%@ page import="util.BreadcrumbItem" %>
<%@ page import="util.TopbarCategoria" %>
<%@ page import="util.TopbarContainer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div>
		<div id="firstbar">
			<div id="leftside">
				<a href="./"></a>
				<%
					Breadcrumb bc = (Breadcrumb) request.getAttribute("breadcrumb");
					if (bc != null) {
						for (BreadcrumbItem bci : bc) {
							if (bci.link == null) {
								out.println("<a>" + bci.nome + "</a>");
							} else {
								out.println("<a href=\"" + bci.link + "\">" + bci.nome + "</a>");
							}
						}
					}
				%>
				<%--<c:catch>
					<jsp:useBean id="breadcrumb" scope="request" type="util.Breadcrumb"/>
					<c:choose>
						<c:when test="${breadcrumb != null}">
							<c:forEach items="${breadcrumb.iterator()}" var="bci">
								<c:choose>
									<c:when test="${bci.link == null}">
										<a><c:out value="${bci.nome}"/></a>
									</c:when>
									<c:otherwise>
										<a href="<c:out value="bci.link"/>"><c:out value="bci.nome"/></a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
					</c:choose>
				</c:catch>--%>
			</div>
			<div id="centerside">
				<form id="search_form" action="cerca" method="get">
					<input type="text" id="search_input" name="q" autocomplete="off"
					       placeholder="Cerca uno strumento..."/>
					<input type="submit" value=""/>
					<div class="results"></div>
				</form>
			</div>
			<ul id="rightside">
				<c:catch var="exp1">
					<jsp:useBean id="utente" scope="session" type="model.bean.Utente"/>
				</c:catch>
				<c:choose>
					<c:when test="${exp1 == null}">
						<li>
							<a>Account</a>
							<ul>
								<c:if test="${utente.admin()}">
									<li><a href="amministrazione">Amministrazione</a></li>
								</c:if>
								<li><a href="impostazioni">Impostazioni</a></li>
								<li><a href="ordini">Ordini</a></li>
								<li><a href="logout">Logout</a></li>
							</ul>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="login">Login</a>
							<ul>
								<li><a href="registrazione">Registrazione</a></li>
							</ul>
						</li>
					</c:otherwise>
				</c:choose>
				<li class="carrello">
					<a href="carrello">
						Carrello
						<label></label>
					</a>
				</li>
			</ul>
		</div>
	</div>
	<div>
		<nav>
			<ul>
				<%
					Integer activeCategoria = (Integer) request.getAttribute("topbar_categoria");
					Integer activeSottocategoria = (Integer) request.getAttribute("topbar_sottocategoria");
					TopbarContainer topbar = (TopbarContainer) request.getAttribute("topbar_categorie_data");
					for (TopbarCategoria topbarCategoria : topbar) {
						Categoria cat = topbarCategoria.getCategoria();
						if (activeCategoria != null && activeCategoria == cat.id) {
							out.println("<li class=\"active\">");
						} else {
							out.println("<li>");
						}
						out.println("<a href=\"categoria?id=" + cat.id + "\">");
						out.println(cat.nome);
						out.println("</a>");
						List<Sottocategoria> sottocats = topbarCategoria.getSottocategorie();
						if (sottocats.size() > 0) {
							out.println("<ul>");
							for (Sottocategoria sottocat : sottocats) {
								if (activeSottocategoria != null && activeSottocategoria == sottocat.id) {
									out.println("<li class=\"active\">");
								} else {
									out.println("<li>");
								}
								out.println("<a href=\"sottocategoria?id=" + sottocat.id + "\">");
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
