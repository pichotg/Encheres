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
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${langue == 'fr'}">
<fmt:setLocale value ="fr"/>
</c:if> 
<c:if test="${langue == 'en'}">
<fmt:setLocale value ="en"/>
</c:if> 
<fmt:setBundle basename ="servlets.multilingue.Langue"/>
<fmt:setBundle basename ="servlets.multilingue.Langue"/>


<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<title><fmt:message key="Liste.des.encheres" /></title>
	<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
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
	
	<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h1 class="display-6"><fmt:message key="Liste.des.encheres"/></h1>
	</div>
	<form class="row col-12" action="filtre" method="post">
		<div class="col-6" style="min-width: 230px;">
			<div class="form-group">
				<label for="categorie"><fmt:message key="Le.nom.contient"/> :</label>
				<c:if test="${param.contient == null}">
					<input type="text" class="form-control" id="contient" name="contient"
						placeholder="<fmt:message key="Le.nom.de.l'article.contient" />">
				</c:if>
				<c:if test="${param.contient != null}">
					<input type="text" class="form-control" id="contient" name="contient" value="${param.contient}">
				</c:if>
			</div>
			<div class="form-group">
				<label for="categorie"><fmt:message key= "Categorie" /> :</label> <select class="custom-select" name="categorie"
					id="categorie">
					<option <c:if test="${param.categorie == null or param.categorie == 'Toutes'}">selected</c:if>
						><fmt:message key="Toutes" /></option>
					<option <c:if test="${param.categorie == 'Informatique'}">selected</c:if>><fmt:message key="Informatique" /></option>
					<option <c:if test="${param.categorie == 'Ameublement'}">selected</c:if>><fmt:message key="Ameublement" /></option>
					<option <c:if test="${param.categorie == 'Vêtement'}">selected</c:if>><fmt:message key="Vetement" /></option>
					<option <c:if test="${param.categorie == 'Sport&Loisirs'}">selected</c:if>><fmt:message key="Sport.et.loisirs" /></option>
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
									for="checkBoxAchats1">Enchères
									ouvertes</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxAchats2"
									name="checkBoxAchats2" <c:if test="${param.checkBoxAchats2 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxAchats2">Mes
									enchères en cours</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxAchats3"
									name="checkBoxAchats3" <c:if test="${param.checkBoxAchats3 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxAchats3">Mes
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
									for="checkBoxVentes1">Mes
									ventes en cours</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxVentes2"
									name="checkBoxVentes2" <c:if test="${param.checkBoxVentes2 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxVentes2">Ventes
									non débutées</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input" id="checkBoxVentes3"
									name="checkBoxVentes3" <c:if test="${param.checkBoxVentes3 == 'on'}">checked</c:if>> <label class="custom-control-label"
									for="checkBoxVentes3">Ventes
									terminées</label>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					var RadioAchat = document.getElementById("achatsRadio");
					var RadioVentes = document.getElementById("ventesRadio");
					var RadioAchatEtat = $('input[id="achatsRadio"]').is(':checked');
					var RadioVentesEtat = $('input[id="ventesRadio"]').is(':checked');
					    
						if(!RadioAchatEtat && !RadioVentesEtat){
					    	$("#checkBoxAchats1").attr("disabled", true);
							$("#checkBoxAchats2").attr("disabled", true);
							$("#checkBoxAchats3").attr("disabled", true);
							$("#checkBoxVentes1").attr("disabled", true);
							$("#checkBoxVentes2").attr("disabled", true);
							$("#checkBoxVentes3").attr("disabled", true);
					    }

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
			<button type="submit" id="rechercher" value="rechercher"
				class="btn btn-primary btn-lg"><fmt:message key="Rechercher" /></button>
			<div class="float-right d-none d-sm-block d-sm-none d-md-block">
				<button id="liste" class="btn btn-outline-dark btn-lg" type="button"
					data-toggle="popover" data-placement="top" data-trigger="hover"
					data-content="Vue en liste">
					<span> <i class="fas fa-align-justify"></i>
					</span>
				</button>
				<button id="grille" class="btn btn-outline-dark btn-lg" type="button"
					data-toggle="popover" data-placement="top" data-trigger="hover"
					data-content="Vue en grille">
					<span> <i class="fas fa-grip-horizontal"></i>
					</span>
				</button>
			</div>
			<script>
				$(document).ready(function() {
					$('[data-toggle="popover"]').popover();
				});

				$("#liste").click(function() {
					$("#listEncheres").addClass("container");
					$(".maCard").css({
						"min-width" : "100%"
					});
				});

				$("#grille").click(function() {
					$("#listEncheres").removeClass("container");
					$(".maCard").css({
						"min-width" : "450px"
					});
				});
			</script>
		</div>
	</form>
	<div id="listEncheres">
		<div class="card-deck p-3 m-3">
			<c:forEach var="enchere" items="${listeEncheres}">
				<div class="card mb-4" style="min-width: 480px;">
					<form action="<%=request.getContextPath()%>/detailVente?cheminEnchere=${f:substringAfter(enchere.noArticle.pathImage, 'imageArticle\\')}" method="post">
						<div class="row no-gutters">
							<div class="col-md-4">
							<c:if test="${not empty f:substringAfter(enchere.noArticle.pathImage, 'imageArticle')}">
								<img class="img-thumbnail" src="<%=request.getContextPath()%>/imageArticle/${f:substringAfter(enchere.noArticle.pathImage, 'imageArticle\\')}">
							</c:if>
							<c:if test="${empty f:substringAfter(enchere.noArticle.pathImage, 'imageArticle')}">
								<img class="img-thumbnail" src="<%=request.getContextPath()%>/imageArticle/imageIndisponible.png">
							</c:if> 
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">${enchere.noArticle.nomArticle}</h5>
									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><fmt:message key="Mise.a.prix" /></span>
										</div>
										<input type="text" class="form-control" value="${enchere.noArticle.miseAPrix}" disabled>
										<div class="input-group-append">
    										<span class="input-group-text"><i class="fas fa-euro-sign"></i>
											</span>
  										</div>
									</div>
									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><fmt:message key="Enchere.actuelle" /></span>
										</div>
										<input type="text" class="form-control" value="${enchere.montantEnchere}" disabled>
										<div class="input-group-append">
    										<span class="input-group-text"><i class="fas fa-euro-sign"></i>
											</span>
  										</div>
									</div>
									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><fmt:message key="Fin.de.l.enchere" /></span>
										</div>
										<input type="text" class="form-control" value="${enchere.noArticle.affichageDateFin()}" disabled>
										<div class="input-group-append">
    										<span class="input-group-text"><i class="fas fa-clock"></i></i>
											</span>
  										</div>
									</div>
									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"><fmt:message key="Retrait" /></span>
										</div>
										<input type="text" class="form-control" value="${enchere.noUtilisateur.getAdresse()}" disabled>
										<div class="input-group-append">
    										<span class="input-group-text"><i class="fas fa-shipping-fast"></i>
											</span>
  										</div>
									</div>
									<div class="form-group input-group"  onclick="location.href='<%=request.getContextPath()%>/profil?id_utilisateur_recherche=${enchere.noArticle.utilisateur.noUtilisateur}';">
										<div class="input-group-prepend">
											<span class="input-group-text"><fmt:message key="Vendeur" /></span>
										</div>
										<input id="hoverinput" type="text" class="form-control" value="${enchere.noArticle.utilisateur.pseudo}" disabled>
										<div class="input-group-append">
    										<span class="input-group-text"><i class="fas fa-user-tie"></i>
											</span>
  										</div>
									</div>
									<button class="btn btn-outline-dark btn-block" type="submit">
										<fmt:message key="Detail.de.la.vente" />
									</button>
								</div>
							</div>
						</div>
						<input type="hidden" value="${enchere.noArticle.noArticle}"
							id="noArticle" name="noArticle">
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>