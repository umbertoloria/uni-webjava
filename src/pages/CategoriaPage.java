package pages;

import model.bean.Categoria;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;
import model.dao.SottocategoriaDAO;
import util.Breadcrumb;

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

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add(categoria.nome);
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("categoria", categoria);
		req.setAttribute("sottocategorie", SottocategoriaDAO.getAllThoseOf(categoria));
		req.setAttribute("prodotti", ProdottoDAO.getFromCategoria(categoria));
		req.getRequestDispatcher("categoria.jsp").include(req, resp);
		req.removeAttribute("categoria");
		req.removeAttribute("sottocategorie");
		req.removeAttribute("prodotti");
	}

}
