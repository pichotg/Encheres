<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="../themes/basique/style.css">
<title>Connexion</title>
</head>
<body>
	<p class="identification">
		
		<%
		if(!request.getParameter("id").trim().equals(""))
			out.print("Connect� en tant que : " + request.getParameter("id"));
		else
			out.print("Vous n'�tes pas connect�");
	%>
	</p>

	<%@ include file="index.html"%>
</body>
</html>
