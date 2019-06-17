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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("plain/text");

		ErrorManager em = new ErrorManager(resp);

		int uid;
		{
			Utente utente = (Utente) req.getSession().getAttribute("utente");
			if (utente == null) {
				// Vado al "logout" perché esso cancella anche "age" nella sessione, che non è ancora implementato.
				em.logout();
				return;
			}
			uid = utente.id;
		}

		int id;
		try {
			id = Integer.parseInt(req.getParameter("id"));
			Indirizzo check = IndirizzoDAO.doRetrieveByKey(id);
			// Se l'indirizzo con questo 'id' è in realtà posseduto da un altro utente, allora facciamo logout.
			if (check == null || check.utente != uid) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			em.logout();
			return;
		}
		String nome = req.getParameter("nome");
		String indirizzo = req.getParameter("indirizzo");
		String citta = req.getParameter("citta");
		String cap = req.getParameter("cap");
		String provincia = req.getParameter("provincia");
		if (nome == null || indirizzo == null || citta == null || cap == null || provincia == null) {
			return;
		}
		nome = nome.trim();
		indirizzo = indirizzo.trim();
		citta = citta.trim();
		cap = cap.trim();
		provincia = provincia.trim();

		Indirizzo indirizzoObj = new Indirizzo(id, nome, indirizzo, citta, cap, provincia, uid);

		IndirizzoValidator indirizzoValidator = new IndirizzoValidator(indirizzoObj);
		if (indirizzoValidator.wrongInput()) {
			em.notice("nome", indirizzoValidator.nome);
			em.notice("indirizzo", indirizzoValidator.indirizzo);
			em.notice("citta", indirizzoValidator.citta);
			em.notice("cap", indirizzoValidator.cap);
			em.notice("provincia", indirizzoValidator.provincia);
		} else {
			Indirizzo app = IndirizzoDAO.doRetrieveByNomeAndUtente(nome, uid);
			if (app != null && app.id != id) {
				// Un'altro indirizzo escluso quello che stiamo modificando, nel caso il nome rimanga lo stesso.
				em.notice("nome", "Possiedi già un indirizzo con questo nome");
			} else if (IndirizzoDAO.doUpdate(indirizzoObj)) {
				em.done("Indirizzo modificato correttamente. Buona spesa!");
				em.reload();
			} else {
				em.message("Al momento non è possibile modificare indirizzi sul proprio account. Riprova più tardi.");
			}
		}
	}

}
