<form class="connexion" action="${pageContext.request.contextPath}/stagiaire" method="post">
	<div>
		<label for="identifiant">Identifiant</label> 
		<input class="input" type="text" id="identifiant" name="id" required="required"><br>
	</div>
	<div>
		<label for="password">Mot de passe</label> 
		<input class="input" type="text" id="password" name="pwd" required="required"><br>
	</div>
	<input id="connexion" class="button" type="submit" value="Se connecter"><br>
</form>