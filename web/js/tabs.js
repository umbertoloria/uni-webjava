$(function () {

	$(".tabs").each(function (index, value) {
		$(this).find("> .tabs_header li:first-child").addClass("active");
		$(this).find("> .tabs_container div:first-child").show();
	});

	$(".tabs .tabs_header li a").click(function () {
		const i = $(this).parent().prevAll().length;
		$(this).parent().addClass("active");
		$(this).parent().siblings().removeClass("active");
		const container = $(this).parent().parent().parent().children(".tabs_container");
		container.find("> div:nth(" + i + ")").slideDown(300).siblings().slideUp(300);
	});

});
