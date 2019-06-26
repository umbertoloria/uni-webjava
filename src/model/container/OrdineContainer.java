package model.container;

import model.bean.*;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;
import model.dao.UtenteDAO;

import java.util.*;

public class OrdineContainer implements Iterable<OrdineHasProdottoContainer> {

	private Utente utente;
	private float totale;
	private String destinazione, pagamento, momento;
	private List<OrdineHasProdottoContainer> list = new ArrayList<>();

	public OrdineContainer(Ordine ordine, Utente utente) {
		if (ordine.utente == utente.id) {
			this.utente = utente;
		} else {
			this.utente = UtenteDAO.doRetrieveByKey(ordine.utente);
		}
		this.totale = ordine.getTotale();
		this.destinazione = ordine.destinazione;
		this.pagamento = ordine.pagamento;
		this.momento = ordine.momento;
		Map<Integer, Produttore> cache = new HashMap<>();
		for (OrdineHasProdotto ordineItem : ordine) {
			Prodotto prodotto = ProdottoDAO.doRetrieveByKey(ordineItem.prodotto);
			assert prodotto != null;
			if (!cache.containsKey(prodotto.produttore)) {
				cache.put(prodotto.produttore, ProduttoreDAO.doRetrieveByKey(prodotto.produttore));
			}
			list.add(new OrdineHasProdottoContainer(ordineItem, prodotto, cache.get(prodotto.produttore)));
		}
	}

	public String getDestinazione() {
		return destinazione;
	}

	public String getPagamento() {
		return pagamento;
	}

	public String getMomento() {
		return momento;
	}

	public float getTotale() {
		return totale;
	}

	public Iterator<OrdineHasProdottoContainer> iterator() {
		return list.iterator();
	}

	public static OrdineContainer[] getFullInfo(Ordine[] ordini) {
		OrdineContainer[] ordiniContainers = new OrdineContainer[ordini.length];
		Map<Integer, Utente> cache = new HashMap<>();
		for (int i = 0; i < ordini.length; i++) {
			if (!cache.containsKey(ordini[i].utente)) {
				cache.put(ordini[i].utente, UtenteDAO.doRetrieveByKey(ordini[i].utente));
			}
			ordiniContainers[i] = new OrdineContainer(ordini[i], cache.get(ordini[i].utente));
		}
		return ordiniContainers;
	}

	public Utente getUtente() {
		return utente;
	}

}
