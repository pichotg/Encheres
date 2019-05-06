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
	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}

		@media (min-width: 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}
	</style>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/basique/signin.css">

	<title>Connexion</title>
</head>

<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
	<form class="form-signin text-center" action="<%=request.getContextPath()%>/connexion" method="post">
		<img class="mb-4" src="<%=request.getContextPath()%>/ressource/userlogin.jpg" alt="" width="150" height="120">
		<h1 class="h3 mb-3 font-weight-normal"></h1>
		<c:if test="${'connexionerror' == error}">
			<div class="alert alert-danger" role="alert">
				Identifiant ou mots de passe Incorrect !
			</div>
		</c:if>
		<label for="inputEmail" class="sr-only">Email ou Pseudo</label>
		<input type="text" id="identifiant" name="identifiant" class="form-control" placeholder="Email ou pseudo"
			required autofocus>
		<label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="motdepasse" name="motdepasse" class="form-control" placeholder="Password" required>
		<div class="checkbox mb-3">
			<label>
				<input type="checkbox" id="remember-me" name="remember-me">Rester Connecter
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
		<p class="text-center">
			Pas encore de compte ? <a href="<%=request.getContextPath()%>/creerCompte.jsp">Créer compte</a>
			<p class="mt-5 mb-3 text-muted">&copy;ENI Encheres - 2019</p>
		</p>
	</form>
</body>

</html>