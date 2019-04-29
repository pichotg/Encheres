package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bo.Enchere;
import jdbc.JDBCTools;


public class EnchereDAO {

	public static void ajouter(Enchere enchere) throws SQLException, ClassNotFoundException{
		Connection cnx=null;
		PreparedStatement rqt=null;

		try {
			cnx = JDBCTools.getConnection();
			rqt=cnx.prepareStatement("insert into encheres(no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)");
			rqt.setInt(1, enchere.getNoUtilisateur().getNoUtilisateur());
			rqt.setInt(2, enchere.getNoArticle().getNoArticle());
			rqt.setDate(3, enchere.getDateEnchere());
			rqt.setInt(4, enchere.getMontant_enchere());
			rqt.executeUpdate();
		} finally {
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	
}
