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
		ajaxPostRequest("ricerca", dataForm(form[0]), function (out) {
			if (out.length > 0) {
				$(form).find(".results").html(out).addClass("shown");
			} else {
				hideSearch();
			}
		});
	}

	let timeout;
	$("#search_form input[name=q]").keyup(function () {
		const input = this;
		clearTimeout(timeout);
		timeout = setTimeout(function () {
			refreshSearch(input);
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
