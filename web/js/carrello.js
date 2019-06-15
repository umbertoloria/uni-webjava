function addToCart(prodotto, quantita, success, failure) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=add&p=" + prodotto + "&q=" + quantita, function (out) {
			const json = $.parseJSON(out);
			if (json.error === undefined) {
				success(json.count);
			} else {
				failure(json.error);
			}
		});
	}
}

function setToCart(prodotto, quantita, success, failure) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=set&p=" + prodotto + "&q=" + quantita, function (out) {
			const json = $.parseJSON(out);
			if (json.error === undefined) {
				success(json.count, json.newtotal);
			} else {
				failure(json.error);
			}
		});
	}
}

function dropFromCart(prodotto, success, failure) {
	ajaxPostRequest("updateCart", "mode=drop&p=" + prodotto, function (out) {
		const json = $.parseJSON(out);
		if (json.error === undefined) {
			success(json.count);
		} else {
			failure(json.error);
		}
	});
}

function aggiornaCarrello() {
	ajaxPostRequest("reloadCart", "", function (out) {
		const json = $.parseJSON(out);
		$("#rightside li.carrello a label").html(json.count);
		const carrello = $("#carrello");
		if (carrello.length === 1) {
			const serial = carrello.attr("data-serial");
			if (serial !== json.serial) {
				location.reload();
			}
		}
	})
}

$(function () {
	aggiornaCarrello();
	setInterval(aggiornaCarrello, 30000);
});
