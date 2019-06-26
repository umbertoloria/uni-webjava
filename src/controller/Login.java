package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.validator.LoginValidator;
import util.ErrorManager;
import util.Security;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet_login")
public class Login extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		String email, password;
		try {
			email = req.getParameter("email").trim();
			password = req.getParameter("password").trim();
		} catch (NullPointerException e) {
			em.logout();
			em.apply();
			return;
		}

		LoginValidator validator = new LoginValidator(email, password);
		if (validator.wrongInput()) {
			em.notice("email", validator.emailMsg);
			em.notice("password", validator.passwordMsg);
		} else {
			Utente saved = UtenteDAO.doRetrieveByEmail(email);
			if (saved == null || !saved.password.equals(Security.sha1(password))) {
				em.notice(null, "Dati di accesso errati");
			} else {
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(60 * 60);
				session.setAttribute("utente", saved);
				em.overlay("Accesso effettuato");
				em.redirect(".");
			}
		}
		em.apply();
	}

}
