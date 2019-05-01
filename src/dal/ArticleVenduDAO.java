package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bo.ArticleVendu;
import bo.Utilisateur;
import jdbc.JDBCTools;

public class ArticleVenduDAO {
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie,etat_vente) VALUES(?,?,?,?,?,?,?,?)";
	private static final String LISTE_MES_VENTES_EN_COURS = "SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres > (SELECT GETDATE()) AND no_utilisateur =? AND etat_vente = 'vec'";
	private static final String LISTE_MES_VENTES_NON_DEBUTEES = "SELECT * FROM ARTICLES_VENDUS WHERE (select GETDATE()) BETWEEN date_debut_encheres AND date_fin_encheres AND no_utilisateur =? AND etat_vente = 'vnd'";
	private static final String LISTE_MES_VENTES_TERMINEES = "SELECT * FROM ARTICLES_VENDUS WHERE date_fin_encheres < (SELECT GETDATE()) AND no_utilisateur =? AND etat_vente = 'vet'";
	private static final String SELECT_BY_ID = "select * from ARTICLES_VENDUS where no_article = ?";

	/**
	 * Mise en vente d'un article
	 * 
	 * @param articleVendu
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void venteArticle(ArticleVendu articleVendu) throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;

		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(INSERT_ARTICLE);
			rqt.setString(1, articleVendu.getNomArticle());
			rqt.setString(4, articleVendu.getDescription());
			rqt.setTimestamp(2, new java.sql.Timestamp(articleVendu.getDateDebutEncheres().getTime()));
			rqt.setTimestamp(3, new java.sql.Timestamp(articleVendu.getDateFinEncheres().getTime()));
			rqt.setInt(5, articleVendu.getMiseAPrix());
			rqt.setInt(6, articleVendu.getUtilisateur().noUtilisateur);
			rqt.setInt(7, articleVendu.getCategorie());
			rqt.setString(8, "vnd");
			rqt.executeUpdate();
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}

	/**
	 * Liste les articles en vente pour l'utilisateur
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<ArticleVendu> listerVenteEnCours(int checkboxChoix)
			throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticlesAll = new ArrayList<ArticleVendu>();
		ArrayList<ArticleVendu> listeArticlesEnCours = new ArrayList<ArticleVendu>();
		ArrayList<ArticleVendu> listeArticlesNonDebut = new ArrayList<ArticleVendu>();
		ArrayList<ArticleVendu> listeArticlesTermi = new ArrayList<ArticleVendu>();

		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.createStatement();
			if (checkboxChoix == 1) {
				rs = rqt.executeQuery(LISTE_MES_VENTES_EN_COURS);
				int identifiantUtilisateur = -1;
				Utilisateur ut = null;
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVendu articleVendu;
				while (rs.next()) {
					identifiantUtilisateur = rs.getInt("no_utilisateur");
					ut = utDAO.getUtilisateurById(identifiantUtilisateur);
					articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("etat_vente"), rs.getString("description"), rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), null,
							rs.getInt("no_categorie"));
					// On set l'utilisateur
					articleVendu.setUtilisateur(ut);
					// On ajout l'article à la liste
					listeArticlesEnCours.add(articleVendu);
					listeArticlesAll.addAll(listeArticlesEnCours);
				}
			}
			if (checkboxChoix == 2) {
				rs = rqt.executeQuery(LISTE_MES_VENTES_NON_DEBUTEES);
				int identifiantUtilisateur = -1;
				Utilisateur ut = null;
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVendu articleVendu;
				while (rs.next()) {
					identifiantUtilisateur = rs.getInt("no_utilisateur");
					ut = utDAO.getUtilisateurById(identifiantUtilisateur);
					articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("etat_vente"), rs.getString("description"), rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), null,
							rs.getInt("no_categorie"));
					// On set l'utilisateur
					articleVendu.setUtilisateur(ut);
					// On ajout l'article à la liste
					listeArticlesNonDebut.add(articleVendu);
					listeArticlesAll.addAll(listeArticlesNonDebut);
				}
			}
			if (checkboxChoix == 3) {
				rs = rqt.executeQuery(LISTE_MES_VENTES_TERMINEES);
				int identifiantUtilisateur = -1;
				Utilisateur ut = null;
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVendu articleVendu;
				while (rs.next()) {
					identifiantUtilisateur = rs.getInt("no_utilisateur");
					ut = utDAO.getUtilisateurById(identifiantUtilisateur);
					articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("etat_vente"), rs.getString("description"), rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), null,
							rs.getInt("no_categorie"));
					// On set l'utilisateur
					articleVendu.setUtilisateur(ut);
					// On ajout l'article à la liste
					listeArticlesTermi.add(articleVendu);
					listeArticlesAll.addAll(listeArticlesTermi);
				}
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}

		return listeArticlesAll;
	}

	/**
	 * Get Article by no_article
	 * 
	 * @param id
	 * @return Utilisateur
	 * @throws SQLException
	 */
	public ArticleVendu getArticleById(int id) throws SQLException {
		ArticleVendu article = null;
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(SELECT_BY_ID);

			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(rs.getInt("no_utilisateur"));
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("etat_vente"), rs.getString("description"), rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						utilisateur, rs.getInt("no_categorie"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conSelect != null)
				conSelect.close();
		}
		return article;
	}
}
