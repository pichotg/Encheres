package servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;
import dal.UtilisateurDAO;

public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreationCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDAO DAOcreerCompte = new UtilisateurDAO();
		boolean bsucces = false;
		
		Utilisateur user = new Utilisateur(0, 
				 request.getParameter("pseudo"),
				 request.getParameter("nom"),
				 request.getParameter("prenom"),
				 request.getParameter("email"),
				 request.getParameter("telephone"),
				 request.getParameter("rue"),
				 request.getParameter("codePostal"),
				 request.getParameter("ville"),
				 request.getParameter("motDePasse"),
				 0,
				 false
				 );
		System.out.println(user.toString());
		try {
			bsucces = DAOcreerCompte.insertUtilisateur(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		if (bsucces) {
			response.sendRedirect("index.html");
		}else {
			// TODO: redirect mot de passe oublier ?
			request.setAttribute("error", "alreadyExist");
		}
		
	}
	
}
