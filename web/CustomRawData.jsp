<%-- 
    Document   : CstomRawData
    Created on : Mar 5, 2015, 12:31:18 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Messages Given report</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<!--<script src="js/jquery-1.9.1.js"></script>-->
<script type ="text/javascript" src="js/datepicker/min.js"></script> 
 <script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="prefix-free.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

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
        <!--calender-->
        <script type="text/javascript">  
       $(document).ready(function(){
            $("#grpData").hide();  
           $.ajax({
               url:"loadAllGroups",
               type:"post",

               dataType:"html",
               success:function(data){
               $("#group").html(data); 
             } 
           });
           
         $("#reporter").hide(); 
         $("#reportType").change(function(){
           var report=$("#reportType").val();
        
        if(report=="IndvRawData"){
          $("#grpData").show(); 
             $("#groupAll").prop("required",true);
        }
        else{
//            $("#group").removeAttr("required");
            $("#reporter").hide();
            $("#grpData").hide();
          $("#groupAll").removeAttr("required");
        }
         });
         
         
           $("#groupAll").change(function(){
           var reportType=$("#groupAll").val();   
           if(reportType=="all_groups"){
         $("#reporter").hide();
          $("#group").removeAttr("required");
           }
           else if(reportType=="selected_groups"){
             $("#reporter").show();
              $("#group").attr("required",true);
           }
           else{
             $("#reporter").hide(); 
             $("#group").removeAttr("required");
           }
           });
           
           
       }); 
       </script> 
         <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
        <script type="text/javascript"> 
$(function() {
//$( "#startDate" ).datepicker();
});

            
        </script> 
        
              <script type="text/javascript">
$(document).ready(function(){
      var currentYear = (new Date).getFullYear();
      var prev_year=parseInt(currentYear)-5;
$( "#startDate" ).datepicker({changeMonth: true, changeYear: true, yearRange:'2010:'+currentYear+'', dateFormat: 'mm/dd/yy', maxDate: new Date()});
//$( document ).tooltip();

  $("#startDate").change(function(){
            var startDate=$("#startDate").val();
     $( "#endDate").val("");
      $("#endDate").datepicker("destroy");
$( "#endDate" ).datepicker({changeMonth: true, changeYear: true, yearRange: ''+prev_year+':'+currentYear+'',beforeShowDay: $.datepicker.noWeekends, dateFormat: 'mm/dd/yy',minDate: startDate,maxDate: new Date()});
});
$("#startDate").attr("required",true);
$("#endDate").attr("required",true);

        }); 
        
        function checker(){
var startDate=$("#startDate").val();
var endDate =$("#endDate").val(); 
if(startDate==""){
    alertify.alert("Please choose start date");
   return false; 
}
if(endDate==""){
    alertify.alert("Please choose end date. ");
    return false;
}
        }
        
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
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Messages Given Report <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>
<br>

<div id="midcontent" style="margin-left:230px ; height: auto">
<div id="dialog" title="Messages Given Report." style=" font-size: 17px;">
    
<p>These reports the number of individuals with the messages each received within the selected period</p>
<p>They include:</p>
<p>1. Messages Given Per Client- This reports shows which messages each client was given within the selected period.</p>
<p>2. Messages Given per Group - This report show the number of clients who were given each message within the selected period </p>
<p>3. Messages Given per DIC - This report shows the number of clients who were given each of the 13 messages per DIC(<i>if available</i>).</p>
<p>4. Messages Given per District - This report shows the number of clients who were given each of the 13 messages within the selected period per partner, per district </p>
<p> </p>
</div>
    <form action="customRawData" method="post" onsubmit="return checker();"><br><br>
<table cellpadding="2px" cellspacing="3px" border="0px">

<tr> <td class="align_button_right" style=" font-size: 18px;">Select Report Type<font color="red">*</font></td>
    <td>  
    <select name="reportType" id="reportType" required class="textbox2">
        <option value="">Choose report type</option>
        <option value="IndvRawData">Messages Given Per Client</option>
        <option value="ReceivedMessages">Messages Given per Group</option>
        <option value="receivedMessageDIC"> Messages Given per DIC</option>
        <option value="receivedMessageDistrict"> Messages Given per District</option>
    </select>  
    </td>
</tr>

<tr id="grpData"> <td class="align_button_right" style=" font-size: 18px;">Select Group(s)<font color="red">*</font></td>
    <td>  
    <select name="groupAll" id="groupAll" class="textbox2">
        <option value="">Choose group(s)</option>
        <option value="all_groups">All Groups</option>
        <option value="selected_groups">Selected Groups(custom selection)</option>
    </select>  
    </td>
</tr>

<tr id="reporter"> <td  class="align_button_right" style=" font-size: 18px;">Select Groups<font color="red">*</font></td>
    <td>  
        <select name="group" id="group" class="textbox2" style="height: 150px;width:345px; " multiple="true">
        <option value="">Chose Group </option>
    </select>  
    </td>
</tr>
    
    <tr> <td class="align_button_right" style=" font-size: 18px;">Start Date<font color="blue">(mm/dd/yyyy)</font><font color="red">*</font></td>
    <td>  
        <input type="text" name="startDate" value="" id="startDate" autocomplete="off" readonly="true" class="textbox" style="border-color:green;"> 
    </td>
</tr>

<tr> <td class="align_button_right" style=" font-size: 18px;">End Date <font color="blue">(mm/dd/yyyy)</font><font color="red">*</font></td>
    <td>  
        <input type="text" name="endDate" value="" id="endDate" autocomplete="off" readonly="true" class="textbox" style="border-color:green;">  
    </td>
</tr>
<tr><td></td></tr>
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
