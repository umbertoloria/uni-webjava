package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.formatters.UtenteValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet_login")
public class Login extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("plain/text");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		if (email == null || password == null) {
			return;
		}
		email = email.trim();
		password = password.trim();

		ErrorManager em = new ErrorManager(resp);

		Utente utente = new Utente(email, password);

		UtenteValidator utenteValidator = new UtenteValidator(utente, false);
		if (!utenteValidator.empty()) {
			em.notice("email", utenteValidator.email);
			em.notice("password", utenteValidator.password);
		} else {
			Utente saved = UtenteDAO.doRetrieveByEmail(email);
			if (saved == null || !saved.password.equals(password)) {
				em.message("Dati di accesso errati.");
			} else {
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(60 * 60);
				session.setAttribute("user", saved);
				em.done("Accesso effettuato");
				em.redirect(".");
				// TODO: Scaricare carrello sulla sessione.
			}
		}
	}

}
