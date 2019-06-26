package page;

import model.bean.Utente;
import model.dao.IndirizzoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/amministrazione")
public class AmministrazionePage extends GenericPage {

	private Utente utente;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		utente = (Utente) req.getSession().getAttribute("utente");
		return utente != null;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("indirizzi", IndirizzoDAO.getAllThoseOf(utente));
		req.getRequestDispatcher("amministrazione.jsp").include(req, resp);
		req.removeAttribute("indirizzi");
	}

}
