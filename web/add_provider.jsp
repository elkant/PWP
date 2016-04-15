<%-- 
    Document   : add_provider
    Created on : Nov 28, 2013, 8:50:57 AM
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
<%session.removeAttribute("groups_there");%>
<%session.removeAttribute("all_providers");%>
<%session.removeAttribute("no_of_providers");%>
<%session.removeAttribute("partner_name");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="prefix-free.js"></script>
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
        
<title>Add Service Providers</title>
 <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script type="text/javascript">

// a function that filters the districts in the passed county as per the county dropdown.
function load_counties(){    
     
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

 function load_all_providers(){
        var providers=document.getElementById("no_of_providers").value;
        var partner=document.getElementById("partner").value;
        var district=document.getElementById("district").value;
   var xmlhttp;
   if (partner=="")
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
}
}
xmlhttp.open("POST","provider_maker?no_of_providers="+providers+"&&partner="+partner+"&&district="+district,true);
xmlhttp.send();
group_dets();
}
 function group_dets(){ 
        var partner=document.getElementById("partner").value;
        var district=document.getElementById("district").value;
 var xmlhttp;
if(partner!="" && district!=""){
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
    document.getElementById("no_of_grp").value=xmlhttp.responseText;
    no_group_allerter();
}
}
xmlhttp.open("POST","group_existence_confirmer?partner="+partner+"&&district="+district,true);
xmlhttp.send();
}
 }
function no_group_allerter(){

        var no_of_gr=document.getElementById("no_of_grp").value;
        if(no_of_gr=="" || no_of_gr==0){
        alertify.alert("No Group Under That District And Partner... Choose Again")
       document.getElementById("no_of_providers").value="";
        
          return false;
} 
else{
    return true;
}
}
</script>

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

<%if (session.getAttribute("adder") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("adder")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("adder");
               
                            }

                        %>
                         <%if (session.getAttribute("updator") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("updator")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
              
                session.removeAttribute("updator");
                            }

                        %>

<div id="content" style="height:470px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Add New Service Providers.</div>

<div id="midcontent" style="margin-left:230px ; height: 400px;">
<div ><h4 style=" font-size: 23px;">Specify details appropriately to add service Provider(s).<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div> 
    

<div id="dialog" title="Add Service Providers Help." style=" font-size: 17px;">
<%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='7'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>

<p style=" font-size: 20px;"><font color="red">*</font> indicates must fill fields</p>
<form action="provider_maker" method="post" onsubmit="return load_all_providers();">
<table cellpadding="2px" cellspacing="3px" border="0px">




<tr><td></td></tr>
<tr>
<td class="align_button_right" style=" font-size: 20px;">County<font color="red">*</font></td>
<td><Select id="county" class="textbox2" onchange="filter_districts(this);"  required ="true" name="county_id" >
<option value="">Choose County</option> 
</select></td>


</tr>   
<tr><td></td></tr>
<input type="hidden" id="no_of_grp" value="" onchange="return no_group_allerter();">
<tr><td></td></tr>
<tr>  
<td class="align_button_right" style=" font-size: 20px;">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner" class="textbox2"  required ="true" onchange="return load_all_providers();"  name="partner" >

<option value="">Choose partner</option>  
</select></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>  
<td class="align_button_right" style=" font-size: 20px;">District<font color="red">*</font></td>
<td><Select id="district" class="textbox2" required ="true" onchange="return load_all_providers();" name="district" >
<option value="">Choose District</option>  
</select></td><td></td></tr>
<tr><td></td></tr>
<td class="align_button_right" style=" font-size: 20px;">Number of Service Providers<font color="red">*</font></td>
<td>
    <input type="text" name="no_of_providers" id="no_of_providers" value="" maxlength="2" onkeypress="return numbers(event)" class="textbox" required>   
    
</td><td></td></tr>
<tr><td></td></tr>
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
