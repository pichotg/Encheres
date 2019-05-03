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

<body>
		<div class="container">
			<article class="card-body mx-auto" style="max-width: 400px;">
				<h1 class="card-title mt-3 text-center">ENI-Enchères</h1>
				<h4 class="card-title mt-3 text-center">Profil</h4>
				<form action="<%=request.getContextPath()%>/modifierProfil" method="post">
					<div class="form-group">
						<label for="pseudo">Pseudo</label>
						<input value="${user.pseudo}" id="pseudo" name="pseudo" class="form-control" placeholder="Pseudo"
							type="text" disabled>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Nom</label>
							<input value="${user.nom}" id="nom" name="nom" class="form-control" placeholder="Nom"
								type="text" disabled>
						</div>
						<div class="form-group col-md-6">
							<label for="prenom">Prenom</label>
							<input value="${user.prenom}" id="prenom" name="prenom" class="form-control"
								placeholder="Prenom" type="text" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="email">Email</label>
						<input value="${user.email}" id="email" name="email" class="form-control" placeholder="Email"
							type="email" disabled>
					</div>
					<div class="form-group">
						<label for="telephone">Teléphone</label>
						<input value="${user.telephone}" id="telephone" name="telephone" class="form-control"
							placeholder="Teléphone" type="text" disabled>
					</div>
					<div class="form-group">
						<label for="rue">Rue</label>
						<input value="${user.rue}" id="rue" name="rue" class="form-control" placeholder="Rue" type="text"
							disabled>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="ville">Ville</label>
							<input value="${user.ville}" id="ville" name="ville" class="form-control" placeholder="Ville"
								type="text" disabled>
						</div>
						<div class="form-group col-md-6">
							<label for="inputCity">Code Postal</label>
							<input value="${user.codePostal}" id="codePostal" name="codePostal" class="form-control"
								placeholder="Code postal" type="text" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="motDePasse">Mot de passe</label>
						<input value="${user.motDePasse}" id="motDePasse" name="motDePasse" class="form-control"
							placeholder="Mot de passe" type="password" disabled>
					</div>
					<div class="form-group">
						<label for="confirmation">Confirmation</label>
						<input value="${user.motDePasse}" id="confirmation" name="confirmation" class="form-control"
							placeholder="Confirmation" type="password" disabled>
					</div>
					<input type="hidden"  value="${user.noUtilisateur}" id="noUtilisateur" name="noUtilisateur">
					<c:if test="${user.noUtilisateur == utilisateur.noUtilisateur}">
						<button type="submit" value="modifier" name="modifier" class="btn btn-primary">Modifier</button>
					</c:if>
				</form>
			</article>
		</div>
	</body>

</html>