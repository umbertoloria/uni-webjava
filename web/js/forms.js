function error_manager(response, info, notice, before_reload) {
	if (response.hasOwnProperty("info")) {
		if (typeof info === 'function') info(response.info);
	}
	if (response.hasOwnProperty("notices")) {
		for (let field in response.notices) {
			if (response.notices.hasOwnProperty(field)) {
				if (typeof notice === 'function') notice(field, response.notices[field]);
			}
		}
	}
	if (response.hasOwnProperty("redirect")) {
		const url = response.redirect.url;
		const delay = response.redirect.delay;
		if (delay === 0) {
			if (url === 'reload') {
				if (typeof before_reload === 'function') before_reload();
				location.reload();
			} else location.href = url;
		} else {
			setTimeout(function () {
				if (url === 'reload') {
					if (typeof before_reload === 'function') before_reload();
					location.reload();
				} else location.href = url;
			}, response.redirect.delay);
		}
	}
	if (response.hasOwnProperty("overlay")) {
		overlay(response.overlay);
	}
	if (response.hasOwnProperty("notification")) {
		Notification.push(response.notification);
	}
}

$(function () {
	$("form").submit(function (ev) {
		const form = $(this);
		if (form.attr("method") !== "post" || form.attr("enctype") !== undefined) {
			return;
		}
		ev.preventDefault();
		const msg = form.find(".msg");
		form.find("fieldset label label").remove();
		msg.removeClass("shown").html("");
		if (!validate(form.attr("action"), form)) {
			return;
		}
		ajaxPostRequest(form.attr("action"), dataForm(form[0]), function (out) {
			error_manager(out, null, function (field, notice) {
				if (field === 'null') {
					form.find(".msg").addClass("shown").html(notice);
				} else {
					const input = form.find("[name=" + field + "]");
					const label = input.siblings("label");
					if (label.length === 0) {
						input.parent().append("<label>" + notice + "</label>");
					} else {
						label.html(notice);
					}
				}
			}, function () {
				form[0].reset();
			});
		});
	});
});
