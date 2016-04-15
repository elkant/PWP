<%-- 
    Document   : copyClientDetails
    Created on : Feb 4, 2015, 11:14:55 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Move Existing Data</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
 <script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="prefix-free.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
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
        <!--calender-->
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
<script type ="text/javascript" src="js/datepicker/jquery.jdpicker.js"></script>
<link rel="stylesheet" href="js/datepicker/jdpicker.css" type="text/css" media="screen" /> 
<script type="text/javascript">
    $(document).ready(function(){
      $("#imgdata").hide() ;    
    $("form").submit(function(){
     $("#submit").hide() ;
     $("#imgdata").show() ; 
        
    }) ;   
        
    });
    </script>
     <script>
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
                        width:500,
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
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Move Data. <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></div>
 <%if (session.getAttribute("MovedData") != null) { %>
                                <script type="text/javascript"> 
                    var n = noty({text: '<%=session.getAttribute("MovedData")%> Click To Close.',
                        layout: 'center',
                        type: 'Success'
                          });
                    
                </script> <%
                session.removeAttribute("MovedData");
                            } %>
<div id="midcontent" style="margin-left:230px ; height: auto">

    <br>
<form action="copyClientDetails" method="post"><br><br>
    <div id="dialog" title="Move Data Help." style=" font-size: 17px;">
<p><font color="red">NOTE</font>: This activity is only done once.</p>
<p>Click on the move data button to move clients from the old form to the new form.
    This will ensure that the clients data conforms to the new clients registration form.</p>
<p>A status report will be shown upon success completion of the importing process. </p>
</div>
<table cellpadding="2px" cellspacing="3px" border="0px">
    <tr><td></td><td><p id="imgdata" style="text-align: center; margin-left: 70%;">Moving Data ...<img src="images/utube.gif"></p></td></tr>
<tr><td></td><td><input type="submit" id="submit" value="Move Data" class="submit" style="text-align: center; margin-left: 75%;"/></td></tr>
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

