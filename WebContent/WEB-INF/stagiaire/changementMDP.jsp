<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="servlets.stagiaire.ServletStagiaire"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stagiaire</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
	<h1>TP Web - Changement de mot de passe</h1>
	<hr id="header">
	<jsp:include page="/WEB-INF/Template/menuStagiaire.jsp"/>
	<form class="changementMDP"	action="${pageContext.request.contextPath}/changementMDP" method="post">
		<div>
			<label for="ancienMDP">Ancien mot de passe</label> 
			<input class="input" type="text" id="ancienMDP" name="ancienMDP"><br>
		</div>
		<div>
			<label for="nouveauMDP">Nouveau mot de passe</label> 
			<input class="input" type="text" id="nouveauMDP" name="nouveauMDP"><br>
		</div>
		<div>
			<label for="confirmationMDP">Confirmation nouveau mot de passe</label>
			<input class="input" type="text" id="confirmationMDP" name="confirmationMDP"><br>
		</div>
		<input id="changementMDP" class="button" type="submit" value="Valider"><br>
	</form>
	<c:if test="${'OK'==statutMDP }">
		<p class="alerte"> Votre mot de passe à bien été changé </p>
	</c:if>
	<c:if test="${'diff'==statutMDP }">
		<p class="alerte"> Le nouveau mot de passe et sa confirmation sont différents </p>
	</c:if>
	<c:if test="${'err'==statutMDP }">
		<p class="alerte"> Votre ancien mot de passe est erroné </p>
	</c:if>
<jsp:include page="/WEB-INF/Template/footer.jsp"></jsp:include>