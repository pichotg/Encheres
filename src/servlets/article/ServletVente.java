package servlets.article;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
	public static final String CHEMIN = "chemin";
	public static final int TAILLE_TAMPON = 10240; // 10 ko

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

		if ("Enregistrer".equals(request.getParameter("action"))) {
			/**
			 * Recuperation des valeurs du formulaire
			 */
			String nomArticle = request.getParameter("article");
			String description = request.getParameter("description");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			int categorie = Integer.parseInt(request.getParameter("categorie"));
			int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			Date debut1 = null;
			Date fin1 = null;
			try {
				debut1 = sdf.parse(request.getParameter("debut"));
				fin1 = sdf.parse(request.getParameter("fin"));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

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
		
			int dernier_id = 0;
			try {
				dernier_id = articleVenduDao.dernier_id()+1;
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String path = null;
			String chemin = this.getServletConfig().getInitParameter(CHEMIN);
		    Part part = request.getPart("photo");
		    String nomFichier = getNomFichier(part);
		    if (nomFichier != null && !nomFichier.isEmpty()) {
			    if (nomFichier.lastIndexOf(".") > 0) {	        
			        String ext = nomFichier.substring(nomFichier.lastIndexOf("."));
			        nomFichier = Integer.toString(dernier_id) + ext;
			        System.out.println(nomFichier);
			    }
		        InputStream input = part.getInputStream();
		        path = ecrireFichier( input, nomFichier, chemin );
		    }
		    
			try {
				ArticleVendu unArticle = new ArticleVendu(dernier_id, nomArticle, etatVente, description, debut1, fin1, miseAPrix,
						prixVente, utilisateur, categorie, path);
				ArticleVenduDAO.venteArticle(unArticle);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			Retrait unRetrait = new Retrait(dernier_id, request.getParameter("rue"), request.getParameter("codePostal"),
					request.getParameter("ville"));

			RetraitDAO retraitDao = new RetraitDAO();

			try {
				retraitDao.ajout_Retrait(unRetrait);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	private static String getNomFichier( Part part ) {
	    /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	    	/* Recherche de l'éventuelle présence du paramètre "filename". */
	        if ( contentDisposition.trim().startsWith("filename") ) {
	            /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
	        	String chaineASupprimer = "\"";
	            return contentDisposition.substring(contentDisposition.indexOf( '=' ) + 1 ).replace(chaineASupprimer, "");
	        }
	    }
	    return null;
	}
	
	private String ecrireFichier( InputStream input, String nomFichier, String chemin ) throws IOException {
		File localFile = new File(chemin + nomFichier);
		String newChemin = localFile.toString();
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(localFile));
		byte[] data = new byte[TAILLE_TAMPON];
 
		int count;
		while ((count = input.read(data, 0, TAILLE_TAMPON)) != -1) {
			output.write(data, 0, count);
		}
 
		input.close();
		output.flush();
		output.close();
		
		return newChemin;
	}
}
