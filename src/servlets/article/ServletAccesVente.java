package servlets.article;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;
import dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletAccesVente
 */
@WebServlet("/ServletAccesVente")
public class ServletAccesVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccesVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDAO.getUtilisateurById(noUtilisateur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rue, codePostal, ville;
		rue = utilisateur.getRue();
		codePostal = utilisateur.getCodePostal();
		ville = utilisateur.getVille();
		
		request.setAttribute("rue", rue);
		request.setAttribute("codePostal", codePostal);
		request.setAttribute("ville", ville);
		
		this.getServletContext().getRequestDispatcher("/creerVente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
