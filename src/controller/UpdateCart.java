package controller;

import model.Carrello;
import model.dao.ProdottoDAO;
import util.Jsonfy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet {

	// Chiamerò questa servlet solo se ho una quantità da aggiornare (non per aggiungere un prodotto).
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// TODO: Ma il carrello deve sempre esistere se un utente è loggato?
		// TODO: Sincronizzare con DB se loggato.

		resp.setContentType("application/json");
		String mode = req.getParameter("mode");
		assert mode != null && (mode.equals("add") || mode.equals("set") || mode.equals("drop"));
		int prodotto = Integer.parseInt(req.getParameter("p"));

		String result = "[]";

		if (ProdottoDAO.doRetrieveByKey(prodotto) != null) {

			Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
			if (mode.equals("drop")) {
				if (carrello != null) {
					carrello.drop(prodotto);
				}
			} else {
				int quantita = Integer.parseInt(req.getParameter("q"));
				if (quantita >= 1) {
					if (mode.equals("add")) {
						if (carrello == null) {
							carrello = new Carrello();
						}
						carrello.add(prodotto, quantita);
					} else {
						if (carrello == null) {
							carrello = new Carrello();
						}
						carrello.set(prodotto, quantita);
					}
				}
			}

			if (carrello != null) {
				req.getSession().setAttribute("carrello", carrello);
				result = Jsonfy.carrello(carrello).toString();
			}

		}

//		if (ProdottoDAO.doRetrieveByKey(prodotto) != null) {
//			Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
//			if (carrello == null) {
//				carrello = new Carrello();
//			}
//			if (mode.equals("add")) {
//			} else {
//				if (carrello != null) {
//					if (quantita > 0) {
//						carrello.set(prodotto, quantita);
//					} else {
//						carrello.drop(prodotto);
//					}
//					if (carrello.getCount() > 0) {
//						req.getSession().setAttribute("carrello", carrello);
//						result = Jsonfy.carrello(carrello).toString();
//					} else {
//						req.getSession().removeAttribute("carrello");
//					}
//				}
//			}
//		}
		resp.getWriter().print(result);
	}

}
