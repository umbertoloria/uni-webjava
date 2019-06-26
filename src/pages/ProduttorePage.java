package pages;

import model.bean.Produttore;
import model.container.ProdottoContainer;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/produttore")
public class ProduttorePage extends GenericPage {

	private Produttore produttore;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		produttore = ProduttoreDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		return produttore != null;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", produttore.nome);
		req.setAttribute("prodotti", ProdottoContainer.getInfoWith(ProdottoDAO.getFromProduttore(produttore),
				produttore));
		req.getRequestDispatcher("parts/Dashboard.jsp").include(req, resp);
		req.removeAttribute("title");
		req.removeAttribute("prodotti");
	}

}
