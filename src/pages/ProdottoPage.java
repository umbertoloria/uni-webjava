package pages;

import model.bean.Prodotto;
import model.bean.Produttore;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;
import util.Breadcrumb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/prodotto")
public class ProdottoPage extends GenericPage {

	private Prodotto prodotto;
	private Produttore produttore;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		prodotto = ProdottoDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		if (prodotto == null) {
			produttore = null;
			return false;
		} else {
			produttore = ProduttoreDAO.doRetrieveByKey(prodotto.produttore);
			return true;
		}
	}

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add(produttore.nome, "produttore?id=" + produttore.id);
		breadcrumb.add(prodotto.nome);
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("prodotto", prodotto);
		req.setAttribute("produttore", produttore);
		req.getRequestDispatcher("prodotto.jsp").include(req, resp);
		req.removeAttribute("prodotto");
		req.removeAttribute("produttore");
	}

}
