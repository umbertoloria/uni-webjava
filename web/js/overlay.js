function overlay(msg) {
	$("#overlay").addClass("shown").prepend("<div>" + msg + "</div>");
}

class Notification {

	static push(msg) {
		if (this.timeout !== undefined) {
			clearTimeout(this.timeout);
		}
		$("#notification").addClass("shown").html(msg);
		this.timeout = setTimeout(function () {
			$("#notification").removeClass("shown");
		}, 3000);
	}

}
