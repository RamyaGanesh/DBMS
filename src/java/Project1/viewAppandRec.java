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
@WebServlet(name = "viewAppandRec", urlPatterns = {"/viewAppandRec"})
public class viewAppandRec extends HttpServlet {

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
            throws ServletException, IOException,  ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
            HttpSession session = request.getSession(true);
           int studid = (Integer)session.getAttribute("userid");
           String sql = " select a.APPOINTMENTID as appid , DOCTORID, APPDATE, SLOTNO, "
                   + "TYPEOFAILMENT, TREATMENTGIVEN, AMOUNT from (appointments a inner join  "
                   + "medicalrecords mr on a.appointmentid = mr.appointmentid) inner join payments p "
                   + "on a.appointmentid = p.appointmentid  where  a.status = 'a' and studentid = " + studid;
           PreparedStatement preStatement = conn.prepareStatement(sql);
               String his = "";
               ResultSet result = preStatement.executeQuery();
               while (result.next())
                   {
                       his = his + result.getString("APPID") + ";" + result.getString("DOCTORID") + ";"
                               + result.getString("APPDATE")+ ";" + result.getString("SLOTNO") + ";" 
                               + result.getString("TYPEOFAILMENT") + ";"  
                               + result.getString("TREATMENTGIVEN") + ";"
                               + result.getString("AMOUNT")+ ",";     
                   }
               request.setAttribute("his", his);
               request.getRequestDispatcher("/viewPastAppointments.jsp").forward(request, response);
       } catch (SQLException se)
       {
          request.getRequestDispatcher("/studentmain.jsp").forward(request, response);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewAppandRec.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(viewAppandRec.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewAppandRec.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(viewAppandRec.class.getName()).log(Level.SEVERE, null, ex);
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
