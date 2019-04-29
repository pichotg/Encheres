package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bo.Personne;
import dal.DAO;


public class IdentificationDAOJdbcImpl implements DAO<Utilisateur>{
	private static final String VERIF_UTILISATEUR = "SELECT * FROM UTILISATEURS where pseudo = ? AND motDePasse = ?";
	private static final String MAJ_MDP = "UPDATE UTILISATEURS SET motDePasse = ? WHERE pseudo = ?";
	
	public IdentificationDAOJdbcImpl() {
		super();
	}
	public Utilisateur verifIdentification(String pseudo, String motDePasse) {
		Utilisateur utilisateur = null;

		try {
			Connection conSelect = JDBCTools.getConnection();
			PreparedStatement preparedStatement = null;

			preparedStatement = conSelect.prepareStatement(VERIF_UTILISATEUR);
			}
			
			preparedStatement.setString(1, pseudo);
			preparedStatement.setString(2, motDePasse);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"), rs.getString("nom"), 
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), 
						rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"), rs.getString("motDePasse"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			preparedStatement.close();
			conSelectAll.close();
		}

		return utilisateur;
	}

	public void updateMDP(String eMail, String newMDP) {
		try {
			Connection conUpdate = JDBCTools.getConnection();
			PreparedStatement preparedStatement = conUpdate.prepareStatement(MAJ_MDP);


			preparedStatement.setString(1, newMDP);
			preparedStatement.setString(2, eMail);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			conUpdate.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Personne> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> selectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Personne t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Personne t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> nomColonnes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> recherche(String nomColonne, String valeur) {
		// TODO Auto-generated method stub
		return null;
	}
}
