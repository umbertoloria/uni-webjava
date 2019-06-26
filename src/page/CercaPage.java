package page;

import model.container.ProdottoContainer;
import model.dao.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cerca")
public class CercaPage extends GenericPage {

	private String[] ss;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		String s = req.getParameter("q");
		if (s == null) {
			ss = null;
			return false;
		} else {
			ss = s.split(" ");
			for (int i = 0; i < ss.length; i++) {
				ss[i] = ss[i].trim();
			}
			return true;
		}
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("filtri", true);
		req.setAttribute("prodotti", ProdottoContainer.getFullInfo(ProdottoDAO.search(ss)));
		req.getRequestDispatcher("parts/Dashboard.jsp").include(req, resp);
		req.removeAttribute("filtri");
		req.removeAttribute("prodotti");
	}

}
