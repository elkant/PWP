<%-- 
    Document   : group_attendanceReport
    Created on : Jun 12, 2014, 11:13:08 AM
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
        
<title>Group attendance report.</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 $(function() {
$( "#datepicker0" ).datepicker();
$( "#datepicker1" ).datepicker();
$( "#datepicker2" ).datepicker();
$( "#datepicker3" ).datepicker();
$( "#datepicker4" ).datepicker();
$( "#datepicker5" ).datepicker();
$( "#datepicker6" ).datepicker();
$( "#datepicker7" ).datepicker();
$( "#datepicker8" ).datepicker();
$( "#datepicker9" ).datepicker();
$( document ).tooltip();
$( "#accordion" ).accordion();

}); 




// a function that filters the districts in the passed county as per the county dropdown.
function load_counties(){    

// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("county").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","county_loader",true);
xmlhttp.send();
}//county loader




function filter_districts(district){

var dist=district.value;    

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (dist=="")
{
//filter the districts    



document.getElementById("district").innerHTML="<option value=\"\">Choose District</option>";
return;
}
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
document.getElementById("district").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","district_loader?county_id="+dist,true);
xmlhttp.send();
}//end of filter districts

//*******************filter healthy facility*************************

function filter_healthy_facility(){

var dist=document.getElementById("district").value;   
// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("health_facility").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_health_facility?district_id="+dist,true);
xmlhttp.send();
filter_groups();
}//end of filter healthy facility

      
        function load_partner(){

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
document.getElementById("partner").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_partners",true);
xmlhttp.send();
}
function filter_groups(){

        var district_id=document.getElementById("district").value; 
        var partner_id=document.getElementById("partner").value; 
      
     if(district_id!="" && partner_id!="") {  
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
document.getElementById("grp").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_groups?district_id="+district_id+"&&partner_id="+partner_id,true);
xmlhttp.send();
}
}
function activate_nhf(ind){
    var deter= ind.value;
    if(deter=="0"){
       document.getElementById("2").style="display: compact";
       document.getElementById("health_facility").setAttribute("required", "true");
//        filter_healthy_facility();
    }
    else{
        document.getElementById("2").style="display: none";
    document.getElementById("health_facility").removeAttribute("required");    
    }
}


function get_years(){

// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("year").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","years_loader",true);
xmlhttp.send();
}
</script>


</head>
<body onload=" load_partner();load_counties();get_years(); ">

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
<div style="margin-left: 0px;width: 100%; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">GROUP ATTENDANCE REPORT.</div>

<div id="content" style="height:auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>


<div id="midcontent" style="margin-left:230px ; height: auto">
<div id="dialog" title="Add Group Help." style=" font-size: 17px;">
<p>Select the details as they appear in the form.</p>
<p>On choosing a group,choose new if it is a new group you want to register on click on existing to select an already existing group </p>
<p>If you want to enter attendance for an individual, select existing group and then choose individual in the next select box.</p>
<p><font color="red">NOTE:</font> a group is only registered oncer.</p>
</div>
<!--<p style=" font-size: 20px;"><font color="red">*</font> indicates must fill fields</p>-->
<form action="group_attendanceReport" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px" >




<tr><td></td></tr>
<tr style="display: compact">
<td class="align_button_right" style=" font-size: 20px;">County<font color="red">*</font></td>
<td><Select id="county" class="textbox2" onchange="filter_districts(this);"  required ="true" name="county_id" >
<option value="">Choose County</option> 
</select></td>


</tr>   
<tr><td></td></tr>
<tr><td></td></tr>
<tr style="display: compact">  
<td class="align_button_right" style=" font-size: 20px;">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner" class="textbox2"  required ="true" onchange="filter_groups();" name="partner" >

<option value="">Choose partner</option>  
</select></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr style="display: compact">  
<td class="align_button_right" style=" font-size: 20px;">District<font color="red">*</font></td>
<td><Select id="district" class="textbox2"  required="true" onchange="filter_groups();" name="district" >

<option value="">Choose District</option>  
</select></td></tr>

<tr style="display: compact">  
<td class="align_button_right" style=" font-size: 20px;">Year<font color="red">*</font></td>
<td><Select id="year" class="textbox2"  required="true" onchange="filter_groups();" name="year" >

<option value="">Choose District</option>  
</select></td></tr>

<tr ><td></td></tr><tr>
<td class="align_button_right" style=" font-size: 20px;">Choose A Group<font color="red">*</font></td>
<td>
    <select name="grp" id="grp" class="textbox2" required onchange="return activate_nhf(this);">
        <option value="">Choose a Group</option>
   </select>
    
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

</body>
</html>
