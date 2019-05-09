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
<style>
body{
	background-color: #f5f5f5;
}
</style>
<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<div class="container">
		<article class="card-body mx-auto" style="max-width: 400px;">
			<h4 class="card-title mt-3 text-center">Profil</h4>
			<form action="<%=request.getContextPath()%>/profil" method="post">
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
				<c:if test="${user.noUtilisateur == utilisateur.noUtilisateur}">
					<div class="for-row form-group">
						<label for="inputCity">Vos crédits disponibles</label>
						<input value="${user.credit}" id="credit" name="credit" class="form-control"
							placeholder="credit" type="text" disabled>
					</div>
				</c:if>
				<input type="hidden" value="${user.noUtilisateur}" id="noUtilisateur" name="noUtilisateur">
				<c:if test="${user.noUtilisateur == utilisateur.noUtilisateur}">
					<button type="submit" value="modifier" name="modifier" class="btn btn-primary">Modifier</button>
				</c:if>
			</form>
		</article>
	</div>
</body>

</html>