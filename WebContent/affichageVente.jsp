<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../themes/basique/style.css">
<title>Vente</title>
</head>
<body>
	<div id="page">
		<h1>ENI-Enchères</h1>
		<h2>Detail Vente</h2>
		<label for="nom"><% %></label><br/>
		<label for="description">Description : <% %></label><br/>
		<label for="categorie">Catégorie  <% %></label><br/>
		<label for="meilleurOffre">Meilleur Offre : <% %></label><br/>
		<label for="miseAPrix">Mise à prix : <% %></label><br/>
		<label for="fin">Fin de l'enchère : <% %></label><br/>
		<label for="retrait">Retrait : <% %></label><br/>
		<label for="vendeur">Vendeur : <% %></label><br/>
		<label for="maProposition">Ma proposition : </label><select id="maProposition" name="maProposition"></select>
		<input type="submit" id="enrechir" name="enrechir" value="Encherir">
	</div>
</body>
</html>