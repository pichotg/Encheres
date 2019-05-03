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
	private static String isEnable = "disabled";
       
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if("enregistrer".equals(request.getParameter("action")))
		{
			UtilisateurDAO utDAO = new UtilisateurDAO();
			Utilisateur utUpdate = new Utilisateur(Integer.parseInt(request.getParameter("noUtilisateur")), 
					request.getParameter("pseudo"), 
					request.getParameter("nom"), 
					request.getParameter("prenom"), 
					request.getParameter("email"), 
					request.getParameter("telephone"), 
					request.getParameter("rue"), 
					request.getParameter("codePostal"), 
					request.getParameter("ville"), 
					request.getParameter("motDePasse"), 
					Integer.parseInt(request.getParameter("credit")), 
					Integer.parseInt(request.getParameter("administrateur")), 
					Integer.parseInt(request.getParameter("etatUtilisateur")));
			try {
				utDAO.updateUtilisateur(utUpdate);
				request.setAttribute("modifier", isEnable);
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/affichageProfil.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("annuler".equals(request.getParameter("action")))
		{
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/affichageProfil.jsp").forward(request, response);
		}
		
	}

}
