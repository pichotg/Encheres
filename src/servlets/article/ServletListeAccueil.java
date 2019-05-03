package servlets.article;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Enchere;
import bo.Utilisateur;
import dal.EnchereDAO;
import dal.UtilisateurDAO;

public class ServletListeAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeAccueil() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies;
		cookies = request.getCookies();
		HttpSession session = request.getSession();
		if(cookies == null)
		{
			Cookie[] cook = new Cookie[1];
			Cookie ck = new Cookie("connexion", "-1");
			cook[0] = ck;
			cookies = cook;
			response.addCookie(ck);
			response.sendRedirect("/liste_encheres.jsp");
		}
		for (Cookie ck : cookies) {
			if ("connexion".equals(ck.getName())) {
				UtilisateurDAO utDAO = new UtilisateurDAO();
				try {
					Utilisateur utilisateur = utDAO.getUtilisateurByPseudo(ck.getValue());
					session.setAttribute("utilisateur", utilisateur);
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		EnchereDAO enchereDAO;
		List<Enchere> encheres = new ArrayList<>();

		enchereDAO = new EnchereDAO();

		try {
			encheres = enchereDAO.selectEncheresEnCours();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listeEncheres", encheres);

		this.getServletContext().getRequestDispatcher("/liste_encheres.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
