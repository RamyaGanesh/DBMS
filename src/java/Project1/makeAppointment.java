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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "makeAppointment", urlPatterns = {"/makeAppointment"})
public class makeAppointment extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException , NumberFormatException{
        response.setContentType("text/html;charset=UTF-8");
        SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
         try
       {
           HttpSession session = request.getSession(true);
           String cardno = request.getParameter("cardno");
           String chname = request.getParameter("chname");
           String expdate = request.getParameter ("expdate");
           String ctype = request.getParameter ("ctype");
           String badd = request.getParameter("badd");
           int stuid = (Integer)session.getAttribute("userid");
           int docid = (Integer)session.getAttribute("doctorid");
           String appdate = (String)session.getAttribute("date");
           int ts = (Integer)session.getAttribute("ts");
           String sp = (String)session.getAttribute("sp");
           int amount = (Integer)session.getAttribute("amount");
                       
           
               String sql = "select * from creditCard where creditcardNo = '" + cardno + "'";
               PreparedStatement preStatement = conn.prepareStatement(sql);
               ResultSet result = preStatement.executeQuery();
               if (!result.next())
               {
                   String sql1 = "INSERT INTO creditCard (creditcardNo, expiryDate, cardHolderName , CARDTYPE) "
                           + "VALUES ('"  + cardno + "' , '" + expdate + "' , '" + chname + "' , '" + ctype + "')";
                   PreparedStatement preStatement1 = conn.prepareStatement(sql1);
                   ResultSet result1 = preStatement1.executeQuery();
               }
               String sql2 = "INSERT INTO appointments appointments (appointmentID, "
                       + "studentId, doctorID,appdate,slotNo, specialization, status, extraCost, notes) VALUES ("
                       + "appointments_seq.nextval , " + stuid + ", " + docid + ", '" + appdate + "' ," + ts + ", '"
                       + sp + "' , 'a' , 0,'' )";
                PreparedStatement preStatement2 = conn.prepareStatement(sql2);
                   ResultSet result2 = preStatement2.executeQuery(); 
                   String sql3 = "select appointmentID from appointments where doctorID = " + docid + "and studentId = "
                           + stuid + "and appdate = '" + appdate + "' and slotNo = " + ts ;
                    PreparedStatement preStatement3 = conn.prepareStatement(sql3);
                   ResultSet result3 = preStatement3.executeQuery(); 
                   int appid = 0;
                   while (result3.next())
                   {
                       appid = Integer.parseInt(result3.getString("appointmentID"));
                   }
                   if (appid != 0)
                   {
                       String sql4 = "INSERT INTO payments(appointmentId, creditcardNo, amount, address) VALUES (" + appid 
                               + ", '" + cardno + "' , " + amount + ", '" + badd + "')";
                       PreparedStatement preStatement4 = conn.prepareStatement(sql4);
                   ResultSet result4 = preStatement4.executeQuery(); 
                    request.getRequestDispatcher("/success.jsp").forward(request, response);
                   }

           } catch (SQLException se)
           {
               request.setAttribute("inserror", "unable to make appointment, error");
               request.getRequestDispatcher("/getPaymentDetails.jsp").forward(request, response);
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
            Logger.getLogger(makeAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(makeAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(makeAppointment.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(makeAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(makeAppointment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(makeAppointment.class.getName()).log(Level.SEVERE, null, ex);
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
