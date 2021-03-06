<%-- 
    Document   : admin
    Created on : 12 mars 2019, 09:43:10
    Author     : prepain
--%>
<%@page import="java.text.DecimalFormat"%>
<%

    if (session.getAttribute("is_connected") == null) {
        response.sendRedirect("index.jsp");
        return;
    } 

%>


<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>

    <body id="corpsadmin">
        <%@include file="header.jsp" %>
        <h1 style="text-align:center;">Page Administrateur</h1>
        
        <hr />
        
        <table style="width:30%; text-align:center;margin : 0 auto;">
            <thead>
                <tr>
                    <th>Nombre de coups</th>
                    <th>Nombre de parties</th>
                </tr>
            </thead>
            <tbody>

            <%
            String td = "<td>";
            String ctd = "</td>";
            String tr = "<tr>";
            String ctr = "</tr>";
            int nb_games = 0;
            double average = 0, sd = 0;

            Connection conn=null;
            Statement stmt=null;
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }catch(Exception e){out.print(e);}

            try{
                String jdbc="jdbc:mysql://localhost:3306/g7";
                String root="g7";
                String mdp="g7";
                conn=DriverManager.getConnection(jdbc,root,mdp);
                stmt=conn.createStatement();

                String sel="SELECT NB_MOVE, NB_GAME from GAME";
                
                // We take the number of moves and the number of games
                PreparedStatement st=conn.prepareStatement(sel);
                ResultSet res=st.executeQuery();
                while (res.next()) {
                    int nb_move = Integer.parseInt(res.getString("nb_move"));
                    
                    String nb_game = res.getString("nb_game");
                    out.println(tr + td + nb_move + ctd + td + nb_game + ctd + ctr);
                    
                    average += nb_move;
                    sd += nb_move * nb_move;
                    
                    nb_games++;
                }

                average /= nb_games;
                sd -= average * average;
                sd = Math.sqrt(sd);

            }catch(Exception e){out.print(e);}

            %>
            </tbody>
        </table>
            
        <hr />
            
        <table style="width:30%; text-align:center;margin : 0 auto;">
            <thead>
                <th>Moyenne</th>
                <th>Écart-type</th>
            </thead>
            <tbody>
                <%
                DecimalFormat df2 = new DecimalFormat(".##");
                out.print(tr + td + average + ctd + td + df2.format(sd) +ctd + ctr);
                %>
            </tbody>
        </table>
                                

        
    </body>
</html>
