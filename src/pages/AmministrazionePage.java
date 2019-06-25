package pages;

import model.bean.Utente;
import model.dao.IndirizzoDAO;
import util.Breadcrumb;

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

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add("Amministrazione");
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("indirizzi", IndirizzoDAO.getAllThoseOf(utente));
		req.getRequestDispatcher("impostazioni.jsp").include(req, resp);
		req.removeAttribute("indirizzi");
	}

}
