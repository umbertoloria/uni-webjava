package util;

import model.bean.Categoria;
import model.bean.Sottocategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TopbarContainer implements Iterable<TopbarCategoria> {

	private List<TopbarCategoria> categorie = new ArrayList<>();

	public void add(Categoria categoria) {
		categorie.add(new TopbarCategoria(categoria));
	}

	public void add(Categoria categoria, Sottocategoria sottocategoria) {
		for (TopbarCategoria cat : categorie) {
			if (cat.relatedTo(categoria)) {
				cat.add(sottocategoria);
			}
		}
	}

	public Iterator<TopbarCategoria> iterator() {
		return categorie.iterator();
	}

}
