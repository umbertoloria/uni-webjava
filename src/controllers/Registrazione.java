package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_registrazione")
public class Registrazione extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("plain/text");
		String nome = req.getParameter("nome").trim();
		String email = req.getParameter("email").trim();
		String password = req.getParameter("password").trim();
		String password2 = req.getParameter("password2").trim();
		if (nome.length() < 3 || nome.length() > 40) {
			resp.getWriter().append("message:Nome non valido: minimo 3 massimo 40 caratteri;");
		} else if (email.length() < 3 || email.length() > 40) {
			resp.getWriter().append("message:E-Mail non valida: minimo 3 massimo 40 caratteri;");
		} else if (password.length() < 3 || password.length() > 16) {
			resp.getWriter().append("message:Password non valida: minimo 3 massimo 16 caratteri;");
		} else if (!password.equals(password2)) {
			resp.getWriter().append("message:Le password fornite devono coincidere.;");
		} else {
			resp.getWriter().append("done:Registrazione effettuata;redirect:login.jsp");
		}
	}

}
