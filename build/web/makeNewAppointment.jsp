<%-- 
    Document   : makeNewAppointmet
    Created on : Apr 8, 2014, 7:16:06 PM
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
        <form type = "get" action="doctorAvailability"  method="POST">
        <h3>select specialization </h3>
             <select name="specialiazation">
             <option value="neuro">neuro</option>
             <option value="cardio">cardio</option>
             <option value="ortho">ortho</option>
             <option value="dental">dental</option>
             <option value="physical">physical</option>
             <option value="vaccination">vaccination</option>
             <option value="dermatology">dermatology</option>
             <option value="ent">ent</option>
             </select>
        
        </br></br> 
        <h3> Enter Date (In format DD-MMM-YY) : </h3> 
        <input type="text" name="appdate">
        <br/></br><input type="submit" value="Get Schedule"> </form>
        <p style="color: red"> 
                <%= request.getAttribute("apperror") %> </p>
        
    </body>
</html>
