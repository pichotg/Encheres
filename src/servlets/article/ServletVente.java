package servlets.article;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.ArticleVendu;
import bo.Utilisateur;
import dal.ArticleVenduDAO;
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		String path = request.getParameter("path");
		Date debut = null;
		Date fin = null;

		try {
			debut = (Date) sdf.parse(request.getParameter("debut"));
			fin = (Date) sdf.parse(request.getParameter("fin"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		

		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
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

		ArticleVendu unArticle = new ArticleVendu(0, nomArticle, etatVente, description, debut, fin, miseAPrix,
				prixVente, utilisateur, categorie, path);
		ArticleVenduDAO DAOarticleVente = new ArticleVenduDAO();
		try {
			ArticleVenduDAO.venteArticle(unArticle);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
