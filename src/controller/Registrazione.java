package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.validators.UtenteValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrazione")
public class Registrazione extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);
		// TODO: Cifrare la password prima di salvarla.

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

		Utente utente = new Utente(email, password, nome);
		UtenteValidator utenteValidator = new UtenteValidator(utente, true);
		if (utenteValidator.wrongInput()) {
			em.notice("nome", utenteValidator.nome);
			em.notice("email", utenteValidator.email);
			em.notice("password", utenteValidator.password);
		} else if (!password.equals(password2)) {
			em.notice("password2", "Le password devono coincidere");
		} else if (UtenteDAO.doRetrieveByEmail(email) != null) {
			em.notice("email", "L'E-Mail fornita è già usata da un altro utente");
		} else {
			if (UtenteDAO.doSave(utente)) {
				em.overlay("Registrazione effettuata");
				em.redirect("login.jsp");
			} else {
				em.notice(null, "Al momento non è possibile accedere al proprio account. Riprova più tardi.");
			}
		}
		em.apply();
	}

}
