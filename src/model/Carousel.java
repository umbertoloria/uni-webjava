package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carousel implements Iterable<CarouselItem> {

	private List<CarouselItem> items = new ArrayList<>();

	public void add(String url, String immagine, String testo) {
		items.add(new CarouselItem(url, immagine, testo));
	}

	public Iterator<CarouselItem> iterator() {
		return items.iterator();
	}

}
