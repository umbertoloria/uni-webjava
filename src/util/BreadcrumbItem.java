package util;

public final class BreadcrumbItem {

	public final String nome, link;

	public BreadcrumbItem(String nome, String link) {
		this.nome = nome;
		this.link = link;
	}

	public BreadcrumbItem(String nome) {
		this.nome = nome;
		this.link = null;
	}

}
