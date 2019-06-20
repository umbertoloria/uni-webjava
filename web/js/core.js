function ajaxPostRequest(url, queryString, success) {
	const conn = new XMLHttpRequest();
	conn.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			success(this.responseText.trim());
		}
	};
	conn.open("POST", url, true);
	conn.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	// conn.setRequestHeader("connection", "close");
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

function addProdottoFromDashboardToCart(elem, prodotto) {
	if (!$(elem).is("[disabled]")) {
		$(elem).attr("disabled", "disabled");
		setTimeout(function () {
			$(elem).removeAttr("disabled");
		}, 3000);
		addToCart(prodotto, 1, function (cart_count) {
			Notification.push("Prodotto aggiunto sul carrello.");
			$("#rightside li.carrello a label").html(cart_count);
		});
	}
}
