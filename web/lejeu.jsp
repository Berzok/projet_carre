<%-- 
    Document   : index
    Created on : 12 mars 2019, 08:57:05
    Author     : ufriedri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet"   href="css/style.css" />
		<script src="script/script.js"></script>
    </head>
    <body id="jeu">
        <h1>Bienvenue dans le jeu du carré!</h1>
		
		<canvas id="terrain" style="background-color:#105993"></canvas>
		<br />
		<br />
		<!--
			<select id="choix">
				<option value="test1">Test 1</option>
			</select>
		-->
		
		<button id="new_game" type="button" onclick="begin('terrain', '0', '0')">Nouvelle partie</button>
		
    </body>
</html>
