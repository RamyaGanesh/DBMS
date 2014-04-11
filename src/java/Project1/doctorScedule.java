/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rg69519
 */
@WebServlet(name = "doctorScedule", urlPatterns = {"/doctorScedule"})
public class doctorScedule extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
       SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
       String scheduleDate = request.getParameter("scheduleDate");
       HttpSession session = request.getSession(true);
       int docid = 0;
       docid = (Integer)session.getAttribute("userid");
              
       String sql ="select appointmentId ,studentid ,starttime, endtime , notes  from (doctors d inner join appointments a "
              + "on d.doctorId = a.doctorId)  inner join timeslots ts on a.slotno = ts.slotno where d.doctorid = " + docid 
                              + "and a.appdate =  '" + scheduleDate + "' and a.status = 'a'";
       PreparedStatement preStatement = conn.prepareStatement(sql);
       String schedule = "";
       try {
		ResultSet result = preStatement.executeQuery();
                  while (result.next())
                   {
                       schedule = schedule + result.getString("appointmentId") + ";" + result.getString("studentid")+ ";" + result.getString("starttime") + ";" +
                       result.getString("endtime") + ";" + result.getString("notes") + ",";     
                   }
                   
              }catch (SQLException se) {
                         request.setAttribute("schedule", "");   
                        }
                   request.setAttribute("schedule", schedule);
                   request.getRequestDispatcher("/scheduledisplay.jsp").forward(request, response);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(doctorScedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(doctorScedule.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(doctorScedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(doctorScedule.class.getName()).log(Level.SEVERE, null, ex);
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
