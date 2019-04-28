function overlay_alert(msg) {
	$("#overlay_shadow").addClass("shown").prepend("<div>" + msg + "</div>");
}

// $(function () {
// 	$("#overlay_shadow").click(function () {
// 		if ($(this).is(".shown")) {
// 			$(this).removeClass("shown");
// 		}
// 	});
// });
