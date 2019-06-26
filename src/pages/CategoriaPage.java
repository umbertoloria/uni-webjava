package pages;

import model.bean.Categoria;
import model.container.ProdottoContainer;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;
import model.dao.SottocategoriaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/categoria")
public class CategoriaPage extends GenericPage {

	private Categoria categoria;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		categoria = CategoriaDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		return categoria != null;
	}

	int getTopbarCategoria() {
		return categoria.id;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("sottocategorie", SottocategoriaDAO.getAllThoseOf(categoria));
		req.getRequestDispatcher("parts/Mosaic.jsp").include(req, resp);
		req.removeAttribute("sottocategorie");
		req.setAttribute("title", categoria.nome);
		req.setAttribute("prodotti", ProdottoContainer.getFullInfo(ProdottoDAO.getFromCategoria(categoria)));
		req.getRequestDispatcher("parts/Dashboard.jsp").include(req, resp);
		req.removeAttribute("title");
		req.removeAttribute("prodotti");
	}

}
