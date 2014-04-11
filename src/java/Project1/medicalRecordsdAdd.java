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
@WebServlet(name = "medicalRecordsdAdd", urlPatterns = {"/medicalRecordsdAdd"})
public class medicalRecordsdAdd extends HttpServlet {

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
        {
          SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
       int appid = 0;
       try{
               appid = Integer.parseInt (request.getParameter("appointmentid").toString());
                 
       } catch ( NumberFormatException nfe)
       {
           request.setAttribute("addmrmessage", "invalid appointment id");
       }
       
           
       if( appid != 0)
       {
              
       String sql ="select * from medicalrecords where appointmentid = " + appid;
       PreparedStatement preStatement = conn.prepareStatement(sql);
              try {
		ResultSet result = preStatement.executeQuery();
                  if (result.next())
                   {
                       request.setAttribute("addmrmessage1", "medical record already exist for appointment id. "
                               + "cannot add new record, please click on edit record to edit/update");     
                   }
                  else
                  {
                     String sql1 ="select * from appointments where appointmentid = " + appid; 
                      PreparedStatement preStatement1 = conn.prepareStatement(sql1);
                      ResultSet result1 = preStatement1.executeQuery();
                      if(result1.next())
                      {
                          HttpSession session = request.getSession(true);
                          int docid = 0;
                          try{
                              docid = Integer.parseInt(result1.getString("doctorid"));
                          } catch (NumberFormatException nfe)
                          {
                             request.setAttribute("addmrmessage", "invalid appointment id"); 
                          }
                         if(docid == (Integer)session.getAttribute("userid") )
                         {
                             session.setAttribute("appid", appid);
                          request.getRequestDispatcher("/AddMedicalRecord.jsp").forward(request, response);
                         }
                        
                          else
                          request.setAttribute("addmrmessage1", "This appointment was made with a different doctor");
                      }
                      
                  }
                   
              }catch (SQLException se) {
                         request.setAttribute("addmrmessage", "invalid appointment id");   
                        }
             
       }
       request.setAttribute("addmrmessage", "invalid appointment id");
       request.getRequestDispatcher("/doctormain.jsp").forward(request, response);
            
       }
       
         finally {
			conn.close();
		}  
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
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(medicalRecordsdAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(medicalRecordsdAdd.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(medicalRecordsdAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(medicalRecordsdAdd.class.getName()).log(Level.SEVERE, null, ex);
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
