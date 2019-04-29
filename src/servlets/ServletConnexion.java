package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Personne;
import dal.DAO;
import jdbc.IdentificationDAOJdbcImpl;

public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DIX_MINUTES = 10 * 60;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeUtilisateur = request.getParameter("typeUtilisateur");
		DAO<Personne> DAOIdentification;
		String eMail = request.getParameter("id");
		String motDePasse = request.getParameter("pwd");
		Personne personne;
		Cookie ck = null;
		HttpSession session;

		DAOIdentification = new IdentificationDAOJdbcImpl();
		personne  = ((IdentificationDAOJdbcImpl) DAOIdentification).verifIdentification(eMail, motDePasse, typeUtilisateur);
		ck = new Cookie("connexion", "NULL");
		session = request.getSession();
			
		
		if ( personne != null) {
			if("stagiaire".equals(typeUtilisateur))
			{
				// on garde dans le cookie le fait que c'est un stagiaire
				ck.setValue("STG");
			}
			else if("animateur".equals(typeUtilisateur)) {
				// ou un animateur
				ck.setValue("ANIM");
			}
			// le cookie est valide 10 minutes
			ck.setMaxAge(DIX_MINUTES);

		} else {
			// Les identifiants sont incorrects, on passe le cookie à NOK
			ck.setValue("NOK");
		}
		// On ajoute le cookie
		response.addCookie(ck);
		// On ajoute la personne connecté à la session
		session.setAttribute("UtilisateurConnecte", personne);
		// On redirige vers la bonne page
		response.sendRedirect(typeUtilisateur);
	}
}
