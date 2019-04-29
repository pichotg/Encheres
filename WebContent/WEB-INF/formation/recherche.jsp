<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form class="recherche" action=<%=request.getContextPath()+request.getAttribute("page")%> method="get">
	<!-- On affiche d'abord toutes les inscriptions -->
		<select name="colonne_recherche">
			<c:forEach var="colonne_recherche" items="${listeColonnes}">
				<option>${colonne_recherche}</option>
			</c:forEach>
		</select>
	<input type=text class="valeur_recherchee" name="valeur_recherchee">
	<input class="button" type="submit" value="Rechercher une formation" />
</form>
