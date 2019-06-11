package model.bean;

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
	public void update(int prodotto, int quantita) {
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

}
