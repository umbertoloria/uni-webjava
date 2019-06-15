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

		ErrorManager em = new ErrorManager(resp);

		Indirizzo indirizzoObj = new Indirizzo(nome, indirizzo, citta, cap, provincia, uid);

		IndirizzoValidator indirizzoValidator = new IndirizzoValidator(indirizzoObj);
		if (indirizzoValidator.wrongInput()) {
			em.notice("nome", indirizzoValidator.nome);
			em.notice("indirizzo", indirizzoValidator.indirizzo);
			em.notice("citta", indirizzoValidator.citta);
			em.notice("cap", indirizzoValidator.cap);
			em.notice("provincia", indirizzoValidator.provincia);
		} else {
			if (IndirizzoDAO.doSave(indirizzoObj)) {
				em.done("Indirizzo inserito correttamente. Buona spesa!");
				em.redirect("./");
			} else {
				em.message("Al momento non è possibile aggiungere indirizzi sul proprio account. Riprova più tardi.");
			}
		}
	}

}
