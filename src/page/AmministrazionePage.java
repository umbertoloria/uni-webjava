package page;

import model.bean.Utente;
import model.container.OrdineContainer;
import model.dao.OrdineDAO;
import model.dao.ProduttoreDAO;
import model.dao.SottocategoriaDAO;
import model.dao.UtenteDAO;

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
		req.setAttribute("utenti", UtenteDAO.getAll());
		req.setAttribute("ordini", OrdineContainer.getFullInfo(OrdineDAO.getAll()));
		req.getRequestDispatcher("amministrazione.jsp").include(req, resp);
		req.removeAttribute("sottocategorie");
		req.removeAttribute("produttori");
		req.removeAttribute("utenti");
		req.removeAttribute("ordini");
	}

}
