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
		JSONObject result = new JSONObject();
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		result.put("cart_count", carrello != null ? carrello.getCount() : 0);
		result.put("cart_serial", carrello != null ? carrello.serialize() : "");
		resp.getWriter().println(result.toString());
	}

}
