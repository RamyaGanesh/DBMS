

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Schedule : </br> </br>
                 
        <% String schedule = (String)request.getAttribute("schedule") ; %>
        <% if (schedule != null) { %>
        <table border = "1">
            <tr><td>Appointment ID </td> <td> Student ID </td> <td> Start time </td> <td>end time </td><td>notes </td> </tr>
        <% String[] schedules = schedule.split(",");%>
        <% for (int i=0; i< schedules.length ; i++ ){ %>
        <tr>
           <% String[] scheduledetails = schedules[i].split(";"); %>
           <% for (int j=0 ; j< scheduledetails.length ; j++) { %>
            <td> <%= scheduledetails[j] %> </td> <% } %> 
        </tr> 
        
        <% } %> 
        </table>
        <% } %>
        
        </body>
</html>
