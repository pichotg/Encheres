package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bo.ArticleVendu;
import jdbc.JDBCTools;

public class ArticleVenduDAO {
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie,etat_vente) VALUES(?,?,?,?,?,?,?,?)";
	private static final String LISTE_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente = 'EC'";
	/**
	 * Mise en vente d'un article
	 * @param articleVendu
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void venteArticle(ArticleVendu articleVendu) throws SQLException, ClassNotFoundException{
	Connection cnx=null;
	PreparedStatement rqt=null;

	try{
		cnx=JDBCTools.getConnection();
		rqt=cnx.prepareStatement(INSERT_ARTICLE);
		rqt.setString(1, articleVendu.getNomArticle());
		rqt.setString(4, articleVendu.getDescription());
		rqt.setDate(2, new java.sql.Date(articleVendu.getDateDebutEncheres().getTime()));
		rqt.setDate(3, new java.sql.Date(articleVendu.getDateFinEncheres().getTime()));
		rqt.setInt(5, articleVendu.getMiseAPrix());
		rqt.setInt(6, articleVendu.getUtilisateur().noUtilisateur);
		rqt.setString(7, articleVendu.getCategorie());
		rqt.setString(8, "EV");
		rqt.executeUpdate();
	}finally{
		if (rqt!=null) rqt.close();
		if (cnx!=null) cnx.close();
	}
}
	/**
	 * Liste les articles en vente
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<ArticleVendu> lister() throws SQLException, ClassNotFoundException{
		Connection cnx=null;
		Statement rqt=null;
		ResultSet rs=null;
		ArrayList<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try{
			cnx=JDBCTools.getConnection();
			rqt=cnx.createStatement();			
			rs=rqt.executeQuery(LISTE_ARTICLE);
			ArticleVendu articleVendu;
			while (rs.next()){
				articleVendu = new ArticleVendu(
									rs.getInt("no_article"),
									rs.getString("nom_article"),
									rs.getString("description"),
									rs.getString("etat_vente"),
									rs.getDate("date_debut_encheres"), 
									rs.getDate("date_fin_encheres"),
									rs.getInt("prix_initial"),
									rs.getInt("prix_vente"),
									rs.getString("no_utilisateur"),
									rs.getInt("no_categorie")
						);
				listeArticles.add(articleVendu);				
			}
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return listeArticles;
	}
	
	
}
