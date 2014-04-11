<%-- 
    Document   : doctorlogin
    Created on : Apr 3, 2014, 7:05:59 PM
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
        <form type = "get" action="SQLpooler"  method="POST">
            <br/> <input type="hidden" name="logintype" value="doctor"> 
            <br/>UserId:<input type="text" name="userid">
            <br/> </br>Password:<input type="password" name="password">
            <br/></br><input type="submit" value="Submit">
            </br> <p style="color: red">Error: 
                <%= request.getAttribute("message") %> </p>
        </form>
       </body>
</html>
