<%-- 
    Document   : header
    Created on : 12 mars 2019, 09:23:59
    Author     : prepain
--%>

<header>
	<link rel="stylesheet"   href="css/style.css">
    <div class="top-bar" id="topbar">
        <div class="top-bar-left" id="topbar">
            <ul class="dropdown menu" data-dropdown-menu id="topbar">
                <li>
                    <img src="images/logo.png" alt="logo du site" />
                </li>
            </ul>
        </div>
        <div class="top-bar-right" id="topbar">
            <ul class="menu" id="topbar">

                <%

                // If the user is not connected
                if (session.getAttribute("is_connected") != null)
                    out.print("<li><a class='button alert' href='LogoutServlet'>Déconnexion</a></li>");

                %>

            </ul>
        </div>
    </div>
</header>