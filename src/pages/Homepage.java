package pages;

import model.Carousel;
import model.bean.Sottocategoria;
import model.container.ProdottoContainer;
import model.dao.ProdottoDAO;
import model.dao.SottocategoriaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class HomePage extends GenericPage {

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Carousel carousel = new Carousel();
		for (Sottocategoria st : SottocategoriaDAO.getFirstMoreProvided(3)) {
			carousel.add("sottocategoria?id=" + st.id, "immagine?id=" + st.immagine, st.nome);
		}
		req.setAttribute("carousel", carousel);
		req.getRequestDispatcher("parts/Carousel.jsp").include(req, resp);
		req.removeAttribute("carousel");
		req.setAttribute("title", "Top 10");
		req.setAttribute("prodotti", ProdottoContainer.getFullInfo(ProdottoDAO.getAll()));
		req.getRequestDispatcher("parts/Dashboard.jsp").include(req, resp);
		req.removeAttribute("title");
		req.removeAttribute("prodotti");
	}

}
