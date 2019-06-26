package controller;

import model.bean.CartaCredito;
import model.bean.Utente;
import model.dao.CartaCreditoDAO;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rimuoviCarta")
public class RimuoviCarta extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			em.logout();
			em.apply();
			return;
		}

		CartaCredito carta;
		try {
			// L'indirizzo deve esistere e soprattutto deve essere di nostra proprietà.
			String id = req.getParameter("num").trim();
			carta = CartaCreditoDAO.doRetrieveByKey(id);
			if (carta == null || carta.utente != utente.id) {
				throw new NullPointerException();
			}
		} catch (NullPointerException | NumberFormatException e) {
			em.logout();
			em.apply();
			return;
		}

		if (CartaCreditoDAO.doRemoveByKey(carta.numero)) {
			em.info("");
		} else {
			em.notification("Al momento non è possibile cancellare una carta sul proprio account. " +
					"Riprova più tardi.");
		}
		em.apply();
	}

}
