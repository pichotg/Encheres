package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bo.Retrait;
import jdbc.JDBCTools;

public class RetraitDAO {
	
	private static final String INSERT_RETRAIT = "INSERT INTO retraits (no_article, rue, code_postal, ville) VALUES(?,?,?,?)";
	
	
	public void ajout_Retrait(Retrait unRetrait) throws SQLException, ClassNotFoundException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(INSERT_RETRAIT);
			rqt.setInt(1, unRetrait.getNoArticle());
			rqt.setString(2, unRetrait.getRue());
			rqt.setString(3, unRetrait.getCode_Postal());
			rqt.setString(4, unRetrait.getVille());
			rqt.executeUpdate();
		} finally {
			if (rqt != null)
				rqt.close();
			if (cnx != null)
				cnx.close();
		}
	}
}
