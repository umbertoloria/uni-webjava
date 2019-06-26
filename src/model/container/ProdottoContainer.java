package model.container;

import model.bean.Prodotto;
import model.bean.Produttore;
import model.dao.ProduttoreDAO;

import java.util.HashMap;
import java.util.Map;

public class ProdottoContainer {

	public final int prodotto_id, prodotto_immagine, produttore_id;
	public final String prodotto_nome, produttore_nome;
	public final float prodotto_prezzo;

	public ProdottoContainer(int prodotto_id, String prodotto_nome, float prodotto_prezzo,
	                         int prodotto_immagine, int produttore_id,
	                         String produttore_nome) {
		this.prodotto_id = prodotto_id;
		this.prodotto_nome = prodotto_nome;
		this.prodotto_prezzo = prodotto_prezzo;
		this.prodotto_immagine = prodotto_immagine;
		this.produttore_id = produttore_id;
		this.produttore_nome = produttore_nome;
	}

	public static ProdottoContainer[] getFullInfo(Prodotto[] prodotti) {
		Map<Integer, Produttore> cache = new HashMap<>();
		ProdottoContainer[] ps = new ProdottoContainer[prodotti.length];
		for (int i = 0; i < prodotti.length; i++) {
			Prodotto prodot = prodotti[i];
			if (!cache.containsKey(prodot.produttore)) {
				cache.put(prodot.produttore, ProduttoreDAO.doRetrieveByKey(prodot.produttore));
			}
			Produttore produt = cache.get(prodot.produttore);
			ps[i] = new ProdottoContainer(prodot.id, prodot.nome, prodot.prezzo, prodot.immagine, produt.id,
					produt.nome);
		}
		return ps;
	}

	public static ProdottoContainer[] getInfoWith(Prodotto[] prodotti, Produttore produttore) {
		ProdottoContainer[] ps = new ProdottoContainer[prodotti.length];
		for (int i = 0; i < prodotti.length; i++) {
			ps[i] = new ProdottoContainer(prodotti[i].id, prodotti[i].nome, prodotti[i].prezzo, prodotti[i].immagine,
					produttore.id, produttore.nome);
		}
		return ps;
	}

}
