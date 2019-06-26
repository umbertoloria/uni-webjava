package pages;

import model.bean.Prodotto;
import model.bean.Produttore;
import model.bean.Utente;
import model.container.RecensioneContainer;
import model.dao.OrdineDAO;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;
import model.dao.RecensioneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/prodotto")
public class ProdottoPage extends GenericPage {

	private Utente utente;
	private Prodotto prodotto;
	private Produttore produttore;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		utente = (Utente) req.getSession().getAttribute("utente");
		prodotto = ProdottoDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		if (prodotto == null) {
			produttore = null;
			return false;
		} else {
			produttore = ProduttoreDAO.doRetrieveByKey(prodotto.produttore);
			return true;
		}
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("prodotto", prodotto);
		req.setAttribute("produttore", produttore);
		req.getRequestDispatcher("prodotto.jsp").include(req, resp);
		req.removeAttribute("prodotto");
		req.removeAttribute("produttore");
		if (utente != null && OrdineDAO.himHasAlreadyBoughtThis(utente, prodotto) &&
				RecensioneDAO.doRetrieveByUtenteAndProdotto(utente.id, prodotto.id) == null) {
			req.setAttribute("enableAdd", true);
		}
		req.setAttribute("prodotto", prodotto);
		req.setAttribute("recensioni", Arrays.asList(RecensioneContainer.getFullInfo(
				RecensioneDAO.getFromProdotto(prodotto))));
		req.getRequestDispatcher("recensioni.jsp").include(req, resp);
		req.removeAttribute("prodotto");
		req.removeAttribute("recensioni");
		req.removeAttribute("enableAdd");
	}

}
