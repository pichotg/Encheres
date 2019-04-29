<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.Formation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des formations</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
	<h1>TP Web - Gestion des formations</h1>
	<hr id="header">
	<jsp:include page="/WEB-INF/Template/menu.jsp" />
	<jsp:include page="/WEB-INF/formation/recherche.jsp" />
	<section class="formations">
		<c:forEach var="formation" items="${ListeFormations}">
			<c:if test="${formation.id == param.id and 'Modifier' == param.ActionModif}">
				<form class="modification" action="modification_formation" method="post">
					<section class="formation">
						<section class="fond_titre">
							<h2>
								<label>
									Libelle de la formation : 
								</label> 
								<input type="hidden" value="${formation.id}" name="id"> 
								<input class="input" type="text" value="${formation.libelle}" name="libelle">
							</h2>
						</section>
						<label>
							Date de début : 
						</label> 
						<input class="input" type="date" value="${formation.getDebut()}"name="debut"><br> 
						<label>
							Date	de fin : 
						</label> 
						<input class="input" type="date" value="${formation.getFin()}" name="fin"><br> 
						<label>
							Description : 
							<textarea rows="5" cols="100" name="description">${formation.description}</textarea>
						</label><br> 
						<input class="modif" type="submit" value="Valider" name="Ajout_Formation"> 
						<input class="modif" type="submit" value="Annuler" name="Ajout_Formation">
					</section>
				</form>
			</c:if>
			<c:if test="${formation.id != param.id or 'Modifier' != param.ActionModif}">
				<section class="formation">
					<section class="fond_titre">
						<h2>${formation.libelle}</h2>
					</section>
					<p>Du ${formation.getDebutFormat()}	au ${formation.getFinFormat()}</p>
					<p>${formation.description}</p>
					<form class="mod" action="gestion_formation" method="post">
						<input type="hidden" value="${formation.id}" name="id">
						<input class="modif" type="submit" value="Modifier" name="ActionModif"> 
						<input class="modif" type="submit" value="Supprimer" name="ActionModif">
					</form>
				</section>
			</c:if>
		</c:forEach>
		<c:if test="${param.ajout == 'Ajouter une formation'}">
			<form class="new" action="ajout_formation" method="post">
				<section class="formation">
					<section class="fond_titre">
						<h2>
							<label>Libelle de la formation :</label>
							<input class="input" type="text" name="libelle">
						</h2>
					</section>
					<label>Date de début :</label> 
					<input class="input" type="date" name="debut"><br> 
					<label>Date de fin : </label> 
					<input class="input" type="date" name="fin"><br> 
					<label>Description: 
						<textarea rows="5" cols="100" name="description"></textarea>
					</label><br> 
					<input class="modif" type="submit" value="Valider" name="Ajout_Formation">
					<input class="modif" type="submit" value="Annuler" name="Ajout_Formation">
				</section>
			</form>
		</c:if>
	</section>
	<form class="ajouter" action="gestion_formation" method="post">
		<input class="ajout" type="submit" value="Ajouter une formation" name="ajout">
	</form>
<jsp:include page="/WEB-INF/Template/footer.jsp"></jsp:include>