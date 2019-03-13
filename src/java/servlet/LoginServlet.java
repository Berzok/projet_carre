/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author prepain
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
			System.out.println(login);
			System.out.println(password);
            
            

            Connection conn=null;
            Statement stmt=null;
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }catch(Exception e){out.print(e);}

            try{
                // Connection to the database
                String jdbc="jdbc:mysql://localhost:3306/g7";
                String root="g7";
                String mdp="g7";
                conn=(Connection) DriverManager.getConnection(jdbc,root,mdp);
                stmt=(Statement) conn.createStatement();

                // preparing the request, to check if the password is correct
                String sel="SELECT mdp from ACCOUNT WHERE LOGIN LIKE ?";
                PreparedStatement st=(PreparedStatement) conn.prepareStatement(sel);
                st.setString(1,login);
                ResultSet res=st.executeQuery();

                if (res.next() && res.getString("mdp").equals(password)) {
                    HttpSession session = request.getSession();

                    session.setAttribute("is_connected", true);
                    session.setAttribute("login", login);
					if(login.equals("admin"))
						response.sendRedirect("admin.jsp");
					else
						response.sendRedirect("lejeu.jsp");
                    return;
                }
                else {// password incorrect
                    response.sendRedirect("index.jsp");
                    return;
                }

            }catch(Exception e){out.print(e);}




            
            
            
            
            
            
            /*
            if (login.equals("user") && password.equals("user"))
            {
                request.setAttribute("is_connected", true);
                request.setAttribute("login", login);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("user.jsp") ;
                requestDispatcher.forward(request, response) ;
            }
            if (login.equals("admin") && password.equals("admin"))
            {
                request.setAttribute("is_connected", true);
                request.setAttribute("login", login);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp") ;
                requestDispatcher.forward(request, response) ;
            }
            else
            {

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp") ;
                requestDispatcher.forward(request, response) ;
            }*/
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
