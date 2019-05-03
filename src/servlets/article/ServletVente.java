package servlets.article;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.ArticleVendu;
import bo.Retrait;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
import dal.RetraitDAO;
import dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletVente")
public class ServletVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Recuperation des valeurs du formulaire
		 */
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		String path = request.getParameter("path");
		Timestamp debut1 = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("debut")));
		Timestamp fin1 = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("fin")));
		
		int prixVente = Integer.parseInt(request.getParameter("miseAPrix"));
		String etatVente = "VND";
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		
		UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
		Utilisateur utilisateur = null;
		
		try {
			utilisateur = utilisateurDAO.getUtilisateurById(noUtilisateur);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArticleVenduDAO articleVenduDao = new ArticleVenduDAO();
		ArticleVendu unArticle = new ArticleVendu(0, nomArticle, etatVente, description, debut1, fin1, miseAPrix,
				prixVente, utilisateur, categorie, path);
		int dernier_id = 0;
		try {
			ArticleVenduDAO.venteArticle(unArticle);
			dernier_id = articleVenduDao.dernier_id();
			//ArticleVenduDAO.venteArticle(unArticle);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Retrait unRetrait = new Retrait(dernier_id,request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"));
		
		RetraitDAO retraitDao = new RetraitDAO();
		
		try {
			retraitDao.ajout_Retrait(unRetrait);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
