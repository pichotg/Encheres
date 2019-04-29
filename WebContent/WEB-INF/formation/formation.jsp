<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.Formation"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formations</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
	<h1>TP Web - Formations</h1>
	<hr id="header">
	<jsp:include page="/WEB-INF/Template/menu.jsp"/>
	<jsp:include page="/WEB-INF/formation/recherche.jsp"/>
	<section class="formations">
		<c:forEach var="formation" items="${ListeFormations}">
			<section class="formation">
				<section class="fond_titre">
					<h2>${formation.libelle}</h2>
				</section>
				<p> Du ${formation.getDebutFormat()} au ${formation.getFinFormat()} </p>
				<p>${formation.description}</p>
				</section>
		</c:forEach>
	</section>
	<jsp:include page="/WEB-INF/Template/footer.jsp"></jsp:include>