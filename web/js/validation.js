function checkRegistrazione(form) {
	const input_nome = form.find("[name=nome]");
	const input_email = form.find("[name=email]");
	const input_password = form.find("[name=password]");


	const email_label = input_email.siblings("label");
	const password_label = input_password.siblings("label");
	const nome_label = input_nome.siblings("label");

	const nome = input_nome.val();
	const email = input_email.val();
	const password = input_password.val();


	if (nome.length < 3) {
		const notice = "Nome non valido: minimo 3 caratteri";
		if (nome_label.length === 0) {
			input_nome.parent().append("<label>" + notice + "</label>");
		} else {
			nome_label.html(notice);
		}
		return false;
	} else if (nome > 40) {
		const notice = "Nome non valido: massimo 40 caratteri";
		if (nome_label.length === 0) {
			input_nome.parent().append("<label>" + notice + "</label>");
		} else {
			nome_label.html(notice);
		}
		return false;
	} else if (email.length < 3) {
		const notice = "E-Mail non valida: minimo 3 caratteri";
		if (email_label.length === 0) {
			input_email.parent().append("<label>" + notice + "</label>");
		} else {
			email_label.html(notice);
		}
		return false;
	} else if (email > 40) {
		const notice = "E-Mail non valida: massimo 40 caratteri";
		if (email_label.length === 0) {
			input_email.parent().append("<label>" + notice + "</label>");
		} else {
			email_label.html(notice);
		}
		return false;
	} else if (email.indexOf("@") === -1) {
		const notice = "E-Mail malformata";
		if (email_label.length === 0) {
			input_email.parent().append("<label>" + notice + "</label>");
		} else {
			email_label.html(notice);
		}
		return false;
	} else if (password.length < 3) {
		const notice = "Password non valida: minimo 3 caratteri";
		if (password_label.length === 0) {
			input_password.parent().append("<label>" + notice + "</label>");
		} else {
			password_label.html(notice);
		}
		return false;
	} else if (password > 16) {
		const notice = "Password non valida: massimo 40 caratteri";
		if (password_label.length === 0) {
			input_password.parent().append("<label>" + notice + "</label>");
		} else {
			password_label.html(notice);
		}
		return false;
	}
	return true;
}

function checkLogin(form) {
	const input_email = form.find("[name=email]");
	const input_password = form.find("[name=password]");

	const email_label = input_email.siblings("label");
	const password_label = input_password.siblings("label");

	const email = input_email.val();
	const password = input_password.val();

	if (email.length < 3) {
		const notice = "E-Mail non valida: minimo 3 caratteri";
		if (email_label.length === 0) {
			input_email.parent().append("<label>" + notice + "</label>");
		} else {
			email_label.html(notice);
		}
		return false;
	} else if (email > 40) {
		const notice = "E-Mail non valida: massimo 40 caratteri";
		if (email_label.length === 0) {
			input_email.parent().append("<label>" + notice + "</label>");
		} else {
			email_label.html(notice);
		}
		return false;
	} else if (email.indexOf("@") === -1) {
		const notice = "E-Mail malformata";
		if (email_label.length === 0) {
			input_email.parent().append("<label>" + notice + "</label>");
		} else {
			email_label.html(notice);
		}
		return false;
	} else if (password.length < 3) {
		const notice = "Password non valida: minimo 3 caratteri";
		if (password_label.length === 0) {
			input_password.parent().append("<label>" + notice + "</label>");
		} else {
			password_label.html(notice);
		}
		return false;
	} else if (password > 16) {
		const notice = "Password non valida: massimo 40 caratteri";
		if (password_label.length === 0) {
			input_password.parent().append("<label>" + notice + "</label>");
		} else {
			password_label.html(notice);
		}
		return false;
	}
	return true;

}

