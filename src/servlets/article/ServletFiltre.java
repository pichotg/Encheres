package servlets.article;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Enchere;
import dal.EnchereDAO;

public class ServletFiltre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletFiltre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EnchereDAO enchereDAO;
		List<Enchere> encheres = new ArrayList<>();
		String contient = request.getParameter("contient");
		String categorie = request.getParameter("categorie");

		enchereDAO = new EnchereDAO();

		try {
			encheres = enchereDAO.filtrageVenteEnCours(contient, categorie);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("listeEncheres", encheres);

		this.getServletContext().getRequestDispatcher("/liste_encheres.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
