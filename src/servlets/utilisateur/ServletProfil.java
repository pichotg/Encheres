package servlets.utilisateur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;
import dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDAO DAOgetUtilisateur = new UtilisateurDAO();
		Utilisateur user = null;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		Integer utilisateurRecherchee = Integer.parseInt(request.getParameter("id_utilisateur_recherche"));
		if (utilisateurRecherchee != null) {
			try {
				user = DAOgetUtilisateur.getUtilisateurById(utilisateurRecherchee);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		session.setAttribute("user", user);
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/affichageProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurDAO utDAO = new UtilisateurDAO();
		HttpSession session = request.getSession();
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		try {
			Utilisateur utAffiche = utDAO.getUtilisateurById(noUtilisateur);
			session.setAttribute("user", utAffiche);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur/modifierProfil.jsp").forward(request, response);
	}

}
