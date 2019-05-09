package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.ArticleVendu;
import bo.EtatVente;
import bo.Utilisateur;
import jdbc.JDBCTools;

public class ArticleVenduDAO {
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie,etat_vente,path_image) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS set nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_utilisateur = ?, no_categorie = ?, etat_vente = ?, path_image = ? WHERE no_article = ?";
	private static final String LISTE_MES_VENTES_EN_COURS = "SELECT * FROM ARTICLES_VENDUS WHERE (select GETDATE()) BETWEEN date_debut_encheres AND date_fin_encheres AND no_utilisateur =? AND etat_vente = 'vec'";
	private static final String LISTE_MES_VENTES_NON_DEBUTEES = "SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres > (SELECT GETDATE()) AND no_utilisateur =? AND etat_vente = 'vnd'";
	private static final String LISTE_MES_VENTES_TERMINEES = "SELECT * FROM ARTICLES_VENDUS WHERE date_fin_encheres < (SELECT GETDATE()) AND no_utilisateur =? AND etat_vente = 'vet'";
	private static final String SELECT_BY_ID = "select * from ARTICLES_VENDUS where no_article = ?";
	private static final String DERNIER_ID = "select MAX(no_article) as numero_article from articles_vendus; ";

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
			rqt.setString(2, articleVendu.getDescription());
			rqt.setTimestamp(3, new java.sql.Timestamp(articleVendu.getDateDebutEncheres().getTime()));
			rqt.setTimestamp(4, new java.sql.Timestamp(articleVendu.getDateFinEncheres().getTime()));
			rqt.setInt(5, articleVendu.getMiseAPrix());
			rqt.setInt(6, articleVendu.getUtilisateur().getNoUtilisateur());
			rqt.setInt(7, articleVendu.getCategorie());
			rqt.setString(8, EtatVente.VND.getNom());
			rqt.setString(9, articleVendu.getPathImage());
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
	public static ArrayList<ArticleVendu> listerVenteEnCours(int noUtilisateur, String checkbox1, String checkbox2,
			String checkbox3) throws SQLException, ClassNotFoundException {
		Connection cnx1 = null, cnx2 = null, cnx3 = null;
		PreparedStatement rqt1 = null, rqt2 = null, rqt3 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null;
		ArrayList<ArticleVendu> listeArticlesAll = new ArrayList<ArticleVendu>();
		ArrayList<ArticleVendu> listeArticlesEnCours = new ArrayList<ArticleVendu>();
		ArrayList<ArticleVendu> listeArticlesNonDebut = new ArrayList<ArticleVendu>();
		ArrayList<ArticleVendu> listeArticlesTermi = new ArrayList<ArticleVendu>();

		try {
			if ("on".equals(checkbox1)) {
				cnx1 = JDBCTools.getConnection();
				rqt1 = cnx1.prepareStatement(LISTE_MES_VENTES_EN_COURS);
				rqt1.setInt(1, noUtilisateur);
				rs1 = rqt1.executeQuery();
				int identifiantUtilisateur = -1;
				Utilisateur ut = null;
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVendu articleVendu;
				while (rs1.next()) {
					identifiantUtilisateur = rs1.getInt("no_utilisateur");
					ut = utDAO.getUtilisateurById(identifiantUtilisateur);
					articleVendu = new ArticleVendu(rs1.getInt("no_article"), rs1.getString("nom_article"),
							rs1.getString("etat_vente"), rs1.getString("description"),
							rs1.getTimestamp("date_debut_encheres"), rs1.getTimestamp("date_fin_encheres"),
							rs1.getInt("prix_initial"), rs1.getInt("prix_vente"), null, rs1.getInt("no_categorie"),
							rs1.getString("path_image"));
					// On set l'utilisateur
					articleVendu.setUtilisateur(ut);
					// On ajout l'article � la liste
					listeArticlesEnCours.add(articleVendu);
				}
				// Doublons impossibles à cette étape
				listeArticlesAll.addAll(listeArticlesEnCours);
			}
			if ("on".equals(checkbox2)) {
				cnx2 = JDBCTools.getConnection();
				rqt2 = cnx2.prepareStatement(LISTE_MES_VENTES_NON_DEBUTEES);
				rqt2.setInt(1, noUtilisateur);
				rs2 = rqt2.executeQuery();
				int identifiantUtilisateur = -1;
				Utilisateur ut = null;
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVendu articleVendu;
				while (rs2.next()) {
					identifiantUtilisateur = rs2.getInt("no_utilisateur");
					ut = utDAO.getUtilisateurById(identifiantUtilisateur);
					articleVendu = new ArticleVendu(rs2.getInt("no_article"), rs2.getString("nom_article"),
							rs2.getString("etat_vente"), rs2.getString("description"),
							rs2.getTimestamp("date_debut_encheres"), rs2.getTimestamp("date_fin_encheres"),
							rs2.getInt("prix_initial"), rs2.getInt("prix_vente"), null, rs2.getInt("no_categorie"),
							rs2.getString("path_image"));
					// On set l'utilisateur
					articleVendu.setUtilisateur(ut);
					// On ajout l'article � la liste
					listeArticlesNonDebut.add(articleVendu);
				}
				// On retire les doublons même si théoriquement il n'y en aura pas
				for (ArticleVendu article : listeArticlesAll) {
					for (ArticleVendu article2 : listeArticlesNonDebut) {
						if (article.getNoArticle() == article2.getNoArticle()) {
							listeArticlesNonDebut.remove(article2);
							// On force le break car si on enlève l'objet, il ne faut pas réitérer dessus
							break;
						}
					}
				}
				listeArticlesAll.addAll(listeArticlesNonDebut);
			}
			if ("on".equals(checkbox3)) {
				cnx3 = JDBCTools.getConnection();
				rqt3 = cnx3.prepareStatement(LISTE_MES_VENTES_TERMINEES);
				rqt3.setInt(1, noUtilisateur);
				rs3 = rqt3.executeQuery();
				int identifiantUtilisateur = -1;
				Utilisateur ut = null;
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVendu articleVendu;
				while (rs3.next()) {
					identifiantUtilisateur = rs3.getInt("no_utilisateur");
					ut = utDAO.getUtilisateurById(identifiantUtilisateur);
					articleVendu = new ArticleVendu(rs3.getInt("no_article"), rs3.getString("nom_article"),
							rs3.getString("etat_vente"), rs3.getString("description"),
							rs3.getTimestamp("date_debut_encheres"), rs3.getTimestamp("date_fin_encheres"),
							rs3.getInt("prix_initial"), rs3.getInt("prix_vente"), null, rs3.getInt("no_categorie"),
							rs3.getString("path_image"));
					// On set l'utilisateur
					articleVendu.setUtilisateur(ut);
					// On ajout l'article � la liste
					listeArticlesTermi.add(articleVendu);
				}
				// On retire les doublons même si théoriquement il n'y en aura pas
				for (ArticleVendu article : listeArticlesAll) {
					for (ArticleVendu article2 : listeArticlesTermi) {
						if (article.getNoArticle() == article2.getNoArticle()) {
							listeArticlesTermi.remove(article2);
							// On force le break car si on enlève l'objet, il ne faut pas réitérer dessus
							break;
						}
					}
				}
				listeArticlesAll.addAll(listeArticlesTermi);
			}
		} finally {
			if (rs1 != null)
				rs1.close();
			if (rqt1 != null)
				rqt1.close();
			if (cnx1 != null)
				cnx1.close();
			if (rs2 != null)
				rs2.close();
			if (rqt2 != null)
				rqt2.close();
			if (cnx2 != null)
				cnx2.close();
			if (rs3 != null)
				rs3.close();
			if (rqt3 != null)
				rqt3.close();
			if (cnx3 != null)
				cnx3.close();
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
						rs.getString("etat_vente"), rs.getString("description"), rs.getTimestamp("date_debut_encheres"),
						rs.getTimestamp("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						utilisateur, rs.getInt("no_categorie"), rs.getString("path_image"));
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

	public int dernier_id() throws ClassNotFoundException, SQLException {
		int dernier_id = 0;
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(DERNIER_ID);
			rqt.executeQuery();
			rs = rqt.executeQuery();
			if (rs.next()) {
				dernier_id = rs.getInt("numero_article");
			}
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
		return dernier_id;
	}

	public static void refreshArticles() throws SQLException {
		Connection cnx = null;
		CallableStatement rqt = null;
		CallableStatement rqt2 = null;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareCall("{call encheres_terminees()}");
			rqt2 = cnx.prepareCall("{call encheres_a_debuter()}");
			rqt.execute();
			rqt2.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (rqt != null)
				rqt.close();
			if (rqt2 != null)
				rqt2.close();
			if (cnx != null)
				cnx.close();
		}
	}

	public static void updateArticle(ArticleVendu articleUpdate) throws SQLException {
		Connection conUpdate = null;
		PreparedStatement preparedStatement = null;
		try {
			conUpdate = JDBCTools.getConnection();
			preparedStatement = conUpdate.prepareStatement(UPDATE_ARTICLE);
			preparedStatement.setString(1, articleUpdate.getNomArticle());
			preparedStatement.setString(2, articleUpdate.getDescription());
			preparedStatement.setTimestamp(3, new java.sql.Timestamp(articleUpdate.getDateDebutEncheres().getTime()));
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(articleUpdate.getDateFinEncheres().getTime()));
			preparedStatement.setInt(5, articleUpdate.getMiseAPrix());
			preparedStatement.setInt(6, articleUpdate.getUtilisateur().getNoUtilisateur());
			preparedStatement.setInt(7, articleUpdate.getCategorie());
			preparedStatement.setString(8, EtatVente.VND.getNom());
			preparedStatement.setString(9, articleUpdate.getPathImage());
			preparedStatement.setInt(10, articleUpdate.getNoArticle());
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
			if (conUpdate != null)
				conUpdate.close();
		}
	}
}
