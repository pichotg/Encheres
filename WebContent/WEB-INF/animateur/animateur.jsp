<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animateur</title>
<link 	rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
		<c:if test="${cookie.connexion.getValue()=='ANIM'}">
			<h1>TP Web - Menu Animateur</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menuAnimateur.jsp"/>
		</c:if>
		<c:if test="${cookie.connexion.getValue()=='NULL' or (empty cookie.connexion)}">
			<h1>TP Web - Acces Animateur</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menu.jsp"/>
			<p>Identification nécessaire !</p>
			<jsp:include page="/WEB-INF/Template/connexion_animateur.jsp"/>
		</c:if>
		<c:if test="${cookie.connexion.getValue()=='NOK'}">
			<h1>TP Web - Acces Animateur</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menu.jsp"/>
			<p>Identifiant ou mot de passe incorrect, veuillez vérifier votre saisie.</p>
			<jsp:include page="/WEB-INF/Template/connexion_animateur.jsp"/>
		</c:if>
		<c:if test="${cookie.connexion.getValue()=='STG'}">
			<h1>TP Web - Acces Animateur</h1>
			<hr id="header">
			<jsp:include page="/WEB-INF/Template/menu.jsp"/>
			<p>Vous êtes connecté en tant que stagiaire, vous n'avez pas accès à ce menu.</p>
			<p>Vous pouvez cependant essayez de vous reconnecter en tant qu'animateur.</p>
			<jsp:include page="/WEB-INF/Template/connexion_animateur.jsp"/>
		</c:if>
	<jsp:include page="/WEB-INF/Template/footer.jsp"/>
