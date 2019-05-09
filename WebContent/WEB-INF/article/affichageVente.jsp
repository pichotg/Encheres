<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="bo.Utilisateur"%>
<%@page import="bo.ArticleVendu"%>
<%@page import="bo.Enchere"%>
<%@page import="bo.Categorie"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Détail de la vente</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="./themes/basique/style.css">
<META HTTP-EQUIV="Refresh" CONTENT="TRUE">
</head>
<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<jsp:include page="/WEB-INF/Template/utilisateurConnecte.jsp" />
	<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-6">Détail de la vente</h1>
	</div>
		<div class="card-deck px-3 py-3 mb-3">
		
			<div class="card mb-4" style="min-width: 400px;">
				<div class="row no-gutters">
					<div class="col-md-4">
					<c:if test="${not empty cheminEnchere}">
						<img class="img-fluid" src="<%=request.getContextPath()%>/ressource/imageArticle/${cheminEnchere}">
					</c:if>
					<c:if test="${empty cheminEnchere}">
						<img class="img-fluid" src="<%=request.getContextPath()%>/ressource/imageArticle/imageIndisponible.png">
					</c:if> 
					</div>
					<div class="col-md-8">
						<h1 class="card-title">${article.nomArticle}</h1>
						<div class="card-body">
							<form action="<%=request.getContextPath()%>/ajouterEnchere"	method="post">
								<h5 class="card-title">${article.description}</h5>
								<input type="hidden" name="noArticle"value="${article.noArticle}">
								<ul class="list-group list-group-flush">
									<li class="list-group-item">Catégorie : ${Categorie.getNameByNo(article.categorie)}</li>
									<li class="list-group-item">Meilleure offre : ${enchereMax.montantEnchere} ${utilisateurMax}</li>
									<li class="list-group-item">Mise à prix : ${article.miseAPrix}</li>
									<li class="list-group-item">Fin de l'enchère : ${article.affichageDateFin()}</li>
									<li class="list-group-item">Retrait : <a href="http://maps.google.com/?q=${article.utilisateur.getAdresse()}"
										target="_blank">${article.utilisateur.getAdresse()}</a></li>
									<li class="list-group-item">Vendeur : <a target="_blank" href="<%=request.getContextPath()%>/profil?id_utilisateur_recherche=${article.utilisateur.noUtilisateur}">
											${article.utilisateur.pseudo} </a>
									</li>
									<c:if
										test="${utilisateur != null && cookie.connexion.value != '-1' && utilisateur.noUtilisateur != article.utilisateur.noUtilisateur}">
										<c:if
											test="${montantEnchere != 0 && montantEnchere < enchereMax}">
											<li class="list-group-item">Enchère actuelle :
												${enchereMax}</li>
											<li class="list-group-item">Ma proposition : <input
												type="number" value="${enchereMax + 1}" name="enchere"
												style="text-align: center;"></li>
										</c:if>
										<c:if test="${montantEnchere == 0}">
											<li class="list-group-item">Vous n'avez pas encore
												enchéri sur cette vente</li>
											<li class="list-group-item">Enchère actuelle :
												${enchereMax}</li>
											<li class="list-group-item">Ma proposition : <input
												type="number" value="${enchereMax +  1}" name="enchere"
												style="text-align: center;"></li>
										</c:if>
										<c:if test="${montantEnchere == enchereMax}">
											<li class="list-group-item">Enchère actuelle :
												${enchereMax}</li>
											<li class="list-group-item">Votre enchère est la plus
												haute actuellement</li>
										</c:if>
										<c:if test="${montantEnchere != enchereMax}">
											<button class="btn btn-lg btn-primary btn-block"
												type="submit">Enchérir</button>
										</c:if>
										
									</c:if>
								</ul>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>