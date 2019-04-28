package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Breadcrumb implements Iterable<BreadcrumbItem> {

	private List<BreadcrumbItem> items = new ArrayList<>();

	public void add(String nome, String link) {
		items.add(new BreadcrumbItem(nome, link));
	}

	public void add(String nome) {
		items.add(new BreadcrumbItem(nome));
	}

	public Iterator<BreadcrumbItem> iterator() {
		return items.iterator();
	}

}
