package pages;

import model.bean.Categoria;
import model.bean.Sottocategoria;
import model.container.ProdottoContainer;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;
import model.dao.SottocategoriaDAO;
import util.Breadcrumb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sottocategoria")
public class SottocategoriaPage extends GenericPage {

	private Sottocategoria sottocategoria;
	private Categoria categoria;

	boolean canWatch(HttpServletRequest req, HttpServletResponse resp) {
		sottocategoria = SottocategoriaDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		if (sottocategoria == null) {
			categoria = null;
			return false;
		} else {
			categoria = CategoriaDAO.doRetrieveByKey(sottocategoria.categoria);
			return true;
		}
	}

	int getTopbarCategoria() {
		return sottocategoria.categoria;
	}

	int getTopbarSottocategoria() {
		return sottocategoria.id;
	}

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add(categoria.nome, "./categoria?id=" + categoria.id);
		breadcrumb.add(sottocategoria.nome);
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", sottocategoria.nome);
		req.setAttribute("prodotti", ProdottoContainer.getFullInfo(ProdottoDAO.getFromSottocategoria(sottocategoria)));
		req.getRequestDispatcher("parts/Dashboard.jsp").include(req, resp);
		req.removeAttribute("title");
		req.removeAttribute("prodotti");
	}

}
