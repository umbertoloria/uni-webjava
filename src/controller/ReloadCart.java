package controller;

import model.Carrello;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reloadCart")
public class ReloadCart extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		if (carrello != null) {
			JSONObject result = new JSONObject();
			result.put("count", carrello.getCount());
			result.put("serial", carrello.serialize());
			resp.getWriter().println(result.toString());
		} else {
			resp.getWriter().println("{}");
		}
	}

}
