package controllers;

import model.Prodotto;
import dao.ProdottoDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ricerca")
public class Ricerca extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		Prodotto[] prodotti = ProdottoDAO.search(req.getParameter("q").trim());
		for (Prodotto prodotto : prodotti) {
			resp.getWriter().append(prodotto.makeList());
		}
	}

}
