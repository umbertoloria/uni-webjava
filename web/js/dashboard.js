$(function () {

	if ($("#filtri").length > 0) {

		function getMinPrezzo() {
			return Number($("#filtri input[name='min']").val());
		}

		function getMaxPrezzo() {
			return Number($("#filtri input[name='max']").val());
		}

		function showMinPrezzo(prezzo) {
			$("#filtri > label:first-child span label").html(euro_format(prezzo));
		}

		function showMaxPrezzo(prezzo) {
			$("#filtri > label:nth-child(2) span label").html(euro_format(prezzo));
		}

		function updateDashboard(min, max) {
			$("#dashboard > div").each(function (index, elem) {
				elem = $(elem);
				const price = Number(elem.attr("data-price"));
				console.log(min + " < " + price + "<" + max);
				if (min <= price && price <= max) {
					elem.fadeIn(300);
				} else {
					elem.fadeOut(300);
				}
			});
		}

		showMinPrezzo(getMinPrezzo());
		showMaxPrezzo(getMaxPrezzo());
		updateDashboard(getMinPrezzo(), getMaxPrezzo());

		$("#filtri input[name='min']").on("input", function () {
			const min = getMinPrezzo();
			showMinPrezzo(min);
			if (getMaxPrezzo() >= min) {
				$("#filtri input[type='submit']").removeAttr("disabled");
			} else {
				$("#filtri input[type='submit']").attr("disabled", "disabled");
			}
		});

		$("#filtri input[name='max']").on("input", function () {
			const max = getMaxPrezzo();
			showMaxPrezzo(max);
			if (getMinPrezzo() <= max) {
				$("#filtri input[type='submit']").removeAttr("disabled");
			} else {
				$("#filtri input[type='submit']").attr("disabled", "disabled");
			}
		});

		$("#filtri input[type='submit']").click(function () {
			if (!$(this).is("[disabled]")) {
				const newlink = location.protocol + '//' + location.host + location.pathname +
					"?min=" + getMinPrezzo() + "&max=" + getMaxPrezzo();
				history.pushState(null, document.title, newlink);
				updateDashboard(getMinPrezzo(), getMaxPrezzo());
			}
		});

	}

});
