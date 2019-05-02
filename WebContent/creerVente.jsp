<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../themes/basique/style.css">
<title>CreerVente</title>
</head>
<body>
<div id="page">
		<h1>ENI-Enchères</h1>
		<h2>Nouvelle vente</h2>
		<form class="vente" action="" method="post">
			<label for="article">Article :</label> 
			<input class="champtexte" type="text" id="article" name="article" /><br />
			<label for="description">Description :</label> 
			<input class="champtexte" type="text" id="description" name="description" /><br />
			<label for="categorie">Categorie :</label> 
			<select class="champtexte" id="categorie" name="categorie"> </select><br />
			<label for="photo">Photo de l'article:</label> 
			<input class="champtexte" type="file" id="photo" name="photo" /><br /> 
			<label for="miseAPrix">Mise à prix :</label> 
			<input class="champtexte" type="text" id="miseAPrix" name="miseAPrix" /> <br />
			<label for="debut">Début de l'enchère :</label> 
			<input type="date" id="debut" name="debut" /><br />
			<label for="fin">Fin de l'enchère :</label> 
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