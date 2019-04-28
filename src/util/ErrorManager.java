package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ErrorManager {

	private HttpServletResponse resp;
	private ArrayList<String> usedFields = new ArrayList<>();

	public ErrorManager(HttpServletResponse resp) {
		this.resp = resp;
	}

	public void notice(String field, String msg) throws IOException {
		if (msg != null && !usedFields.contains(field)) {
			usedFields.add(field);
			resp.getWriter().append("notice:");
			resp.getWriter().append(field);
			resp.getWriter().append(":");
			resp.getWriter().append(msg);
			resp.getWriter().append(";");
		}
	}

	public void message(String msg) throws IOException {
		resp.getWriter().append("message:");
		resp.getWriter().append(msg);
		resp.getWriter().append(";");
	}

	public void done(String msg) throws IOException {
		resp.getWriter().append("done:");
		resp.getWriter().append(msg);
		resp.getWriter().append(";");
	}

	public void redirect(String msg) throws IOException {
		resp.getWriter().append("redirect:");
		resp.getWriter().append(msg);
		resp.getWriter().append(";");
	}

}
