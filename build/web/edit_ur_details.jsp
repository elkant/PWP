<%-- 
    Document   : edit_ur_details
    Created on : Mar 19, 2014, 11:40:31 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/main.css" type="text/css" media="all"/>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <!--<script src="js/jquery-1.9.1.js"></script>-->
<script type="text/javascript" src="js/jquery_min.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
    <title>Edit Your Details</title>
<script type="text/javascript">
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }}

  function phone_validator(){
    var phone=document.getElementById("phone").value;
var res=""
   var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("phonez").textContent=xmlhttp.responseText;
res=document.getElementById("phonez").textContent;

                    if(res.match("The Phone Number is Ok") || phone==""){

    document.getElementById("sub").disabled=false;   
}
else{
 document.getElementById("sub").disabled=true;
 document.getElementById("phone").focus();
}
}
}
xmlhttp.open("POST","phone_validator?phone="+phone,true);
xmlhttp.send();   
  }          
        </script> 
    
        <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('.textbox').jBox('Tooltip', {
    position: {
       x: 'right'
    },
    width:300,
    outside: 'x'
});      
 
 
    $('.textbox2').jBox('Tooltip', {
    position: {
       x: 'right'
    },
    width:300,
    outside: 'x'
});


     });
    
        
   </script>
    
    
</head>
    <body onload="phone_validator();">

     <%
     String fname="",mname="",lname="",phone="",usern="";
//     String userid=session.getAttribute("userid").toString();
      String userid=session.getAttribute("userid").toString();
     dbConn conn = new dbConn();

     String select_user_details="SELECT users.username,clerks.first_name,clerks.sur_name,clerks.phone "
 + "FROM users JOIN clerks ON users.userid=clerks.clerk_id WHERE UserID='"+userid+"'";
     conn.rs=conn.st.executeQuery(select_user_details);
    if(conn.rs.next()==true){
        fname=conn.rs.getString(2); 
        lname=conn.rs.getString(3);        
         usern=conn.rs.getString(1);
         phone=conn.rs.getString(4);
//         out.println(fname);
     }
             
conn.rs.close();
conn.st.close();
%>

   
<div id="container" style="height: 600px;">

                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("level") != null) {

                   if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}%>
             <%}%>

              <%if (session.getAttribute("det_update") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("det_update")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("det_update");
                            }

                        %>  
                
            <!--=====================================================================================--> 
    
            <div id="content" style="height:auto;margin-left:130px ;height: 500px; border-width: 0px" >
                <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div id="midcontent" style="margin-left:130px; height: 500px;">
       
<!--<div style=" position: absolute; left: 90px; width: 1080px; background: #ffffff; padding-top:10px;">-->
    <h3><p align="center">All the fields marked <font color='red'>*</font> must be filled.</p></h3> 
    <!--<h5><p align="center">The Fields marked with <font color="red">*</font> are editable fields.</p></h5>-->
    <br><br>
    <form action="save_ur_details" method="POST" style=" position: absolute; margin-left:0px; width: 780px;">
        <input type="hidden" name="userid" value="<%=userid%>">
<table style="margin-left: 30px; font-size: 18px; width: 400px;">
    <tr><td>First Name <font color='red'>*</font></td><td colspan="2"><input type="text" class='textbox' name="fname" id="fname" value="<%=fname%>" title="Change your first name. <br>(<font color='red'>This field is required.</font>)" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Last Name <font color='red'>*</font></td><td colspan="2"><input type="text" class='textbox' name="lname" id="lname" value="<%=lname%>" title="Click here to change your surname/lastname. <br>(<font color='red'>This field is required.</font>)" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Phone No.</td><td colspan="2"><input type="text" name="phone" maxlength="10" class='textbox' id="phone" pattern="[0-9]{10}" value="<%=phone%>" autocomplete="off" title="click here to change your phone number.<br>(<font color='red'>This field is optional.</font>)" onkeyup="return phone_validator();"></td><td></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Username <font color='red'>*</font></td><td colspan="2"><input type="text" class='textbox' name="username" id="username" value="<%=usern%>" title="click here to change your username.<br>(<font color='red'>This field is required.</font>)" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>New Password <font color='red'>*</font></td><td colspan="2"><input type="password" class='textbox' name="pass" id="password" value="" title="Click here to input a new password.<br>(<font color='red'>This field is required.</font>)" oninput="checkPasswords()" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td>Confirm Password<font color='red'>*</font></td><td colspan="2"><input type="password" class='textbox' name="pass2" id="conf_password" title="Click here to confirm the new password.<br>(<font color='red'>This field is required.</font>)<br> <i>This password MUST match the prior entered password.</i>" oninput="checkPasswords()" value="" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td colspan="3"><input type="submit" id="sub" disabled="true" value="Save" style="margin-left: 130px; background-color: orange"></td></tr>
</table>
<p id="phonez" name="phonez" style=" top: 200px; left: 400px; color: red"></p> 
    </form>
<!--</div>-->        
       
                        
        
        
</div>
</div>
</div>
</body>
</html>

