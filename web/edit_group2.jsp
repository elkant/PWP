<%-- 
    Document   : edit_group2
    Created on : Dec 6, 2013, 10:10:23 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((session.getAttribute("userid")==null)) {
        response.sendRedirect("index.jsp");
    } 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

<script src="prefix-free.js"></script>
                
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
	</script>                
                
         <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script> 

<title>Edit Group Details</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 $(function() {
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

function filter_healthy_facility(district){

var dist=district.value;    
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
//set the partner


</script>

</head>
<body>

<div id="container" style="height:auto;">
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


<div id="content" style="height:auto;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Edit Group Details.</div>


<div id="midcontent" style="margin-left:230px ; height: auto;">
<div ><h4 style=" font-size: 23px;"> Enter correct group details and save.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div> 
    
<div id="dialog" title="Edit Group Details Help." style=" font-size: 15px;">
<%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='6'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>
<form action="edit_group" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">




<tr><td></td></tr>
<tr>
<td class="align_button_right" style=" font-size: 20px;">County<font color="red">*</font></td>
<td><Select id="county" class="textbox2" onchange="filter_districts(this);"  required ="true" name="county_id" >
<%=session.getAttribute("county_details").toString()%>
</select></td>


</tr>   
<tr><td></td></tr>
<tr><td></td></tr>
<tr>  
<td class="align_button_right" style=" font-size: 20px;">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner" class="textbox2"  required ="true" onchange="filter_target_pop(this);" name="partner" >
 <%=session.getAttribute("partner_dets").toString()%>
</select></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>  
<td class="align_button_right" style=" font-size: 20px;">District<font color="red">*</font></td>
<td><Select id="district" class="textbox2"  required ="true" onchange="filter_healthy_facility(this);" name="district" >
 <%=session.getAttribute("district_details").toString()%>
</select></td><td></td></tr>
<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 20px;">Healthy Facility<font color="red">*</font></td>
<td><Select id="health_facility" class="textbox2"  required ="true" name="health_facility" >
 <%=session.getAttribute("healthy_facility_details").toString()%>

</select></td><td></td></tr>
<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 20px;">Group Name: <font color="red">*</font></td>
<td>
  <%=session.getAttribute("group_det").toString()%> 
</td><td></td></tr>

<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 20px;">Location: <font color="red">*</font></td>
<td>
  <%=session.getAttribute("location").toString()%> 
</td><td></td></tr>

<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 20px;">Year Formed: <font color="red">*</font></td>
<td>
  <%=session.getAttribute("year").toString()%> 
</td><td></td></tr>
<tr><td></td></tr>
<tr><td></td><td><input type="submit" value="Save" class="submit"/></td></tr>

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
