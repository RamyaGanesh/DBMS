<%-- 
    Document   : getPaymentDetails
    Created on : Apr 8, 2014, 9:54:53 PM
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
        Student ID: <%= session.getAttribute("userid") %>
        <br><br>
        Doctor ID: <%= session.getAttribute("doctorid") %>
        <br><br>
        Appointment Date: <%= session.getAttribute("date") %>
        <br><br>
        Time Slot: <%= session.getAttribute("ts") %>
        <br><br>
        Specialization : <%= session.getAttribute("sp") %>
        <br><br>
        <B> Amount (subjected to change after appointment) : <%= session.getAttribute("amount") %>
        </br><br>
        Copayment (subjected to change after appointment): <%= session.getAttribute("copay") %> </B>
        </br><br><br>
        <h3>Please enter payment details:
        
        </br>
         <form type = "get" action="makeAppointment"  method="POST">
            
            <br/></br>Credit card NO: <input type="text" name="cardno">
             <br/></br>Credit type:
             <select name="ctype">
             <option value="Debit, Visa">Debit, Visa</option>
             <option value="Debit, Master">Debit, Master</option>
             <option value="Credit, Visa">Credit, Visa</option>
             <option value="Credit, Master">Credit, Master</option>
             </select>
            <br/></br>Card Holder Name:  <input type="text" name="chname">
             <br/></br>Expiry Date (in dd-MMM-yy format):  <input type="text" name="expdate">
             <br/></br>Billing Address:  <input type="text" name="badd">
                  
             <br/></br><input type="submit" value="Submit"> </form> </h3>
         <p style="color: red"> 
                <%= request.getAttribute("inserror") %> </p>
        
    </body>
</html>
