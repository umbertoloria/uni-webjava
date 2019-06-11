function overlay(msg) {
	$("#overlay").addClass("shown").prepend("<div>" + msg + "</div>");
}

function notification(msg) {
	$("#notification").addClass("shown").html(msg);
	setTimeout(function () {
		$("#notification").removeClass("shown").html("");
	}, 3000);
}

// $(function () {
// 	$("#overlay").click(function () {
// 		if ($(this).is(".shown")) {
// 			$(this).removeClass("shown");
// 		}
// 	});
// });
