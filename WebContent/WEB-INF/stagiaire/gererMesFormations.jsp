<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gérer mes formations</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
	<h1>TP Web - Gestion de mes formations</h1>
	<hr id="header">
	<jsp:include page="/WEB-INF/Template/menuStagiaire.jsp" />
	<form id="mesFormations" action="${pageContext.request.contextPath}/mes_formations" method="post">
	<label>Mes Formations</label><br>
		<select  size="7" multiple="multiple" name="aGerer">
			<c:forEach var="formation" items="${mesFormations}">
				<option value="${formation.id}">${formation.libelle}</option>
			</c:forEach>
		</select> 
		<input type="submit" value="desinscription" name="action">
	</form>
	<form id="mesFormations" action="${pageContext.request.contextPath}/mes_formations" method="post">
	<label>Catalogue</label><br>
		<select size="7" multiple="multiple" name="aGerer">
			<c:forEach var="formation" items="${autresFormations}">
				<option value="${formation.id}">${formation.libelle}</option>
			</c:forEach>
		</select> 
		<input type="submit" value="inscription" name="action">
	</form>
	<jsp:include page="/WEB-INF/Template/footer.jsp" />