package model.container;

import model.Carrello;
import model.bean.CarrelloItem;
import model.bean.Prodotto;
import model.bean.Produttore;
import model.dao.ProdottoDAO;
import model.dao.ProduttoreDAO;

import java.util.*;

public class CarrelloContainer implements Iterable<CarrelloItemContainer> {

	private float total;
	private String serialize;
	private List<CarrelloItemContainer> list = new ArrayList<>();

	public CarrelloContainer(Carrello carrello) {
		this.total = carrello.getTotal();
		this.serialize = carrello.serialize();
		Map<Integer, Produttore> cache = new HashMap<>();
		for (CarrelloItem carrelloItem : carrello) {
			Prodotto prodotto = ProdottoDAO.doRetrieveByKey(carrelloItem.prodotto);
			assert prodotto != null;
			if (!cache.containsKey(prodotto.produttore)) {
				cache.put(prodotto.produttore, ProduttoreDAO.doRetrieveByKey(prodotto.produttore));
			}
			list.add(new CarrelloItemContainer(carrelloItem, prodotto, cache.get(prodotto.produttore)));
		}
	}

	public String serialize() {
		return serialize;
	}

	public float getTotal() {
		return total;
	}

	public Iterator<CarrelloItemContainer> iterator() {
		return list.iterator();
	}

}
