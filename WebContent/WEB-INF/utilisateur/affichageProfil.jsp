<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/basique/style.css">
<title>Mon profil</title>
</head>
<body>
 <h1>Mon Profil</h1>
 <div id="profil">
	<h2>${user.pseudo}</h2>
	<c:if test="${'Modifier' != param.modifier}">
	<form action="<%=request.getContextPath()%>/modifierProfil" method="post">
		<label for="pseudo"> ${user.pseudo} </label><br/>
		<label for="nom"> ${user.nom}</label><br/>
		<label for="prenom"> ${user.prenom}</label><br/>
		<label for="email"> ${user.email}</label><br/>
		<label for="telephone"> ${user.telephone}</label><br/>
		<label for="rue">  ${user.rue}</label><br/>
		<label for="codePostal"> ${user.codePostal}</label><br/>
		<label for="ville">  ${user.ville}</label><br/>
		<c:if test="${user.noUtilisateur == utilisateur.noUtilisateur}">
		<input class="modif" type="submit" value="Modifier" name="modifier">
		<input type="hidden" value="${user.noUtilisateur}" name="id_utilisateur_recherche">
		</c:if>
	</form>
	</c:if>
	
	<c:if test="${'Modifier' == param.modifier}">
	<form action="<%=request.getContextPath()%>/modifierProfil" method="post">
		<input class="champtexte" type="text" id="identifiant" name="identifiant" value="${user.pseudo}"/>
		<label for="nom"> ${user.nom}</label><br/>
		<label for="prenom"> ${user.prenom}</label><br/>
		<label for="email"> ${user.email}</label><br/>
		<label for="telephone"> ${user.telephone}</label><br/>
		<label for="rue">  ${user.rue}</label><br/>
		<label for="codePostal"> ${user.codePostal}</label><br/>
		<label for="ville">  ${user.ville}</label><br/>
		<input class="annuler" type="submit" value="annuler" name="action">
		<input class="enregistrer" type="submit" value="enregistrer" name="action">
		<input type="hidden" value="${user.noUtilisateur}" name="id_utilisateur_recherche">
	</form>
	</c:if>
</div>
</body>
</html>