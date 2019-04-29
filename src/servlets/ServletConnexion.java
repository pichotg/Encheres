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
 * Servlet qui servira � se connecter au site.
 * @author adeloffre2018
 *
 */
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DIX_MINUTES = 10 * 60;
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
		Utilisateur utilisateur;
		Cookie ck = null;
		HttpSession session;

		DAOIdentification = new UtilisateurDAO();
		utilisateur  = DAOIdentification.verifIdentification(identifiant, motDePasse);
		ck = new Cookie("connexion", "NULL");
		session = request.getSession();
			
		
		if ( utilisateur != null) {
			// le cookie est valide 10 minutes
			ck.setValue("OK");
			ck.setMaxAge(DIX_MINUTES);

		} else {
			// Les identifiants sont incorrects, on passe le cookie à NOK
			ck.setValue("NOK");
		}
		// On ajoute le cookie
		response.addCookie(ck);
		// On ajoute la personne connecté à la session
		session.setAttribute("UtilisateurConnecte", utilisateur);
	}
}
