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
		<h1>ENI-Enchères</h1>
		<form class="compte" action="" method="post">
			<label for="pseudo">Pseudo :</label> 
			<input class="champtexte" type="text" id="pseudo" name="pseudo" value="" /> 
			<label for="nom">Nom :</label> 
			<input class="champtexte" type="text" id="nom" name="nom" value="" /><br />
			<label for="prenom">Prénom :</label> 
			<input class="champtexte" type="text" id="prenom" name="prenom" value="" /> 
			<label for="email">Email:</label> 
			<input class="champtexte" type="text" id="email" name="email"	value="" /><br /> 
			<label for="telephone">Téléphone :</label> 
			<input class="champtexte" type="text" id="telephone" name="telephone" value="" /> 
			<label for="rue">Rue :</label> 
			<input class="champtexte" type="text" id="rue" name="rue" value="" /><br />
			<label for="codePostal">Code Postal :</label> 
			<input class="champtexte" type="text" id="codePostal" name="codePostal" value="" /> 
			<label for="Ville">Ville :</label> 
			<input class="champtexte" type="text" id="Ville" name="Ville" value="" /> <br/>
			<label for="motDePasseActuel">Mot de passe :</label> 
			<input class="champtexte" type="text" id="motDePasseActuel" name="motDePasseActuel" value="" /><br/>
			<label for="nouvMDP">Nouveau mot de passe :</label> 
			<input class="champtexte" type="text" id="nouvMDP" name="nouvMDP" value="" />
			<label for="confirmation">Confirmation :</label> 
			<input class="champtexte" type="text" id="confirmation" name="confirmation" value="" /><br/><br/>
			<label >Crédit : </label> <br/><br/>
			<input type="submit" id="enregistrer" name="enregistrer" value="Enregistrer" />
			<input type="submit" id="supprimer" name="supprimer" value="Supprimer mon compte" />
		</form>
	</div>
</body>
</html>