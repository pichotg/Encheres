<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../themes/basique/style.css">
<title>CreerCompte</title>
</head>
<body>
	<div id="page">
	<div id="entete">
		<h1>ENI-Enchères</h1>
		<h2>Mon Profil</h2>
		</div>
		<div id="contenu">
		<form class="compte" action="<%=request.getContextPath()%>/creerCompte" method="post">
			<label for="pseudo">Pseudo :</label> 
			<input class="champtexte" type="text" id="pseudo" name="pseudo" /> 
			<label for="nom">Nom :</label> 
			<input class="champtexte" type="text" id="nom" name="nom" /><br />
			<label for="prenom">Prénom :</label> 
			<input class="champtexte" type="text" id="prenom" name="prenom" /> 
			<label for="email">Email:</label> 
			<input class="champtexte" type="text" id="email" name="email" /><br /> 
			<label for="telephone">Téléphone :</label> 
			<input class="champtexte" type="text" id="telephone" name="telephone" /> 
			<label for="rue">Rue :</label> 
			<input class="champtexte" type="text" id="rue" name="rue" /><br />
			<label for="codePostal">Code Postal :</label> 
			<input class="champtexte" type="text" id="codePostal" name="codePostal" /> 
			<label for="Ville">Ville :</label> 
			<input class="champtexte" type="text" id="Ville" name="Ville" /> <br/>
			<label for="motDePasse">Mot de passe :</label> 
			<input class="champtexte" type="password" id="motDePasse" name="motDePasse" /> 
			<label for="confirmation">Confirmation :</label> 
			<input class="champtexte" type="password" id="confirmation" name="confirmation" /><br/>
			<input type="submit" id="creer" name="creer" value="Creer" />
			<input type="submit" id="annuler" name="annuler" value="Annuler" />
		</form>
		</div>
	</div>
</body>
</html>