function checkIndirizzo(form) {
	const input_nome = form.find("[name=nome]");
	const input_indirizzo = form.find("[name=indirizzo]");
	const input_cap = form.find("[name=cap]");
	const input_provincia = form.find("[name=provincia]");

	const nome_label = input_nome.siblings("label");
	const indirizzo_label = input_indirizzo.siblings("label");
	const cap_label = input_cap.siblings("label");
	const provincia_label = input_provincia.siblings("label");


	const nome = input_nome.val();
	const indirizzo = input_indirizzo.val();
	const cap = input_cap.val();
	const provincia = input_provincia.val();


	if (nome.length < 3) {
		const notice = "Nome non valido: minimo 3 caratteri";
		if (nome_label.length === 0) {
			input_nome.parent().append("<label>" + notice + "</label>");
		} else {
			nome_label.html(notice);
		}
		return false;
	} else if (nome.length > 40) {
		const notice = "Nome non valido: massimo 100 caratteri";
		if (nome_label.length === 0) {
			input_nome.parent().append("<label>" + notice + "</label>");
		} else {
			nome_label.html(notice);
		}
		return false;
	} else if (indirizzo.length < 3) {
		const notice = "indirizzo non valido: minimo 3 caratteri";
		if (indirizzo_label.length === 0) {
			input_indirizzo.parent().append("<label>" + notice + "</label>");
		} else {
			indirizzo_label.html(notice);
		}
		return false;
	} else if (nome > 40) {
		const notice = "indirizzo non valido: massimo 100 caratteri";
		if (indirizzo_label.length === 0) {
			input_indirizzo.parent().append("<label>" + notice + "</label>");
		} else {
			indirizzo_label.html(notice);
		}
		return false;
	} else if (provincia.length < 3) {
		const notice = "provincia non valida: minimo 3 caratteri";
		if (provincia_label.length === 0) {
			input_provincia.parent().append("<label>" + notice + "</label>");
		} else {
			provincia_label.html(notice);
		}
		return false;
	} else if (provincia.length > 40) {
		const notice = "provincia non valido: massimo 100 caratteri";
		if (provincia_label.length === 0) {
			input_provincia.parent().append("<label>" + notice + "</label>");
		} else {
			provincia_label.html(notice);
		}
		return false;
	} else if (cap.length !== 5) {
		const notice = "CAP non valido: sempre 5 caratteri numerici";
		if (cap_label.length === 0) {
			input_cap.parent().append("<label>" + notice + "</label>");
		} else {
			cap_label.html(notice);
		}
		return false;
	}
	return true;
}

function checkCarta(form) {
	const input_numero = form.find("[name=numero]");
	const input_cvv = form.find("[name=cvv]");
	const input_saldo = form.find("[name=saldo]");

	const numero_label = input_numero.siblings("label");
	const cvv_label = input_cvv.siblings("label");
	const saldo_label = input_saldo.siblings("label");

	const numero = input_numero.val();
	const cvv = input_cvv.val();
	const saldo = input_saldo.val();


	if (numero.length !== 16) {
		const notice = "Numero carta non valido: richieste 16 cifre";
		if (numero_label.length === 0) {
			input_numero.parent().append("<label>" + notice + "</label>");
		} else {
			numero_label.html(notice);
		}
		return false;
	} else if (cvv.length < 3 || cvv.length > 4) {
		const notice = "CVV non valido: richieste 3 o 4 cifre";
		if (cvv_label.length === 0) {
			input_cvv.parent().append("<label>" + notice + "</label>");
		} else {
			cvv_label.html(notice);
		}
		return false;
	} else if (Number(saldo)) {
		const notice = "CVV non valido: richieste 3 o 4 cifre";
		if (saldo_label.length === 0) {
			input_saldo.parent().append("<label>" + notice + "</label>");
		} else {
			saldo_label.html(notice);
		}
		return false;
	}
	return true;
}

function checkChangePass(form) {
	const input_nuova = form.find("[name=nuova]");
	const input_conferma = form.find("[name=conferma]");

	const nuova_label = input_nuova.siblings("label");
	const conferma_label = input_conferma.siblings("label");

	const nuova = input_nuova.val();
	const conferma = input_conferma.val();

	if (nuova.length < 3) {
		const notice = "Password non valida: minimo 3 caratteri";
		if (nuova_label.length === 0) {
			input_nuova.parent().append("<label>" + notice + "</label>");
		} else {
			nuova_label.html(notice);
		}
		return false;
	} else if (nuova > 16) {
		const notice = "Password non valida: massimo 40 caratteri";
		if (nuova_label.length === 0) {
			input_nuova.parent().append("<label>" + notice + "</label>");
		} else {
			nuova_label.html(notice);
		}
		return false;
	} else if (nuova === conferma) {
		const notice = "La password non coincide";
		if (conferma_label.length === 0) {
			input_conferma.parent().append("<label>" + notice + "</label>");
		} else {
			conferma_label.html(notice);
		}
		return false;
	}

}


