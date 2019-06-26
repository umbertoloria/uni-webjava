package controller;

import model.bean.Indirizzo;
import model.bean.Utente;
import model.dao.IndirizzoDAO;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rimuoviIndirizzo")
public class RimuoviIndirizzo extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			em.logout();
			em.apply();
			return;
		}

		Indirizzo indirizzo;
		try {
			// L'indirizzo deve esistere e soprattutto deve essere di nostra proprietà.
			int id = Integer.parseInt(req.getParameter("id").trim());
			indirizzo = IndirizzoDAO.doRetrieveByKey(id);
			if (indirizzo == null || indirizzo.utente != utente.id) {
				throw new NullPointerException();
			}
		} catch (NullPointerException | NumberFormatException ignore) {
			em.logout();
			em.apply();
			return;
		}

		if (IndirizzoDAO.doRemoveByKey(indirizzo.id)) {
			em.info("");
		} else {
			em.notification("Al momento non è possibile cancellare un indirizzo sul proprio account. " +
					"Riprova più tardi.");
		}
		em.apply();
	}

}
