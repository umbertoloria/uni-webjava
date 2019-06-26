package controller;

import model.Carrello;
import model.bean.Indirizzo;
import model.bean.Ordine;
import model.bean.Utente;
import model.dao.IndirizzoDAO;
import model.dao.OrdineDAO;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/acquista")
public class Acquista extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		if (utente == null || carrello == null) {
			// Effettuo il logout perché questo evento non deve mai capitare.
			em.logout();
			em.apply();
			return;
		}

		Indirizzo indirizzo;
		try {
			// L'indirizzo è indispensabile per effettuare l'ordine.
			int id = Integer.parseInt(req.getParameter("indirizzo"));
			indirizzo = IndirizzoDAO.doRetrieveByKey(id);
			if (indirizzo == null) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			em.logout();
			em.apply();
			return;
		}

		Ordine o = new Ordine(utente.id, indirizzo.toString(), carrello);
		if (OrdineDAO.doSave(o)) {
			// Tutto ok.
			req.getSession().removeAttribute("carrello");
			em.overlay("Acquisto effettuato");
			em.redirect("ordini");
		} else {
			// Si è verificato un problema non dovuto dall'utente.
			em.overlay("Si è verificato un problema. Riprova più tardi.");
			em.reload();
		}
		em.apply();
	}

}
