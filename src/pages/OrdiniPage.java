package pages;

import model.bean.Utente;
import model.dao.OrdineDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ordini")
public class OrdiniPage extends GenericPage {

	private Utente utente;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		utente = (Utente) req.getSession().getAttribute("utente");
		return utente != null;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("ordini", OrdineDAO.getAllThoseOf(utente));
		req.getRequestDispatcher("ordini.jsp").include(req, resp);
		req.removeAttribute("ordini");
	}

}
