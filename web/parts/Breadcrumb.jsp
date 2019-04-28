<%@ page import="util.Breadcrumb" %>
<%@ page import="util.BreadcrumbItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	Breadcrumb bc = (Breadcrumb) request.getAttribute("breadcrumb");
	out.println("<div id='breadcrumb'>");
	String[] indices = {"first", "second", "third", "fourth"};
	int index = 0;
	for (BreadcrumbItem bci : bc) {
		if (bci.link == null) {
			out.println("<a class='" + indices[index] + "'>" + bci.nome + "</a>");
		} else {
			out.println("<a class='" + indices[index] + "' href='" + bci.link + "'>" + bci.nome + "</a>");
		}
		index++;
	}
	out.println("</div>");
%>
