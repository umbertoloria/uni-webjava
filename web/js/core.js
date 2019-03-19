function ajaxPostRequest(url, queryString, success) {
	const conn = new XMLHttpRequest();
	conn.onreadystatechange = function () {
		if (this.readyState === 4 && this.status === 200) {
			success(this.responseText);
		}
	};
	conn.open("POST", url, true);
	conn.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	conn.send(queryString);
}

function dataForm(form) {
	const formData = new FormData(form);
	let queryString = "";
	for (let entry of formData.entries()) {
		queryString += entry[0] + "=" + entry[1] + "&";
	}
	return queryString;
}
