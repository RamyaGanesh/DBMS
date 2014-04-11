/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Project1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SQLpooler", urlPatterns = {"/SQLpooler"})
public class SQLpooler extends HttpServlet {

    public static Connection makeMyConnection() throws SQLException, ClassNotFoundException{
		//URL of Oracle database server
                Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl"; 

		//properties for creating connection to Oracle database
		Properties props = new Properties();
		props.setProperty("user", <username>);
		props.setProperty("password", <password>);

		//creating connection to Oracle database using JDBC
		Connection conn = DriverManager.getConnection(url,props);
		return conn;
	}
    
    
    protected void LoginController (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException
    {
        String logintype = request.getParameter("logintype");
        switch (logintype)
                {
                    case "student":
                    StudentLoginValid(request, response);
                    break;
                    case "doctor":
                    DoctorLoginValid(request, response);
                    break;
                    default:
                    NurseLoginValid(request, response);
                  }
        
    }
    
    protected void StudentLoginValid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
             
        Connection conn = makeMyConnection();
        int studentid= 0;
		try{
                       try{
                       studentid = Integer.parseInt (request.getParameter("userid").toString());
                       }
                       catch (NumberFormatException nfe)
                       {
                         request.setAttribute("message", "sorry invalid username/password.Please try again");
                       }
                              
                        String passw = request.getParameter("password").toString();
                        String sql ="select * from students where studentid = " + studentid + "and password = '" + 
                                passw + "'";
			//creating PreparedStatement object to execute query
			PreparedStatement preStatement = conn.prepareStatement(sql);
			//executing the query and then committing it before closing in the finally block.
			ResultSet result = preStatement.executeQuery();		
			if (!result.next() ) {   
                             request.setAttribute("message","sorry invalid username/password.Please try again");
                             request.getRequestDispatcher("/studentlogin.jsp").forward(request, response);
                               } 
			else {                            
                            HttpSession session = request.getSession(true);
                            session.setAttribute("userid", studentid);
                            session.setAttribute("passwprd", passw);
                            String sql2 = " select * from medicalrecords mr inner join appointments a "
                                    + "on mr.appointmentid  = a.appointmentid where studentid = " + studentid + "and "
                                    + "typeofailment = 'vaccination' and a.appdate <  add_months "
                                    + "( ( select universityEnrollmentDate from students where studentid = a.studentid) , 5)";
                            PreparedStatement preStatement2 = conn.prepareStatement(sql2);
			//executing the query and then committing it before closing in the finally block.
			ResultSet result2 = preStatement2.executeQuery();
                        int vacccount = 0;
                        while (result2.next())
                            vacccount++;
                        session.setAttribute("vaccount", vacccount);
                        request.getRequestDispatcher("/studentmain.jsp").forward(request, response);
			}
                }
			
		finally {
			conn.close();
		}

	}
    
    
    protected void DoctorLoginValid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
             
        Connection conn = makeMyConnection();
        int doctorid= 0;
		try{
                       try{
                       doctorid = Integer.parseInt (request.getParameter("userid").toString());
                       }
                       catch (NumberFormatException nfe)
                       {
                         request.setAttribute("message", "sorry invalid username/password.Please try again");
                       }
                              
                        String passw = request.getParameter("password").toString();
                        String sql ="select * from doctors where doctorid = " + doctorid + "and password = '" + 
                                passw + "'";
			//creating PreparedStatement object to execute query
			PreparedStatement preStatement = conn.prepareStatement(sql);
			//executing the query and then committing it before closing in the finally block.
			ResultSet result = preStatement.executeQuery();		
			if (!result.next() ) {   
                             request.setAttribute("message","sorry invalid username/password.Please try again");
                             request.getRequestDispatcher("/doctorlogin.jsp").forward(request, response);
                               } 
			else {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("userid", doctorid);
                            session.setAttribute("passwprd", passw);
                            session.setAttribute("usertype", "doctor");
                            request.getRequestDispatcher("/doctormain.jsp").forward(request, response);
			}
                }
			
		finally {
			conn.close();
		}

	}
    protected void NurseLoginValid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
             
        Connection conn = makeMyConnection();
        int nurseid= 0;
		try{
                       try{
                       nurseid = Integer.parseInt (request.getParameter("userid").toString());
                       }
                       catch (NumberFormatException nfe)
                       {
                         request.setAttribute("message", "sorry invalid username/password.Please try again");
                       }
                              
                        String passw = request.getParameter("password").toString();
                        String sql ="select * from nurses where nurseid = " + nurseid + "and password = '" + 
                                passw + "'";
			//creating PreparedStatement object to execute query
			PreparedStatement preStatement = conn.prepareStatement(sql);
			//executing the query and then committing it before closing in the finally block.
			ResultSet result = preStatement.executeQuery();		
			if (!result.next() ) {   
                             request.setAttribute("message","sorry invalid username/password.Please try again");
                             request.getRequestDispatcher("/nurselogin.jsp").forward(request, response);
                               } 
			else {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("userid", nurseid);
                            session.setAttribute("passwprd", passw);
                            request.getRequestDispatcher("/nursemain.jsp").forward(request, response);
			}
                }
			
		finally {
			conn.close();
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
        try {
            
            LoginController(request, response);
                        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQLpooler.class.getName()).log(Level.SEVERE, null, ex);
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
           
            LoginController(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQLpooler.class.getName()).log(Level.SEVERE, null, ex);
        }
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



