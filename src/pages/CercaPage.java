package pages;

import model.container.ProdottoContainer;
import model.dao.ProdottoDAO;
import util.Breadcrumb;

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

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add("Ricerca");
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("prodotti", ProdottoContainer.getFullInfo(ProdottoDAO.search(ss)));
		req.getRequestDispatcher("parts/Dashboard.jsp").include(req, resp);
		req.removeAttribute("prodotti");
	}

}
