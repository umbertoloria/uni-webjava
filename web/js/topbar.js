$(function () {

	function hideSearch() {
		const search_results = $("#search_form .results");
		if (search_results.hasClass("shown")) {
			search_results.removeClass("shown");
			setTimeout(function () {
				search_results.html("");
			}, 300);
		}
	}

	function refreshSearch(input) {
		if ($(input).val().length < 3) {
			hideSearch();
			return;
		}
		const form = $(input).closest("form");
		const url = "ricerca";
		const data = dataForm(form[0]);
		ajaxPostRequest(url, data, function (out) {
			if (out.trim().length > 0) {
				$(form).find(".results").html(out).addClass("shown");
			} else {
				hideSearch();
			}
		});
	}

	let timeout;
	$("#search_form input[name=q]").keyup(function () {
		const form = this;
		clearTimeout(timeout);
		timeout = setTimeout(function () {
			refreshSearch(form);
		}, 300);
	}).focus(function () {
		refreshSearch(this);
	});

	$("#search_form").click(function (ev) {
		ev.stopPropagation();
	});

	$(window).click(function () {
		hideSearch();
	});

});
