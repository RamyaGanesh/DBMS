package Project1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/medicalRecordsdEdit"})
public class medicalRecordsdEdit extends HttpServlet {

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
         SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
           
           int appid = 0;
       try{
               appid = Integer.parseInt (request.getParameter("appointmentid").toString());
                 
       } catch ( NumberFormatException nfe)
       {
           request.setAttribute("editmrmessage", "invalid appointment id");
       }
       HttpSession session = request.getSession(true);
       int docid = (Integer)session.getAttribute("userid");
           
       if( appid != 0)
       {
          String sql = "select * from (appointments a inner join doctors d on a.doctorid = d.doctorid )inner join medicalrecords m "
                  + "on a.appointmentid = m.appointmentid "
                  + "where a.appointmentid = " + appid + " and d.doctorid = " + docid ;
       PreparedStatement preStatement = conn.prepareStatement(sql);
              try {
		ResultSet result = preStatement.executeQuery();
                if (result.next())
                   {
                       session.setAttribute("appid", appid); 
                       session.setAttribute("tg", result.getString("treatmentgiven"));
                       session.setAttribute("toa", result.getString("typeofailment") );
                       request.getRequestDispatcher("/EditMedicalRecord.jsp").forward(request, response);
                       
                   }
       }catch (SQLException se) {
                         request.setAttribute("editmrmessage", "invalid appointment id/ appointment of different doctor");   
                        }
       }
       request.setAttribute("editmrmessage", "invalid appointment id/ appointment of different doctor");
       request.getRequestDispatcher("/doctormain.jsp").forward(request, response);
       
    }finally {
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
            Logger.getLogger(medicalRecordsAddNew.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(medicalRecordsAddNew.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(medicalRecordsAddNew.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(medicalRecordsAddNew.class.getName()).log(Level.SEVERE, null, ex);
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


