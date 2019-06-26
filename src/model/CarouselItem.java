package model;

public class CarouselItem {

	public final String url, immagine, testo;

	public CarouselItem(String url, String immagine, String testo) {
		this.url = url;
		this.immagine = immagine;
		this.testo = testo;
	}

	public String getUrl() {
		return url;
	}

	public String getImmagine() {
		return immagine;
	}

	public String getTesto() {
		return testo;
	}

}
