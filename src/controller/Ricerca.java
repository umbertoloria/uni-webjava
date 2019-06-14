package controller;

import model.dao.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ricerca")
public class Ricerca extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO: Usare JSON anche qui!
		resp.setContentType("text/html");
		String q = req.getParameter("q").trim();
		if (q.length() >= 3) {
			req.setAttribute("prodotti", ProdottoDAO.search(q));
			RequestDispatcher disp = req.getRequestDispatcher("parts/ProdottoListItem.jsp");
			disp.include(req, resp);
		}
	}

}
