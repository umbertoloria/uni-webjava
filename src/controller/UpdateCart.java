package controller;

import model.Carrello;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;
import org.json.JSONObject;
import util.ErrorManager;
import util.Formats;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String mode = req.getParameter("mode");

		ErrorManager em = new ErrorManager(resp);

		Prodotto prodotto;
		try {
			int pid = Integer.parseInt(req.getParameter("p"));
			prodotto = ProdottoDAO.doRetrieveByKey(pid);
			if (prodotto == null) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			em.notification("Il prodotto non esiste");
			em.apply();
			return;
		}

		// Il prodotto esiste. Mode: "drop" | "add" | "set".
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");

		JSONObject res = new JSONObject();
		if (mode.equals("drop")) {

			// DROP: rimuovere un prodotto. Se è possibile lo si fa, altrimenti errore.
			if (carrello != null) {
				// Se il carrello esiste, rimuoviamo il prodotto.
				carrello.drop(prodotto.id);
				int cart_count = carrello.getCount();
				if (cart_count > 0) {
					req.getSession().setAttribute("carrello", carrello);
					res.put("cart_count", cart_count);
					res.put("cart_total", Formats.euro(carrello.getTotal()));
					res.put("cart_serial", carrello.serialize());
				} else {
					req.getSession().removeAttribute("carrello");
					res.put("cart_count", 0);
					res.put("cart_total", Formats.euro(0));
					res.put("cart_serial", "");
				}
			} else {
				// Non si vorrebbe mai rimuovere un prodotto da un carrello già vuoto.
				em.notification("Il carrello è già vuoto.");
			}

		} else {

			Integer quantita = null;
			try {
				quantita = Integer.parseInt(req.getParameter("q"));
				if (quantita < 1) {
					quantita = null;
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				em.notification("Quantità non valida");
			}

			if (quantita != null) {

				// La quantità è accettabile. Mode: "add" | "set". Visto che si aggiunge in entrambi i casi,
				// ci assicuriamo di avere sempre un carrello istanziato.

				if (carrello == null) {
					carrello = new Carrello();
				}

				if (mode.equals("add")) {
					// ADD
					carrello.add(prodotto.id, quantita);
				} else if (mode.equals("set")) {
					// SET
					carrello.set(prodotto.id, quantita);
					res.put("product_total", Formats.euro(prodotto.prezzo * quantita));
				}

				req.getSession().setAttribute("carrello", carrello);
				res.put("cart_count", carrello.getCount());
				res.put("cart_total", Formats.euro(carrello.getTotal()));
				res.put("cart_serial", carrello.serialize());

			}

		}

		if (!res.isEmpty()) {
			em.info(res);
		}
		em.apply();

	}

}
