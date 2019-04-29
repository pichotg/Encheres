package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCTools {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// chargement du driver
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/sql/pool_cnx");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
