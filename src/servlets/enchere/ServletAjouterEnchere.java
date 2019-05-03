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
import dal.EnchereDAO;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArticleVendu article = (ArticleVendu) session.getAttribute("article");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
		Enchere enchere = new Enchere(article, utilisateur, new Date(), montantEnchere);

		/**
		 * Dans la couche BO, on ne peut pas vérifier l'enchere max car elle n'est pas dans les objets
		 * Du coup, on fait d'autres vérifications dans la couche BLL/Servlet
		 */
		if (enchere.achatPossible()) {
			// On vérifie maintenant que l'enchère est bien supérieure à celle courante
			try {
				if (enchere.getMontant_enchere() > EnchereDAO.verifEnchereSup(enchere))
					EnchereDAO.ajouter(enchere);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
