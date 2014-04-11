<%-- 
    Document   : viewFutureAppointments
    Created on : Apr 10, 2014, 8:57:08 AM
    Author     : rg69519
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your future appointments</h1>
        
        <% String history = (String)request.getAttribute("recs") ; %>
        <% if (history != null) { %>
        <table border = "1">
            <tr><td>Appointment ID </td><td>Doctor ID </td><td>Doctor Name </td> <td>Date </td> <td>slot No </td><td>Specialization  </td>
                </td></tr>
        <% String[] histories = history.split(",");%>
        <% for (int i=0; i< histories.length ; i++ ){ %>
        <tr>
            <% String[] historydetails = histories[i].split(";"); %>
           <% for (int j=0 ; j< historydetails.length ; j++) { %>
           <td> <%= historydetails[j] %> </td> <% } %> <td> 
           <form type = "get" action="CancelAppointment"  method="POST">
               <input type = "hidden" name = "cancelapp" value = "<%= historydetails[0] %> " >
               <input type="submit" value="Cancel Appointment"> </form>
           </td> 
          </tr> 
        
        <% } %> 
        </table>
        <% } %>
    </body>
</html>
