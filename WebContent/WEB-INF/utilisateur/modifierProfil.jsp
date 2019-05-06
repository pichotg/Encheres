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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/themes/basique/signin.css">
    <title>Mon profil</title>
</head>

<body>
	<jsp:include page="/WEB-INF/Template/navigation.jsp" />
    <div class="container">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Modifier Profil</h4>
            <form action="<%=request.getContextPath()%>/modifierProfil" method="post">
                <div class="form-group">
                    <label for="pseudo">Pseudo</label>
                    <input value="${user.pseudo}" id="pseudo" name="pseudo" class="form-control" placeholder="Pseudo"
                        type="text">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail4">Nom</label>
                        <input value="${user.nom}" id="nom" name="nom" class="form-control" placeholder="Nom"
                            type="text">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="prenom">Prenom</label>
                        <input value="${user.prenom}" id="prenom" name="prenom" class="form-control"
                            placeholder="Prenom" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input value="${user.email}" id="email" name="email" class="form-control" placeholder="Email"
                        type="email">
                </div>
                <div class="form-group">
                    <label for="telephone">Teléphone</label>
                    <input value="${user.telephone}" id="telephone" name="telephone" class="form-control"
                        placeholder="Teléphone" type="text">
                </div>
                <div class="form-group">
                    <label for="rue">Rue</label>
                    <input value="${user.rue}" id="rue" name="rue" class="form-control" placeholder="Rue" type="text">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="ville">Ville</label>
                        <input value="${user.ville}" id="ville" name="ville" class="form-control" placeholder="Ville"
                            type="text">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputCity">Code Postal</label>
                        <input value="${user.codePostal}" id="codePostal" name="codePostal" class="form-control"
                            placeholder="Code postal" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label for="motDePasse">Mot de passe</label>
                    <input value="${user.motDePasse}" id="motDePasse" name="motDePasse" class="form-control"
                        placeholder="Mot de passe" type="password">
                </div>
                <div class="form-group">
                    <label for="confirmation">Confirmation</label>
                    <input value="${user.motDePasse}" id="confirmation" name="confirmation" class="form-control"
                        placeholder="Confirmation" type="password">
                </div>
                <div class="form-group">
                    <label for="credit">Crédit</label>
                    <input value="${user.credit}" id="credit" name="credit" class="form-control" placeholder="credit"
                        type="text" disabled>
                </div>
                <input type="hidden" value="${user.noUtilisateur}" id="noUtilisateur" name="noUtilisateur">
                <div class="btn-group btn-block"> 
                    <button type="submit" value="enregistrer" name="action" class="btn btn-success">Enregistrer</button>
                    <button type="submit" value="supprimer" name="action" class="btn btn-danger">Supprimer</button>
                    <button type="submit" value="annuler" name="action" class="btn btn-primary">Retour</button>
                </div>
            </form>
        </article>
    </div>
</body>

</html>