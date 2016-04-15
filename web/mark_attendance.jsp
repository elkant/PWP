<%-- 
    Document   : mark_attendance
    Created on : Nov 29, 2013, 10:56:57 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((session.getAttribute("userid")==null)) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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

	</script>               
                            
                
<title>Mark / Edit Attendance</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 $(function() {
$( "#datepicker0" ).datepicker({maxDate: new Date()});
$( "#datepicker1" ).datepicker({maxDate: new Date()});
$( "#datepicker2" ).datepicker({maxDate: new Date()});
$( "#datepicker3" ).datepicker({maxDate: new Date()});
$( "#datepicker4" ).datepicker({maxDate: new Date()});
$( "#datepicker5" ).datepicker({maxDate: new Date()});
$( "#datepicker6" ).datepicker({maxDate: new Date()});
$( "#datepicker7" ).datepicker({maxDate: new Date()});
$( "#datepicker8" ).datepicker({maxDate: new Date()});
$( "#datepicker9" ).datepicker({maxDate: new Date()});
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
//set the partner

function load_sessions(){
  
        var group_id=document.getElementById("group").value;
//        alert(group_id)
if(group_id=="0"){
 document.getElementById("individual").hidden=false; 
 document.getElementById("individual").required=true;
 document.getElementById("txt").hidden=false;
 load_individuals();
}
else{
    document.getElementById("individual").hidden=true; 
    document.getElementById("txt").hidden=true;
    document.getElementById("individual").required=false;
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
}
}
xmlhttp.open("POST","sessions_loader?group_id="+group_id,true);
xmlhttp.send(group_id);

}
//set the partner

}

function load_individuals(){
  
        var district_id=document.getElementById("district").value; 
        var partner_id=document.getElementById("partner").value; 
        var year=document.getElementById("year").value; 
        var period=document.getElementById("period").value; 
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
document.getElementById("individual").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_individuals?district_id="+district_id+"&&partner_id="+partner_id+"&&year="+year+"&&period="+period,true);
xmlhttp.send();  
      
}

function load_sessions2(){
  
        var individual_id=document.getElementById("individual").value;
        
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
}
}
xmlhttp.open("POST","individual_session_loader?individual_id="+individual_id,true);
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

</head>
<body onload=" load_partner();load_counties();get_years();">
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


<div id="content" style="height: auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 4. Mark Attendance.</div>


<div id="midcontent" style="margin-left:230px ; height: auto">
<div > <h4 style=" font-size: 23px;">Select details appropriately to Mark Attendance.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div> 
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 15px;">
<%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='15'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>
<p style=" font-size: 20px;"><font color="red">*</font> indicates must fill fields</p>

<form action="attendance_level_decider" method="post" onsubmit="return load_sessions();">
<table cellpadding="2px" cellspacing="3px" border="0px" >

<%if (session.getAttribute("edit_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("edit_success")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("edit_success");
                            }

                        %>


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
    </select></td><td rowspan="4">
        <%if(session.getAttribute("district_id")!=null && session.getAttribute("partner_id")!=null && session.getAttribute("group_name_s")!=null && session.getAttribute("complete")!=null) {%>
      <div style="font-size: 16px; background-color: #E3E3E3; width: 300px;">
            <br>
        District Name : <%=session.getAttribute("district_name")%><br><br>
        Partner Name : <%=session.getAttribute("partner_name")%><br><br>
        Group Name : <%=session.getAttribute("group_name_s")%>
        
        <br><br>
        <a href="edit_register1" style="font-size: 20px; text-decoration: none;"><div style="margin-left: 50px;" class="buttonx">Edit Last Marked</div></a>
        </div><%}%><br>
    </td></tr>
<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 18px;">Group Name<font color="red">*</font></td>
<td><Select id="group" class="textbox2"  required ="true" name="group" onchange="load_sessions();">
<option value="">Choose Group</option>  

</select></td><td></td></tr>
<tr> <td class="align_button_right" style=" font-size: 18px;">Choose PEPfar Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox2"   required ="true" onchange=" return load_quarters(this);">
 <option value="">Choose Year</option> 


</select></td>
</tr>

<tr> <td class="align_button_right" style=" font-size: 18px;">Choose Period<font color="red">*</font></td>
    <td><Select name="period" id="period" class="textbox2"   required ="true" onchange=" return load_individuals();">
 <option value="">Choose Period</option> 


</select></td>
</tr>
<tr><td></td></tr>
<td><p id="txt" hidden="true" style=" font-size: 18px;">Choose an Individual</p></td>
<td><Select id="individual" class="textbox2"  required ="true" name="individual" onchange="load_sessions2();" hidden="true">
        
<option value="" >Choose Individual</option>  

</select>
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