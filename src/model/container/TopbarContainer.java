package model.container;

import model.bean.Categoria;
import model.bean.Sottocategoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TopbarContainer implements Iterable<TopbarCategoriaContainer> {

	private List<TopbarCategoriaContainer> categorie = new ArrayList<>();

	public void add(Categoria categoria) {
		categorie.add(new TopbarCategoriaContainer(categoria));
	}

	public void add(Categoria categoria, Sottocategoria sottocategoria) {
		for (TopbarCategoriaContainer cat : categorie) {
			if (cat.relatedTo(categoria)) {
				cat.add(sottocategoria);
			}
		}
	}

	public Iterator<TopbarCategoriaContainer> iterator() {
		return categorie.iterator();
	}

}
