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
	<META HTTP-EQUIV="Refresh" CONTENT="TRUE">
</head>

<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-6">Liste des enchères</h1>
	</div>
	<form class="row col-12" action="filtre" method="post">
		<div class="col-6" style="min-width: 230px;">
			<div class="form-group">
				<label for="categorie">Le nom contient :</label>
				<c:if test="${param.contient == null}">
					<input type="text" class="form-control" id="contient" name="contient"
						placeholder="Le nom de l'article contient">
				</c:if>
				<c:if test="${param.contient != null}">
					<input type="text" class="form-control" id="contient" name="contient" value="${param.contient}">
				</c:if>
			</div>
			<div class="form-group">
				<label for="categorie">Catégorie :</label> <select class="custom-select" name="categorie"
					id="categorie">
					<option <c:if test="${param.categorie == null or param.categorie == 'Toutes'}">selected</c:if>
						>Toutes</option>
					<option <c:if test="${param.categorie == 'Informatique'}">selected</c:if>>Informatique</option>
					<option <c:if test="${param.categorie == 'Ameublement'}">selected</c:if>>Ameublement</option>
					<option <c:if test="${param.categorie == 'Vêtement'}">selected</c:if>>Vêtement</option>
					<option <c:if test="${param.categorie == 'Sport&Loisirs'}">selected</c:if>>Sport&Loisirs</option>
				</select>
			</div>
			<c:if test="${utilisateur != null && cookie.connexion.value != '-1'}">
				<!--Achats-->
				<div class="row">
					<div class="form-group col">
						<div class="custom-control custom-radio">
							<input type="radio" id="achatsRadio" name="achatsVentesRadio" class="custom-control-input"
								value="achatsRadio" <c:if test="${param.achatsVentesRadio == 'achatsRadio'}">checked</c:if>> <label class="custom-control-label"
								for="achatsRadio">Achats</label>
						</div>
						<div class="ml-3">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxAchats1"
									name="checkBoxAchats1" <c:if test="${param.checkBoxAchats1 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxAchats1">enchères
									ouvertes</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxAchats2"
									name="checkBoxAchats2" <c:if test="${param.checkBoxAchats2 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxAchats2">mes
									enchères en cours</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxAchats3"
									name="checkBoxAchats3" <c:if test="${param.checkBoxAchats3 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxAchats3">mes
									enchères remportées</label>
							</div>
						</div>
					</div>
					<!--Mes Ventes-->
					<div class="form-group col">
						<div class="custom-control custom-radio">
							<input type="radio" id="ventesRadio" name="achatsVentesRadio" class="custom-control-input"
								value="ventesRadio" <c:if test="${param.achatsVentesRadio == 'ventesRadio'}">checked</c:if>> <label class="custom-control-label" for="ventesRadio">Mes
								ventes</label>
						</div>
						<div class="ventes ml-3">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxVentes1"
									name="checkBoxVentes1" <c:if test="${param.checkBoxVentes1 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxVentes1">mes
									ventes en cours</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxVentes2"
									name="checkBoxVentes2" <c:if test="${param.checkBoxVentes2 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxVentes2">ventes
									non débutées</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxVentes3"
									name="checkBoxVentes3" <c:if test="${param.checkBoxVentes3 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxVentes3">ventes
									terminées</label>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					var RadioAchat = document.getElementById("achatsRadio");
					var RadioVentes = document.getElementById("ventesRadio")

					RadioAchat.onclick = function () {
						$("#checkBoxAchats1").attr("disabled", false);
						$("#checkBoxAchats2").attr("disabled", false);
						$("#checkBoxAchats3").attr("disabled", false);

						$("#checkBoxVentes1").attr("disabled", true).prop("checked", false);
						$("#checkBoxVentes2").attr("disabled", true).prop("checked", false);
						$("#checkBoxVentes3").attr("disabled", true).prop("checked", false);
					}

					RadioVentes.onclick = function () {
						$("#checkBoxVentes1").attr("disabled", false);
						$("#checkBoxVentes2").attr("disabled", false);
						$("#checkBoxVentes3").attr("disabled", false);

						$("#checkBoxAchats1").attr("disabled", true).prop("checked", false);
						$("#checkBoxAchats2").attr("disabled", true).prop("checked", false);
						$("#checkBoxAchats3").attr("disabled", true).prop("checked", false);
					}
				</script>
			</c:if>
		</div>
		<div class="col-6">
			<button type="submit" id="rechercher" value="rechercher" class="btn btn-primary btn-lg">Rechercher</button>
		</div>
	</form>
	<div>
		<div class="card-deck px-3 py-3 mb-3">
			<c:forEach var="enchere" items="${listeEncheres}">
				<div class="card mb-4" style="min-width: 400px;">
					<form action="<%=request.getContextPath()%>/detailVente" method="post">
						<div class="row no-gutters">
							<div class="col-md-4">
								<img class="img-fluid" src="<%=request.getContextPath()%>/ressource/userlogin.jpg">
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">${enchere.noArticle.description}</h5>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">Mise à prix :
											${enchere.noArticle.prixVente}</li>
										<li class="list-group-item">Fin de l'enchère :
											${enchere.noArticle.affichageDateFin()}</li>
										<li class="list-group-item">Retrait :
											${enchere.noUtilisateur.getAdresse()}</li>
										<li class="list-group-item">Vendeur : <a target="_blank"
												href="<%=request.getContextPath()%>/profil?id_utilisateur_recherche=${enchere.noArticle.utilisateur.noUtilisateur}">
												${enchere.noArticle.utilisateur.pseudo} </a>
										</li>
										<li class="list-group-item">
											<button class="btn btn-lg btn-primary btn-block" type="submit">Détail de la
												vente</button>
										</li>
									</ul>
									<input type="hidden" value="${enchere.noArticle.noArticle}" id="noArticle"
										name="noArticle">
								</div>
							</div>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>