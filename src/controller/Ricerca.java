package controller;

import model.container.ProdottoContainer;
import model.dao.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ricerca")
public class Ricerca extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html");
		String q = req.getParameter("q").trim();
		if (q.length() >= 3) {
			String[] qs = q.split(" ");
			for (int i = 0; i < qs.length; i++) {
				qs[i] = qs[i].trim();
			}
			req.setAttribute("prodotti", ProdottoContainer.getFullInfo(ProdottoDAO.search(qs)));
			req.getRequestDispatcher("parts/ProdottoListItem.jsp").include(req, resp);
		}
	}

}
