<%-- 
    Document   : YearlyTargets
    Created on : Oct 30, 2014, 10:24:12 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <title>PWP Yearly Targets</title>
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
        <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
        <link rel="shortcut icon" href="images/header.JPG"/>
        <script type ="text/javascript" src="js/datepicker/min.js"></script>
<!--        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

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
            
              <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
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
       <script type="text/javascript">
  function nullchecker(){
      var no_of_rws=parseInt(document.getElementById("all_rows").value);
     var rtn=true;
      var i=1;
           var found="";
     while(i<=no_of_rws){
     var county,partner,year,target;
     county=document.getElementById("countyid"+i).value;
     partner=document.getElementById("partnerid"+i).value;
     year=document.getElementById("year"+i).value;
     target=document.getElementById("target"+i).value;
     if(county=="" && (partner!="" || year!="" || target!="")){
      alertify.alert("<image src=\"images/warning.png\" width=\"90px\" height=\"70px\"><b>Enter all Details at line .."+i+".</b>");
             rtn=false;
             break;
     }
     if(partner=="" && (county!="" || year!="" || target!="")){
      alertify.alert("<image src=\"images/warning.png\" width=\"90px\" height=\"70px\"><b>Enter all Details at line .."+i+".</b>");
             rtn=false;
             break;
     }
     if(year=="" && (partner!="" || county!="" || target!="")){
      alertify.alert("<image src=\"images/warning.png\" width=\"90px\" height=\"70px\"><b>Enter all Details at line .."+i+".</b>");
             rtn=false;
             break;
     }
     if(target=="" && (partner!="" || county!="" || year!="")){
      alertify.alert("<image src=\"images/warning.png\" width=\"90px\" height=\"70px\"><b>Enter all Details at line .."+i+".</b>");
             rtn=false;
             break;
             
     }
     
       i++; 
        }  
return rtn;
     }
//      
function deleteRow()
{
    var all_rows=document.getElementById("all_rows").value;
   
     if(all_rows=="1"){
        
    }
    else{
    var rws2=--all_rows
    if(rws2<0){
       rws2=0 
    }
document.getElementById('tb').deleteRow(all_rows)
document.getElementById("all_rows").value=rws2;
}
}
function insRow()
{
var all_rows=document.getElementById("all_rows").value;
    var rws2=++all_rows

   document.getElementById("all_rows").value=rws2;
var tbl = document.getElementById('tb');
var lastRow = tbl.rows.length;
var x=document.getElementById('tb').insertRow(lastRow)
var a=x.insertCell(0);
var y=x.insertCell(1);
var z=x.insertCell(2);
var v=x.insertCell(3);
var q=x.insertCell(4);
//var l=x.insertCell(5);
var hi = 1;
y.innerHTML="<select id='countyid"+rws2+"' name='countyid"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange=''></select>"
z.innerHTML="<select id='partnerid"+rws2+"' name='partnerid"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange=''></select>"
v.innerHTML="<select id='year"+rws2+"' name='year"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange=''></select>"
q.innerHTML="<input type='text' value='' name='target"+rws2+"' id='target"+rws2+"' maxlength='10' onkeypress='return numbers(event)' class='textbox'style='width: 70px;border-color: green'>";
//l.innerHTML="<input type='text' value='' name='estimatedcost"+rws2+"' id='estimatedcost"+rws2+"' maxlength='10' onkeypress='return numbers(event)' class='textbox'style='width: 70px;border-color: green'>";
a.innerHTML=""+rws2+""

loadCounty();
loadPartners();
loadYears();
}

function rws(){
    var all_rows=0;
   
    document.getElementById("all_rows").value=all_rows;
    for( var i=0; i<=0; i++){
  insRow(); 
    }
}

function loadCounty(){
    var current_row=document.getElementById("all_rows").value;
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
//  var data=xmlhttp.responseText;
//   alert(data)
document.getElementById("countyid"+current_row).innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","loadCounty?current_row="+current_row,true);
xmlhttp.send();
}


