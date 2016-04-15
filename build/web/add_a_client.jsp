<%-- 
    Document   : add_a_client
    Created on : Apr 1, 2014, 1:09:16 PM
    Author     : Geofrey Nyabuto
--%><%@page import="pwp.dbConn"%>
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
                        width:500,
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
<title>Select Message</title>
<script type="text/javascript"> 
function filter_groups(){
        var cat=document.getElementById("cat").value;
        if(cat==1){
       document.getElementById("1").style="display: compact";
       document.getElementById("no_of_clients").required=true;   
        }
        if(cat==2){
          document.getElementById("1").style="display: none";
          document.getElementById("no_of_clients").value="";
          document.getElementById("no_of_clients").removeAttribute("required");
        }
}
</script>
<script type="text/javascript">
    $(document).ready( function(){
       $.ajax({
        url:"marked_sess",
        type:"post",
        dataType:"html",
        success:function(data){
        $("#chosen_session").html(data);  
        } 
    });
    });
    </script>
    
        <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('.textbox2').jBox('Tooltip', {
    position: {
       x: 'right'
    },
    width:300,
    outside: 'x'
});      
         
     });
    
        
   </script>
    
    <link rel="stylesheet" href="select2/css/select2.css">  
</head>
<body>

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
<div id="content" style="height:auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 180px;width: 850px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 2/5. Select message (s) given.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></div>
<a href="add_a_group.jsp" class="linkstyle" style="height: 30px; margin-left: 50px; background: #ff00cc"><< Go Back</a>
<div id="midcontent" style="margin-left:230px ; height: auto;">
<div id="dialog" title="Select message Help." style=" font-size: 17px;">
<p>Select the messages you want to register,<b>press and hold on CTRL Button to multi-select</b>(select more than one message).</p><p> The messages that are preceeded by a * are those whose attendance has already been marked.</p>
</div>
<p style=" font-size: 20px;"><font color="red" >*</font> indicates required fields</p>
<form action="add_clients_session" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
<input type="hidden" name="src" value="add_a_clients2.jsp">
<tr><td></td></tr>
 <%if (session.getAttribute("already_clients") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("already_clients")%> . Click To Close.',
                        layout: 'center',
                        type: 'Success'
//                        ,
// 
//                         timeout: 8800
                     });
                    
                </script> <%
                session.removeAttribute("already_clients");
                            }

                        %>
<tr> <td class="align_button_right" style=" font-size: 18px;">Group Name  : </td>
    <td><p style="font-size: 17px;"><%=session.getAttribute("group_name").toString()%></p></td>
</tr>
<!--                        
                        <tr> <td class="align_button_right" style=" font-size: 18px;">Choose Client's Registration Year <font color="red">*</font></td>
    <td><select name="year" id="year" class="textbox2"   required ="true" onchange=" return load_sess(this);">
 <option value="">Choose Year</option> 


</select></td>
</tr>-->
<tr><td></td></tr><tr style="display: compact">
<td class="align_button_right" style=" font-size: 20px;">Choose Message (s) Given<font color="red">*</font></td>
<td>
    <select name="chosen_session" id="chosen_session" class="textbox2" onchange="" required="true" title="<font color='red'>NOTE : </font><br>1. Messages that are preceeded by an aesterick (*) are those messages whose attendance has already been marked.<br>2. Users are required/expected to select message(s) which they want to add or edit <br>3. Users can select more than one message by pressing and holding Control button (CTRL). When editing, please select only one message which you want to edit. <br>" style="height: 220px; width:300px;" multiple>
        <option value="">Loading Messages ....</option>
<!--        <option value="1" title="Knowledge of HIV Status">1. Knowledge of HIV Status</option>
        <option value="2" title="Partner HIV Testing">2. Partner HIV Testing</option>
        <option value="3" title="Child HIV Testing">3. Child HIV Testing</option>
        <option value="4" title="Discordance">4. Discordance</option>
        <option value="5" title="HIV Disclosure">5. HIV Disclosure</option>
        <option value="6" title="Risk Factor/Reduction">6. Risk Factor/Reduction</option>
        <option value="7" title="Condom Use">7. Condom Use</option>
        <option value="8" title="Alcohol and Substance Abuse">8. Alcohol and Substance Abuse</option>
        <option value="9" title="Adherence">9. Adherence</option>
        <option value="10" title="STIs">10. STIs</option>
        <option value="11" title="Family Planning">11. Family Planning</option>
        <option value="12" title="PMTCT">12. PMTCT</option>
        <option value="13" title="TB">13. TB</option>-->
   </select>
    
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
   <script src="select2/js/select2.js"></script>
   <script type="text/javascript">
 $(document).ready(function(){
// $('select').select2();    
 });   
</script>
</body>
</html>
