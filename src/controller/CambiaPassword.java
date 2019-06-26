package controller;

import model.bean.Utente;
import model.dao.UtenteDAO;
import model.validator.CambioPasswordValidator;
import util.ErrorManager;
import util.Security;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_cambiaPassword")
public class CambiaPassword extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
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

		CambioPasswordValidator validator = new CambioPasswordValidator(nuova, conferma);
		if (validator.wrongInput()) {
			em.notice("nuova", validator.nuova);
			em.notice("conferma", validator.conferma);
		} else if (!nuova.equals(conferma)) {
			em.notice("conferma", "Le password non coincidono");
		} else if (vecchia.equals(nuova)) {
			em.notice("vecchia", "La tua password è già questa.");
		} else {

			// Il modello 'utente' nella sessione potrebbe essere non aggiornato rispetto ad eventuali modifiche
			// effettuate su una sessione diversa da quella corrente e successive al login su questa sessione.
			// Per questo motivo, scarichiamo dal DB i dati sicuramente aggiornati dell'utente loggato.
			Utente actual = UtenteDAO.doRetrieveByKey(utente.id);
			assert actual != null;

			vecchia = Security.sha1(vecchia);
			nuova = Security.sha1(nuova);
			if (!vecchia.equals(actual.password)) {
				em.notice("vecchia", "La vecchia password è errata");
			} else {
				Utente changed = new Utente(actual.id, actual.email, nuova, actual.nome, actual.tipo);
				if (UtenteDAO.updatePassword(changed)) {
					// Se è possibile cambiare la password, salviamo 'changed' in sessione.
					req.getSession().setAttribute("utente", changed);
					em.overlay("Riaccedi per poter usare la tua nuova password");
					em.redirect("logout");
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
