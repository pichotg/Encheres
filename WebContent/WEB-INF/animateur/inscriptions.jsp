<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des inscriptions</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
	<h1>TP Web - Liste des inscriptions</h1>
	<hr id="header">

<form class="recherche" action="liste_inscriptions" method="get">	
	<input type="text" list="formations" name="inscriptions_par_formation">
	<datalist id="formations">
		<c:forEach var="formation" items="${liste_formations}">
			<option value="${formation.id}">${formation.libelle}</option>
		</c:forEach>
	</datalist>
	
	<input type=text list="stagiaires" name="inscriptions_par_stagiaire">
	<datalist id="stagiaires">
		<c:forEach var="stagiaire" items="${liste_stagiaires}">
			<option value="${stagiaire.id}">${stagiaire.nom} ${stagiaire.prenom}</option>
		</c:forEach>
	</datalist>
	
	<input class="button" type="submit" value="Rechercher les inscriptions" />
</form>
<section class="formations">
	<c:forEach var="inscription" items="${liste_inscriptions}">
		<section class="formation">
			<section class="fond_titre">
				<h2>${inscription.formation.libelle}</h2>
			</section>
			<label>Informations du stagiaire</label>
			<ul>
				<li>Nom du stagiaire : ${inscription.stagiaire.nom}</li>
				<li>Prenom du stagiaire : ${inscription.stagiaire.prenom}</li>
			</ul>
			<label>Informations de la formation</label>
			<ul>
				<li>Du ${inscription.formation.getDebutFormat()}	au ${inscription.formation.getFinFormat()}</li>
				<li>${inscription.formation.description}</li>
			</ul>
			<form class="mod" action="gestion_formation" method="post">
				<input type="hidden" value="${inscription.formation.id}" name="idForm">
				<input type="hidden" value="${inscription.stagiaire.id}" name="idStg">
				<input class="modif" type="submit" value="Supprimer" name="ActionModif">
			</form>
		</section>
	</c:forEach>
</section>
