package controller;

import model.bean.CartaCredito;
import model.bean.Utente;
import model.dao.CartaCreditoDAO;
import model.validator.CartaCreditoValidator;
import util.ErrorManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet_aggiungiCarta")
public class AggiungiCarta extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ErrorManager em = new ErrorManager(resp);

		Utente utente = (Utente) req.getSession().getAttribute("utente");
		if (utente == null) {
			em.logout();
			em.apply();
			return;
		}


		String numero, mese, annoStr, cvv, saldoStr;
		try {
			numero = req.getParameter("numero").trim();
			mese = req.getParameter("mese").trim();
			annoStr = req.getParameter("anno").trim();
			cvv = req.getParameter("cvv");
			saldoStr = req.getParameter("saldo");
		} catch (NumberFormatException | NullPointerException e) {
			em.logout();
			em.apply();
			return;
		}

		int anno;
		try {
			anno = Integer.parseInt(annoStr);
		} catch (NumberFormatException e) {
			em.notice(null, "Anno non valido");
			em.apply();
			return;
		}

		float saldo;
		try {
			saldo = Float.parseFloat(saldoStr);
		} catch (NumberFormatException e) {
			em.notice(null, "Il saldo deve essere un numero positivo");
			em.apply();
			return;
		}

		CartaCreditoValidator cartaValidator = new CartaCreditoValidator(numero, mese, anno, cvv, saldo);
		if (cartaValidator.wrongInput()) {
			em.notice(null, cartaValidator.numeroMsg);
			em.notice(null, cartaValidator.meseMsg);
			em.notice(null, cartaValidator.annoMsg);
			em.notice(null, cartaValidator.cvvMsg);
			em.notice(null, cartaValidator.saldoMsg);
		} else {
			CartaCredito carta = new CartaCredito(numero, mese, anno, cvv, saldo, utente.id);
			if (CartaCreditoDAO.doRetrieveByKey(numero) != null) {
				em.notice(null, "Esiste già una carta con questo numero");
			} else if (CartaCreditoDAO.doSave(carta)) {
				em.overlay("Carta inserita correttamente. Buona spesa!");
				em.reload();
			} else {
				em.overlay("Al momento non è possibile aggiungere carte di credito sul proprio account. " +
						"Riprova più tardi.");
			}
		}
		em.apply();
	}

}
