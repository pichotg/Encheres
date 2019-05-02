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
		UtilisateurDAO DAOgetUtilisateur = new UtilisateurDAO();
		Utilisateur user = null;
			
		Integer utilisateurRecherchee = Integer.parseInt(request.getParameter("id_utilisateur_recherche"));
		if (utilisateurRecherchee != null) {
			try {
				user = DAOgetUtilisateur.getUtilisateurById(utilisateurRecherchee);
			} catch (SQLException e) {
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
		
		if("Modifier".equals(request.getParameter("modifier")))
		{
			doGet(request, response);
		}
		if("enregistrer".equals(request.getParameter("action")))
		{
			HttpSession session = request.getSession();
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
		}
		if("annuler".equals(request.getParameter("action")))
		{
			doGet(request, response);
		}
		
	}

}
