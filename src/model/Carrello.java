package model;

import model.bean.CarrelloItem;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Carrello implements Iterable<CarrelloItem> {

	private HashMap<Integer, Integer> prodotti = new HashMap<>();

	/** Aggiunge al carrello una nuova quantità di un prodotto dato. */
	public void add(int prodotto, int quantita) {
		if (prodotti.containsKey(prodotto)) {
			quantita += prodotti.get(prodotto);
		}
		prodotti.put(prodotto, quantita);
	}

	/** Assegna una nuova quantità ad un prodotto dato, anche se non esiste. */
	public void set(int prodotto, int quantita) {
		prodotti.put(prodotto, quantita);
	}

	/** Rimuove un prodotto dal carrello. */
	public void drop(int prodotto) {
		prodotti.remove(prodotto);
	}

	/** Restituisce la quantità totale di prodotti nel carrello. */
	public int getCount() {
		int count = 0;
		for (Integer prodotto : prodotti.keySet()) {
			count += prodotti.get(prodotto);
		}
		return count;
	}

	public Iterator<CarrelloItem> iterator() {
		ArrayList<CarrelloItem> result = new ArrayList<>();
		for (Integer prodotto : prodotti.keySet()) {
			int quantita = prodotti.get(prodotto);
			result.add(new CarrelloItem(prodotto, quantita));
		}
		return result.iterator();
	}

	public String serialize() {
		StringBuilder serial = new StringBuilder();
		for (Integer prodotto : prodotti.keySet()) {
			int quantita = prodotti.get(prodotto);
			serial.append(prodotto);
			serial.append(':');
			serial.append(quantita);
			serial.append(';');
		}
		return serial.toString();
	}

	// TODO: Migliorare questa classe.
	public float getTotal() {
		float total = 0;
		for (Integer prodotto : prodotti.keySet()) {
			int quantita = prodotti.get(prodotto);
			Prodotto prodottoObj = ProdottoDAO.doRetrieveByKey(prodotto);
			assert prodottoObj != null;
			total += prodottoObj.prezzo * quantita;
		}
		return total;
	}

}
