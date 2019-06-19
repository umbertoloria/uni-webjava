// TODO: Forse rinominare "success" in "action"
function addToCart(prodotto, quantita, success) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=add&p=" + prodotto + "&q=" + quantita, function (out) {
			// Avrò solo message (per errori) o done (per aggiornamento dati)
			error_manager(JSON.parse(out), function (out) {
				const cart_count = out.hasOwnProperty("cart_count") ? out.cart_count : NaN;
				success(cart_count);
			});
		});
	} else {
		notification("Quantità non valida");
	}
}

function setToCart(prodotto, quantita, success) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=set&p=" + prodotto + "&q=" + quantita, function (out) {
			error_manager(JSON.parse(out), function (out) {
				const cart_count = out.hasOwnProperty("cart_count") ? out.cart_count : NaN;
				const product_total = out.hasOwnProperty("product_total") ? out.product_total : NaN;
				const cart_total = out.hasOwnProperty("cart_total") ? out.cart_total : NaN;
				success(cart_count, product_total, cart_total);
			});
		});
	} else {
		notification("Quantità non valida");
	}
}

function dropFromCart(prodotto, success) {
	ajaxPostRequest("updateCart", "mode=drop&p=" + prodotto, function (out) {
		error_manager(JSON.parse(out), function (out) {
			const cart_count = out.hasOwnProperty("cart_count") ? out.cart_count : NaN;
			const cart_total = out.hasOwnProperty("cart_total") ? out.cart_total : NaN;
			success(cart_count, cart_total);
		});
	});
}

function aggiornaCarrello() {
	ajaxPostRequest("reloadCart", "", function (out) {
		const json = $.parseJSON(out);
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

$(function () {
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
