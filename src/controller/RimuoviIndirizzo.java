package controller;

import model.bean.Indirizzo;
import model.bean.Utente;
import model.dao.IndirizzoDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rimuoviIndirizzo")
public class RimuoviIndirizzo extends HttpServlet {

	/** Possible output: "logout", "fail", "done" */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("plain/text");

		int uid;
		{
			Utente utente = (Utente) req.getSession().getAttribute("utente");
			if (utente == null) {
				resp.getWriter().println("logout");
				return;
			}
			uid = utente.id;
		}

		int id;
		try {
			id = Integer.parseInt(req.getParameter("id").trim());
		} catch (NullPointerException | NumberFormatException ignore) {
			resp.getWriter().println("fail");
			return;
		}

		Indirizzo indirizzo = IndirizzoDAO.doRetrieveByKey(id);
		if (indirizzo == null) {
			resp.getWriter().println("fail");
		} else if (indirizzo.utente != uid) {
			// TODO: Testare con multipli utenti.
			resp.getWriter().println("fail");
		} else if (IndirizzoDAO.doRemoveByKey(id)) {
			resp.getWriter().println("done");
		} else {
			resp.getWriter().println("fail");
		}

	}

}
