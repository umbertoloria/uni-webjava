package pages;

import model.bean.Produttore;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;
import util.Breadcrumb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/produttore")
public class ProduttorePage extends GenericPage {

	private Produttore produttore;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		produttore = ProduttoreDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		return produttore != null;
	}

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add(produttore.nome);
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("prodotti", ProdottoDAO.getFromProduttore(produttore));
		req.setAttribute("produttore", produttore);
		req.getRequestDispatcher("produttore.jsp").include(req, resp);
		req.removeAttribute("prodotti");
		req.removeAttribute("produttore");
	}

}
