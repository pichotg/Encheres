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
<head>
	<meta charset="utf-8">
	<title>Ajouter une enchère</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
		
	<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>

<jsp:include page="WEB-INF/Template/navigation.jsp"></jsp:include>

<h4 class="text-center"><u> Détail vente</u></h4>
<div class="row justify-content-center" style="height: 200px;">
<div class="col-3">
<label>Nom</label><p>test<p>
</div>
<div class="col-3">
<label>Description</label><p>test<p>
</div>
<div class="col-3">
<label>Catégorie</label><p>test<p>
</div>
<div class="col-4">
<label>Meilleure offre</label><p>test<p>
</div>
<div class="col-4">
<label>Mise à prix</label><p>test<p>
</div>
<div class="col-4">
<label>Fin de l'enchère</label><p>test<p>
</div>
<div class="col-4">
<label>Retrait</label><p>test<p>
</div>
<div class="col-4">
<label>Vendeur</label><p>test</p>
</div>
<div class="col-4">
<form action="" class="form-signin">
<input type="number" name="enchere" id="enchere" >
<input type="submit" name="encherir" id="encherir" value="Enchérir">
</form>
</div>
	
</body>
</html>