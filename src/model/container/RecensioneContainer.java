package model.container;

import model.bean.Recensione;
import model.bean.Utente;
import model.dao.UtenteDAO;

import java.util.HashMap;
import java.util.Map;

public class RecensioneContainer {

	public final int voto;
	public final String titolo, commento, username, momento;

	public RecensioneContainer(int voto, String titolo, String commento, String username, String momento) {
		this.voto = voto;
		this.titolo = titolo;
		this.commento = commento;
		this.username = username;
		this.momento = momento;
	}

	public static RecensioneContainer[] getFullInfo(Recensione[] recensioni) {
		Map<Integer, Utente> cache = new HashMap<>();
		RecensioneContainer[] ps = new RecensioneContainer[recensioni.length];
		for (int i = 0; i < recensioni.length; i++) {
			Recensione rec = recensioni[i];
			if (!cache.containsKey(rec.utente)) {
				cache.put(rec.utente, UtenteDAO.doRetrieveByKey(rec.utente));
			}
			Utente utente = cache.get(rec.utente);
			ps[i] = new RecensioneContainer(rec.voto, rec.titolo, rec.commento, utente.nome, rec.momento);
		}
		return ps;
	}

	public int getVoto() {
		return voto;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getCommento() {
		return commento;
	}

	public String getUsername() {
		return username;
	}

	public String getMomento() {
		return momento;
	}

}
