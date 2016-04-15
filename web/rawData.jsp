<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Raw Data Report</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
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
        <!--calender-->
<!--
<script type ="text/javascript" src="js/datepicker/jquery.jdpicker.js"></script>
<link rel="stylesheet" href="js/datepicker/jdpicker.css" type="text/css" media="screen" /> -->
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
           
         $("#reporter").hide();   
           $("#partnerAll").change(function(){
           var reportType=$("#partnerAll").val();   
           if(reportType=="all_partners"){
         $("#reporter").hide();
          $("#partner").removeAttr("required");
           }
           else if(reportType=="selected_partners"){
             $("#reporter").show();
              $("#partner").attr("required",true);
           }
           else{
             $("#reporter").hide(); 
             $("#partner").removeAttr("required");
           }
           });
           
           
       }); 
       $(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
                        width:700,
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
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Raw Data Report <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>

<br>
<div id="midcontent" style="margin-left:230px ; height: auto">
<div id="dialog" title="Raw Data Report Help." style=" font-size: 17px;">
<p>Within this page, users are able to generate raw data report.</p>
<p>Users can  generate raw data for all the partners or they can generate raw data for a few selected partners.</p>
<p><font color="red">NOTE: </font> This raw data report generated show all data for each client.<b>ie</b> it show all the messages a client has received and also the services this client has been provided with.</p>
</div>
<form action="allRawData" method="post"><br><br>
<table cellpadding="2px" cellspacing="3px" border="0px">

<tr> <td class="align_button_right" style=" font-size: 18px;">Select Raw Data Report Type<font color="red">*</font></td>
    <td>  
    <select name="partnerAll" id="partnerAll" required class="textbox2">
        <option value="">Choose report type</option>
        <option value="all_partners">All Partners</option>
        <option value="selected_partners">Selected Partners(custom selection)</option>
    </select>  
    </td>
</tr>

<tr id="reporter"> <td  class="align_button_right" style=" font-size: 18px;">Select Partners<font color="red">*</font></td>
    <td>  
        <select name="partner" id="partner" class="textbox2" style="height: 150px; " multiple="true">
        <option value="">Chose Partner </option>
    </select>  
    </td>
</tr>

</td><td></td></tr>
<tr><td></td><td><input type="submit" value="Generate" class="submit"/></td></tr>
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
