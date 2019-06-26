package page;

import model.bean.Categoria;
import model.bean.Sottocategoria;
import model.dao.CategoriaDAO;
import model.dao.SottocategoriaDAO;
import util.TopbarContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class GenericPage extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		if (!canWatch(req, resp)) {
			resp.sendRedirect("./");
			return;
		}
		// HEAD
		req.getRequestDispatcher("parts/Head.jsp").include(req, resp);
		// TOPBAR
		TopbarContainer topbar = new TopbarContainer();
		for (Categoria categoria : CategoriaDAO.getAll()) {
			topbar.add(categoria);
			for (Sottocategoria sottocategoria : SottocategoriaDAO.getAllThoseOf(categoria)) {
				topbar.add(categoria, sottocategoria);
			}
		}
		req.setAttribute("topbar_categorie_data", topbar);
		if (getTopbarCategoria() >= 0) {
			req.setAttribute("topbar_categoria", getTopbarCategoria());
		}
		if (getTopbarSottocategoria() >= 0) {
			req.setAttribute("topbar_sottocategoria", getTopbarSottocategoria());
		}
		req.getRequestDispatcher("parts/Topbar.jsp").include(req, resp);
		req.removeAttribute("topbar_categorie_data");
		req.removeAttribute("topbar_categoria");
		req.removeAttribute("topbar_sottocategoria");
		fillPage(req, resp);
		// FOOTER
		req.getRequestDispatcher("parts/Footer.jsp").include(req, resp);
	}

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return true;
	}

	int getTopbarCategoria() {
		return -1;
	}

	int getTopbarSottocategoria() {
		return -1;
	}

	abstract void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
