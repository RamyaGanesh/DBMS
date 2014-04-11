<%-- 
    Document   : scheduleForAppointment
    Created on : Apr 8, 2014, 7:52:33 PM
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
        <h3>Appointment Date: <%=  session.getAttribute("date") %></h3>
        </br> <h3> Specialization : <%= session.getAttribute("sp") %> </h3>
        </br></br>
        List of Available doctors: 
        
        </br></br>
        
        
        <% String schedule = (String)request.getAttribute("sch") ; %>
        <% if (schedule != null) { %>
        <table border = "1">
            <tr><td>Doctor ID </td><td>Doctor Name </td> <td> Start time </td> <td>end time </td><td>Doctor Type </td><td> </td> </tr>
        <% String[] schedules = schedule.split(",");%>
        <% for (int i=0; i< schedules.length ; i++ ){ %>
        <tr>
            <% String sch = schedules[i]; %>
           <% String[] scheduledetails = schedules[i].split(";"); %>
           <% for (int j=0 ; j< scheduledetails.length ; j++) { %>
           <td> <%= scheduledetails[j] %> </td> <% } %> 
           <td> 
           <form type = "get" action="makeAppointmentCheck"  method="POST">
               <input type = "hidden" name = "sch" value = "<%= sch %> " >
               <input type="submit" value="Make Appointment"> </form>
           </td>
        </tr> 
        
        <% } %> 
        </table>
        <% } %>
        
    </body>
</html>