function loadPartners(){
    
var xmlhttp; 
 var current_row=document.getElementById("all_rows").value;
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
//   var data=xmlhttp.responseText;
//   alert(data)
document.getElementById("partnerid"+current_row).innerHTML=xmlhttp.responseText;
//document.getElementById("approved_by").innerHTML=xmlhttp.responseText;

}
}
xmlhttp.open("POST","loadPartner?current_row="+current_row,true);
xmlhttp.send();
}


function loadYears(){
    
var xmlhttp; 
 var current_row=document.getElementById("all_rows").value;
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
//   var data=xmlhttp.responseText;
//   alert(data)
document.getElementById("year"+current_row).innerHTML=xmlhttp.responseText;
//document.getElementById("approved_by").innerHTML=xmlhttp.responseText;

}
}
xmlhttp.open("POST","loadYears?current_row="+current_row,true);
xmlhttp.send();
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

</script></head>   
<body onload="rws();">
   
    
 <div id="container" style="height:auto;" >    
       <%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}%>
   

<div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #CFDDEE; font-size: 24px; text-align: center;">Procurement Request Form Entry . <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>    
  
      <div id="content" style="height:auto;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                
                 <% if (session.getAttribute("prf_added") != null) { %>
                                <script type="text/javascript"> 
                    var n = noty({text: '<%=session.getAttribute("prf_added")%>',
                        
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("prf_added");
                            }
%>
                
   
<div id="dialog" title="Request Form." style=" font-size: 17px;">
    <p>Within this page, the user can be able to enter procurement data to the system.</p> 
    <p>All the fields marked <font color="red">*</font> are mandatory fields and their respective values must be entered.</p>
    <p><font color="red">NOTE : </font> If more than 1 item was requested, click on add row to add another row for entry of the Item data.</p>
    <p>Incase you want to delete a redundant row click on delete row to delete the row with its associated data. <br>
    <font color="red">Warning: </font> This will delete the data already entered on the last row.
    </p>
</div>
  <p style="margin-left: 40%;"><font color="red">NOTE:</font> All fields are must enter.</p>
  
                      <div id="midcontent" style="margin-left:20px ; height:auto;" onmousemove="return fill_others1();" >
      <form style=" margin-left: 70px; width: 900px;" method="post" action="saveTargets" onsubmit="return nullchecker();">
       
          
          <br><input type="hidden" id="all_rows" name="all_rows" value="0" >
    <input type="hidden" id="total_rows" name="total_rows" value="">
    <input type="button" onclick="insRow()" value="Add Row" class="textbox" style=" height: 30px; width: 100px;">
        <input type="button" onclick="deleteRow()" value="Delete row" class="textbox" style=" height: 30px; width: 100px;">
<br><br>
   <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style"  style="margin-left: 50px;background: #ccccff; width:800px; ">
    
                      <tr>
                      <td style="width:29.85%">County Name<font color="red">*</font></td>
                      <td style="width:20.75%">Partner Name<font color="red">*</font></td>
                       <td style="width:20.5%">Pepfar Year<font color="red"></font></td>
                      <td style="width:14.5%">Target<font color="red">*</font></td>
                      <!--<td style="width:24.5%">Estimated-Cost<font color="red"></font></td>-->
                      </tr>
</table>
<table cellpadding="2px" cellspacing="3px" border="1px" class="table_style" id="tb"  style="margin-left: 50px; background: #ccccff; width:800px;;">
</table>
    <br>
        <input type="submit" name="sub" value="Save" class="textbox1" style="background: #cc99ff; color: #0000ff;height: 30px; width: 100px;" >
     
<br><br>     
     </form> 
   </div>

        
</div>
                        
 
        
 <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
 <div id="footer">
             <p align="center">&copyPWP System Aphia Plus | USAID <%=year%></p>
            </div>                             
</div>
</body>
</html>

