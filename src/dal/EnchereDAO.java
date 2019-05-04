package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;
import jdbc.JDBCTools;

public class EnchereDAO {

	private static final String INSERT_ENCHERE = "insert into encheres(no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";
	private static final String SELECT_ENCHERE = "select count(*) as nbre  from ENCHERES where no_utilisateur = ? and no_article = ?;";
	private static final String ENCHERE_EXISTANTE = "select * from ENCHERES where no_utilisateur = ? and no_article = ?;";
	private static final String UPDATE_ENCHERE = "update encheres set date_enchere = ?, montant_enchere = ? where no_utilisateur = ? and no_article = ?;";
	private static final String SELECT_ENCHERE_EN_COURS = "SELECT *\r\n" + "FROM ENCHERES\r\n" + "INNER JOIN\r\n"
			+ "(SELECT no_article,MAX(montant_enchere) as enchereMax FROM ENCHERES GROUP BY no_article) as topscore \r\n"
			+ "ON ENCHERES.no_article = topscore.no_article\r\n" + "JOIN\r\n" + "ARTICLES_VENDUS\r\n"
			+ "ON ENCHERES.no_article = ARTICLES_VENDUS.no_article\r\n"
			+ "AND ENCHERES.montant_enchere = topscore.enchereMax \r\n"
			+ "AND date_enchere BETWEEN (SELECT MIN(date_debut_encheres) FROM ARTICLES_VENDUS) \r\n"
			+ "AND (SELECT MAX(date_fin_encheres) FROM ARTICLES_VENDUS)\r\n"
			+ "order by ARTICLES_VENDUS.date_fin_encheres ASC";
	private static final String SELECT_ENCHERE_EN_COURS_BY_ID = "SELECT * FROM ENCHERES "
			+ "WHERE date_enchere BETWEEN (SELECT MIN(date_debut_encheres) FROM ARTICLES_VENDUS) "
			+ "AND (SELECT MAX(date_fin_encheres) FROM ARTICLES_VENDUS) AND no_utilisateur =?";
	private static final String SELECT_ENCHERE_REMPORTEE = "SELECT * FROM ENCHERES JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article WHERE montant_enchere = prix_vente AND ENCHERES.no_utilisateur = ?";
	private static final String FILTRAGE_CATEGORIE = "SELECT *\r\n" + "FROM ENCHERES\r\n" + "INNER JOIN\r\n"
			+ "(SELECT no_article,MAX(montant_enchere) as enchereMax FROM ENCHERES GROUP BY no_article) as topscore \r\n"
			+ "ON ENCHERES.no_article = topscore.no_article\r\n" + "JOIN\r\n" + "ARTICLES_VENDUS\r\n"
			+ "ON ENCHERES.no_article = ARTICLES_VENDUS.no_article\r\n"
			+ "AND ENCHERES.montant_enchere = topscore.enchereMax \r\n"
			+ "AND date_enchere BETWEEN (SELECT MIN(date_debut_encheres) FROM ARTICLES_VENDUS) \r\n"
			+ "AND (SELECT MAX(date_fin_encheres) FROM ARTICLES_VENDUS)\r\n"
			+ "AND ENCHERES.no_article in (SELECT ARTICLES_VENDUS.no_article FROM ARTICLES_VENDUS WHERE no_categorie = ?)\r\n"
			+ "AND ENCHERES.no_article in (SELECT ARTICLES_VENDUS.no_article FROM ARTICLES_VENDUS WHERE nom_article like ?)\r\n"
			+ "order by ARTICLES_VENDUS.date_fin_encheres ASC";
	private static final String FILTRAGE_SANS_CATEGORIE = "SELECT *\r\n" + "FROM ENCHERES\r\n" + "INNER JOIN\r\n"
			+ "(SELECT no_article,MAX(montant_enchere) as enchereMax FROM ENCHERES GROUP BY no_article) as topscore \r\n"
			+ "ON ENCHERES.no_article = topscore.no_article\r\n" + "JOIN\r\n" + "ARTICLES_VENDUS\r\n"
			+ "ON ENCHERES.no_article = ARTICLES_VENDUS.no_article\r\n"
			+ "AND ENCHERES.montant_enchere = topscore.enchereMax \r\n"
			+ "AND date_enchere BETWEEN (SELECT MIN(date_debut_encheres) FROM ARTICLES_VENDUS) \r\n"
			+ "AND (SELECT MAX(date_fin_encheres) FROM ARTICLES_VENDUS)\r\n"
			+ "AND ENCHERES.no_article in (SELECT ARTICLES_VENDUS.no_article FROM ARTICLES_VENDUS WHERE nom_article like ?)\r\n"
			+ "order by ARTICLES_VENDUS.date_fin_encheres ASC";
	private static final String SELECT_ENCHERE_MAX = "SELECT MAX(montant_enchere) AS enchereMax FROM ENCHERES WHERE no_article = ?";

