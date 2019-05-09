package servlets.enchere;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
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

public class ServletAjouterEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAjouterEnchere() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArticleVenduDAO articleVenduDao = new ArticleVenduDAO();
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleVendu articleVendu = null;
		try {
			articleVendu = articleVenduDao.getArticleById(noArticle);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Enchere enchereMax = EnchereDAO.getEnchereMaxByNoArticle(noArticle);
		Utilisateur utilisateurOld = enchereMax.getNoUtilisateur();
		int montantEnchereOld = enchereMax.getMontantEnchere();
		Utilisateur utilisateurNew = (Utilisateur) session.getAttribute("utilisateur");
		int montantEnchereNew = Integer.parseInt(request.getParameter("enchere"));
		Enchere enchere = new Enchere(articleVendu, utilisateurNew, new Date(), montantEnchereNew);

		/**
		 * Dans la couche BO, on ne peut pas vérifier l'enchere max car elle n'est pas dans les objets
		 * Du coup, on fait d'autres vérifications dans la couche BLL/Servlet
		 */
		if (enchere.achatPossible()) {
			// On vérifie maintenant que l'enchère est bien supérieure à celle courante
			try {
				if (enchere.getMontantEnchere() > EnchereDAO.verifEnchereSup(enchere))
				{
					EnchereDAO.ajouter(enchere);
					// On recrédite pour l'ancien enchérisseur
					UtilisateurDAO.utpdateCredit(utilisateurOld.getCredit() + montantEnchereOld, utilisateurOld.getNoUtilisateur());
					// On débite pour le nouvel enchérisseur
					UtilisateurDAO.utpdateCredit(utilisateurNew.getCredit() - montantEnchereNew, utilisateurNew.getNoUtilisateur());
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}
}
