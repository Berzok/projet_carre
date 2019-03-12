<%-- 
    Document   : header
    Created on : 12 mars 2019, 09:23:59
    Author     : prepain
--%>

<header>
    <div class="top-bar">
        <div class="top-bar-left">
            <ul class="dropdown menu" data-dropdown-menu>
                <li>
                    <img src="images/logo.png" alt="logo du site" />
                </li>
            </ul>
        </div>
        <div class="top-bar-right">
            <ul class="menu">

                <%

                // If the user is not connected
                if (session.getAttribute("is_connected") != null)
                    out.print("<li><a class='button alert' href='LogoutServlet'>Déconnexion</a></li>");

                %>

            </ul>
        </div>
    </div>
</header>