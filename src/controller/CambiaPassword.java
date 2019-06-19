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
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			// Vado al "logout" perché esso cancella anche "age" nella sessione, che non è ancora implementato.
			em.logout();
			em.apply();
			return;
		}

		String vecchia, nuova, conferma;
		try {
			vecchia = req.getParameter("vecchia").trim();
			nuova = req.getParameter("nuova").trim();
			conferma = req.getParameter("conferma").trim();
		} catch (NullPointerException e) {
			em.logout();
			em.apply();
			return;
		}

		PasswordValidator passwordValidator = new PasswordValidator(nuova, conferma);
		if (passwordValidator.wrongInput()) {
			em.notice("nuova", passwordValidator.nuova);
			em.notice("conferma", passwordValidator.conferma);
		} else if (!nuova.equals(conferma)) {
			em.notice("password2", "Le password non coincidono");
		} else if (vecchia.equals(nuova)) {
			em.notice("vecchia", "La tua password è già questa.");
		} else {

			// Il modello 'utente' nella sessione potrebbe essere non aggiornato rispetto ad eventuali modifiche
			// effettuate su una sessione diversa da quella corrente e successive al login su questa sessione.
			// Per questo motivo, scarichiamo dal DB i dati sicuramente aggiornati dell'utente loggato.
			Utente actual = UtenteDAO.doRetrieveByKey(utente.id);
			assert actual != null;

			if (!vecchia.equals(actual.password)) {
				em.notice("vecchia", "La vecchia password è errata");
			} else {
				Utente changed = new Utente(actual.id, actual.email, nuova, actual.nome, actual.tipo);
				if (UtenteDAO.updatePassword(changed)) {
					// Se è possibile cambiare la password, salviamo 'changed' in sessione.
					req.getSession().setAttribute("utente", changed);
					em.overlay("Riaccedi per poter usare la tua nuova password");
					em.redirect("logout.jsp");
				} else {
					// Se non è possibile cambiare la password, salviamo comunque la versione aggiornata
					// dell'utente in sessione.
					req.getSession().setAttribute("utente", actual);
					em.notice(null, "Non è stato possibile cambiare la password. Riprova più tardi.");
				}
			}

		}
		em.apply();
	}

}
