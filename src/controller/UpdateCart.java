package controller;

import model.Carrello;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;
import org.json.JSONObject;
import util.Formats;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		String mode = req.getParameter("mode");
		assert mode != null && (mode.equals("add") || mode.equals("set") || mode.equals("drop"));
		int prodotto = Integer.parseInt(req.getParameter("p"));
		JSONObject res = new JSONObject();
		// TODO: Sincronizzare con DB se loggato.
		Prodotto prodottoObj = ProdottoDAO.doRetrieveByKey(prodotto);
		if (prodottoObj != null) {
			Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
			if (mode.equals("drop")) {
				if (carrello != null) {
					carrello.drop(prodotto);
				} else {
					res.put("error", "Il carrello è vuoto");
				}
			} else {
				int quantita = Integer.parseInt(req.getParameter("q"));
				if (quantita >= 1) {
					if (carrello == null) {
						carrello = new Carrello();
					}
					if (mode.equals("add")) {
						carrello.add(prodotto, quantita);
					} else {
						carrello.set(prodotto, quantita);
						res.put("newtotal", Formats.euro(prodottoObj.prezzo * quantita));
					}
				} else {
					res.put("error", "La quantità è negativa");
				}
			}
			if (carrello != null) {
				req.getSession().setAttribute("carrello", carrello);
				res.put("count", carrello.getCount());
			}
		} else {
			res.put("error", "Il prodotto non esiste");
		}
		resp.getWriter().print(res.toString());
	}

}