	/**
	 * Ajout d'une ench�re
	 * 
	 * @param enchere
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void ajouter(Enchere enchere) throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		java.sql.Date datSql = new java.sql.Date(enchere.getDateEnchere().getTime());
		if (verifPremiereEnchere(enchere)) {
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(INSERT_ENCHERE);
				rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
				rqt.setInt(2, enchere.getNoArticle().getNoArticle());
				rqt.setDate(3, datSql);
				rqt.setInt(4, enchere.getMontantEnchere());
				rqt.executeUpdate();

			} finally {
				if (rqt != null)
					rqt.close();
				if (cnx != null)
					cnx.close();
			}
		} else {
			try {
				cnx = JDBCTools.getConnection();
				rqt = cnx.prepareStatement(UPDATE_ENCHERE);
				rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
				rqt.setInt(2, enchere.getNoArticle().getNoArticle());
				rqt.setDate(3, datSql);
				rqt.setInt(4, enchere.getMontantEnchere());
				rqt.executeUpdate();
			} finally {
				if (rqt != null)
					rqt.close();
				if (cnx != null)
					cnx.close();
			}
		}
	}

	/**
	 * Verifie si une enchere existe déjà sinon insert de celle ci
	 * 
	 * @param enchere
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Boolean verifPremiereEnchere(Enchere enchere) throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Boolean bool = false;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_ENCHERE);
			rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
			rqt.setInt(2, enchere.getNoArticle().getNoArticle());
			rs = rqt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("nbre") == 0) {
					bool = true;
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
		return bool;
	}

	/**
	 * V�rifie si l'enchere est bien sup�rieure aux ench�res existantes
	 * 
	 * @param enchere
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int verifEnchereSup(Enchere enchere) throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int enchereMax = 0;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_ENCHERE_MAX);
			rqt.setInt(1, enchere.getNoArticle().getNoArticle());
			rs = rqt.executeQuery();
			if (rs.next()) {

				enchereMax = rs.getInt("enchereMax");
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
		return enchereMax;
	}

	/**
	 * Methode pour retourner les encheres en cours
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Enchere> selectEncheresEnCours() throws SQLException {
		List<Enchere> encheres = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		Connection conSelectAll = null;
		ResultSet rs = null;

		try {
			conSelectAll = JDBCTools.getConnection();
			preparedStatement = conSelectAll.prepareStatement(SELECT_ENCHERE_EN_COURS);
			rs = preparedStatement.executeQuery();
			ArticleVenduDAO articleDAO = new ArticleVenduDAO();
			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			// On parcourt le resultat de la requete et on cr�e les objets li�s �
			// l'ench�re
			while (rs.next()) {
				ArticleVendu articleVendu = articleDAO.getArticleById(rs.getInt("no_article"));
				Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(rs.getInt("no_utilisateur"));
				Enchere e = new Enchere(articleVendu, utilisateur, rs.getDate("date_enchere"),
						rs.getInt("montant_enchere"));
				encheres.add(e);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conSelectAll != null)
				conSelectAll.close();
		}
		return encheres;
	}

	/**
	 * Methode pour retourner les encheres en cours de l'utilisateur
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Enchere> selectEncheresUtilisateur(int idutilisateur) throws SQLException {
		List<Enchere> encheres = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(SELECT_ENCHERE_EN_COURS_BY_ID);
			preparedStatement.setInt(1, idutilisateur);
			rs = preparedStatement.executeQuery();
			ArticleVenduDAO articleDAO = new ArticleVenduDAO();
			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			// On parcourt le resultat de la requete et on cr�e les objets li�s �
			// l'ench�re
			while (rs.next()) {
				ArticleVendu articleVendu = articleDAO.getArticleById(rs.getInt("no_article"));
				Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(rs.getInt("no_utilisateur"));
				Enchere e = new Enchere(articleVendu, utilisateur, rs.getDate("date_enchere"),
						rs.getInt("montant_enchere"));
				encheres.add(e);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conSelect != null)
				conSelect.close();
		}
		return encheres;
	}

	/**
	 * Methode pour retourner les encheres remport�es de l'utilisateur
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Enchere> selectEncheresRemporteesUtilisateur(int idutilisateur) throws SQLException {
		List<Enchere> encheres = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(SELECT_ENCHERE_REMPORTEE);
			preparedStatement.setInt(1, idutilisateur);
			rs = preparedStatement.executeQuery();
			ArticleVenduDAO articleDAO = new ArticleVenduDAO();
			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			// On parcourt le resultat de la requete et on cr�e les objets li�s �
			// l'ench�re
			while (rs.next()) {
				ArticleVendu articleVendu = articleDAO.getArticleById(rs.getInt("no_article"));
				Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(rs.getInt("no_utilisateur"));
				Enchere e = new Enchere(articleVendu, utilisateur, rs.getDate("date_enchere"),
						rs.getInt("montant_enchere"));
				encheres.add(e);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conSelect != null)
				conSelect.close();
		}
		return encheres;
	}

	public List<Enchere> filtrageVenteEnCours(String contient, String categorie)
			throws SQLException, ClassNotFoundException {

		ArrayList<Enchere> encheres = new ArrayList<>();
		Connection conFiltre = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			conFiltre = JDBCTools.getConnection();

			// Si la cat�gorie est non vide, on prend la requ�te classique
			if (!Categorie.ALL.getName().equals(categorie) && categorie != null) {
				preparedStatement = conFiltre.prepareStatement(FILTRAGE_CATEGORIE);
				preparedStatement.setInt(1, Categorie.getNoByName(categorie));
				preparedStatement.setString(2, "%" + contient.trim() + "%");
			}
			// Sinon on prend la version avec toutes les catégories
			else if (Categorie.ALL.getName().equals(categorie)) {
				preparedStatement = conFiltre.prepareStatement(FILTRAGE_SANS_CATEGORIE);
				preparedStatement.setString(1, "%" + contient.trim() + "%");
			} else {
				preparedStatement = conFiltre.prepareStatement(SELECT_ENCHERE_EN_COURS);
			}

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int identifiantUtilisateur = rs.getInt("no_utilisateur");
				int identifiantArticle = rs.getInt("no_article");
				UtilisateurDAO utDAO = new UtilisateurDAO();
				ArticleVenduDAO avDAO = new ArticleVenduDAO();
				Utilisateur ut = utDAO.getUtilisateurById(identifiantUtilisateur);
				ArticleVendu av = avDAO.getArticleById(identifiantArticle);
				Enchere enchere = new Enchere(av, ut, rs.getDate("date_enchere"), rs.getInt("montant_enchere"));

				// On ajoute l'enchere � la liste
				encheres.add(enchere);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conFiltre != null)
				conFiltre.close();
		}

		return encheres;
	}

	/**
	 * Verification de l'existance d'une enchere pour un utilisateur sur un article
	 * donnée avec d'autres paramètres
	 * 
	 * @param enchere
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Enchere enchereExistante(int noUtilisateur, int noArticle)
			throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Enchere enchere = null;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(ENCHERE_EXISTANTE);
			rqt.setInt(1, noUtilisateur);
			rqt.setInt(2, noArticle);
			rs = rqt.executeQuery();
			if (rs.next()) {
				UtilisateurDAO utDAO = new UtilisateurDAO();
				Utilisateur ut = utDAO.getUtilisateurById(rs.getInt("no_utilisateur"));
				ArticleVenduDAO avDAO = new ArticleVenduDAO();
				ArticleVendu av = avDAO.getArticleById(rs.getInt("no_article"));
				enchere = new Enchere(av, ut, rs.getDate("date_enchere"), rs.getInt("montant_enchere"));
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
		return enchere;
	}

	/**
	 * V�rifie si l'enchere est bien sup�rieure aux ench�res existantes
	 * 
	 * @param enchere
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int getEnchereMax(int noArticle) throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int enchereMax = 0;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_ENCHERE_MAX);
			rqt.setInt(1, noArticle);
			rs = rqt.executeQuery();
			if (rs.next()) {

				enchereMax = rs.getInt("enchereMax");
			}
		} finally {
			if (rs != null)
				rs.close();
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
		return enchereMax;
	}
}
