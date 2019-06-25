package pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutPage extends GenericPage {

	void fillPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("utente");
		// TODO: Cancellare anche "age" relativo all'user, non ancora implementato...
		req.getRequestDispatcher("logout.jsp").include(req, resp);
	}

}
