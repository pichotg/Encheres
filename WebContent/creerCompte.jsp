<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	<title>CreerCompte</title>
</head>
<style>
body{
	background-color: #f5f5f5;
}
</style>
<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<div id="page">
		<div class="container text-center">
			<article class="card-body mx-auto" style="max-width: 400px;">
				<h4 class="card-title mt-3 text-center">Créer un compte</h4>
				<form action="<%=request.getContextPath()%>/creerCompte" method="post">
					<c:if test="${'alreadyExist' == error}">
						<div class="alert alert-danger" role="alert">Identifiant ou
							mail exist déjà !</div>
					</c:if>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<input id="pseudo" name="pseudo" class="form-control" placeholder="Pseudo" type="text">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<input id="nom" name="nom" class="form-control" placeholder="Nom" type="text">
						<input id="prenom" name="prenom" class="form-control" placeholder="Prenom" type="text">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-envelope"></i>
							</span>
						</div>
						<input id="email" name="email" class="form-control" placeholder="Email" type="email">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-phone"></i>
							</span>
						</div>
						<input id="telephone" name="telephone" class="form-control" placeholder="Teléphone" type="text">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-home"></i>
							</span>
						</div>
						<input id="rue" name="rue" class="form-control" placeholder="Rue" type="text">
					</div>
					<!-- form-group end.// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-home"></i>
							</span>
						</div>
						<input id="ville" name="ville" class="form-control" placeholder="Ville" type="text"> <input
							id="codePostal" name="codePostal" class="form-control" placeholder="Code postal" type="text">
					</div>
					<!-- form-group end.// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input id="motDePasse" name="motDePasse" class="form-control" placeholder="Mot de passe"
							type="password">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input id="confirmation" name="confirmation" class="form-control" placeholder="Confirmation"
							type="password">
					</div>
					<!-- form-group// -->
					<div class="form-group">
						<button type="submit" id="creer" name="creer" class="btn btn-primary btn-block">
							Create Account</button>
					</div>
					<!-- form-group// -->
					<p class="text-center">
						Déjà un compte ? <a href="<%=request.getContextPath()%>/connexion.jsp">Connexion</a>
						<p class="mt-5 mb-3 text-muted">&copy;ENI Encheres - 2019</p>
					</p>
				</form>
			</article>
		</div>
	</div>
</body>

</html>