<%-- 
    Document   : nursemain
    Created on : Apr 3, 2014, 7:31:48 PM
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
        <h1>Hi Nurse <%= session.getAttribute("userid") %> </h1>
        </br> 
            <form type = "get" action="nurseMedicalRecordsView"  method="POST">
            
            <br/>Student ID: <input type="text" name="studentid">
           
            <br/></br><input type="submit" value="View records"> </form>
    </body>
</html>
