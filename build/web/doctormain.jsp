<%-- 
    Document   : doctormain
    Created on : Apr 3, 2014, 7:31:36 PM
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
        <h3>Hi DOCTOR <%= session.getAttribute("userid") %> </h3>
        
        <table border = "1">
            <tr><td> <h3>Please enter a date for appointments schedule </h3>
        </br> </br>        
        <form type = "get" action="doctorScedule"  method="POST">
            
            <br/>Date:<input type="text" name="scheduleDate">
           
            <br/></br><input type="submit" value="Submit"> </form>
        <p>Please enter date in DD-MMM-YY (ex: 12-FEB-14) format </p> 
        </br></br> </td><td>
        <h3> Add new medical record </h3> </br>
         <h3>Please enter an appointment id to add new medical record </h3>
         
          <form type = "get" action="medicalRecordsdAdd"  method="POST">
            
            <br/>Appointment ID: <input type="text" name="appointmentid">
           
            <br/></br><input type="submit" value="Add record"> </form>
          </br> <p style="color: red"> 
                <%= request.getAttribute("addmrmessage1") %> </p>
          <p style="color: red"> 
                <%= request.getAttribute("addmrmessage") %> </p>
                </td></tr>
            <tr><td> 
              <h3> Edit medical record </h3> </br>
         <h3>Please enter an appointment id to edit medical record </h3>
         
          <form type = "get" action="medicalRecordsdEdit"  method="POST">
            
            <br/>Appointment ID: <input type="text" name="appointmentid">
           
            <br/></br><input type="submit" value="Edit record"> </form>
         </br> <p style="color: red"> 
                <%= request.getAttribute("editmrmessage") %> </p>  
                </td> <td>
                    <h3> View Student's Medical records </h3> </br>
         <h3>Please enter student id of student </h3>
         
          <form type = "get" action="medicalRecordsView"  method="POST">
            
            <br/>Student ID: <input type="text" name="studentid">
           
            <br/></br><input type="submit" value="View records"> </form>
         </br> <p style="color: red"> 
                <%= request.getAttribute("viewmrmessage") %> </p>
                </td></tr>
            <tr><td>
                    <h3> Edit Appointments/Add Notes </h3> </br>
                <form type = "get" action="AddNotes"  method="POST">
               </br> </br>Appointment ID: <input type = "text" name = "appid" >
                </br> </br>Notes: <input type = "text" style="width: 300px" name = "notes" >
                </br> </br><input type="submit" value="Add Notes"> </form>
            <p style="color: red"> 
                <%= request.getAttribute("addnotes") %> </p></td>
                <td>
                    <h3> Add Extra Cost </h3> </br>
                   <form type = "get" action="AddextraCost"  method="POST">
               </br> </br>Appointment ID: <input type = "text" name = "appid" >
                </br> </br>Extra Cost <input type = "text" name = "ecost" >
                </br> </br><input type="submit" value="Add Notes"> </form> 
                     <p style="color: red"> 
                <%= request.getAttribute("ecost") %> </p>
                </td>
            </tr> 
        </table>
      </body>
</html>
