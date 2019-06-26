package model.container;

import model.bean.Ordine;
import model.bean.OrdineHasProdotto;
import model.bean.Prodotto;
import model.bean.Produttore;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;

import java.util.*;

public class OrdineContainer implements Iterable<OrdineHasProdottoContainer> {

	private float totale;
	private String destinazione, pagamento, momento;
	private List<OrdineHasProdottoContainer> list = new ArrayList<>();

	public OrdineContainer(Ordine ordine) {
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

}
