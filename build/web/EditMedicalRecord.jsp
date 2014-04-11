<%-- 
    Document   : EditMedicalRecord
    Created on : Apr 6, 2014, 4:13:44 PM
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
        <h1>Edit Medical Record</h1>
        <% int appid= (Integer)session.getAttribute("appid");%>
        <h3> Appointment ID: <%= appid %> </h3>
        <h3> Type of Ailment: <%= session.getAttribute("toa") %> </h3>
        <h3> Treatment Given: <%= session.getAttribute("tg") %> </h3>
        <form type = "get" action="medicalRecordsEditNew"  method="POST">
            
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
            <br/> </br> Treatment given:  <input type="text" name="tg">
            
            <br/></br><input type="submit" value="Edit Medical Record"> </form>
        <p style="color: red"> 
                <%= request.getAttribute("editmr") %> </p>
    </body>
</html>
