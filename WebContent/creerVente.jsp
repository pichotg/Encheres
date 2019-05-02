<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../themes/basique/style.css">
<title>CreerVente</title>
</head>
<body>
<div id="page">
		<h1>ENI-Ench�res</h1>
		<h2>Nouvelle vente</h2>
		<form class="vente" action="<%=request.getContextPath()%>/vente?noUtilisateur=${utilisateur.noUtilisateur}" method="post">
			<label for="article">Article :</label> 
			<input class="champtexte" type="text" id="article" name="article" /><br />
			<label for="description">Description :</label> 
			<input class="champtexte" type="text" id="description" name="description" /><br />
			<label for="categorie">Categorie :</label> 
			 <select class="champtexte" id="categorie" name="categorie" > 
			<c:forEach items="<%=Categorie.values()%>" var="categorie">
    			<option value="${entry.noCategorie}">${entry.name}</option>
			</c:forEach></select>
			<label for="photo">Photo de l'article:</label> 
			<input class="champtexte" type="file" id="photo" name="photo" /><br /> 
			<label for="miseAPrix">Mise � prix :</label> 
			<input class="champtexte" type="text" id="miseAPrix" name="miseAPrix" /> <br />
			<label for="debut">D�but de l'ench�re :</label> 
			<input type="date" id="debut" name="debut" /><br />
			<label for="fin">Fin de l'ench�re :</label> 
			<input class="champtexte" type="date" id="fin" name="fin" /> <br />
			<label for="rue">Rue :</label> 
			<input class="champtexte" type="text" id="rue" name="rue" /> <br/>
			<label for="codePostal">Code postal :</label> 
			<input class="champtexte" type="text" id="codePostal" name="codePostal" /> <br />
			<label for="ville">Ville :</label> 
			<input class="champtexte" type="text" id="ville" name="ville" /><br/>
			<input type="submit" id="enregistrer" name="enregistrer" value="Enregistrer" />
			<input type="submit" id="annuler" name="annuler" value="Annuler" />
		</form>
	</div>
</body>
</html>