package page;

import model.bean.Utente;
import model.dao.ProduttoreDAO;
import model.dao.SottocategoriaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/amministrazione")
public class AmministrazionePage extends GenericPage {

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		Utente utente = (Utente) req.getSession().getAttribute("utente");
		return utente != null && utente.admin();
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sottocategorie", SottocategoriaDAO.getAll());
		req.setAttribute("produttori", ProduttoreDAO.getAll());
		req.getRequestDispatcher("amministrazione.jsp").include(req, resp);
		req.removeAttribute("sottocategorie");
		req.removeAttribute("produttori");
	}

}
