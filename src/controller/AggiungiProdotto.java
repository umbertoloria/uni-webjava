package controller;

import model.bean.Immagine;
import model.dao.ImmagineDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/aggiungiProdotto")
public class AggiungiProdotto extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getSession().getAttribute("utente") == null) {
			return;
		}
		Part part = req.getPart("file");
		if (part != null) {
			Immagine immagine = new Immagine(part.getInputStream());
			if (ImmagineDAO.doSave(immagine)) {
				resp.getWriter().println("error");
			} else {
				resp.getWriter().println("done");
			}
		}
	}

}
