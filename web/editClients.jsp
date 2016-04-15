<%-- 
    Document   : editClients
    Created on : Dec 9, 2014, 10:53:43 AM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.JPG"/>
        <title>Edit Clients</title>
        <!--<link href="media/js/jquery.js" rel="stylesheet" type="text/css" />-->
	<!--<script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
         <script src="dataTable/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>

         
         <script src="dataTable/jquery.dataTables.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="dataTable/jquery.jeditable.js" type="text/javascript"></script>
        <script src="scripts/jquery_ui.js" type="text/javascript"></script>
         <script src="scripts/jquery.validate.js" type="text/javascript"></script>
          <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
          <script src="scripts/dataTables.scroller.js" type="text/javascript"></script>
          <script src="scripts/dataTables.colReorder.js" type="text/javascript"></script>
          <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
          <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
          
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
        
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
        
<title>Edit Clients.</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools

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
function filter_groups(){
  var district_id=document.getElementById("district").value; 
        var partner_id=document.getElementById("partner").value;   
 if(district_id!="" && partner_id!="") { 
       var dis_part=partner_id+"-"+district_id;
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
document.getElementById("grp").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","loadGRP?district="+district_id+"&&partner="+partner_id,true);
xmlhttp.send();
}
}   
  $(document).ready( function (){
$("#grp").change( function(){
 var district,partner,group;
 district=$("#district").val();
 partner=$("#partner").val(); 
 group=$("#grp").val(); 
$("#demo").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='example'><tr><td>Loading Data...<img src='images/utube.gif' alt='.'></td></tr></table>");
 $.ajax({
               url:"loadEditClients?partner="+partner+"&&district="+district+"&&group="+group,
               type:"post",
//               data:""
               dataType:"html",
               success:function(data){
               $("#example").html(data);
              $('#example').dataTable().makeEditable({
  sUpdateURL: "saveEditedClient",
                  "aoColumns": [ null,null,null,null,
                      
               {
                           //indicator: 'Saving Engine Version...',
                    tooltip: 'Click to change group',
                    loadtext: 'loading...',
                    type: 'select',
                    onblur: 'cancel',
                    submit: 'Ok',
                    loadurl: 'allGroups',
                    loadtype: 'GET',
//                    data: "{'':'Please select...', 'Female':'Female','Male':'Male'}",
                    sUpdateURL: "saveEditedClient"                                                                      
                        },
                 {
                           //indicator: 'Saving Engine Version...',
                    tooltip: 'Click to select provider',
                    loadtext: 'loading...',
                    type: 'select',
                    onblur: 'cancel',
                    submit: 'Ok',
                    loadurl: 'getProvider',
                    loadtype: 'GET',
//                    data: "{'':'Please select...', 'Female':'Female','Male':'Male'}",
                    sUpdateURL: "saveEditedClient"                                                                      
                        },
                        
                     {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'text',
                            submit:'Save changes',
                            callback : function(value,settings){
                                //window.location.reload();
                                alert("called");
                            },
                           fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
//document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;

}
                                                                                              
                        },
                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value,settings){
                                window.location.reload();
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
//document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
}
                                                                                              
                        },
                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value,settings){
                                window.location.reload();
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
//document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
}
                                                                                              
                        },
                        {
                           //indicator: 'Saving Engine Version...',
                    tooltip: 'Click to select Age',
                    loadtext: 'loading...',
                    type: 'select',
                    onblur: 'cancel',
                    submit: 'Ok',
                    loadurl: 'getAge',
                    loadtype: 'GET',
//                    data: "{'':'Please select...', 'Female':'Female','Male':'Male'}",
                    sUpdateURL: "saveEditedClient"                                                                      
                        },
                         {
                           //indicator: 'Saving Engine Version...',
                    tooltip: 'Click to select gender',
                    loadtext: 'loading...',
                    type: 'select',
                    onblur: 'cancel',
                    submit: 'Ok',
//                						loadurl: 'EngineVersionList.php',
//                						loadtype: 'GET',
                    data: "{'':'Please select...', 'Female':'Female','Male':'Male'}",
                    sUpdateURL: "saveEditedClient"                                                                      
                        }
            ]
//////                    
              });
//alert("data");           
            }
////                            
////                            
                        });
//    
});
//
});
</script>

</head>
<body onload="load_counties();load_partner();">

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
<br>
<div id="content" style="height:auto; margin-left: 10px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: -20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 100px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Edit Client Details.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>

<br>
<div id="midcontent" style="margin-left:10px ; height: auto">
<div id="dialog" title="Edit Clients." style=" font-size: 17px;">
    <p>The fields that have a <font color="red">*</font> are <b>editable</b>.</p>
    <p>Users are able to change <b>group, service provider</b>,and client's basic details including;
    <b>first name,middle name, last name,age,and gender.</b>
    The other fields are un-editable.</p>
    <p><img src="images/editClient.JPG" alt='editor image' style='height: 100px; width: 450px;'></p>
    <p>e.g double click where the arrow points to as shown above...</p>
    <!--<p>NOTE: to edit, double click on the field that you need to edit, if it is the first name of a client, just click on this first name..-->
    </p><p><font color='red'>NOTE</font>: Changes will be shown when the user refreshes the page.</p>
</div>
<!--<p style=" font-size: 20px;"><font color="red">*</font> indicates must fill fields</p>-->
<form action="#" method="post" style="width:1150px;">
<table style="margin-left:200px;" cellpadding="2px" cellspacing="3px" border="0px">

    <tr><td colspan="4">SELECT PATAMETERS HERE</td></tr>
<tr>
<td><Select id="county" class="textbox2" onchange="filter_districts(this);"  required ="true" name="county_id" style="width:180px;height:30px;" >
<option value="">Choose County</option> 
</select></td>
<td><Select id="partner" class="textbox2"  required ="true" onchange="filter_groups();" name="partner" style="width:180px;height:30px;" >
  
<option value="">Choose partner</option>  
</select></td>
<td><Select id="district" class="textbox2"  required="true" onchange="filter_groups();" name="district" style="width:180px;height:30px;" >
<option value="">Choose District</option>  
</select></td>
<td><select name="grp" id="grp" class="textbox2" required style="width:180px;height:30px;">
        <option value="">Choose Group</option>
   </select></td>
<!--<td><input type="button" id="submit" value="Search"></td>-->
</tr>

</table>
    
  <div id="demo" style="width:100%;margin-left:3px; z-index:0;">
                         <table cellpadding="4px" cellspacing="4px" style="padding-top: 1px;"border="0" class="display" id="example">
<tr><td>SELECT PARAMETERS</td></tr>
               </table>
 </div>   
    
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

