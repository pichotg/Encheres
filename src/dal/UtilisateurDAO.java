package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bo.Utilisateur;
import jdbc.JDBCTools;

public class UtilisateurDAO {
	private static final String VERIF_UTILISATEUR = "SELECT * FROM UTILISATEURS where (pseudo = ? OR email = ? ) AND mot_de_passe = ?";
	private static final String GET_UTILISATEUR_BY_ID = "SELECT * FROM UTILISATEURS where no_utilisateur = ?";
	private static final String VERIF_ALREADY_EXIST_UTILISATEUR = "SELECT * FROM UTILISATEURS where pseudo = ? OR email = ?";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String MAJ_MDP = "UPDATE UTILISATEURS SET motDePasse = ? WHERE pseudo = ?";

	public UtilisateurDAO() {
		super();
	}

	public Utilisateur verifIdentification(String pseudoOrEmail, String motDePasse) throws SQLException {
		Utilisateur utilisateur = null;
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(VERIF_UTILISATEUR);

			preparedStatement.setString(1, pseudoOrEmail);
			preparedStatement.setString(2, pseudoOrEmail);
			preparedStatement.setString(3, motDePasse);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}

		} catch (ClassNotFoundException |

				SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conSelect != null)
				conSelect.close();
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
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
			if (conUpdate != null)
				conUpdate.close();
		}
	}

	public boolean verifExistUtilisateur(Utilisateur newutilisateur) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(VERIF_ALREADY_EXIST_UTILISATEUR);

			preparedStatement.setString(1, newutilisateur.getPseudo());
			preparedStatement.setString(2, newutilisateur.getEmail());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
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
		return true;
	}

	public boolean insertUtilisateur(Utilisateur newutilisateur) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conInsert = null;

		if (verifExistUtilisateur(newutilisateur)) {
			return false;
		} else {
			try {
				conInsert = JDBCTools.getConnection();
				preparedStatement = conInsert.prepareStatement(INSERT_UTILISATEUR);

				preparedStatement.setString(1, newutilisateur.getPseudo());
				preparedStatement.setString(2, newutilisateur.getNom());
				preparedStatement.setString(3, newutilisateur.getPrenom());
				preparedStatement.setString(4, newutilisateur.getEmail());
				preparedStatement.setString(5, newutilisateur.getTelephone());
				preparedStatement.setString(6, newutilisateur.getRue());
				preparedStatement.setString(7, newutilisateur.getCodePostal());
				preparedStatement.setString(8, newutilisateur.getVille());
				preparedStatement.setString(9, newutilisateur.getMotDePasse());
				preparedStatement.setInt(10, newutilisateur.getCredit());
				preparedStatement.setBoolean(11, newutilisateur.getAdministrateur());
				
				preparedStatement.executeUpdate();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				if (preparedStatement != null)
					preparedStatement.close();
				if (conInsert != null)
					conInsert.close();
			}
			return true;
		}
	}
	
	public Utilisateur getUtilisateurById(int id) throws SQLException {
		Utilisateur utilisateur = null;
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(GET_UTILISATEUR_BY_ID);

			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}

		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (conSelect != null)
				conSelect.close();
		}

		return utilisateur;
		
	}
}
