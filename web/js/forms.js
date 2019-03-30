function agisci(form, out) {
	const commands = out.split(";");
	let queue = [];
	for (let command of commands) {
		let index = 0;
		for (let char of command) {
			if (char === ':') {
				break;
			}
			index++;
		}
		queue.push([command.substr(0, index), command.substr(index + 1)]);
	}
	for (let item of queue) {
		const option = item[0];
		const parameter = item[1];
		if (option === 'message') {
			form.find(".msg").addClass("shown").html(parameter);
		} else if (option === 'redirect') {
			setTimeout(function () {
				location.href = parameter;
			}, 3000);
		} else if (option === 'done') {
			alert(parameter);
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
