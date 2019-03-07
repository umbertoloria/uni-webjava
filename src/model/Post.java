package model;

import database.DB;
import database.Record;
import database.Table;

import java.util.ArrayList;

public class Post {

	private int id;
	private String titolo, testo;

	public Post(int id) {
		Table t = DB.query("SELECT * FROM posts WHERE id = '" + id + "'");
		if (t == null) {
			return;
		}
		Record r = t.get(0);
		this.id = (int) r.get(0);
		titolo = (String) r.get(1);
		testo = (String) r.get(2);
	}

	public int getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getTesto() {
		return testo;
	}

	public static Post[] getPosts() {
		ArrayList<Post> posts = new ArrayList<>();
		Table t = DB.query("SELECT id FROM posts ORDER BY titolo");
		for (Record record : t) {
			posts.add(new Post((int) record.get(0)));
		}
		return posts.toArray(new Post[0]);
	}

	public String makeHTML() {
		StringBuilder result = new StringBuilder();
		result.append("<div>");
		result.append("<header>");
		result.append(titolo);
		result.append("</header>");
		result.append("<p>");
		result.append(testo);
		result.append("</p>");
		result.append("</div>");
		return result.toString();
	}
}
