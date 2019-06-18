package controller;

import model.Carrello;
import model.bean.Indirizzo;
import model.bean.Ordine;
import model.bean.Utente;
import model.dao.IndirizzoDAO;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/acquista")
public class Acquista extends HttpServlet {

	private Indirizzo indirizzo;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);
		Utente utente = (Utente) req.getSession().getAttribute("utente");
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		if (utente == null || carrello == null) {
			// Effettuo il logout perché questo evento non deve mai capitare.
			em.logout();
		} else if (!input(req)) {
			// L'indirizzo è indispensabile per effettuare l'ordine.
			em.logout();
		} else {

			Ordine o = Ordine.makeFromCart(carrello, indirizzo, utente);
			if (o == null) {
				// Si è verificato un problema non dovuto dall'utente.
				em.message("Si è verificato un problema. Riprova più tardi.");
				em.reload();
			} else {
				// Tutto ok.
				em.message("Acquisto effettuato");
				em.redirect("storico.jsp");
			}

		}
		em.apply();
	}

	private boolean input(HttpServletRequest req) {
		try {
			int indirizzo_id = Integer.parseInt(req.getParameter("indirizzo"));
			indirizzo = IndirizzoDAO.doRetrieveByKey(indirizzo_id);
			if (indirizzo == null) {
				throw new NumberFormatException();
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
