<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stagiaire</title>
<link 	rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
		<c:if test="${cookie.connexion.getValue()=='STG'}">
			<h1>TP Web - Menu Stagiaire</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menuStagiaire.jsp"/>
		</c:if>
		<c:if test="${cookie.connexion.getValue()=='NULL' or empty cookie.connexion}">
			<h1>TP Web - Acces Stagiaire</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menu.jsp"/>
			<p>Identification nécessaire !</p>
			<jsp:include page="/WEB-INF/Template/connexion_stagiaire.jsp"/>
		</c:if>
			<c:if test="${cookie.connexion.getValue()=='NOK'}">
			<h1>TP Web - Acces Stagiaire</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menu.jsp"/>
			<p>Identifiant ou mot de passe incorrect, veuillez vérifier votre saisie.</p>
			<jsp:include page="/WEB-INF/Template/connexion_stagiaire.jsp"/>
		</c:if>
		<c:if test="${cookie.connexion.getValue()=='ANIM'}">
			<h1>TP Web - Acces Stagiaire</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menu.jsp"/>
			<p>Vous êtes connecté en tant qu'animateur, vous n'avez pas accès à ce menu.</p>
			<p>Vous pouvez cependant essayez de vous reconnecter en tant que stagiaire.</p>
			<jsp:include page="/WEB-INF/Template/connexion_stagiaire.jsp"/>
		</c:if>
	<jsp:include page="/WEB-INF/Template/footer.jsp"/>
