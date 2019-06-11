package util;

import model.bean.Carrello;
import model.bean.CarrelloItem;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class Jsonfy {

	public static JSONArray carrello(Carrello carrello) {
		JSONArray result = new JSONArray();
		for (CarrelloItem carrelloItem : carrello) {
			JSONObject orizzonte = new JSONObject();
			orizzonte.put("utente", carrelloItem.utente);
			orizzonte.put("prodotto", carrelloItem.prodotto);
			orizzonte.put("quantita", carrelloItem.quantita);
			Prodotto p = ProdottoDAO.doRetrieveByKey(carrelloItem.prodotto);
			assert p != null;
			orizzonte.put("totale", Formats.euro(p.prezzo * carrelloItem.quantita));
			result.put(orizzonte);
		}
		return result;
	}

}
