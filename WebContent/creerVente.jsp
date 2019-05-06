<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CreerVente</title>
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
		<h2>Nouvelle vente</h2>
	</div>
	<div class="container">
	<article class="card-body mx-auto" style="max-width: 500px;" > 
	<div class="form-group input-group">
		<form class="vente" action="<%=request.getContextPath()%>/vente?noUtilisateur=${utilisateur.noUtilisateur}" method="post">
			
			<div class="row align-items-center">
			<label for="article">Article</label>
			<div class="col"><input class="form-control" type="text" id="article" name="article" required/></div>
			</div><br /> 			
			
			<div class="row align-items-center">
			<label for="description">Description</label> 
			<div class="col"><textarea class="form-control" id="description" name="description" required></textarea></div>
			</div><br />
			
			<div class="row align-items-center">
			<label for="categorie">Categorie</label> 
			<div class="col"><select class="form-control" id="categorie" name="categorie" >
			<c:forEach items="<%=Categorie.values()%>" var="categorie">
    			<option value="${categorie.noCategorie}">${categorie.name}</option>
			</c:forEach></select></div></div><br />
			
			<div class="row align-items-center">
			<label for="photo">Photo de l'article</label> 
			<div class="col"><input class="form-control" type="file" id="photo" name="photo" /></div>
			</div><br />
			
			<div class="row align-items-center">
			<label for="miseAPrix">Mise à prix</label> 
			<div class="col"><input class="form-control" type="text" id="miseAPrix" name="miseAPrix" required/> </div></div><br />
			
			<div class="row align-items-center">
			<label for="debut">Début de l'enchère</label> 
			<div class="col"><input class="form-control" type="datetime-local" id="debut" name="debut" required/></div></div><br />
			
			<div class="row align-items-center">
			<label for="fin">Fin de l'enchère</label> 
			<div class="col"><input class="form-control" type="datetime-local" id="fin" name="fin" required/></div></div> <br />
			
			<div class="row align-items-center">
			<label for="rue">Rue</label> 
			<div class="col"><input class="form-control" type="text" id="rue" name="rue" value="${rue}" required/> </div></div><br/>
			
			<div class="row align-items-center">
			<label for="codePostal">Code postal</label> 
			<div class="col"><input class="form-control" type="text" id="codePostal" name="codePostal" value="${codePostal}" required/> </div></div><br />
			
			<div class="row align-items-center">
			<label for="ville">Ville</label> 
			<div class="col"><input class="form-control" type="text" id="ville" name="ville" value="${ville}" required /></div></div><br/>
			
			<input class="btn btn-primary " type="submit" id="enregistrer" name="action" value="Enregistrer">&nbsp;&nbsp;&nbsp;
			<input class="btn btn-primary " type="submit" id="annuler" name="action" value="Annuler" />
		</form>
 </div>
	</article>
	</div> 
</body>
</html>