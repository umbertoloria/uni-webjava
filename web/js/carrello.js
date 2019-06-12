function addToCart(prodotto, quantita, success, failure) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=add&p=" + prodotto + "&q=" + quantita, function (out) {
			const carrello = $.parseJSON(out);
			if (carrello.count === 0) {
				failure();
			} else {
				let totalCartCount = 0;
				$.each(carrello, function (index, value) {
					totalCartCount += value.quantita;
				});
				success(totalCartCount);
			}
		});
	}
}

function setToCart(prodotto, quantita, func = null, success) {
	if (quantita >= 1) {
		ajaxPostRequest("updateCart", "mode=set&p=" + prodotto + "&q=" + quantita, function (out) {
			let totalCartCount = 0;
			$.each($.parseJSON(out), function (index, value) {
				totalCartCount += value.quantita;
				if (func !== null) {
					func(value);
				}
			});
			success(totalCartCount);
		});
	}
}

function dropFromCart(prodotto, func = null, success) {
	ajaxPostRequest("updateCart", "mode=drop&p=" + prodotto, function (out) {
		let totalCartCount = 0;
		$.each($.parseJSON(out), function (index, value) {
			totalCartCount += value.quantita;
			if (func !== null) {
				func(value);
			}
		});
		success(totalCartCount);
	});
}
