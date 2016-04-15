<%-- 
    Document   : choose_individual
    Created on : Apr 2, 2014, 8:54:16 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((session.getAttribute("partner_name")==null) || (session.getAttribute("userid")==null) || (session.getAttribute("district_name")==null)) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/numeric.js"></script>
<script type="text/javascript" src="js/jquery_min.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
  <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
  <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
	<script src="js/jquery-1.9.1.js"></script>
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
        
        function load_sess(pe){
var yr=pe.value;    
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
document.getElementById("chosen_session").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","check_individual_marked?id="+yr,true);
xmlhttp.send();
}

	</script> 
        
<title>Choose Individual.</title>
<script type="text/javascript">  
</script> 
<script type="text/javascript">
</script>
</head>
<body>

    <div id="container" style="height:700px;">
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
</div>
<div id="content" style="height:600px;">
       <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div id="dialog" title="Add Individuals Help." style=" font-size: 17px;">
<p>By selecting one individual, all those individuals with whom they were registered together with will also be  loaded for marking/editing.</p>
</div>
<div style="margin-left: 200px;width: 850px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 3/6. Continuation... Choosing An Existing Individual.</div>
<div id="midcontent" style="margin-left:150px ; width: 900px; height: auto">
<div ><h3 style=" font-size: 23px;">Choose One Individual whom you want to mark/edit his/her attendance.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h3></div> 
 <a href="add_a_client.jsp" class="linkstyle" style="height: 30px; margin-left: 50px;background: #ff00cc"><< Go Back</a>
<div style="margin-left: 20px; margin-top: 5px; font-size: 17px;">
    <br>
     <form action="choose_individual_sess" style="margin-left: 0px; width: 500px;"> 
         Choose An Individual  <select name="prov" id="prov" style="height: 40px;" class="textbox2" required="true" onchange="return load_sess(this);">
         <% if(session.getAttribute("a_clt")!=null){ %><%=session.getAttribute("a_clt").toString()%><%}%>
     </select>
     <input type="submit" name="sub" value="Next >>" class="textbox1" style=" height: 40px; background:  #cc99ff; color: #0000ff">
     </form>   <br><br> 
 </div>
</div>
     <div id="chosen_session" style=" position: absolute; top: 250px; margin-left: 820px; font-size: 8px;">
<!--     Here    -->
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
