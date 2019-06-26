package model.bean;

import model.Carrello;
import model.dao.ProdottoDAO;

import java.util.HashMap;
import java.util.Iterator;

public class Ordine implements Iterable<OrdineHasProdotto> {

	public final int id, utente;
	public final String destinazione, pagamento, momento;
	private HashMap<Integer, OrdineHasProdotto> prodotti = new HashMap<>();

	/** Ogni coppia di OrdineHasProdotto deve contenere valori differenti di 'prodotto' */
	public Ordine(int utente, String destinazione, String pagamento, Carrello carrello) {
		this.id = -1;
		this.utente = utente;
		this.destinazione = destinazione;
		this.pagamento = pagamento;
		this.momento = null;
		for (CarrelloItem item : carrello) {
			Prodotto p = ProdottoDAO.doRetrieveByKey(item.prodotto);
			assert p != null;
			prodotti.put(item.prodotto, new OrdineHasProdotto(item.prodotto, p.prezzo, item.quantita));
		}
	}

	/** Ogni coppia di OrdineHasProdotto deve contenere valori differenti di 'prodotto' */
	public Ordine(int id, int utente, String destinazione, String pagamento, String momento,
	              OrdineHasProdotto[] ordineHasProdotti) {
		this.id = id;
		this.utente = utente;
		this.destinazione = destinazione;
		this.pagamento = pagamento;
		this.momento = momento;
		for (OrdineHasProdotto item : ordineHasProdotti) {
			prodotti.put(item.prodotto, new OrdineHasProdotto(item.prodotto, item.prezzo, item.quantita));
		}
	}

	public Iterator<OrdineHasProdotto> iterator() {
		return prodotti.values().iterator();
	}

	public float getTotale() {
		float totale = 0;
		for (OrdineHasProdotto item : prodotti.values()) {
			totale += item.prezzo * item.quantita;
		}
		return totale;
	}

}
