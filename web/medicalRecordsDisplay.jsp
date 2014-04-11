<%-- 
    Document   : medicalRecordsDisplay
    Created on : Apr 6, 2014, 8:03:24 PM
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
        <h3> Appointment records for student <%= request.getAttribute("studentid") %> </h3>
        <% String mr = (String)request.getAttribute("mr"); %>
        <% if (mr != null) { %>
        <table border = "1">
         <tr><td>Appointment ID </td><td>DOCTOR ID </td> <td>Appointment date </td><td> specialization  </td>
              <td> type of ailment  </td> <td> treatment given  </td><td>notes </td> </tr>
         <% String[] mrs = mr.split(",");%>
        <% for (int i=0; i< mrs.length ; i++ ){ %>
        <tr>
            <% String[] mrdetails = mrs[i].split(";"); %>
           <% for (int j=0 ; j< mrdetails.length ; j++) { %>
            <td> <%= mrdetails[j] %> </td> <% } %> 
        </tr>
        <% } %> 
        </table>
        <% } %>
    </body>
</html>
