<%-- 
    Document   : index
    Created on : 12 mars 2019, 09:22:21
    Author     : prepain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        
        <div class="card" style="width: 300px; margin:10px auto;text-align:center;">
        <div class="card-divider">
            <h3 style="width: 100%;">Connexion</h3>
        </div>

        <div class="card-section">
            <img src="images/profil.png">
        </div>

        <div class="card-section">
            <form method="POST" action="LoginServlet">

            <label for="login">Login</label>
            <input type="text" name="login" id="login" />

            <label for="password">Mot de passe</label>
            <input type="password" name="password" id="password" />

            <input type="submit" class="button expanded" value="Se connecter" />

            </form>
        </div>

    </div>
    </body>
</html>
