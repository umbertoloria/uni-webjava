package model.bean;

import java.io.InputStream;

public class Immagine {

	public final int id;
	public final byte[] data;
	public final InputStream stream;

	public Immagine(int id, byte[] data) {
		this.id = id;
		this.data = data;
		this.stream = null;
	}

	public Immagine(InputStream stream) {
		this.id = -1;
		this.data = null;
		this.stream = stream;
	}

}
