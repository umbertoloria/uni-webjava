package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.validators.RegistrazioneValidator;
import util.ErrorManager;
import util.Security;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_registrazione")
public class Registrazione extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		String nome, email, password, password2;
		try {
			nome = req.getParameter("nome").trim();
			email = req.getParameter("email").trim();
			password = req.getParameter("password").trim();
			password2 = req.getParameter("password2").trim();
		} catch (NullPointerException e) {
			em.logout();
			em.apply();
			return;
		}

		RegistrazioneValidator validator = new RegistrazioneValidator(nome, email, password, password2);
		if (validator.wrongInput()) {
			em.notice("nome", validator.nomeMsg);
			em.notice("email", validator.emailMsg);
			em.notice("password", validator.passwordMsg);
			em.notice("password2", validator.password2Msg);
		} else if (UtenteDAO.doRetrieveByEmail(email) != null) {
			em.notice("email", "L'E-Mail fornita è già usata da un altro utente");
		} else {
			Utente utente = new Utente(email, Security.sha1(password), nome);
			if (UtenteDAO.doSave(utente)) {
				em.overlay("Registrazione effettuata");
				em.redirect("login");
			} else {
				em.notice(null, "Al momento non è possibile accedere al proprio account. Riprova più tardi.");
			}
		}
		em.apply();
	}

}
