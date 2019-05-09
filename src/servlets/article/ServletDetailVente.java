package servlets.article;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
import dal.EnchereDAO;
import dal.UtilisateurDAO;

public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDetailVente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère l'utilisateur courant
		Cookie[] cookies;
		cookies = request.getCookies();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = null;
		Utilisateur utilisateurMax = null;
		ArticleVendu article = null;
		ArticleVenduDAO articleDAO = new ArticleVenduDAO();
		Enchere enchere = null;
		Enchere enchereMax = null;
		int noArticle = -1;

		if (cookies == null) {
			Cookie[] cook = new Cookie[1];
			Cookie ck = new Cookie("connexion", "-1");
			cook[0] = ck;
			cookies = cook;
			response.addCookie(ck);
		}
		for (Cookie ck : cookies) {
			if ("connexion".equals(ck.getName())) {
				UtilisateurDAO utDAO = new UtilisateurDAO();
				try {
					utilisateur = utDAO.getUtilisateurByPseudo(ck.getValue());
					if (utilisateur != null) {
						session.setAttribute("utilisateur", utilisateur);
					}
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// On récupère l'article
		noArticle = Integer.parseInt(request.getParameter("noArticle"));
		try {
			article = articleDAO.getArticleById(noArticle);
			request.setAttribute("article", article);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// On vérifie si l'utilisateur a déjà fait une enchère pour le champ "Ma
		// proposition"
		try {
			if (utilisateur != null && article != null) {
				enchere = EnchereDAO.enchereExistante(utilisateur.getNoUtilisateur(), article.getNoArticle());
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (enchere != null) {
			request.setAttribute("montantEnchere", enchere.getMontantEnchere());
			request.setAttribute("dateEnchere", enchere.getDateEnchere());
			
		} else {
			request.setAttribute("montantEnchere", 0);
		}
		// On récupère le montant max des encheres sur l'article
		String utilisateurEnchereMax = "";
		try {
			enchereMax = EnchereDAO.getEnchereMaxByNoArticle(article.getNoArticle());
			UtilisateurDAO utDAO = new UtilisateurDAO();
			utilisateurMax = utDAO.getUtilisateurById(enchereMax.getNoUtilisateur().getNoUtilisateur());
			if (null != utilisateurMax) {
				utilisateurEnchereMax = utilisateurMax.getPseudo();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("enchere", request.getParameter("uneEnchere"));
		request.setAttribute("cheminEnchere", request.getParameter("cheminEnchere"));
		request.setAttribute("enchereMax", enchereMax);
		request.setAttribute("utilisateurMax", utilisateurEnchereMax);

		this.getServletContext().getRequestDispatcher("/WEB-INF/article/affichageVente.jsp").forward(request, response);
	}

}
