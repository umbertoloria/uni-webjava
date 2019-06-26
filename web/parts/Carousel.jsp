<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="carousel" scope="request" type="model.Carousel"/>
<c:choose>
	<c:when test="${not empty carousel}">
		<div id="carousel">
			<c:forEach items="${carousel.iterator()}" var="item">
				<a href="<c:out value="${item.url}"/>">
					<img src="<c:out value="${item.immagine}"/>" alt/>
					<span>
						<c:out value="${item.testo}"/>
					</span>
				</a>
			</c:forEach>
		</div>
	</c:when>
</c:choose>
<script>
	$("#carousel > a:first-child").addClass("active");
	setInterval(function () {
		const total = $("#carousel > a").length;
		const curr = $("#carousel > a.active").removeClass("active").prevAll().length;
		const next = (curr + 1) % total;
		$("#carousel > a:nth(" + curr + ")").removeClass("active").addClass("fading");
		setTimeout(function () {
			$("#carousel > a:nth(" + curr + ")").removeClass("fading");
		}, 500);
		$("#carousel > a:nth(" + next + ")").addClass("active");
	}, 2000);
</script>