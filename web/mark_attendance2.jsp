<%-- 
    Document   : mark_attendance2
    Created on : Nov 29, 2013, 11:03:51 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((session.getAttribute("sessions_group_name")==null)||(session.getAttribute("userid")==null)) {
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

           <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
	 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
         <!--calender-->
         
        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>	
	<script src="js/js/jquery-ui-1.10.3.custom.js"></script>

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

<title>Sessions Details.</title> 
<script type="text/javascript">
$(document).ready(function() {
      
     $.ajax({
        url:"loadMinDate",
        type:"post",
        dataType:"html",
        success:function(data){
        var minDate=data;

        
$( "#datepicker1" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker2" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker3" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker4" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker5" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker6" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker7" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker8" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker9" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker10" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker11" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker12" ).datepicker({minDate: minDate,maxDate: new Date()});
$( "#datepicker13" ).datepicker({minDate: minDate,maxDate: new Date()});    
    } 
    });

//$( document ).tooltip();
});
    
    
</script>
 <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script type="text/javascript">
    
function nullchecker(){ 
 var dateObject,day,month,year,current_date;
 var selected_date,selected_month,selected_year;
  //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    
   current_date=month+"/"+day+"/"+year;  
var i=1;
var all_sess="";
while(i<=13){ 
  
  if(document.getElementById("datepicker"+i)!=null){   
    var datepicker=document.getElementById("datepicker"+i).value;
//        var messages_given=document.getElementById("messages_given"+i).value;
        var method_used=document.getElementById("method_used"+i).value;
        var time_taken=document.getElementById("time_taken"+i).value;
   
//if(datepicker=="" || messages_given=="" || method_used=="" || time_taken==""){
    if((datepicker!="" &&(method_used=="" || time_taken=="")) || (time_taken!="" &&(method_used=="" || datepicker==""))|| (method_used!="" &&(datepicker=="" || time_taken==""))){
    alertify.alert ("Enter all Details For Session "+i);
        return false;
        break;
}
 if(new Date(datepicker) > new Date(current_date)){
     alertify.alert("This date cannot be greater than current date. Check session date at session "+i);   
    return false;
break;    
    }
//  GET SESSION DATE YEAR.................  

//if(method_used!=""){
//var dates=datepicker.split("/");
//var yea=dates[2];
//if(yea!=//out.println(session.getAttribute("year").toString());%>){
//     alertify.alert("The dates Chosen Must be within the earlier Selected Year. At Message/Session No. "+i);   
//    return false;
//break;    
//    }
//    }
   
    all_sess+=datepicker;
} 
   i++;
}
if(all_sess==""){
     alertify.alert ("Enter Data for atleast one session");
        return false;   
    
}
}


   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
</script> 
</head>
<body>
    <div id="container" style=" width: 1300px; height: 750px;" >
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
</div>
<div id="content" style="height: auto; width:auto">
        <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 220px;width: 850px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 3/5. Mark Attendance - Enter Sessions details <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>

<div id="midcontent" style="margin-left:0px ; width: 1300px; height: auto">
   <div ><a href="add_a_client.jsp" class="linkstyle" style="height: 30px; margin-left: 50px; background: #ff00cc"><< Go Back</a>
        <!--<a href="mark_attendance2.jsp" class="linkstyle" title="You ony need to refresh this page if you have entered data for a session whose data is missing." style="height: 30px; width: 200px; margin-left: 50px; background: #ff00cc;position: absolute; left: 800px; top: 160px;">Refresh Page</a></div>-->


                        <%if (session.getAttribute("clients_exist")!= null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("clients_exist")%>.Click to close',
                        layout: 'center',
                        type: 'Success'});
                    
                </script> <%
                session.removeAttribute("clients_exist");
                           }

                        %>
                        
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 20px;">
<p><font color="red">Note: </font>Only the message(s) that were earlier pre-selected in step 2/5 are enabled for data entry.</p>
<p>Session date, Method used, and time taken (duration) are mandatory fields.</p>
<p>After entering all this data, click on save and continue</p>
</div>
<form action="add_sessions" name="form" onsubmit="return nullchecker();" method="post" >
    <br>
    <div style=" font-size: 21px; height: 50px;">
    <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 1300px">
        <tr style="height: 40px;">    
     <th> District Name :  <%=session.getAttribute("district_name").toString()%>.</th>
      <th> Partner Name :  <%=session.getAttribute("partner_name").toString()%></th>
      <th> Group Name : <%=session.getAttribute("sessions_group_name").toString()%></th>
        </tr>
    </table>  
</div>
   <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 1300px">
<tr><th>Messages: </th>
<th>1. Knowledge Of HIV Status</th>
       <th>2. Partner HIV Testing</th>
       <th>3. Child HIV Testing</th>
       <th>4. Discordance</th>
       <th>5. HIV Disclosure</th>
       <th>6. Risk Factors / Reduction.</th>
       <th>7. Condom Use</th>
       <th>8. Alcohol And Substance Abuse</th>
       <th>9. Adherence</th>
       <th>10. STIs</th>
       <th>11. Family Planning</th>
       <th>12. PMTCT</th>
       <th>13. TB</th>
</tr>
           <%=session.getAttribute("session_details").toString()%>

           <tr><td colspan="14"><input type="submit" name="sub" onmouseover="return nullchecker1();" value="Save And Continue" class="textbox1" style="background: #cc99ff; color: #0000ff; width: 170px; height: 40px;"></td></tr>
</table>
   
       <br><br>
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
