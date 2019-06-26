package controller;

import model.bean.Recensione;
import model.bean.Utente;
import model.dao.RecensioneDAO;
import model.validator.RecensioneValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_aggiungiRecensione")
public class AggiungiRecensione extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			em.logout();
			em.apply();
			return;
		}

		int prodotto;
		try {
			prodotto = Integer.parseInt(req.getParameter("prodotto").trim());
		} catch (NumberFormatException | NullPointerException e) {
			em.logout();
			em.apply();
			return;
		}

		int voto;
		String titolo;
		String commento;
		try {
			voto = Integer.parseInt(req.getParameter("voto").trim());
			if (voto < 1 || voto > 5) {
				throw new NullPointerException();
			}
			titolo = req.getParameter("titolo").trim();
			commento = req.getParameter("commento").trim();
		} catch (NullPointerException e) {
			em.logout();
			em.apply();
			return;
		}

		RecensioneValidator validator = new RecensioneValidator(voto, titolo, commento);
		if (validator.wrongInput()) {
			em.notice("voto", validator.votoMsg);
			em.notice("titolo", validator.titoloMsg);
			em.notice("commento", validator.commentoMsg);
		} else {
			Recensione recensione = new Recensione(prodotto, voto, titolo, commento, utente.id);
			if (RecensioneDAO.doRetrieveByUtenteAndProdotto(utente.id, prodotto) != null) {
				em.notice("nome", "Hai già fatto una recensione di questo prodotto");
			} else if (RecensioneDAO.doSave(recensione)) {
				em.overlay("Recensione inserita correttamente. Grazie per il feedback!");
				em.reload();
			} else {
				em.notice(null, "Al momento non è possibile aggiungere una recensione di questo prodotto." +
						"Riprova più tardi.");
			}
		}
		em.apply();
	}

}
