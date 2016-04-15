<%-- 
    Document   : add_guest
    Created on : Mar 15, 2014, 8:08:40 PM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
        <link rel="shortcut icon" href="images/header.JPG"/>
        <title>Guest</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
   
   
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
        </script> 
        
    </head>
    <body>
       
        <div id="container" style="width: 900px;">
              <div id="header" align="center">
                  <br/>
       
              </div>
            
            
            <div id="content" style="height:600px; width: 800px;">
                
              
                <div id="midcontent" style="margin-left:40px ;width: 800px;">
                    <h3>Register as a guest Here.</h3>
                    
                    <h4>
                        
                     <%
                            if (session.getAttribute("clerk_added") != null) {
                                out.println(session.getAttribute("clerk_added"));
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
                        
                        
                        
                        
                   
                     </h4>
                    <p><font color="red">*</font> indicates must fill fields</p>
                    <form action="add_clerk" method="post">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:150px ;">
                       
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Userid<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text readonly value="<%=generateRandomNumber(1000,10888)%>" required name=userid class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Surname<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="s_name" student_name class="textbox"/></td></tr><tr>
<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td><a href="index.jsp" class="linkstyle">Go Back</a></td></tr>
                               
                            </tr>
                            
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="f_name" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                           
                            
                            
                            <tr>
                               
                            <td class="align_button_left"><label for="email">Phone Number</label></td>
                            <td><input  type="text" name="phoneno" pattern="[0-9]{10,10}" class="textbox" /></td>
                            
                           
                            
                            </tr>
                            <tr>
                                <td>
                                    
                                    
                                                           
                                </td>
                                 
                            </tr>
                          
                     

                            
                         
                           <tr> <td class="align_button_left"><label for="town">Username <font color="red">*</font></label></td>
                            <td><input id="town" type=text required name=username class="textbox"/></td></tr>
                            
                           <tr>
                            <td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
                            <td><input id="password" type=password required name=pass oninput="checkPasswords()" class="textbox"/></td>
                           </tr>
                           <tr>
                           
                                <td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
                                <td ><input id="conf_password" type=password required name=conf_password oninput="checkPasswords()" class="textbox"/></td>
                                <td></td>
                           </tr>  
                           
                           <tr>
                           <input type="hidden" name="level" id="level" value="5">
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
