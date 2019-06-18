function overlay(msg) {
	$("#overlay").addClass("shown").prepend("<div>" + msg + "</div>");
}

// FIXME: Trova un modo per nascondere questa variabile all'esterno di questo script.
let notification_timeout = null;

function notification(msg) {
	clearTimeout(notification_timeout);
	$("#notification").addClass("shown").html(msg);
	notification_timeout = setTimeout(function () {
		$("#notification").removeClass("shown");
	}, 3000);
}
