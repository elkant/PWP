<%-- 
    Document   : add_clients
    Created on : Nov 27, 2013, 10:28:26 AM
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
//set the partner
function get_period(){
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
document.getElementById("period").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","period_loader",true);
xmlhttp.send();
}
function load_quarters(yr){
        var year=yr.value;

        var prev_year=year-1;
    if(year!="0"){
    document.getElementById("period").innerHTML="<select id='period' name='period' style='width: 120px;' required><option value=''>Choose Period</option><option value='1'>Oct-Dec ("+prev_year+")</option><option value='2'>Jan-March ("+year+")</option><option value='3'>April-June ("+year+")</option><option value='4'>July-Sept("+year+")</option></select>";
 }
}
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
<title>Add Individuals</title>
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

        function load_partner(){
var xmlhttp="";
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

//*******************filter healthy facility*************************

function filter_groups(){

        var district_id=document.getElementById("district").value; 
        var partner_id=document.getElementById("partner").value; 
     if(district_id!="" && partner_id!="") {  
       var dis_part=partner_id+"-"+district_id;
//        alert("here  :  "+dis_part)
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
document.getElementById("group").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_groups?district_id="+district_id+"&&partner_id="+partner_id,true);
xmlhttp.send();
}
}
 function load_no_of_clients(){
     var clients=document.getElementById("no_of_clients").value;
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
}
}
xmlhttp.open("POST","load_no_clients?clients="+clients,true);
xmlhttp.send();
}
 function get_providers(){
     var group=document.getElementById("group").value;
     var partner=document.getElementById("partner").value;
     var district=document.getElementById("district").value;
//     alert("partner  : "+partner+"  distr  : "+district+"   group  : "+group)
     if(group!="" && partner!="" && district!=""){
         var xmlhttp=""
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
document.getElementById("provider").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","get_providers?group="+group+"&&partner="+partner+"&&district="+district,true);
xmlhttp.send();
if(group=="0"){
    
document.getElementById("n1").hidden=false;  
document.getElementById("n2").hidden=false;
document.getElementById("nhf").required=true;
get_nhfs();
}
else{
document.getElementById("n1").hidden=true;  
document.getElementById("n2").hidden=true;
document.getElementById("nhf").required=false;
}
}

 }
function get_nhfs(){

var dist=document.getElementById("district").value;;    
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
document.getElementById("nhf").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_health_facility?district_id="+dist,true);
xmlhttp.send();
}//end of filter healthy facility
</script>

</head>
<body onload=" load_partner();load_counties();get_years();">

<div id="container" >
<%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%}%>
<div id="header" align="center">
<br/>
</div>
 <%if (session.getAttribute("clients_added") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("clients_added")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 6800});
                    
                </script> <%
                session.removeAttribute("clients_added");
                            }

                        %>
                         <%if (session.getAttribute("already_clients") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("already_clients")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 6800});
                    
                </script> <%
                session.removeAttribute("already_clients");
                            }

                        %>

<div id="content" style="height:700px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Add New Individuals.</div>


<div id="midcontent" style="margin-left:230px ; height: 450px;">
<div >    <h4 style=" font-size: 23px;">Specify details appropriately to add Individuals(s).<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div> 
<div id="dialog" title="Add Individuals Help." style=" font-size: 17px;">
<%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='11'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>
<p style=" font-size: 20px;"><font color="red" >*</font> indicates must fill fields</p>
<form action="add_clients_session" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
<input type="hidden" name="src" value="add_clients2.jsp">



<tr><td></td></tr>
<tr>
<td class="align_button_right" style=" font-size: 18px;">County<font color="red">*</font></td>
<td><Select id="county" class="textbox2" onchange="filter_districts(this);"  required ="true" name="county_id" >
<option value="">Choose County</option> 
</select></td>


</tr>   
<tr><td></td></tr>
<tr><td></td></tr>
<tr>  
<td class="align_button_right" style=" font-size: 18px;">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner" class="textbox2"  required ="true" onchange="filter_groups();" name="partner" >

<option value="">Choose partner</option>  
</select></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>  
<td class="align_button_right" style=" font-size: 18px;">District<font color="red">*</font></td>
<td><Select id="district" class="textbox2"  required ="true" onchange="filter_groups();" name="district" >

<option value="">Choose District</option>  
</select></td><td></td></tr>
<tr><td></td></tr>
<tr><td class="align_button_right" style=" font-size: 18px;">Group Name<font color="red">*</font></td>
<td><select id="group" class="textbox2"  required ="true" name="group" onchange="get_providers()">
<option value="">Choose Group</option>  
</select></td><td></td></tr>
<tr><td class="align_button_right" style=" font-size: 18px;">Choose Service Provider<font color="red">*</font></td>
<td><select id="provider" class="textbox2"  required ="true" name="provider">
<option value="">Choose Provider</option>  

</select></td><td></td></tr>

<tr><td class="align_button_right" style=" font-size: 18px;"><p hidden="true" id="n1">Choose Healthy Facility<font color="red">*</font></p></td>
<td><p hidden="true" id="n2"><select id="nhf" class="textbox2"  required ="true" name="nhf">
<option value="">Choose Healthy Facility</option>  

</select></p></td><td></td></tr>
</p>
<tr> <td class="align_button_right" style=" font-size: 18px;">Choose PEPfar Year <font color="red">*</font></td>
    <td><Select name="year" id="year" class="textbox2"   required ="true" onchange=" return load_quarters(this);">
 <option value="">Choose Year</option> 


</select></td>
</tr>

<tr> <td class="align_button_right" style=" font-size: 18px;">Choose Period<font color="red">*</font></td>
<td><Select name="period" id="period" class="textbox2"   required ="true">
 <option value="">Choose Period</option> 


</select></td>
</tr>

<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 18px;">Number of Individuals<font color="red">*</font></td>
<td>
    <input type="text" name="no_of_clients" maxlength="2" id="no_of_clients" value="" class="textbox" onkeypress="return numbers(event)" required>   
    
</td><td></td></tr>
<tr><td></td><td><input type="submit" value="Next" class="submit"/></td></tr>

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
