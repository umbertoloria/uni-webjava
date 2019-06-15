package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.validators.PasswordValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cambiaPassword")
public class CambiaPassword extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("plain/text");

		int uid;
		{
			Utente utente = (Utente) req.getSession().getAttribute("utente");
			if (utente == null) {
				// Vado al "logout" perché esso cancella anche "age" nella sessione, che non è ancora implementato.
				resp.sendRedirect("logout.jsp");
				return;
			}
			uid = utente.id;
		}

		String vecchia = req.getParameter("vecchia");
		String nuova = req.getParameter("nuova");
		String conferma = req.getParameter("conferma");
		if (vecchia == null || nuova == null || conferma == null) {
			return;
		}
		vecchia = vecchia.trim();
		nuova = nuova.trim();
		conferma = conferma.trim();

		ErrorManager em = new ErrorManager(resp);

		PasswordValidator passwordValidator = new PasswordValidator(nuova, conferma);
		if (passwordValidator.wrongInput()) {
			em.notice("nuova", passwordValidator.nuova);
			em.notice("conferma", passwordValidator.conferma);
		} else if (!nuova.equals(conferma)) {
			em.message("Le password non coincidono");
		} else if (vecchia.equals(nuova)) {
			em.notice("vecchia", "La tua password è già questa.");
		} else {

			// Il modello 'utente' nella sessione potrebbe essere non aggiornato rispetto ad eventuali modifiche
			// effettuate su una sessione diversa da quella corrente e successive al login su questa sessione.
			// Per questo motivo, scarichiamo dal DB i dati sicuramente aggiornati dell'utente loggato.
			Utente actual = UtenteDAO.doRetrieveByKey(uid);
			assert actual != null;

			if (!vecchia.equals(actual.password)) {
				em.notice("vecchia", "La vecchia password è errata");
			} else {
				Utente changed = new Utente(actual.id, actual.email, nuova, actual.nome, actual.tipo);
				if (UtenteDAO.updatePassword(changed)) {
					// Se è possibile cambiare la password, salviamo 'changed' in sessione.
					req.getSession().setAttribute("utente", changed);
					em.done("Riaccedi per poter usare la tua nuova password");
					em.redirect("logout.jsp");
				} else {
					// Se non è possibile cambiare la password, salviamo comunque la versione aggiornata
					// dell'utente in sessione.
					req.getSession().setAttribute("utente", actual);
					em.message("Non è stato possibile cambiare la password. Riprova più tardi.");
				}
			}

		}
	}

}
