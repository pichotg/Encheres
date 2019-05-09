package servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurDAO utDAO = new UtilisateurDAO();
		HttpSession session = request.getSession();
		Utilisateur utAvantModif = (Utilisateur) session.getAttribute("user");
		if("enregistrer".equals(request.getParameter("action")))
		{
			Utilisateur utUpdate = new Utilisateur(utAvantModif.getNoUtilisateur(), 
					request.getParameter("pseudo"), 
					request.getParameter("nom"), 
					request.getParameter("prenom"), 
					request.getParameter("email"), 
					request.getParameter("telephone"), 
					request.getParameter("rue"), 
					request.getParameter("codePostal"), 
					request.getParameter("ville"), 
					request.getParameter("motDePasse"),
					utAvantModif.getCredit(), 
					utAvantModif.getAdministrateur(), 
					utAvantModif.getEtatUtilisateur());
			try {	
				utDAO.updateUtilisateur(utUpdate);
				// On r�cup�re l'utilisateur mis � jour afin de g�rer l'affichage une fois le formulaire valid�
				Utilisateur newUtilisateur = utDAO.getUtilisateurById(utAvantModif.getNoUtilisateur());
				session.setAttribute("user", newUtilisateur);
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/affichageProfil.jsp?id_utilisateur_recherche="+utAvantModif.getNoUtilisateur()).forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("annuler".equals(request.getParameter("action")))
		{
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/affichageProfil.jsp").forward(request, response);
		}
		if("supprimer".equals(request.getParameter("action")))
		{
			try {
				utDAO.deleteUtilisateur(utAvantModif);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/deconnexion").forward(request, response);
		}
		
	}

}
