$(function () {

	let tabIndex;
	if (window.location.hash) {
		tabIndex = window.location.hash.substr(1);
		if (isNaN(tabIndex)) {
			tabIndex = undefined;
		}
	}

	$(".tabs").each(function () {
		$(this).find(".tabs_header > li > a").each(function (index) {
			$(this).attr("href", "#" + (index + 1));
		});
		if (tabIndex === undefined) {
			tabIndex = 1;
		}
		$(this).find("> .tabs_header > li:nth-child(" + tabIndex + ")").addClass("active");
		$(this).find("> .tabs_container > div:nth-child(" + tabIndex + ")").show();
	});

	$(".tabs .tabs_header li a").click(function () {
		const i = $(this).parent().prevAll().length;
		$(this).parent().addClass("active");
		$(this).parent().siblings().removeClass("active");
		const container = $(this).parent().parent().parent().children(".tabs_container");
		container.find("> div:nth(" + i + ")").slideDown(300).siblings().slideUp(300);
	});

});
