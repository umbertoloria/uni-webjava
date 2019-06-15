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
		resp.setContentType("plain/text");
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String password = req.getParameter("password"); // TODO: Cifrare la password prima di salvarla.
		String password2 = req.getParameter("password2");
		if (nome == null || email == null || password == null || password2 == null) {
			return;
		}
		nome = nome.trim();
		email = email.trim();
		password = password.trim();
		password2 = password2.trim();

		ErrorManager em = new ErrorManager(resp);

		Utente utente = new Utente(email, password, nome);

		UtenteValidator utenteValidator = new UtenteValidator(utente, true);
		if (utenteValidator.wrongInput()) {
			em.notice("nome", utenteValidator.nome);
			em.notice("email", utenteValidator.email);
			em.notice("password", utenteValidator.password);
		} else if (!password.equals(password2)) {
			em.notice("password2", "Le password devono coincidere");
		} else if (UtenteDAO.doRetrieveByEmail(email) != null) {
			em.message("L'E-Mail fornita è già usata da un altro utente");
		} else {
			if (UtenteDAO.doSave(utente)) {
				em.done("Registrazione effettuata");
				em.redirect("login.jsp");
			} else {
				em.message("Al momento non è possibile accedere al proprio account. Riprova più tardi.");
			}
		}
	}

}
