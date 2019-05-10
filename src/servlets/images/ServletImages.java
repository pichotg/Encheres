package servlets.images;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Image servlet for serving from absolute path.
 * 
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/04/imageservlet.html
 */
@WebServlet("/imageArticle/*")
public class ServletImages extends HttpServlet {

	private String imagePath;

	public void init() throws ServletException {

		this.imagePath = "c:\\temp\\imageArticle";

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère le nom de l'image
		String requestedImage = request.getPathInfo();
		String contentType = "";

		// si le nom est null, on affiche l'image par défaut
		if (requestedImage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));

		// Si l'image n'existe pas ou plus, on affiche l'image par défaut
		if (!image.exists()) {
			image = new File(imagePath, URLDecoder.decode("/imageIndisponible.png", "UTF-8"));
			contentType = getServletContext().getMimeType(image.getName());
			response.reset();
			response.setContentType(contentType);
			response.setHeader("Content-Length", String.valueOf(image.length()));
			Files.copy(image.toPath(), response.getOutputStream());
			return;
		}

		contentType = getServletContext().getMimeType(image.getName());

		// On vérifie que c'est bien une image, sinon encore une fois, on affiche
		// l'image par défaut
		if (contentType == null || !contentType.startsWith("image")) {
			image = new File(imagePath, URLDecoder.decode("/imageIndisponible.png", "UTF-8"));
			contentType = getServletContext().getMimeType(image.getName());
			response.reset();
			response.setContentType(contentType);
			response.setHeader("Content-Length", String.valueOf(image.length()));
			Files.copy(image.toPath(), response.getOutputStream());
			return;
		}

		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));

		Files.copy(image.toPath(), response.getOutputStream());
	}

}