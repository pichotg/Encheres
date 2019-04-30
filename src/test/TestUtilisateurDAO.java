package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import bo.Utilisateur;
import dal.UtilisateurDAO;

public class TestUtilisateurDAO {
	private static UtilisateurDAO userDao;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Utilisateur user = new Utilisateur(0,"gpichot",
				"pichot",
				"gaspard" ,
				"gpichot@mail.fr",
				"0651845076",
				"la rue pichot",
				"86000",
				"Poitiers","root",1000,false);
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Encheres",
				"sa",
				"Pa$$w0rd");
		
		
		userDao = new UtilisateurDAO();
		
		userDao.insertUtilisateur(user);
		
		System.out.println("User :"+ user.toString()+ "inserted");
		
		System.out.println(userDao.getAllUtilisateur().toString());
	}
	

}
