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
		<form class="connexion" action="" method="post">
		<div class="bloc_identifiant">
			<label for="identifiant">Identifiant</label>
			<input class="champtexte" type="text" id="identifiant" name="identifiant" value=""/>
		</div>
		<div class="bloc_motdepasse">
			<label for="motdepasse">Mot de passe</label>
			<input class="champtexte" type="text"  id="motdepasse" name="motdepasse" value=""/>
		</div>
		<div class="bloc_connexion">
			<input type="submit" id="seconnecter" value="Se connecter" />
		</div>
		</form>
	</div>
</body>
</html>