package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bo.Utilisateur;

public class IdentificationDAOJdbc{
	private static final String VERIF_UTILISATEUR = "SELECT * FROM UTILISATEURS where pseudo = ? AND motDePasse = ?";
	private static final String MAJ_MDP = "UPDATE UTILISATEURS SET motDePasse = ? WHERE pseudo = ?";

	public IdentificationDAOJdbc() {
		super();
	}

	public Utilisateur verifIdentification(String pseudo, String motDePasse) throws SQLException {
		Utilisateur utilisateur = null;
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;
		
		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(VERIF_UTILISATEUR);
			
			preparedStatement.setString(1, pseudo);
			preparedStatement.setString(2, motDePasse);
			rs = preparedStatement.executeQuery();

			if(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"), rs.getString("nom"), 
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), 
						rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"), rs.getString("motDePasse"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}


		}catch(ClassNotFoundException|

	SQLException e)
	{
		e.printStackTrace();
	}finally
	{
		if(rs != null) rs.close();
		if(preparedStatement != null) preparedStatement.close();
		if(conSelect != null) conSelect.close();
	}

	return utilisateur;
	}

	public void updateMDP(String pseudo, String newMDP) throws SQLException {
		Connection conUpdate = null;
		PreparedStatement preparedStatement = null;
		try {
			conUpdate = JDBCTools.getConnection();
			preparedStatement = conUpdate.prepareStatement(MAJ_MDP);


			preparedStatement.setString(1, newMDP);
			preparedStatement.setString(2, pseudo);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			conUpdate.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(preparedStatement != null) preparedStatement.close();
			if(conUpdate != null) conUpdate.close();
		}
	}
}
