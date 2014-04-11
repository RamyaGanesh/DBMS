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
@WebServlet(name = "makeAppointmentCheck", urlPatterns = {"/makeAppointmentCheck"})
public class makeAppointmentCheck extends HttpServlet {

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
         HttpSession session = request.getSession(true);
         String date =  (String)session.getAttribute("date");
         String sp= (String)session.getAttribute("sp");
         String sch = request.getParameter("sch");
         String appinfo[] = sch.split(";"); 
         int docid = Integer.parseInt(appinfo[0]);
         String starttime = appinfo[2];
         String endtime = appinfo[3];
         String type = appinfo[4];
         int ts = CalculateTimeSlot (starttime , endtime);
         int amount = CalculateAmount (sp, type, (Integer)session.getAttribute("userid"));
         float copay = Calculatecopay (amount, (Integer)session.getAttribute("userid"));
         session.setAttribute("amount", amount);
         session.setAttribute("copay", copay);
         session.setAttribute("doctorid",docid );
         session.setAttribute("ts", ts);
         request.getRequestDispatcher("/getPaymentDetails.jsp").forward(request, response);
           
      
    }
    
    protected int CalculateTimeSlot (String starttime , String endtime) throws SQLException, ClassNotFoundException, NumberFormatException
    {
        SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
            String sql ="select slotNO from Timeslots where starttime = '" + starttime + "' and endtime = '" + endtime + "'" ;
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            int slotNo = 0;
            while(result.next())
            {
            slotNo = Integer.parseInt(result.getString("slotno"));
            }
            return slotNo;
       }finally {
			conn.close();
		}
    }
    
  protected int CalculateAmount (String sp, String type, int studentid) throws SQLException, ClassNotFoundException, NumberFormatException
  {
     SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
            String sql ="select cost from doctorTypeCost where type = '" + type + "' and specialization = '" + sp + "'" ;
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            int cost = 0;
            boolean first = true;
            if (sp.equalsIgnoreCase("physical"))
                first = FirstPhysical(studentid);
                      
            if (!sp.equalsIgnoreCase("physical")  || !first )
            {
                while(result.next())
            {
                cost = Integer.parseInt(result.getString("cost"));
            }
            }
            return cost;
       }finally {
			conn.close();
		} 
  }
  
   protected float Calculatecopay (int amount , int studentid) throws SQLException, ClassNotFoundException, NumberFormatException
  {
     SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
           float copayment = 0;
            String sql ="select  copayment ,  deductible from students s "
                    + "inner join insurance i on  s.policyid = i.policyid  where studentid = " + studentid ;
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            int ded = 0 ;
            float cop = 0;
            while(result.next())
            {
            ded = Integer.parseInt(result.getString("DEDUCTIBLE"));
            cop = Float.parseFloat(result.getString("copayment"));
            }
            String sql2 = "select sum ( amount) as payment_student from appointments a inner join payments p on "
                    + "a.appointmentid = p.appointmentid where studentid =" + studentid + "and extract( year from a.APPDATE)= "
                    + "extract ( year from sysdate)" ;
            PreparedStatement preStatement2 = conn.prepareStatement(sql2);
            ResultSet result2 = preStatement2.executeQuery();
            int paythisyear = 0;
            while(result2.next())
            {
                if(result2.getString("payment_student") != null)
            paythisyear = Integer.parseInt(result2.getString("payment_student"));
            }
            int difference = paythisyear - ded; 
            if (difference > 0)
                copayment = (cop/100)*amount;
            return (copayment);
            
            
       }finally {
			conn.close();
		} 
  }
   
   protected boolean FirstPhysical (int studentid) throws SQLException, ClassNotFoundException, NumberFormatException
  {
     SQLpooler sqlpooler = new SQLpooler();
       Connection conn = sqlpooler.makeMyConnection();
       try
       {
           String sql ="select count(*) as noofphysicals from appointments a where specialization = 'physical' and status = 'a' "
                    + "and extract( year from a.APPDATE)= extract ( year from sysdate) and studentid = " + studentid;
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            int noofphysicals =0;
            while(result.next())
            {
            noofphysicals = Integer.parseInt(result.getString("noofphysicals"));
            }
            if (noofphysicals != 0)
                return false;
            else
                return true;
                   
            
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
            Logger.getLogger(makeAppointmentCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(makeAppointmentCheck.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(makeAppointmentCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(makeAppointmentCheck.class.getName()).log(Level.SEVERE, null, ex);
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
