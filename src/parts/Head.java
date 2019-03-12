package parts;

public class Head {

	public static String put() {
		Resource[] resources = {
				new CSSResource("css/format.css"),
				new CSSResource("css/topbar.css"),
				new CSSResource("css/prodotto.css"),
				new JSResource("js/core.js"),
		};
		StringBuilder res = new StringBuilder();
		res.append("<!DOCTYPE html>");
		res.append("<html><head>");
		res.append("<title>MCommerce</title>");
		for (Resource resource : resources) {
			res.append(resource.makeHTML());
		}
		res.append("</head><body>");
		return res.toString();
	}

	static abstract class Resource {

		abstract String makeHTML();

	}

	static class CSSResource extends Resource {

		private String href;

		CSSResource(String href) {
			this.href = href;
		}

		String makeHTML() {
			return "<link rel='stylesheet' href='" + href + "'/>";
		}

	}

	static class JSResource extends Resource {

		private String href;

		JSResource(String href) {
			this.href = href;
		}

		String makeHTML() {
			return "<script type='text/javascript' src='" + href + "'></script>";
		}

	}

}
