<%-- 
    Document   : studentmain
    Created on : Apr 2, 2014, 9:22:54 PM
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
        <h2> Hi student <%= session.getAttribute("userid") %> </h2>
        
        </br> </br> 
         <form type = "get" action="makeNewAppointment.jsp"  method="POST">
        <input type="submit" value="Make Appointment"> </form>
         <br></br> 
         <form type = "get" action="viewAppandRec"  method="POST">
        <input type="submit" value="View Past Appointments and Medical Records"> </form>
        <br></br>
        <form type = "get" action="viewFutureAppointments"  method="POST">
        <input type="submit" value="view/cancel future appointments"> </form>
        <br></br>
        No of Vaccinations in first semester : <%= session.getAttribute("vaccount") %>
        <% if ((Integer)session.getAttribute("vaccount") < 3){ %>
        <p style=" color: red"> Warning: you have not had the required vaccinations in your first semester. So your housing , dining and library privileges have been discontinued. </p>
        <% } %>
        
                  
    </body>
</html>
