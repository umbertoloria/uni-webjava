package controller;

import model.bean.Immagine;
import model.dao.ImmagineDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/immagine")
public class VisualizzaImmagine extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Immagine immagine = ImmagineDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("id")));
		if (immagine != null) {
			assert immagine.data != null;
			resp.setContentType("image/jpeg");
			resp.getOutputStream().write(immagine.data);
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}
	}

}
