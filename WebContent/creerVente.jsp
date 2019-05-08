<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="/WEB-INF/Template/utilisateurConnecte.jsp" />
	<title>CreerVente</title>

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ressource/js/moment.js"></script>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
		<h2>Nouvelle vente</h2>
	</div>
	<div class="container">
		<article class="card-body mx-auto" style="max-width: 500px;">
			<div class="form-group input-group">
				<form class="vente" action="<%=request.getContextPath()%>/vente?noUtilisateur=${utilisateur.noUtilisateur}"
					method="post" enctype='multipart/form-data'>

					<div class="row align-items-center">
						<label for="article">Article</label>
						<div class="col"><input class="form-control" type="text" id="article" name="article"
								placeholder="Nom de l'article" required /></div>
					</div><br />

					<div class="row align-items-center">
						<label for="description">Description</label>
						<div class="col"><textarea class="form-control" id="description" name="description"
								placeholder="Description détailler de l'objet " required></textarea></div>
					</div><br />

					<div class="row align-items-center">
						<label for="categorie">Categorie</label>
						<div class="col"><select class="form-control" id="categorie" name="categorie">
								<c:forEach items="<%=Categorie.values()%>" var="categorie">
									<option value="${categorie.noCategorie}">${categorie.name}</option>
								</c:forEach>
							</select></div>
					</div><br />

					<div class="row align-items-center">
						<label for="photo">Photo de l'article</label>
						<div class="col"><input class="form-control" type="file" id="photo" name="photo" /></div>
					</div><br />

					<div class="row align-items-center">
						<label for="miseAPrix">Mise à prix</label>
						<div class="col"><input class="form-control" type="number" id="miseAPrix" name="miseAPrix" min="1"
								placeholder="Prix de départ de l'enchere > 1€" required /> </div>
					</div><br />
					<div class="row align-items-center">
						<label for="debut">Début de l'enchère</label>
						<div class="col">
							<div class="input-group date" id="debut" data-target-input="nearest">
								<input type="text" class="form-control datetimepicker-input" name="debut" data-target="#debut" required>
								<div class="input-group-append" data-target="#debut" data-toggle="datetimepicker">
									<div class="input-group-text"><i class="fa fa-calendar"></i></div>
								</div>
							</div>
						</div>
					</div><br />
					<div class="row align-items-center">
						<label for="fin">Fin de l'enchère</label>
						<div class="col">
							<div class="input-group date" id="fin" data-target-input="nearest">
								<input type="text" class="form-control datetimepicker-input" name="fin" data-target="#fin" required>
								<div class="input-group-append" data-target="#fin" data-toggle="datetimepicker">
									<div class="input-group-text"><i class="fa fa-calendar"></i></div>
								</div>
							</div>
						</div>
					</div> <br />
					<script type="text/javascript">
						$(function () {
								$('#debut').datetimepicker({
										locale : 'fr',
										minDate : moment().format(),
								});
								$('#fin').datetimepicker({
										locale : 'fr',
										useCurrent: false
								});
								$("#debut").on("change.datetimepicker", function (e) {
										$('#fin').datetimepicker('minDate', e.date);
								});
								$("#fin").on("change.datetimepicker", function (e) {
										$('#debut').datetimepicker('maxDate', e.date);
								});
						});
					</script>
					<fieldset style="border: solid 1px black; margin-left : -20px">
						<legend style="width: auto; padding-left: 10px; padding-right: 10px;">Retrait</legend>
						<div class="control-group" style="padding-left:24px;padding-right:24px;">
							<div class="row align-items-center">
								<label for="rue">Rue</label>
								<div class="col"><input class="form-control" type="text" id="rue" name="rue" value="${rue}" required />
								</div>
							</div><br />

							<div class="row align-items-center">
								<label for="codePostal">Code postal</label>
								<div class="col"><input class="form-control" type="text" id="codePostal" name="codePostal"
										value="${codePostal}" required /> </div>
							</div><br />

							<div class="row align-items-center">
								<label for="ville">Ville</label>
								<div class="col"><input class="form-control" type="text" id="ville" name="ville" value="${ville}"
										required /></div>
							</div><br />
						</div>
					</fieldset><br />

					<input class="btn btn-primary " type="submit" id="enregistrer" name="action" value="Enregistrer">
					<input class="btn btn-primary " type="reset" value="Annuler">
					<!-- <a href="index.jsp"><button class="btn btn-primary ">Annuler</button></a> -->
				</form>
			</div>
		</article>
	</div>
</body>

</html>