package model.container;

import model.bean.Categoria;
import model.bean.Sottocategoria;

import java.util.ArrayList;
import java.util.List;

public class TopbarCategoriaContainer {

	private Categoria categoria;
	private List<Sottocategoria> sottocategorie = new ArrayList<>();

	public TopbarCategoriaContainer(Categoria categoria) {
		this.categoria = categoria;
	}

	public void add(Sottocategoria sottocategoria) {
		sottocategorie.add(sottocategoria);
	}

	public boolean relatedTo(Categoria categoria) {
		return categoria.id == this.categoria.id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public List<Sottocategoria> getSottocategorie() {
		return sottocategorie;
	}

}
