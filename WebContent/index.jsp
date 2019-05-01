<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.Utilisateur"%>
<%@page import="bo.Enchere"%>
<%@page import="bo.ArticleVendu"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>index</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
<h1>ENI - Enchères</h1>
	<hr id="header">
	<aside class="menu">
	<c:if test="${utilisateur.noUtilisateur != null}">
	<p><a href="<%=request.getContextPath()%>/deconnexion">Déconnexion</a></p><br>
	</c:if>
	<c:if test="${utilisateur.noUtilisateur == -1 or utilisateur.noUtilisateur == null}">
	<p><a href="<%=request.getContextPath()%>/connexion.jsp">Connexion / inscription</a></p><br>
	</c:if>
	</aside>

	<h2>Liste des enchères</h2>
	<p>Filtres :</p>
	<form class="recherche" action="<%=request.getContextPath()%>/filtre" method="post">
		<div class="nomArticleContient">
			<label for="contient">Le nom de l'article contient</label>
			<input class="champtexte" type="text" id="contient" name="contient" value="" />
		</div>
		<div class ="categorie">
		<label for="categorie">Catégorie</label>
		<select name="categorie">
			<option></option>
			<option>Informatique</option>
			<option>Ameublement</option>
			<option>Vêtement</option>
			<option>Sport&Loisirs</option>
		</select>
		</div>
		<input type="submit" id="rechercher" value="rechercher" />
	</form>
</body>
</html>