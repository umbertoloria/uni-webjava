package pages;

import util.Breadcrumb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/carrello")
public class CarrelloPage extends GenericPage {

	Breadcrumb makeBreadcrumb(HttpServletRequest req, HttpServletResponse resp) {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.add("Carrello");
		return breadcrumb;
	}

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("carrello.jsp").include(req, resp);
	}

}
