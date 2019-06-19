package util;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ErrorManager {

	private static class Redirect {

		private final String url;
		private final int delay;

		Redirect(String url, int delay) {
			this.url = url;
			this.delay = delay;
		}

		JSONObject toJSON() {
			JSONObject json = new JSONObject();
			json.put("url", url);
			json.put("delay", delay);
			return json;
		}

	}

	private static class Notices {

		private final HashMap<String, Object> notices = new HashMap<>();

		void add(String field, Object msg) {
			if (msg != null) {
				if (field == null) {
					field = "null";
				} else if (field.equals("null")) {
					return;
				}
				if (!notices.containsKey(field)) {
					notices.put(field, msg);
				}
			}
		}

		JSONObject toJSON() {
			if (notices.isEmpty()) {
				return null;
			} else {
				JSONObject json = new JSONObject();
				for (String field : notices.keySet()) {
					json.put(field, notices.get(field));
				}
				return json;
			}
		}

	}

	private HttpServletResponse resp;
	private Notices notices = new Notices();
	private Object info;
	private Redirect redirect;
	private String overlay;
	private String notification;

	public ErrorManager(HttpServletResponse resp) {
		this.resp = resp;
	}

	public void notice(String field, Object msg) {
		notices.add(field, msg);
	}

	public void info(Object msg) {
		if (info == null) {
			info = msg;
		}
	}

	public void redirect(String url) {
		if (redirect == null) {
			redirect = new Redirect(url, 1500);
		}
	}

	public void reload() {
		redirect("reload");
	}

	public void logout() {
		redirect = new Redirect("logout.jsp", 0);
	}

	public void overlay(String msg) {
		if (overlay == null) {
			overlay = msg;
		}
	}

	public void notification(String msg) {
		if (notification == null) {
			notification = msg;
		}
	}

	public void apply() throws IOException {
		JSONObject json = new JSONObject();
		if (notices != null) {
			json.put("notices", notices.toJSON());
		}
		json.put("info", info);
		if (redirect != null) {
			json.put("redirect", redirect.toJSON());
		}
		json.put("overlay", overlay);
		json.put("notification", notification);
		resp.setContentType("application/json");
		resp.getWriter().print(json.toString());
	}

}
