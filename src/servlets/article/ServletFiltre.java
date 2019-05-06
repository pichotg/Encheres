package servlets.article;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

public class ServletFiltre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletFiltre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Enchere> encheresFiltrees = new ArrayList<>();
		List<Enchere> encheresFinales = new ArrayList<>();
		EnchereDAO enchereDAO;
		HttpSession session = request.getSession();
		String contient = request.getParameter("contient");
		String categorie = request.getParameter("categorie");
		String achatsVentesRadio = request.getParameter("achatsVentesRadio");
		String checkBoxAchats1 = request.getParameter("checkBoxAchats1");
		String checkBoxAchats2 = request.getParameter("checkBoxAchats2");
		String checkBoxAchats3 = request.getParameter("checkBoxAchats3");
		String checkBoxVentes1 = request.getParameter("checkBoxVentes1");
		String checkBoxVentes2 = request.getParameter("checkBoxVentes2");
		String checkBoxVentes3 = request.getParameter("checkBoxVentes3");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int noUtilisateur = -1;

		enchereDAO = new EnchereDAO();

		try {
			if(utilisateur == null)
			{
				encheresFiltrees = enchereDAO.filtrageEncheresDeconnecte(contient, categorie);
				encheresFinales.addAll(encheresFiltrees);
			}
			else
			{
				encheresFiltrees = enchereDAO.filtrageEncheresConnecte(contient, categorie);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (utilisateur != null) {
			noUtilisateur = utilisateur.getNoUtilisateur();
			// Si l'utilisateur a décidé de filtrer ses ventes, il faut filtrer de nouveau
			// la liste filtrée par nom et catégorie
			if ("ventesRadio".equals(achatsVentesRadio)) {
				try {
					ArrayList<ArticleVendu> articles = ArticleVenduDAO.listerVenteEnCours(noUtilisateur, checkBoxVentes1,
							checkBoxVentes2, checkBoxVentes3);
					// On parcourt les articles filtrés par les checkbox
					for (ArticleVendu article : articles) {
						// On parcourt les encheres filtrées
						for (Enchere enchere : encheresFiltrees) {
							// On n'ajoute que celles qui correspondent aux deux filtres
							if (article.getNoArticle() == enchere.getNoArticle().getNoArticle()) {
								encheresFinales.add(enchere);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// Si l'utilisateur a décidé de filtrer ses achats, idem mais avec les encheres
			if ("achatsRadio".equals(achatsVentesRadio)) {
				try {
					ArrayList<Enchere> encheres = EnchereDAO.listerAchatEnCours(noUtilisateur, checkBoxAchats1,
							checkBoxAchats2, checkBoxAchats3);
					// On parcourt les articles filtrés par les checkbox
					for (Enchere enchere : encheres) {
						// On parcourt les encheres
						for (Enchere enchere2 : encheresFiltrees) {
							// On n'ajoute que celles qui correspondent aux deux filtres
							if (enchere.getNoArticle().getNoArticle() == enchere2.getNoArticle().getNoArticle()) {
								encheresFinales.add(enchere);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(achatsVentesRadio == null)
			{
				encheresFinales.addAll(encheresFiltrees);
			}
		}
		request.setAttribute("listeEncheres", encheresFinales);
		request.setAttribute("contient", contient);
		request.setAttribute("categorie", categorie);
		request.setAttribute("achatsVentesRadio", achatsVentesRadio);
		request.setAttribute("checkBoxAchats1", checkBoxAchats1);
		request.setAttribute("checkBoxAchats2", checkBoxAchats2);
		request.setAttribute("checkBoxAchats3", checkBoxAchats3);
		request.setAttribute("checkBoxVentes1", checkBoxVentes1);
		request.setAttribute("checkBoxVentes2", checkBoxVentes2);
		request.setAttribute("checkBoxVentes3", checkBoxVentes3);
		this.getServletContext().getRequestDispatcher("/liste_encheres.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
