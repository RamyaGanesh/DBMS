<%-- 
    Document   : nursemedicalRecordsDisplay
    Created on : Apr 10, 2014, 5:31:50 PM
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
             
        <h3> Medical records for student <%= request.getAttribute("studentid") %> </h3>
        <% String mr = (String)request.getAttribute("mr"); %>
        <% if (mr != null) { %>
        <table border = "1">
         <tr><td>APPOINTMENT ID </td> <td>DOCTORID </td> <td> Doctor name </td> <td> phone number </td> <td>SPECIALIZATION </td><td>notes </td><td>Add notes </td> </tr>
         <% String[] mrs = mr.split(",");%>
        <% for (int i=0; i< mrs.length ; i++ ){ %>
        <tr>
            <% String[] mrdetails = mrs[i].split(";"); %>
           <% for (int j=0 ; j< mrdetails.length ; j++) { %>
            <td> <%= mrdetails[j] %> </td> <% } %> 
            <td> <form type = "get" action="AddNotes"  method="POST">
               <input type = "hidden" name = "appid" value = "<%= mrdetails[0] %> " >
               <input type = "text" style="width: 300px" name = "notes" >
               <input type="submit" value="Add Notes"> </form> </td>
        </tr>
        <% } %> 
        </table>
        <% } %>
    </body>
</html>
