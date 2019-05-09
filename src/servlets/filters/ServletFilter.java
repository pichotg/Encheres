package servlets.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dal.ArticleVenduDAO;

/**
 * Servlet Filter implementation class ServletFilter
 */
@WebFilter("/ServletFilter")
public class ServletFilter implements Filter {
	
	private static final int CINQ_MINUTES = 5 * 60;
    /**
     * Default constructor. 
     */
    public ServletFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * Ce filtre permet de rafraichir la base de données à chaque chargement de page 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse responseHttp = (HttpServletResponse) response;
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		Cookie[] cookies;
		cookies = requestHttp.getCookies();
		
		if (cookies == null) {
			Cookie[] cook = new Cookie[1];
			Cookie ck = new Cookie("connexion", "-1");
			cook[0] = ck;
			cookies = cook;
			responseHttp.addCookie(ck);
		}
		for (Cookie ck : cookies) {
			if ("connexion".equals(ck.getName())) {
				ck.setMaxAge(CINQ_MINUTES);
				responseHttp.addCookie(ck);
			}
		}
		
		try {
			ArticleVenduDAO.refreshArticles();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
