<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${langue == 'fr'}">
<fmt:setLocale value ="fr"/>
</c:if> 
<c:if test="${langue == 'en'}">
<fmt:setLocale value ="en"/>
</c:if> 
<fmt:setBundle basename ="servlets.multilingue.Langue"/>
<fmt:setBundle basename ="servlets.multilingue.Langue"/>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark border-bottom shadow-sm">
	<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><fmt:message key="ENI.Encheres" /></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
		aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		<!-- Le menu lorsqu'on est connecté, à savoir lorsque l'utilisateur connecté n'est pas null et que le cookie ne vaut pas -1 -->
			<c:if test="${utilisateur != null && cookie.connexion.value != '-1'}">
				<li class="nav-item">
					<a class="p-2 text-light" href="<%=request.getContextPath()%>/deconnexion"><fmt:message key="Deconnexion" /></a>
				</li>
				<li class="nav-item">
					<a class="p-2 text-light" href="<%=request.getContextPath()%>/accesVente?noUtilisateur=${utilisateur.noUtilisateur}"><fmt:message key="Vendre.un.article" /></a>
				</li>
				<li class="nav-item">
					<a class="p-2 text-light" href="<%=request.getContextPath()%>/profil?id_utilisateur_recherche=${utilisateur.noUtilisateur}"><fmt:message key="Mon.profil" /></a>
				</li>
			</c:if>
		<!-- Le menu lorsqu'on est déconnecté, à savoir lorsque l'utilisateur connecté est null ou que le cookie vaut -1 -->
			<c:if test="${utilisateur == null || cookie.connexion.value == '-1'}">
				<li class="nav-item">
					<a class="p-2 text-light" href="<%=request.getContextPath()%>/connexion.jsp"><fmt:message key="Connexion" /></a>
				</li>
				<li class="nav-item">
					<a class="p-2 text-light" href="<%=request.getContextPath()%>/creerCompte.jsp"><fmt:message key="Inscription" /></a>
				</li>
			</c:if>
		</ul>
		<c:if test="${utilisateur != null && cookie.connexion.value != '-1'}">
				<span class="navbar-text">
					<a class="p-2 text-light" href="<%=request.getContextPath()%>/profil?id_utilisateur_recherche=${utilisateur.noUtilisateur}">${utilisateur.pseudo}</a>
				</span>
		</c:if>	
	</div>
	<form action="<%=request.getContextPath()%>/langue" method ="get"><button type="submit" name="Langue" title="Envoyer"  style = "background-color : rgb(33, 37, 41)" value="fr"><img src="/TrocEncheres/ressource/imageArticle/fr.png" alt="" /></button></form>
	<form action="<%=request.getContextPath()%>/langue" method ="get"><button type="submit" name="Langue" title="Envoyer" style = "background-color : rgb(33, 37, 41)" value="en"><img src="/TrocEncheres/ressource/imageArticle/uk.png" alt="" /></button></form>
</nav>