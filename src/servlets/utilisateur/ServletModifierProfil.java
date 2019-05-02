package servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;
import dal.UtilisateurDAO;

public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDAO DAOgetUtilisateur = new UtilisateurDAO();
		Utilisateur user = null;
			
		Integer utilisateurRecherchee = Integer.parseInt(request.getParameter("id_utilisateur_recherche"));
		if (utilisateurRecherchee != null) {
			try {
				user = DAOgetUtilisateur.getUtilisateurById(utilisateurRecherchee);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/affichageProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
