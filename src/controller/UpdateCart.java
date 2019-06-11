package controller;

import model.bean.Carrello;
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
		resp.setContentType("application/json");
		int prodotto = Integer.parseInt(req.getParameter("p"));
		int quantita = Integer.parseInt(req.getParameter("q"));
		// TODO: ma il carrello deve sempre esistere se un utente è loggato?
		// TODO: Sincronizzare con DB se loggato.
		String result = "[]";
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		if (carrello != null) {
			if (quantita > 0) {
				carrello.update(prodotto, quantita);
			} else {
				carrello.drop(prodotto);
			}
			if (carrello.getCount() > 0) {
				req.getSession().setAttribute("carrello", carrello);
				result = Jsonfy.carrello(carrello).toString();
			} else {
				req.getSession().removeAttribute("carrello");
			}
		}
		resp.getWriter().print(result);
	}

}
