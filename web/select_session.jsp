<%-- 
    Document   : add_a_group
    Created on : Apr 1, 2014, 8:12:53 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <!-- To manage success, fail notifications  -->            
 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>  

<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel='stylesheet' type='text/css' href='css/btn.css' />
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.9.1.js"></script>
<script src="prefix-free.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>

   <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">

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
                <script>
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
			show: {
				effect: "blind",
				duration: 500
			},
			hide: {
				effect: "explode",
				duration: 700
			}
		});

		$( "#opener" ).click(function() {
			$( "#dialog" ).dialog( "open" );
		});
	});
	</script> 
        
<title>Add a Group.</title>

</head>
<body onload=" load_partner();load_counties(); ">

<div id="container" >
<%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}%>
<div id="header" align="center">
<br/>
</div>

<div id="content" style="height:auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 1/6. Add Or Select an Existing Group.</div>


<div id="midcontent" style="margin-left:230px ; height: auto">
<div ><h4 style=" font-size: 23px;">Specify details appropriately to add a group<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</h4></div> 
<p style="font-size: 15px;"><font color="red" >NOTE : </font>For Individual Sessions,In Choose category, Choose Existing group and then Select Individual. </p> 

<div id="dialog" title="Add Group Help." style=" font-size: 17px;">
<%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='4'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>
<!--<p style=" font-size: 20px;"><font color="red">*</font> indicates must fill fields</p>-->
<form action="selected_session" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px" >

<tr><td></td></tr><tr style="display: compact">
<td class="align_button_right" style=" font-size: 20px;">Choose Category<font color="red">*</font></td>
<td>
    <select name="chosen_session" id="chosen_session" class="textbox2" onchange="" required="true">
        <option value="">Choose Session</option>
        <option value="1">Session 1</option>
        <option value="2">Session 2</option>
        <option value="3">Session 3</option>
        <option value="4">Session 4</option>
        <option value="5">Session 5</option>
        <option value="6">Session 6</option>
        <option value="7">Session 7</option>
        <option value="8">Session 8</option>
        <option value="9">Session 9</option>
        <option value="10">Session 10</option>
        <option value="11">Session 11</option>
        <option value="12">Session 12</option>
        <option value="13">Session 13</option>
   </select>
    
</td><td></td></tr>
<tr><td></td><td><input type="submit" value="Next >>" class="submit"/></td></tr>

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

</body>
</html>
