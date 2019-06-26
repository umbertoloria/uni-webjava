package model.container;

import model.bean.OrdineHasProdotto;
import model.bean.Prodotto;
import model.bean.Produttore;

public class OrdineHasProdottoContainer {

	public final float prezzo;
	public final int quantita, prodotto_id, prodotto_immagine;
	public final String prodotto_nome, produttore_nome;

	public OrdineHasProdottoContainer(OrdineHasProdotto elem, Prodotto prodotto, Produttore produttore) {
		this.prezzo = elem.prezzo;
		this.quantita = elem.quantita;
		this.prodotto_id = prodotto.id;
		this.prodotto_nome = prodotto.nome;
		this.prodotto_immagine = prodotto.immagine;
		this.produttore_nome = produttore.nome;
	}

}
