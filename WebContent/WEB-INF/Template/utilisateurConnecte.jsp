<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bo.Utilisateur"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${utilisateur != null && cookie.connexion.value != '-1'}">
	<div class="p-2 text-dark">connecté en tant que : ${utilisateur.pseudo}</div>
</c:if>