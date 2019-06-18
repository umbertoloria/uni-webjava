package controller;

import model.bean.Indirizzo;
import model.bean.Utente;
import model.dao.IndirizzoDAO;
import model.validators.IndirizzoValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/aggiungiIndirizzo")
public class AggiungiIndirizzo extends HttpServlet {

	private String nome, indirizzo, citta, cap, provincia;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);
		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			em.logout();
		} else if (!input(req)) {
			em.internalError();
		} else {

			Indirizzo indirizzo = new Indirizzo(nome, this.indirizzo, citta, cap, provincia, utente.id);
			IndirizzoValidator indirizzoValidator = new IndirizzoValidator(indirizzo);
			if (indirizzoValidator.wrongInput()) {
				em.notice("nome", indirizzoValidator.nome);
				em.notice("indirizzo", indirizzoValidator.indirizzo);
				em.notice("citta", indirizzoValidator.citta);
				em.notice("cap", indirizzoValidator.cap);
				em.notice("provincia", indirizzoValidator.provincia);
			} else {
				if (IndirizzoDAO.doRetrieveByNomeAndUtente(nome, utente.id) != null) {
					em.notice("nome", "Possiedi già un indirizzo con questo nome");
				} else if (IndirizzoDAO.doSave(indirizzo)) {
					em.done("Indirizzo inserito correttamente. Buona spesa!");
					em.reload();
				} else {
					em.message("Al momento non è possibile aggiungere indirizzi sul proprio account. " +
							"Riprova più tardi.");
				}
			}

		}
		em.apply();
	}

	private boolean input(HttpServletRequest req) {
		try {
			nome = req.getParameter("nome").trim();
			indirizzo = req.getParameter("indirizzo").trim();
			citta = req.getParameter("citta").trim();
			cap = req.getParameter("cap").trim();
			provincia = req.getParameter("provincia").trim();
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

}
