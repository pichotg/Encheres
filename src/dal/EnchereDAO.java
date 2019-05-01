package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import jdbc.JDBCTools;


public class EnchereDAO {

	private static final String INSERT_ENCHERE = "insert into encheres(no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";
	private static final String SELECT_ENCHERE = "select count(*) as nbre  from ENCHERES where no_utilisateur = ? and no_article = ?;";
	private static final String UPDATE_ENCHERE = "update encheres set date_enchere = ?, montant_enchere = ? where no_utilisateur = ? and no_article = ?;";
	private static final String SELECT_ALL = "select * from encheres";
	
	public static void ajouter(Enchere enchere) throws SQLException, ClassNotFoundException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		if (enchere.achatPossible()) {
			if (verifPremiereEnchere(enchere)) {
				try {
					cnx = JDBCTools.getConnection();
					rqt=cnx.prepareStatement(INSERT_ENCHERE);
					rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
					rqt.setInt(2, enchere.getNoArticle().getNoArticle());
					rqt.setDate(3, enchere.getDateEnchere());
					rqt.setInt(4, enchere.getMontant_enchere());
					rqt.executeUpdate();
				} finally {
					if (rqt!=null) rqt.close();
					if (cnx!=null) cnx.close();
				}
			} else {
				try {
					cnx = JDBCTools.getConnection();
					rqt=cnx.prepareStatement(UPDATE_ENCHERE);
					rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
					rqt.setInt(2, enchere.getNoArticle().getNoArticle());
					rqt.setDate(3, enchere.getDateEnchere());
					rqt.setInt(4, enchere.getMontant_enchere());
					rqt.executeUpdate();
				}finally {
					if (rqt!=null) rqt.close();
					if (cnx!=null) cnx.close();
				}
			}	
		}
	}

	public static Boolean verifPremiereEnchere(Enchere enchere) throws SQLException, ClassNotFoundException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs = null;
		Boolean bool = false;
		try {
			cnx = JDBCTools.getConnection();
			rqt=cnx.prepareStatement(SELECT_ENCHERE);
			rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
			rqt.setInt(2, enchere.getNoArticle().getNoArticle());
			rs = rqt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("nbre") == 0) {
					bool = true;
				}
			}
		} finally {
			if(rs != null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return bool;
	}

	public List<Enchere> selectAll() throws SQLException {
		List<Enchere> encheres = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		Connection conSelectAll = null;
		ResultSet rs = null;

		try {
			conSelectAll = JDBCTools.getConnection();
			preparedStatement = conSelectAll.prepareStatement(SELECT_ALL);
			rs = preparedStatement.executeQuery();
			ArticleVenduDAO articleDAO = new ArticleVenduDAO();
			UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			// On parcourt le r�sultat de la requ�te et on cr�e un objet Formation
			while (rs.next()) {
				ArticleVendu articleVendu = articleDAO.getArticleById(rs.getInt("no_article"));
				Utilisateur utilisateur = utilisateurDAO.getUtilisateurById(rs.getInt("no_utilisateur"));
				Enchere e = new Enchere(articleVendu, utilisateur, rs.getDate("date_enchere"), rs.getInt("montant_enchere"));
				encheres.add(e);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (rs != null) rs.close();
			if (preparedStatement != null)preparedStatement.close();
			if (conSelectAll != null)conSelectAll.close();
		}
		return encheres;
	}
}
