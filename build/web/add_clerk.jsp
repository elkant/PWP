<%-- 
    Document   : add_clerk
    Created on : Nov 26, 2013, 4:39:51 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    if (session.getAttribute("userid")==null || (session.getAttribute("level").toString().equals("2") || session.getAttribute("level").toString().equals("5"))) {
      
        response.sendRedirect("index.jsp");
           } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.JPG"/>
        <title>Add New User</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  
   <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript">
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }
            } 
//$(function() {
//$( "#datepicker" ).datepicker();
//});

            
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
    <body>
       
     <div id="container" style="height:700px;" >
<% if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%}%>
              <div id="header" align="center">   
              </div>
            
            
            <div id="content" style="height:600px; width: 900px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                 <div style="margin-left: 320px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Add New Users. </div>
             
                <div id="midcontent" style="margin-left:230px ; height:500px;" >
                  
                         <%if (session.getAttribute("clerk_added") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("clerk_added")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("clerk_added");
                            }

                        %>
                        <!--creating random user id-->
                         <%!
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
%>  
                        
                        
                        
                        
                   
                   
                    <p><font color="red">*</font> indicates required fields.</p>
                    <form action="add_clerk" method="post">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:150px ;">
                       
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Userid<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text title="This is auto-generated by the system." style="background-color: grey;" readonly value="<%=generateRandomNumber(1000,10888)%>" required name=userid class="textbox"/></td></tr><tr>
                               </tr>
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Surname<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text title="Enter user's surname/last name.<br> (<font color='red'>This field is required.</font>)" required name="s_name" student_name class="textbox"/></td></tr><tr>
                       </tr>
                            <tr>
                                <td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="f_name" title="Enter user's First name.<br> (<font color='red'>This field is required</font>)" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                             <tr>
                               
                            <td class="align_button_left"><label for="email">Phone Number</label></td>
                            <td><input  type="text" name="phoneno" title="Enter user's phone number.<br> (<font color='red'>This field is optional</font>)" pattern="[0-9]{10,10}" class="textbox" /></td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                 
                            </tr>
                          
                      <tr> <td class="align_button_left"><label for="town">Username <font color="red">*</font></label></td>
                          <td><input id="town" type=text title="Enter user's username. (<i>Users will use this username to log in to the system.</i>)<br> <font color='red'>This field is required and should be unique.</font>" required name=username class="textbox"/></td></tr>
                            
                           <tr>
                            <td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
                            <td><input id="password" type=password required name=pass oninput="checkPasswords()" title="Enter your passwod here. Please ensure you remember this password.<br> (<font color='red'>This field is required.</font>)" class="textbox"/></td>
                           </tr>
                           <tr>
                           
                                <td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
                                <td ><input id="conf_password" type=password required name=conf_password oninput="checkPasswords()" title="Repeat your password here. it should match the prior entered password. <br>(<font color='red'>This field is required.</font>)" class="textbox"/></td>
                                <td></td>
                           </tr>  
                           
                           <tr>
                           
                                <td class="align_button_left"><label for="">Choose Level <font color="red">*</font></label></td>
                                <td ><select name="level" id="level" required class='textbox2' title="Select access level.<font color='red'>NOTE : </font><br> 1. Users who usually does data entry should select <b>User</b> as their level.<br> 2.Select your correct access level. <br> (<font color='red'>This field is required.</font>)">
                                         <option value="">Choose Level</option>
                                        <option value="0">Administrator</option>
                                        <option value="3">M&E Officer</option>
                                        <option value="2">User</option>
                                        
                                    </select>
                                </td>
                                <td></td>
                           </tr> 
            
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                           <tr> 
                               <td class="align_button_left"><input  size="12px"  type="reset" value="clear" /></td> <td class="align_button_right">
                               <input type="submit" class="submit" value="Add" onmouseover="getAge();"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
          
            </div>
<div id="footer">
               <p align="center" style=" font-size: 18px;"> &copyPWP System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>
