<%-- 
    Document   : deletePartnerData
    Created on : Sep 23, 2015, 8:31:57 AM
    Author     : Nyabuto Geofrey
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>delete Partner data</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="prefix-free.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

 <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
<!--	<script src="js/jquery-1.9.1.js"></script>-->
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.mouse.js"></script>
	<script src="ui/jquery.ui.draggable.js"></script>
	<script src="ui/jquery.ui.position.js"></script>
	<script src="ui/jquery.ui.resizable.js"></script>
	<script src="ui/jquery.ui.button.js"></script>
	<script src="ui/jquery.ui.dialog.js"></script>
	<script src="ui/jquery.ui.effect.js"></script>
	<script src="ui/jquery.ui.effect-blind.js"></script>
	<script src="ui/jquery.ui.effect-explode.js"></script>
	<link rel="stylesheet" href="ui-essentials/demos.css">

        <script type="text/javascript">  
       $(document).ready(function(){
       
           $.ajax({
               url:"load_Partners",
               type:"post",

               dataType:"html",
               success:function(data){
               $("#partner").html(data); 
             } 
           });
           
          
        }); 
       </script> 
        <link rel="stylesheet" href="select2/css/select2.css">   
    </head>
    <body>
        
    <div id="container" style="height: auto;">
        
   <%if(session.getAttribute("level")!=null){if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}else{}}%>
<div id="header" align="center">
<br/>
</div>
<div id="content" style="height:auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Split partner data<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>

                       <% if (session.getAttribute("splitData") != null)  { %>
                                <script type="text/javascript"> 
                    var n = noty({text: '<%=session.getAttribute("splitData")%> .<br> Click here to close.',
                        layout: 'center',
                        type: 'Success'});
                 </script> <%session.removeAttribute("splitData");} %>
           
                 
                 
<div id="midcontent" style="margin-left:230px ; height: auto">
<div id="dialog" title="split data Help." style=" font-size: 17px;">
<p></p>
</div>
<p style=" font-size: 20px;"><font color="red" >*</font> indicates must select fields</p>
<form action="deletePartnerData" method="post"><br><br>
<table cellpadding="2px" cellspacing="3px" border="0px">

<tr id="reporter"> <td  class="align_button_right" style=" font-size: 18px;">Select Partner (s)<font color="red">*</font></td>
    <td>  
        <select name="partner" id="partner" style="width:250px;" required class="textbox2" multiple="true">
        <option value="">Choose Partner (s) </option>
    </select>  
    </td>
</tr>

</td><td></td></tr>
<tr><td></td><td><input type="submit" value="Split" class="submit"/></td></tr>
</table>
</form>
</div>
</div>

<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copyPWP System Aphia Plus | USAID <%=year%></p>
            </div>
</div>    
   <script src="select2/js/select2.js"></script>
   <script type="text/javascript">
 $(document).ready(function(){
 $('select').select2();    
 });   
</script> 
            
    </body>
</html>

