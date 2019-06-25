function ajaxPostRequest(url, data, success) {
	$.ajax({
		url: url,
		data: data,
		// dataType: "json", TODO: Pensaci...
		type: 'POST',
		success: function (out, status) {
			if (status === "success") {
				success(out);
			}
		}
	});
}

function dataForm(form) {
	const formData = new FormData(form);
	let data = {};
	for (let entry of formData.entries()) {
		data[entry[0]] = entry[1];
	}
	return data;
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
