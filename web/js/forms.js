function agisci(form, out) {
	const commands = out.split(";");
	let queue = [];
	for (let command of commands) {
		let index = command.indexOf(':');
		queue.push([command.substr(0, index), command.substr(index + 1)]);
	}
	form.find("fieldset label label").remove();
	for (let item of queue) {
		const option = item[0];
		const parameter = item[1];
		if (option === 'notice') {
			const index = parameter.indexOf(':');
			const field = parameter.substring(0, index);
			const realParameter = parameter.substring(index + 1);
			const fieldDOM = form.find("input[name=" + field + "]");
			const label = fieldDOM.parent().find("label");
			if (label.length === 0) {
				fieldDOM.parent().append("<label>" + realParameter + "</label>");
			} else {
				label.html(realParameter);
			}
		} else if (option === 'message') {
			form.find(".msg").addClass("shown").html(parameter);
		} else if (option === 'redirect') {
			setTimeout(function () {
				location.href = parameter;
			}, 1500);
		} else if (option === 'done') {
			overlay(parameter);
		}
	}
}

$(function () {
	$("form").submit(function (ev) {
		let form = $(this);
		if (form.attr("method") !== "post") {
			return;
		}
		ev.preventDefault();
		ajaxPostRequest(form.attr("action"), dataForm(form[0]), function (out) {
			agisci(form, out);
		});
	});
});
