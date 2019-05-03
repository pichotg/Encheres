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
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Liste des enchères</title>
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
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h2 class="display-6">Liste des enchères</h1>
	</div>
	<form class="row col-12" action="filtre" method="post">
		<div class="col-6">
			<div class="form-group">
				<label for="categorie">Filtres :</label>
				<input type="text" class="form-control" id="contient" name="contient" placeholder="Le nom de l'article">
			</div>
			<div class="form-group">
				<label for="categorie">Catégorie :</label>
				<select class="custom-select" name="categorie" id="categorie">
					<option selected>Toutes</option>
					<option>Informatique</option>
					<option>Ameublement</option>
					<option>Vêtement</option>
					<option>Sport et Loisirs</option>
				</select>
			</div>

		</div>
		<div class="col-6">
			<button type="submit" id="rechercher" value="rechercher"
				class="btn btn-primary btn-lg">Rechercher</button>
		</div>
	</form>
	<div >
		<div class="card-deck px-3 py-3 mb-3">
		
		<c:forEach var="enchere" items="${listeEncheres}">
			
			<div class="card mb-4" style="min-width: 400px;">
				<div class="row no-gutters">
					<div class="col-md-4">
	      				<img class="img-fluid" src="<%=request.getContextPath()%>/ressource/userlogin.jpg">
	   				 </div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">${enchere.noArticle.description}</h5>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">Mise à prix : ${enchere.noArticle.prixVente}</li>
								<li class="list-group-item">Fin de l'enchère : ${enchere.noArticle.dateFinEncheres}</li>
								<li class="list-group-item">Retrait : ${enchere.noUtilisateur.getAdresse()}</li>
								<li class="list-group-item">Vendeur :
									<a target="_blank"
										href="<%=request.getContextPath()%>/profil?id_utilisateur_recherche=${enchere.noArticle.utilisateur.noUtilisateur}">
										${enchere.noArticle.utilisateur.pseudo}
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
		</c:forEach>
		
		</div>
	</div>
</body>

</html>