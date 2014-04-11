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

/**
 *
 * @author rg69519
 */
@WebServlet(name = "medicalRecordsView", urlPatterns = {"/medicalRecordsView"})
public class medicalRecordsView extends HttpServlet {

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
           String stid = request.getParameter("studentid");
           int sid = 0;
           try{
               sid = Integer.parseInt(stid);
           } catch (NumberFormatException nfe)
           {
               request.setAttribute("viewmrmessage", "invalid student id");
           }
           
           if(sid != 0)
           {
               String sql = "select a.appointmentid as appointmentid, studentid, DOCTORID, APPDATE , SPECIALIZATION, TREATMENTGIVEN , notes, "
                       + "TYPEOFAILMENT from appointments a inner join medicalrecords mr on a.appointmentid= "
                       + "mr.appointmentid where studentid = " + sid ; 
               PreparedStatement preStatement = conn.prepareStatement(sql);
               String mr = "";
               try {
		ResultSet result = preStatement.executeQuery();
                  while (result.next())
                   {
                       mr = mr + result.getString("appointmentid") + ";" +result.getString("DOCTORID") + ";" + result.getString("APPDATE")+ ";" + result.getString("SPECIALIZATION") + ";" +
                       result.getString("TYPEOFAILMENT") + ";" + result.getString("TREATMENTGIVEN") + ";" +  result.getString("notes") + ",";     
                   }
                   
              }catch (SQLException se) {
                         request.setAttribute("mr", "");   
                        }
               request.setAttribute("mr", mr);
               request.setAttribute("studentid", sid);
                   request.getRequestDispatcher("/medicalRecordsDisplay.jsp").forward(request, response);
               
           }
           else
           {
           request.setAttribute("viewmrmessage", "invalid student id");
           request.getRequestDispatcher("/doctormain.jsp").forward(request, response);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(medicalRecordsView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(medicalRecordsView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(medicalRecordsView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(medicalRecordsView.class.getName()).log(Level.SEVERE, null, ex);
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
