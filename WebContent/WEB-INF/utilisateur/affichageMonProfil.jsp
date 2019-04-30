<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Mon profil</title>
</head>
<body>
 <h1>Mon Profil</h1>
 <div id="profil">
	<h2>${user.pseudo}</h2>
	<c:if test="${'Modifier' == param.ActionModif}">
	<form action="">
		<label for="pseudo"> ${user.pseudo} </label><br/>
		<label for="nom"> ${user.nom}</label><br/>
		<label for="prenom"> ${user.prenom}</label><br/>
		<label for="email"> ${user.email}</label><br/>
		<label for="telephone"> ${user.telephone}</label><br/>
		<label for="rue">  ${user.rue}</label><br/>
		<label for="codePostal"> ${user.codePostal}</label><br/>
		<label for="ville">  ${user.ville}</label><br/>
		<input class="modif" type="submit" value="Modifier" name="modifier">
	</form>
	</c:if>
</div>
</body>
</html>