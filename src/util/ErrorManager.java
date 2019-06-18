package util;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ErrorManager {

	private HttpServletResponse resp;
	private HashMap<String, Object> notices = new HashMap<>();
	private Object message;
	private Object done;
	private Object redirect;

	public ErrorManager(HttpServletResponse resp) {
		this.resp = resp;
	}

	public void notice(String field, Object msg) {
		if (field != null && msg != null && !notices.containsKey(field)) {
			notices.put(field, msg);
		}
	}

	public void message(Object msg) {
		if (message == null) {
			message = msg;
		}
	}

	public void done(Object msg) {
		if (done == null) {
			done = msg;
		}
	}

	public void redirect(Object url) {
		if (redirect == null) {
			redirect = url;
		}
	}

	public void reload() {
		redirect("reload");
	}

	public void logout() {
		// TODO: evitare che nel json ci siano altre info e voglio solo fare logout veloce veloce.
		redirect("logout.jsp");
	}

	public void internalError() {
		message("Errore interno. Riprova più tardi.");
	}

	//	public void apply(PrintWriter out) {
	public void apply() throws IOException {
		JSONObject json = new JSONObject();
		if (!notices.isEmpty()) {
			JSONObject jsonNotices = new JSONObject();
			for (String field : notices.keySet()) {
				jsonNotices.put(field, notices.get(field));
			}
			json.put("notices", jsonNotices);
		}
		if (message != null) {
			json.put("message", message);
		}
		if (done != null) {
			json.put("done", done);
		}
		if (redirect != null) {
			json.put("redirect", redirect);
		}
		resp.setContentType("application/json");
		resp.getWriter().print(json.toString());
	}

}
