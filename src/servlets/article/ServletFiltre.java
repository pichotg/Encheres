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
		String filtrageVente = request.getParameter("filtrageVente");
		String filtrageAchat = request.getParameter("filtrageAchat");
		String checkBoxAchat1 = request.getParameter("checkBoxAchat1");
		String checkBoxAchat2 = request.getParameter("checkBoxAchat2");
		String checkBoxAchat3 = request.getParameter("checkBoxAchat3");
		String checkBoxVente1 = request.getParameter("checkBoxVente1");
		String checkBoxVente2 = request.getParameter("checkBoxVente2");
		String checkBoxVente3 = request.getParameter("checkBoxVente3");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int noUtilisateur = -1;

		enchereDAO = new EnchereDAO();

		try {
			encheresFiltrees = enchereDAO.filtrageVenteEnCours(contient, categorie);
			encheresFinales.addAll(encheresFiltrees);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (utilisateur != null) {
			noUtilisateur = utilisateur.getNoUtilisateur();
			// Si l'utilisateur a décidé de filtrer ses ventes, il faut filtrer de nouveau
			// la liste filtrée par nom et catégorie
			if (filtrageVente != null) {
				try {
					ArrayList<ArticleVendu> articles = ArticleVenduDAO.listerVenteEnCours(noUtilisateur, checkBoxVente1,
							checkBoxVente2, checkBoxVente3);
					// On parcourt les articles filtrés par les checkbox
					for (ArticleVendu article : articles) {
						// On parcourt les encheres
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
			if (filtrageAchat != null) {
				try {
					ArrayList<Enchere> encheres = EnchereDAO.listerAchatEnCours(noUtilisateur, checkBoxAchat1,
							checkBoxAchat2, checkBoxAchat3);
					// On parcourt les articles filtrés par les checkbox
					for (Enchere enchere : encheres) {
						// On parcourt les encheres
						for (Enchere enchere2 : encheresFiltrees) {
							// On n'ajoute que celles qui correspondent aux deux filtres
							if (enchere.getNoArticle().getNoArticle() == enchere2.getNoArticle().getNoArticle()
									&& enchere.getNoUtilisateur().getNoUtilisateur() == enchere2.getNoUtilisateur()
											.getNoUtilisateur()) {
								encheresFinales.add(enchere);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("listeEncheres", encheresFinales);
		request.setAttribute("contient", contient);
		request.setAttribute("categorie", categorie);
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
