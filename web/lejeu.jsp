<%-- 
    Document   : index
    Created on : 12 mars 2019, 08:57:05
    Author     : ufriedri
--%>

<%

    if (session.getAttribute("is_connected") == null) {
        response.sendRedirect("index.jsp");
        return;
    } 

%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<%@include file="head.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="shortcut icon" href="res/itembox.png">
		<link rel="stylesheet"   href="css/style.css" />
    </head>
	<%@include file="header.jsp" %>
    <body id="jeu">
		
        <h1 style="border-bottom: 2px solid black">Bienvenue dans le jeu du carré!</h1>
		
		<p id="essais"></p>
		<br />
		<br />
	
		<div id="area">
			
			
			<div id="button_area">
			
				<button id="new_game" type="button" onclick="begin('terrain', '0', '0')">Nouvelle partie</button>
				
			</div>
	
			<canvas id="terrain" style="background-color:#105993"></canvas>
			<!--
				<select id="choix">
					<option value="test1">Test 1</option>
				</select>
			-->
		
			<table id="scores">
				<tr>
					<th>N° partie</th>
					<th>Nombre de coups</th>
				</tr>
			</table>
			
			
		</div>
		
    </body>
</html>
<script src="script/script.js"></script>
