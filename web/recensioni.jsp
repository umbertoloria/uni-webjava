<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.bean.Utente" %>
<%@ page import="util.Formats" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="prodotto" scope="request" type="model.bean.Prodotto"/>
<jsp:useBean id="recensioni" scope="request" type="java.util.List"/>
<c:choose>
	<c:when test="${not empty recensioni}">
		<div id="recensioni">
			<c:forEach items="${recensioni}" var="rec">
				<div>
					<div class="stelle mark<c:out value="${rec.voto}"/>"></div>
					<h2><c:out value="${rec.titolo}"/></h2>
					<p><c:out value="${rec.commento}"/></p>
					<label>
						<span>
							<c:out value="${rec.username}"/>
						</span>
						<span>${Formats.date(rec.momento)}</span>
					</label>
				</div>
			</c:forEach>
		</div>
	</c:when>
</c:choose>

<%
	Boolean enableAdd = (Boolean) request.getAttribute("enableAdd");
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	if (utente != null && enableAdd != null && enableAdd) {
%>
<form action="servlet_aggiungiRecensione" method="post">
	<h2> Aggiungi una recensione</h2>
	<fieldset>
		<input type="hidden" name="prodotto" value="<c:out value="${prodotto.id}"/>"/>
		<label>
			<span>Voto<div class="stelle mark3"></div></span>
			<input type="range" name="voto" min="1" max="5"
			       oninput="$(this).prev().find('.stelle').attr('class', 'stelle mark' + $(this).val())"/>
		</label>
		<label>
			<span>Titolo</span>
			<input type="text" name="titolo" placeholder="Ottimo strumento"/>
		</label>
		<label>
			<span>Commento</span>
			<textarea name="commento" placeholder="Costruito benissimo ed ha un suono splendente"></textarea>
		</label>
	</fieldset>
	<input type="submit" value="Aggiungi questa recensione"/>
	<div class="msg"></div>
</form>
<%
	}
%>
