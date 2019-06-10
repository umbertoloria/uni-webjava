function ajaxPostRequest(url, queryString, success) {
	const conn = new XMLHttpRequest();
	conn.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			success(this.responseText);
		}
	};
	conn.open("POST", url, true);
	conn.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	conn.send(queryString);
}

function dataForm(form) {
	const formData = new FormData(form);
	let queryString = "";
	for (let entry of formData.entries()) {
		queryString += entry[0] + "=" + entry[1] + "&";
	}
	return queryString;
}

function addProdottoToCart(elem, prodotto, quantita) {
	if (!$(elem).is("[disabled]")) {
		ajaxPostRequest("addToCart", "p=" + prodotto + "&q=" + quantita, function (data) {
			$(elem).attr("disabled", "disabled");
			setTimeout(function () {
				$(elem).removeAttr("disabled");
			}, 3000);
			if (data.startsWith("ok:")) {
				notification_alert("Prodotto aggiunto sul carrello.");
				$("#rightside label.carrello a span").html(data.substr(3));
			} else {
				alert("out: " + data);
				notification_alert("Problema...");
			}
		})
	}
}
