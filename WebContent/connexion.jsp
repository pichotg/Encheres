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
<body class="text-center">
	<form class="form-signin" action="<%=request.getContextPath()%>/connexion" method="post">
	<img class="mb-4" src="<%=request.getContextPath()%>/ressource/userlogin.jpg" alt="" width="150" height="120">
	<h1 class="h3 mb-3 font-weight-normal"></h1>
	<c:if test="${'connexionerror' == error}">
		<div class="alert alert-danger" role="alert">
			Identifiant ou mots de passe Incorrect !
		</div>
	</c:if>
	<label for="inputEmail" class="sr-only">Email ou Pseudo</label>
	<input type="text" id="inputEmail" class="form-control" placeholder="Email ou pseudo" required autofocus>
	<label for="inputPassword" class="sr-only">Password</label>
	<input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
	<div class="checkbox mb-3">
		<label>
			<input type="checkbox" value="remember-me">Rester Connecter
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