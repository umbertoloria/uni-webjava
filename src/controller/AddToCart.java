package controller;

import model.bean.Carrello;
import model.dao.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int prodotto = Integer.parseInt(req.getParameter("p"));
		int quantita = Integer.parseInt(req.getParameter("q"));
		if (ProdottoDAO.doRetrieveByKey(prodotto) != null && quantita >= 1) {
			Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
			// TODO: Sincronizzare con DB se loggato.
			if (carrello == null) {
				carrello = new Carrello();
			}
			carrello.add(prodotto, quantita);
			req.getSession().setAttribute("carrello", carrello);
			resp.getWriter().print("ok:" + carrello.getCount());
		} else {
			resp.getWriter().print("no");
		}
	}

}
