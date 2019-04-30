package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletDeconnexion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies;
		cookies = request.getCookies();
		for (Cookie ck : cookies) {
			if ("connexion".equals(ck.getName())) {
				ck.setValue("NULL");
				response.addCookie(ck);
			}
		}
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
