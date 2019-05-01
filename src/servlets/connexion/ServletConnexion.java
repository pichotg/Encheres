package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;
import dal.UtilisateurDAO;

/**
 * Servlet qui servira à se connecter au site.
 * 
 * @author adeloffre2018
 *
 */
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int CINQ_MINUTES = 5 * 60;
	private static final int SE_SOUVENIR = 30 * 24 * 60 * 60; // On se souvient de l'utilisateur pendant 30 jours.

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		UtilisateurDAO DAOIdentification;
		String identifiant = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motdepasse");
		Utilisateur utilisateur = null;

		Cookie ck = null;

		DAOIdentification = new UtilisateurDAO();
		utilisateur = DAOIdentification.verifIdentification(identifiant, motDePasse);
		ck = new Cookie("connexion", "NULL");


		if (utilisateur != null) {
			// le cookie est valide 10 minutes
			ck.setValue("OK");
			// Si on n'a pas coché se souvenir de moi
			ck.setMaxAge(CINQ_MINUTES);
			// Si on a coché , il faudra mettre SE_SOUVENIR pour être mémorisé 30 jours
			request.setAttribute("utilisateur", utilisateur);
			System.out.println(utilisateur.getNoUtilisateur());
		} else {
			// Les identifiants sont incorrects, on passe le cookie Ã  NOK
			ck.setValue("NOK");
		}
		// On ajoute le cookie
		response.addCookie(ck);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}

