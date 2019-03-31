package model;

import aspects.Box;
import aspects.List;
import dao.SottocategoriaDAO;
import utils.Formats;

public final class Prodotto implements Box, List {

	public final int id, sottocategoria;
	public final String nome;
	public final int produttore;
	public final float prezzo;
	public final String immagine, descrizione;

	public Prodotto(int id, int sottocategoria, String nome, int produttore, float prezzo, String immagine,
	                String descrizione) {
		this.id = id;
		this.sottocategoria = sottocategoria;
		this.nome = nome;
		this.produttore = produttore;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.descrizione = descrizione;
	}

	public String makeBox() {
		String part = "<a class='prebox' href='sottocategoria.jsp?id=" + sottocategoria + "'>" +
				SottocategoriaDAO.doRetrieveByKey(sottocategoria).nome + "</a>";
		// TODO: Inserire nome produttore.
		return "<div>" + part + "<a class='box' href='prodotto.jsp?id=" + id + "'> " +
				"<header>" + nome + "</header>" +
				"<img src='" + immagine + "'/>" +
				"<span> " + Formats.euro(prezzo) + "</span></a>" +
				"</div>";
	}

	public String makeList() {
		return "<a href='prodotto.jsp?id=" + id + "'>" +
				"<img src='" + immagine + "'/>" +
				"<span>" + nome + "</span></a>";
	}

}
