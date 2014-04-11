<%-- 
    Document   : AddMedicalRecord
    Created on : Apr 5, 2014, 10:04:05 PM
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
        <h1>Add New Medical Record</h1>
        <% int appid= (Integer)session.getAttribute("appid");%>
        <h2> Appointment ID: <%= appid %> </h2>
        <form type = "get" action="medicalRecordsAddNew"  method="POST">
            
             <br/>Type of Ailment:
             <select name="toa">
             <option value="neuro">neuro</option>
             <option value="cardio">cardio</option>
             <option value="ortho">ortho</option>
             <option value="dental">dental</option>
             <option value="physical">physical</option>
             <option value="vaccination">vaccination</option>
             <option value="dermatology">dermatology</option>
             <option value="ent">ent</option>
             </select>
            <br/>Treatment given:  <input type="text" name="tg">
            
            <br/></br><input type="submit" value="Add Medical Record"> </form>
        <p style="color: red"> 
                <%= request.getAttribute("addmr") %> </p>
    </body>
</html>
