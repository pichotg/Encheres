<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bo.Formation"%>
<%@page import="bo.BOException"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formations</title>
<link rel="stylesheet" href="./themes/basique/style.css">
</head>
<body>
	<h1>TP Web - Erreur</h1>
	<hr id="header">
	<jsp:include page="/WEB-INF/Template/menu.jsp" />
	<section class="formations">
		<section class="formation">
			<section class="fond_titre">
				<h2>Erreur</h2>
			</section>
				<jsp:useBean id="erreur" class="bo.BOException"
					type="bo.BOException" scope="request" />
				<p>
					Une erreur s'est produite :
					<jsp:getProperty name="erreur" property="message" /></p>
		</section>
	</section>
	<a href=""><button>Retour à l'écran précédent</button></a>

</body>
</html>