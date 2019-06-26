function addToCart(prodotto, quantita, action) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=add&p=" + prodotto + "&q=" + quantita, function (json) {
			error_manager(json, function (out) {
				const cart_count = out.hasOwnProperty("cart_count") ? out.cart_count : NaN;
				const cart_serial = out.hasOwnProperty("cart_serial") ? out.cart_serial : NaN;
				action(cart_count, cart_serial);
			});
		});
	} else {
		Notification.push("Quantità non valida");
	}
}

function setToCart(prodotto, quantita, action) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=set&p=" + prodotto + "&q=" + quantita, function (json) {
			error_manager(json, function (out) {
				const cart_count = out.hasOwnProperty("cart_count") ? out.cart_count : NaN;
				const product_total = out.hasOwnProperty("product_total") ? out.product_total : NaN;
				const cart_total = out.hasOwnProperty("cart_total") ? out.cart_total : NaN;
				const cart_serial = out.hasOwnProperty("cart_serial") ? out.cart_serial : NaN;
				action(cart_count, product_total, cart_total, cart_serial);
			});
		});
	} else {
		Notification.push("Quantità non valida");
	}
}

function dropFromCart(prodotto, action) {
	ajaxPostRequest("updateCart", "mode=drop&p=" + prodotto, function (json) {
		error_manager(json, function (out) {
			const cart_count = out.hasOwnProperty("cart_count") ? out.cart_count : NaN;
			const cart_total = out.hasOwnProperty("cart_total") ? out.cart_total : NaN;
			const cart_serial = out.hasOwnProperty("cart_serial") ? out.cart_serial : NaN;
			action(cart_count, cart_total, cart_serial);
		});
	});
}

$(function () {

	function aggiornaCarrello() {
		ajaxPostRequest("reloadCart", "", function (json) {
			const cart_count = json.hasOwnProperty("cart_count") ? json.cart_count : NaN;
			const cart_serial = json.hasOwnProperty("cart_serial") ? json.cart_serial : NaN;
			$("#rightside li.carrello a label").html(cart_count);
			const carrello = $("#carrello");
			if (carrello.length === 1) {
				if (carrello.attr("data-serial") !== cart_serial) {
					location.reload();
				}
			}
		})
	}

	aggiornaCarrello();
	setInterval(aggiornaCarrello, 30000);

});

class Serializator {

	static set(serial, prodotto, quantita) {
		const app = serial.split(";");
		app.pop();
		let result = "";
		$.each(app, function (index, item) {
			if (Number(item.split(":")[0]) === prodotto) {
				result += prodotto + ":" + quantita + ";";
			} else {
				result += item + ";";
			}
		});
		return result;
	}

	static drop(serial, prodotto) {
		const app = serial.split(";");
		app.pop();
		let result = "";
		$.each(app, function (index, item) {
			if (Number(item.split(":")[0]) !== prodotto) {
				result += item + ";";
			}
		});
		return result;
	}

}
