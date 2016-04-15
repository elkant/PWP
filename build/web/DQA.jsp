<%-- 
    Document   : DQA
    Created on : Jul 1, 2014, 11:08:09 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

<script src="prefix-free.js"></script>
   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

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
      
       <!--calender-->
<script type ="text/javascript" src="js/datepicker/min.js"></script>
<script type ="text/javascript" src="js/datepicker/jquery.jdpicker.js"></script>
<link rel="stylesheet" href="js/datepicker/jdpicker.css" type="text/css" media="screen" /> 
 <script>
	$(function() {
                 var dateObject,day,month,year,current_date;
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=day+"/"+month+"/"+year; 
   
//  START FOR ENROLLMENT===================================================
       $('#date1').jdPicker({
     date_format:"dd/mm/YYYY",
     show_week:1,
     week_label:"we",
     start_of_week:3,
     date_min:"01/01/2010",
     date_max:current_date,
     selectable_days:[1, 2, 3, 4, 5]
//     non_selectable:["May 24 2000"],
//     rec_non_selectable: ["Jan 01", "May 26"]
}); 

 $('#date2').jdPicker({
     date_format:"dd/mm/YYYY",
     show_week:1,
     week_label:"we",
     start_of_week:3,
     date_min:"01/01/2010",
     date_max:current_date,
     selectable_days:[1, 2, 3, 4, 5]
//     non_selectable:["May 24 2000"],
//     rec_non_selectable: ["Jan 01", "May 26"]
}); 
//END FOR ENROLLMEMT===================================================
//FOR SESSIONS ATTENDED================================================
     $('#date3').jdPicker({
     date_format:"dd/mm/YYYY",
     show_week:1,
     week_label:"we",
     start_of_week:3,
     date_min:"01/01/2010",
     date_max:current_date,
     selectable_days:[1, 2, 3, 4, 5]
//     non_selectable:["May 24 2000"],
//     rec_non_selectable: ["Jan 01", "May 26"]
}); 

 $('#date4').jdPicker({
     date_format:"dd/mm/YYYY",
     show_week:1,
     week_label:"we",
     start_of_week:3,
     date_min:"01/01/2010",
     date_max:current_date,
     selectable_days:[1, 2, 3, 4, 5]
//     non_selectable:["May 24 2000"],
//     rec_non_selectable: ["Jan 01", "May 26"]
}); 

//END FOR SESSIONS============================================================
	});
	</script>
        
        
       <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script>
  function disabler(here) {
      var valu=here.value;
//      alert(valu)
      if(valu==""){
          document.getElementById("date1").value="";
          document.getElementById("date2").value="";
          
         document.getElementById("date1").hidden=true;
         document.getElementById("date2").hidden=true;
         document.getElementById("output").innerHTML="";  
      }
      else if (valu=="1"){
        document.getElementById("date1").value="";
        document.getElementById("date2").value="";
        document.getElementById("date1").hidden=false;
        document.getElementById("date2").hidden=true;  
//        LesserDates();  
document.getElementById("output").innerHTML=""; 
           }
      else if (valu=="2"){
        document.getElementById("date1").value="";
        document.getElementById("date2").value="";
        document.getElementById("date1").hidden=true;
        document.getElementById("date2").hidden=false; 
        document.getElementById("output").innerHTML=""; 
//      GreaterDates();   
      }
      else if (valu=="3"){
        document.getElementById("date1").value="";
        document.getElementById("date2").value="";
        document.getElementById("date1").hidden=false;
        document.getElementById("date2").hidden=false;  
        document.getElementById("output").innerHTML=""; 
//      BetweenDates();  
      }
      else{
          
      }
  } 
    
    
    function LesserDates(){    
   var date1,date2;
   date1=document.getElementById("date1").value;
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
document.getElementById("output").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","beforeDate?date1="+date1,true);
xmlhttp.send();
}


    function LesserSessions(){    
   var date1,date2;
   date1=document.getElementById("date3").value;
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
document.getElementById("output").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","lesserSessions?date1="+date1,true);
xmlhttp.send();
}



    function GreaterDates(){    
   var date1,date2;
   date1=document.getElementById("date2").value;
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
document.getElementById("output").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","afterDate?date1="+date1,true);
xmlhttp.send();
}

  function GreaterSessions(){    
   var date1,date2;
   date1=document.getElementById("date4").value;
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
document.getElementById("output").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","greaterSession?date1="+date1,true);
xmlhttp.send();
}



    function BetweenDates(){    
   var date1,date2;
   date1=document.getElementById("date1").value;
   date2=document.getElementById("date2").value;
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
document.getElementById("output").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","betweenDates?date1="+date1+"&&date2="+date2,true);
xmlhttp.send();
}
  function BetweenSessions(){    
   var date1,date2;
   date1=document.getElementById("date3").value;
   date2=document.getElementById("date4").value;
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
document.getElementById("output").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","betweenSession?date1="+date1+"&&date2="+date2,true);
xmlhttp.send();
}

function decider(){
//    alert("called")
  var date1,date2,datetype;
  date1=document.getElementById("date1").value;
 date2=document.getElementById("date2").value;
 datetype=document.getElementById("dates").value;
 if(date1!="" && date2!="" && datetype=="3"){
 BetweenDates();    
 }
 if(date1!="" && date2=="" && datetype=="1"){
 LesserDates() ;   
 }
 if(date1=="" && date2!="" && datetype=="2"){
 GreaterDates();    
 }
}

function decider2(){
//    alert("called")
  var date1,date2,datetype;
  date1=document.getElementById("date3").value;
 date2=document.getElementById("date4").value;
 datetype=document.getElementById("dates1").value;
 if(date1!="" && date2!="" && datetype=="3"){
 BetweenDates();    
 }
 if(date1!="" && date2=="" && datetype=="1"){
 LesserDates() ;   
 }
 if(date1=="" && date2!="" && datetype=="2"){
 GreaterDates();    
 }
}


</script>
        
<title>DQA Module</title>


</head>
<body>

    <div id="container" style=" height: auto">
<% if (session.getAttribute("level").toString().equals("1") || session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%}%>
<div id="header" align="center">
<br/>
</div>

<div id="content" style="height:400px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 02px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left:0px;width: 120%; margin-top:0px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Data Quality Checking Module.<img src="images/help.png" id="opener" title="Click Here to get help on system data quality." alt=" Help Image " style=" width: 40px; height: 40px;"></div>


<div id="midcontent" style="margin-left:0px ; height: 300px;">
<div id="dialog" title="DQA." style=" font-size: 17px;">

</div>
    <br><br>
<form style="height: 200px; margin-left: 50px; width:1100px;" action="#" method="post">
    <table style="width:100%" border="1px">
        <tr><th>Module</th><th colspan="3">Parameter</th><th>OutPut</th></tr>
        <tr>
          <td> Enrollments Dates </td>
          <td><select name="dates" id="dates" onchange="disabler(this);"> 
          <option value="0">Choose Category</option>
          <option value="1">Lesser Dates</option>
          <option value="2">Greater Dates</option>
          <option value="3">Dates Between</option>
          </select>
         </td>
         <td> <input type="text" name="date1" id="date1" hidden="true" onchange="decider();"> </td> 
        
          <td> <input type="text" name="date2" id="date2" hidden="true" onchange="decider();"> </td>
          <td><p id="output">OutPut Here</p></td>
            
        </tr>
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