function checkRecensione(form) {
	const input_voto = form.find("[name=voto]");
	const input_titolo = form.find("[name=titolo]");
	const input_commento = form.find("[name=commento]");

	const voto_label = input_voto.siblings("label");
	const titolo_label = input_titolo.siblings("label");
	const commento_label = input_commento.siblings("label");

	const voto = input_voto.val();
	const titolo = input_titolo.val();
	const commento = input_commento.val();

	if (titolo.length < 3) {
		const notice = "Titolo non valido: minimo 3 caratteri";
		if (titolo_label.length === 0) {
			input_titolo.parent().append("<label>" + notice + "</label>");
		} else {
			titolo_label.html(notice);
		}
		return false;
	} else if (titolo.length > 40) {
		const notice = "Titolo non valida: massimo 40 caratteri";
		if (titolo_label.length === 0) {
			input_titolo.parent().append("<label>" + notice + "</label>");
		} else {
			titolo_label.html(notice);
		}
		return false;
	} else if(commento.length<3){
		const notice = "Commento non valido: minimo 3 caratteri";
		if (commento_label.length === 0) {
			input_commento.parent().append("<label>" + notice + "</label>");
		} else {
			commento_label.html(notice);
		}
		return false;
	}else if(voto<1||voto>5){
		const notice = "Voto non valido: minimo 1 massimo 5";
		if (voto_label.length === 0) {
			input_voto.parent().append("<label>" + notice + "</label>");
		} else {
			voto_label.html(notice);
		}
		return false;

	}
	return true;

}

function checkProdotto(form) {
	const input_nome = form.find("[name=nome]");
	const input_descrizione= form.find("[name=descrizione]");
	const input_prezzo= form.find("[name=prezzo]");

	const nome_label = input_nome .siblings("label");
	const descrizione_label = input_descrizione.siblings("label");
	const prezzo_label = input_prezzo.siblings("label");

	const nome = input_nome.val();
	const descrizione = input_descrizione.val();
	const prezzo = input_prezzo.val();

	if (nome .length < 3) {
		const notice = "Titolo non valido: minimo 3 caratteri";
		if (nome_label.length === 0) {
			input_nome.parent().append("<label>" + notice + "</label>");
		} else {
			nome_label.html(notice);
		}
		return false;
	} else if (nome.length > 40) {
		const notice = "Titolo non valida: massimo 40 caratteri";
		if (nome_label.length === 0) {
			input_nome.parent().append("<label>" + notice + "</label>");
		} else {
			nome_label.html(notice);
		}
		return false;
	} else if(descrizione.length<3){
		const notice = "Commento non valido: minimo 3 caratteri";
		if (descrizione_label.length === 0) {
			input_descrizione.parent().append("<label>" + notice + "</label>");
		} else {
			descrizione_label.html(notice);
		}
		return false;
	}else if(/^(\d*([.,](?=\d{3}))?\d+)+((?!\2)[.,]\d\d)?€/.test(prezzo)){
		const notice = "Commento non valido: minimo 3 caratteri";
		if (prezzo_label.length === 0) {
			input_prezzo.parent().append("<label>" + notice + "</label>");
		} else {
			prezzo_label.html(notice);
		}
		return false;
	}
	return true;

}

function validate(action, form) {
	if (action === "servlet_registrazione") {
		return checkRegistrazione(form);
	}
	if (action === "servlet_cambiaPassword") {
		return checkChangePass(form);
	}
	if (action === "servlet_login") {
		return checkLogin(form);
	}
	if (action === "servlet_aggiungiCarta") {
		return checkCarta(form);
	}
	if (action === "servlet_aggiungiIndirizzo") {
		return checkIndirizzo(form);
	}
	if (action === "servlet_aggiungiRecensione") {
		return checkRecensione(form);
	}
	if(action==="servlet_aggiungiProdotto"){
		return checkProdotto(form);
	}
}