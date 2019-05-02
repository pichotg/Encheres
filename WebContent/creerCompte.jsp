<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="../themes/basique/style.css">
<title>CreerCompte</title>
</head>
<body>
	<div id="page">
	<div id="entete">
		<h1>ENI-Ench�res</h1>
		<h2>Mon Profil</h2>
		</div>
		<c:if test="${'alreadyExist' == error}">
			<p>alreadyExist</p>
		</c:if>
		<div id="contenu">
		<form class="compte" action="<%=request.getContextPath()%>/creerCompte" method="post">
			<label for="pseudo">Pseudo :</label> 
			<input required="required" class="champtexte" type="text" id="pseudo" name="pseudo" /> 
			<label for="nom">Nom :</label> 
			<input required="required" class="champtexte" type="text" id="nom" name="nom" /><br />
			<label for="prenom">Pr�nom :</label> 
			<input required="required" class="champtexte" type="text" id="prenom" name="prenom" /> 
			<label for="email">Email:</label> 
			<input required="required" class="champtexte" type="text" id="email" name="email" /><br /> 
			<label for="telephone">T�l�phone :</label> 
			<input required="required" class="champtexte" type="text" id="telephone" name="telephone" /> 
			<label for="rue">Rue :</label> 
			<input required="required" class="champtexte" type="text" id="rue" name="rue" /><br />
			<label for="codePostal">Code Postal :</label> 
			<input required="required" class="champtexte" type="text" id="codePostal" name="codePostal" /> 
			<label for="Ville">Ville :</label> 
			<input required="required" class="champtexte" type="text" id="ville" name="Ville" /> <br/>
			<label for="motDePasse">Mot de passe :</label> 
			<input required="required" class="champtexte" type="password" id="motDePasse" name="motDePasse" /> 
			<label for="confirmation">Confirmation :</label> 
			<input required="required" class="champtexte" type="password" id="confirmation" name="confirmation" /><br/>
			<input type="submit" id="creer" name="creer" value="Creer" />
			<input type="submit" id="annuler" name="annuler" value="Annuler" />
		</form>
		</div>
	</div>
</body>
</html>
