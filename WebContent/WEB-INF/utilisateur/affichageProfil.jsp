<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	<title>Mon profil</title>
</head> 
<body class="text-center">
	<div id="page">
			<div class="container">
				<article class="card-body mx-auto" style="max-width: 400px;">
					<h1 class="card-title mt-3 text-center">ENI-Enchères</h1>
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
							<input ${modifier} value=${user.pseudo} id="pseudo" name="pseudo" class="form-control" placeholder="Pseudo" type="text">
						</div>
						<!-- form-group// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-user"></i>
								</span>
							</div>
							<input ${modifier} value=${user.nom} id="nom" name="nom" class="form-control" placeholder="Nom" type="text">
							<input ${modifier} value=${user.prenom} id="prenom" name="prenom" class="form-control" placeholder="Prenom" type="text">
						</div>
						<!-- form-group// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-envelope"></i>
								</span>
							</div>
							<input ${modifier} value=${user.email} id="email" name="email" class="form-control" placeholder="Email" type="email">
						</div>
						<!-- form-group// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-phone"></i>
								</span>
							</div>
							<input ${modifier} value=${user.telephone} id="telephone" name="telephone" class="form-control" placeholder="Teléphone" type="text">
						</div>
						<!-- form-group// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-home"></i>
								</span>
							</div>
							<input ${modifier} value=${user.rue} id="rue" name="rue" class="form-control" placeholder="Rue" type="text">
						</div>
						<!-- form-group end.// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-home"></i>
								</span>
							</div>
							<input ${modifier} value=${user.ville} id="ville" name="ville" class="form-control" placeholder="Ville" type="text"> <input
								id="codePostal" name="codePostal" class="form-control" placeholder="Code postal" type="text">
						</div>
						<!-- form-group end.// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-lock"></i>
								</span>
							</div>
							<input ${modifier} value=${user.motDePasse} id="motDePasse" name="motDePasse" class="form-control" placeholder="Mot de passe"
								type="password">
						</div>
						<!-- form-group// -->
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-lock"></i>
								</span>
							</div>
							<input ${modifier} value=${user.motDePasse} id="confirmation" name="confirmation" class="form-control" placeholder="Confirmation"
								type="password">
						</div>
					</form>
				</article>
			</div>
		</div>
</body>
</html>