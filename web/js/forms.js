function error_manager(response, notice, message, done, redirect, reload) {
	// TODO: Separare redirect normale (che potrebbe ritardarsi) da logout che va fatto immediatamente...
	// FIXME: Poi applicare questa distinzione su carrello.jsp, tanto per cominciare...
	if (response.hasOwnProperty("notices")) {
		for (let field in response.notices) {
			if (response.notices.hasOwnProperty(field)) {
				notice(field, response.notices[field]);
			}
		}
	}
	if (response.hasOwnProperty("message")) {
		message(response.message);
	}
	if (response.hasOwnProperty("done")) {
		done(response.done);
	}
	if (response.hasOwnProperty("redirect")) {
		if (response.redirect === 'reload') {
			reload();
		} else {
			redirect(response.redirect);
		}
	}
}

$(function () {
	$("form").submit(function (ev) {
		const form = $(this);
		if (form.attr("method") !== "post") {
			return;
		}
		ev.preventDefault();
		ajaxPostRequest(form.attr("action"), dataForm(form[0]), function (out) {
			form.find("fieldset label label").remove();
			form.find(".msg").removeClass("shown").html("");
			const msg = form.find(".msg");
			error_manager(JSON.parse(out), function (field, notice) {
				const input = form.find("input[name=" + field + "]");
				const label = input.parent().find("label");
				if (label.length === 0) {
					input.parent().append("<label>" + notice + "</label>");
				} else {
					label.html(notice);
				}
			}, function (message) {
				form.find(".msg").addClass("shown").html(message);
			}, function (done) {
				msg.removeClass("shown").html("");
				form.find("input[name]").each(function () {
					$(this).parent().children("label").remove();
				});
				overlay(done);
			}, function (url) {
				setTimeout(function () {
					location.href = url;
				}, 1500);
			}, function () {
				setTimeout(function () {
					form[0].reset();
					location.reload();
				}, 1500);
			});
		});
	});
});
