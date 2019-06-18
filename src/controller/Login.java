package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.validators.UtenteValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

	private Utente utente;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);
		if (!input(req)) {
			em.internalError();
		} else {

			UtenteValidator utenteValidator = new UtenteValidator(utente, false);
			if (utenteValidator.wrongInput()) {
				em.notice("email", utenteValidator.email);
				em.notice("password", utenteValidator.password);
			} else {
				Utente saved = UtenteDAO.doRetrieveByEmail(utente.email);
				if (saved == null || !saved.password.equals(utente.password)) {
					em.message("Dati di accesso errati");
				} else {
					HttpSession session = req.getSession();
					session.setMaxInactiveInterval(60 * 60);
					session.setAttribute("utente", saved);
					em.done("Accesso effettuato");
					em.redirect(".");
					// TODO: Scaricare carrello sulla sessione.
				}
			}

		}
		em.apply();
	}

	private boolean input(HttpServletRequest req) {
		try {
			String email = req.getParameter("email").trim();
			String password = req.getParameter("password").trim();
			utente = new Utente(email, password);
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

}
