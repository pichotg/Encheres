package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Utilisateur;
import jdbc.JDBCTools;

public class UtilisateurDAO {
	
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS where no_utilisateur = ?";
	private static final String VERIF_UTILISATEUR = "SELECT * FROM UTILISATEURS where (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	private static final String SELECT_ALL_UTILISATEUR = "SELECT * FROM UTILISATEURS";
	private static final String GET_UTILISATEUR_BY_ID = "SELECT * FROM UTILISATEURS where no_utilisateur = ?";
	private static final String GET_UTILISATEUR_BY_PSEUDO = "SELECT * FROM UTILISATEURS where pseudo = ?";
	private static final String VERIF_ALREADY_EXIST_UTILISATEUR = "SELECT * FROM UTILISATEURS where pseudo = ? OR email = ?";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,etat_utilisateur) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String MAJ_ALL = "UPDATE UTILISATEURS SET pseudo = ?,nom = ?,prenom = ?, email = ?, telephone = ?,rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ?, etat_utilisateur = ? WHERE no_utilisateur = ?";

	public UtilisateurDAO() {
		super();
	}

	/**
	 * @param pseudoOrEmail
	 * @param motDePasse
	 * @return Utilisateur 
	 * @throws SQLException
	 */
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
						rs.getInt("credit"), rs.getInt("administrateur"), rs.getInt("etat_utilisateur"));
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
	
	/**
	 * return all user from BDD
	 * @return ArrayList<Utilisateur>
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> getAllUtilisateur() throws SQLException {
		ArrayList<Utilisateur> listUser = new ArrayList<Utilisateur>();
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(SELECT_ALL_UTILISATEUR);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				listUser.add(new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getInt("administrateur"),rs.getInt("etat_utilisateur") ));
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

		return listUser;
	}
	/**
	 * Update all info
	 * @param Utilisateur
	 * @throws SQLException
	 */
	public void updateUtilisateur(Utilisateur user) throws SQLException {
		Connection conUpdate = null;
		PreparedStatement preparedStatement = null;
		try {
			conUpdate = JDBCTools.getConnection();
			preparedStatement = conUpdate.prepareStatement(MAJ_ALL);
			//Set
			preparedStatement.setString(1, user.getPseudo());
			preparedStatement.setString(2, user.getNom());
			preparedStatement.setString(3, user.getPrenom());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getTelephone());
			preparedStatement.setString(6, user.getRue());
			preparedStatement.setString(7, user.getCodePostal());
			preparedStatement.setString(8, user.getVille());
			preparedStatement.setString(9, user.getMotDePasse());
			preparedStatement.setInt(10, user.getCredit());
			preparedStatement.setInt(11, user.getAdministrateur());
			preparedStatement.setInt(12, user.getEtatUtilisateur());
			//where
			preparedStatement.setInt(13, user.getNoUtilisateur());

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

	/**
	 * Check if user already exist, before insert
	 * @param newutilisateur
	 * @return true if User already exist else false
	 * @throws SQLException
	 */
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

	/**
	 * Insert new user in BDD
	 * @param newutilisateur
	 * @return false if User already exist or error else true => Succes
	 * @throws SQLException
	 */
	public boolean insertUtilisateur(Utilisateur newutilisateur) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection conInsert = null;
		boolean breturn = false;
		if (verifExistUtilisateur(newutilisateur)) {
			return breturn;
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
				preparedStatement.setInt(11, newutilisateur.getAdministrateur());
				preparedStatement.setInt(12,1);
				
				
				preparedStatement.executeUpdate();
				breturn = true;

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				if (preparedStatement != null)
					preparedStatement.close();
				if (conInsert != null)
					conInsert.close();
			}
			return breturn;
		}
	}
	
	/**
	 * Get Utilisateur by no_utilisateur
	 * @param id
	 * @return Utilisateur
	 * @throws SQLException
	 */
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
						rs.getInt("credit"), rs.getInt("administrateur"),rs.getInt("etat_utilisateur"));
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
	
	/**
	 * Get Utilisateur by pseudo
	 * @param id
	 * @return Utilisateur
	 * @throws SQLException
	 */
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws SQLException {
		Utilisateur utilisateur = null;
		PreparedStatement preparedStatement = null;
		Connection conSelect = null;
		ResultSet rs = null;

		try {
			conSelect = JDBCTools.getConnection();
			preparedStatement = conSelect.prepareStatement(GET_UTILISATEUR_BY_PSEUDO);

			preparedStatement.setString(1, pseudo);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getInt("administrateur"),rs.getInt("etat_utilisateur"));
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
	
	/**
	 * Delete Utilisateur TODO: delete FK
	 * @param Utilisateur user
	 */
	public void deleteUtilisateur(Utilisateur user) {

		try {
			Connection conDelete = JDBCTools.getConnection();

			PreparedStatement preparedStatement = conDelete.prepareStatement(DELETE_UTILISATEUR);

			preparedStatement.setInt(1, user.getNoUtilisateur());

			if (preparedStatement.executeUpdate() == 0) {
				System.err.println("Suppression impossible : Cette utilisateur n'est pas pr�sent en base.");
			}
			preparedStatement.close();
			conDelete.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

}
