<%@ page contentType="text/html;charset=UTF-8" %>
<h1>Arrivederci</h1>
<script>
	overlay("Stai per arrivare alla pagina iniziale");
	setTimeout(function () {
		location.href = ".";
	}, 2000);
</script>
