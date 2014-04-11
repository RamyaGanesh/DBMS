<%-- 
    Document   : viewPastAppointments
    Created on : Apr 10, 2014, 8:39:22 AM
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
        <h1>Your Records </h1>
        
        <% String history = (String)request.getAttribute("his") ; %>
        <% if (history != null) { %>
        <table border = "1">
            <tr><td>Appointment ID </td><td>Doctor ID </td> <td>Date </td> <td>slot No </td><td>Type of Ailment </td>
                <td>Treatment Given </td><td>Amount </td></tr>
        <% String[] histories = history.split(",");%>
        <% for (int i=0; i< histories.length ; i++ ){ %>
        <tr>
            <% String[] historydetails = histories[i].split(";"); %>
           <% for (int j=0 ; j< historydetails.length ; j++) { %>
           <td> <%= historydetails[j] %> </td> <% } %> 
          </tr> 
        
        <% } %> 
        </table>
        <% } %>
    </body>
</html>
