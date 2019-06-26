package controller;

import model.bean.*;
import model.dao.ImmagineDAO;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;
import model.dao.SottocategoriaDAO;
import model.validator.AggiungiProdottoValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet("/servlet_aggiungiProdotto")
public class AggiungiProdotto extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		Utente u = (Utente) req.getSession().getAttribute("utente");
		if (u == null || !u.admin()) {
			return;
		}

		String nome, descrizione, prezzo;
		Sottocategoria sottocategoria;
		Produttore produttore;
		InputStream immagine;
		try {
			nome = req.getParameter("nome");
			sottocategoria = SottocategoriaDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("sottocategoria")));
			produttore = ProduttoreDAO.doRetrieveByKey(Integer.parseInt(req.getParameter("produttore")));
			descrizione = req.getParameter("descrizione");
			prezzo = req.getParameter("prezzo").trim();
			immagine = req.getPart("file").getInputStream();
			if (sottocategoria == null || produttore == null) {
				throw new NullPointerException();
			}
		} catch (NumberFormatException | NullPointerException e) {
			resp.sendRedirect("amministrazione");
			return;
		}

		AggiungiProdottoValidator validator = new AggiungiProdottoValidator(nome, descrizione, prezzo);
		if (validator.wrongInput()) {
			resp.getWriter().println("Errore nome: " + validator.nomeMsg);
			resp.getWriter().println("Errore descrizione: " + validator.descrizioneMsg);
			resp.getWriter().println("Errore prezzo: " + validator.prezzoMsg);
		} else {
			float realPrezzo = Float.parseFloat(prezzo);
			if (immagine.available() > 0) {
				Immagine img = ImmagineDAO.doSave(new Immagine(immagine));
				if (img == null) {
					resp.sendRedirect("amministrazione");
				} else {
					Prodotto prodotto = ProdottoDAO.doSave(new Prodotto(sottocategoria.id, nome, produttore.id,
							realPrezzo, img.id, descrizione));
					if (prodotto == null) {
						resp.sendRedirect("amministrazione");
					} else {
						resp.sendRedirect("prodotto?id=" + prodotto.id);
					}
				}
			} else {
				resp.sendRedirect("amministrazione");
			}
		}
	}

}
