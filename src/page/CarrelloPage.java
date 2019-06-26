package page;

import model.bean.Utente;
import model.dao.CartaCreditoDAO;
import model.dao.IndirizzoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/carrello")
public class CarrelloPage extends GenericPage {

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utente u = (Utente) req.getSession().getAttribute("utente");
		if (u != null) {
			req.setAttribute("indirizzi", IndirizzoDAO.getAllThoseOf(u));
			req.setAttribute("carte", CartaCreditoDAO.getAllThoseOf(u));
		}
		req.getRequestDispatcher("carrello.jsp").include(req, resp);
		req.removeAttribute("indirizzi");
		req.removeAttribute("carte");
	}

}
