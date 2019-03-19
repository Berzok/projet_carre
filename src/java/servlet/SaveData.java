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
import static java.lang.System.out;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ufriedri
 */
@WebServlet(name = "SaveData", urlPatterns = {"/SaveData"})
public class SaveData extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @throws java.sql.SQLException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		Connection connexion = null;
		Statement stmt = null;
		
		out.print("ID: " + request.getParameter("id_partie"));
		out.print("N°: " + request.getParameter("moves"));
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(Exception e){out.print(e);}
		
		
		
		try (PrintWriter out = response.getWriter()) {
			String jdbc = "jdbc:mysql://localhost:3306/g7";
			String user = "g7";
			String mdp = "g7";
			connexion = (Connection) DriverManager.getConnection(jdbc, user, mdp);
			
			stmt = (Statement) connexion.createStatement();
			String id_game = String.valueOf(request.getParameter("id_partie"));
			String nb_moves = String.valueOf(request.getParameter("moves"));
			
			String ins = "INSERT INTO GAME(nb_move, login_user, nb_game) VALUES(?, ?, ?)";
			PreparedStatement ps = (PreparedStatement) connexion.prepareStatement(ins);
			ps.setString(1, nb_moves);
			ps.setString(2, "user");
			ps.setString(3, id_game);
			
			stmt.executeUpdate(ins);
			
			
			
			
		}
	}
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
		}
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
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
		}
    }	
}
