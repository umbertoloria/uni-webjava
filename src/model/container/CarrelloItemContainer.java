package model.container;

import model.bean.CarrelloItem;
import model.bean.Prodotto;
import model.bean.Produttore;

public class CarrelloItemContainer {

	public final int prodotto_id, quantita, prodotto_immagine;
	public final float prodotto_prezzo;
	public final String prodotto_nome, produttore_nome;

	public CarrelloItemContainer(CarrelloItem carrelloItem, Prodotto prodotto, Produttore produttore) {
		this.prodotto_id = prodotto.id;
		this.quantita = carrelloItem.quantita;
		this.prodotto_immagine = prodotto.immagine;
		this.prodotto_prezzo = prodotto.prezzo;
		this.prodotto_nome = prodotto.nome;
		this.produttore_nome = produttore.nome;
	}

}
