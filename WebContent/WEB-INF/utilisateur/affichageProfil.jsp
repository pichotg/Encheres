<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="bo.Utilisateur"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/basique/style.css">
<title>Détail du profil</title>
</head>
<body>
<div id="page">
		<h1>ENI-Enchères</h1>
		<h2>${user.pseudo}</h2>
		<label for="pseudo"> ${user.pseudo} </label><br/>
		<label for="nom"> ${user.nom}</label><br/>
		<label for="prenom"> ${user.prenom}</label><br/>
		<label for="email"> ${user.email}</label><br/>
		<label for="telephone"> ${user.telephone}</label><br/>
		<label for="rue">  ${user.rue}</label><br/>
		<label for="codePostal"> ${user.codePostal}</label><br/>
		<label for="ville">  ${user.ville}</label><br/>
</div>
</body>
</html>