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

@WebServlet("/modificaIndirizzo")
public class ModificaIndirizzo extends HttpServlet {

	private Utente utente;
	private Integer id;
	private String nome, indirizzo, citta, cap, provincia;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);
		utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			// Vado al "logout" perché esso cancella anche "age" nella sessione, che non è ancora implementato.
			em.logout();
		} else if (!idInput(req)) {
			em.internalError();
		} else if (!dataInput(req)) {
			em.internalError();
		} else {

			Indirizzo indirizzo = new Indirizzo(id, nome, this.indirizzo, citta, cap, provincia, utente.id);
			IndirizzoValidator indirizzoValidator = new IndirizzoValidator(indirizzo);
			if (indirizzoValidator.wrongInput()) {
				em.notice("nome", indirizzoValidator.nome);
				em.notice("indirizzo", indirizzoValidator.indirizzo);
				em.notice("citta", indirizzoValidator.citta);
				em.notice("cap", indirizzoValidator.cap);
				em.notice("provincia", indirizzoValidator.provincia);
			} else {
				Indirizzo app = IndirizzoDAO.doRetrieveByNomeAndUtente(nome, utente.id);
				if (app != null && app.id != id) {
					// Un'altro indirizzo escluso quello che stiamo modificando, nel caso il nome rimanga lo stesso.
					em.notice("nome", "Possiedi già un indirizzo con questo nome");
				} else if (IndirizzoDAO.doUpdate(indirizzo)) {
					em.done("Indirizzo modificato correttamente. Buona spesa!");
					em.reload();
				} else {
					em.message("Al momento non è possibile modificare indirizzi sul proprio account. " +
							"Riprova più tardi.");
				}
			}

		}
		em.apply();
	}

	private boolean idInput(HttpServletRequest req) {
		try {
			id = Integer.parseInt(req.getParameter("id"));
			Indirizzo check = IndirizzoDAO.doRetrieveByKey(id);
			// Se l'indirizzo con questo 'id' non è in nostro possesso, allora non possiamo modificarlo.
			if (check == null || check.utente != utente.id) {
				throw new NumberFormatException();
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean dataInput(HttpServletRequest req) {
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
