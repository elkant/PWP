<%-- 
    Document   : mark_register3
    Created on : Nov 29, 2013, 10:01:43 PM
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
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <script type="text/javascript" >
            function change_image(src){
             var source=src.id;
             var data=src.value;
//             alert(source);
if(data=="2"){
document.getElementById("2"+source).src="images/x1.png"; 
}
else{
document.getElementById("2"+source).src="images/tck.png"; 
}
            }
            </script>
<title>Mark Attendance.</title> 
</head>
<body>
    <div id="container" style=" width: 1300px;" >
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
<div id="content" style="height: auto">
        <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 270px;width: 850px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 4/5. Mark Attendance - Tick Sessions Attended <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>
<br>
<div id="midcontent" style="margin-left:0px ; width: 1300px; height: auto">
 
    <%if (session.getAttribute("register3") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("register3")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("register3");
                            }

                        %>
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 20px;">
<p>This is the marking attendance page.</p>
<p>Only the message earlier pre-selected in step (2/5) will be enabled for marking.</p>
<p>By default the attendance is loaded marked, select absent if the client did not receive the specific message.</p>
<p>All the other messages are disabled hence their attendance cannot be marked. </p>
</div>
<form action="save_register" name="form" onsubmit="return nullchecker();" method="post" >
    <br>
    <div style=" font-size: 21px; height: 50px;">
    <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 1300px">
        <tr style="height: 40px;">    
     <th> District Name :  <%=session.getAttribute("district_name").toString()%></th>
      <th> Partner Name :  <%=session.getAttribute("partner_name").toString()%></th>
      <th> Group Name : <%=session.getAttribute("sessions_group_name").toString()%></th>
        </tr>
    </table>  
</div>
   
   
   <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 1300px">
           <tr>
          <th>Serial No.</th> 
          <th colspan="3"> Client Details</th> 
          <th colspan="13">PWP Information/Counseling Provided (Select Appropriately)<br>  Use The Codes Provided</th> 
     </tr>
       <tr> 
       <th>Serial No</th>
       <th>Name Of Client</th>
       <th>Age</th>
       <th>Sex</th>
       
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
       
       <!--<input type="hidden" name="pos" value="<%=session.getAttribute("total_clients")%>">-->
       <c:forEach items="${clientale}" var="client_dets">
       <tr>

          <input type="hidden" name="client_id${client_dets.positioner}" value="${client_dets.client_id}" />
          
           <td style=" width: 30px;">${client_dets.positioner}</td>
            <td>${client_dets.client_name} </td>
             <td style=" width: 20px;">${client_dets.age} </td>
              <td style=" width: 40px;">${client_dets.sex}     </td>
            <%
  int i=1;
while(i<=13) {

if(session.getAttribute("chosen_session_1").toString().contains(","+i+","))  {              
%>

<td style=" width: 50px;"> <select name="session${client_dets.positioner}<%=i%>"  id ="session${client_dets.positioner}<%=i%>" style=" width: 47px; "onchange="return change_image(this);">    
               <%=session.getAttribute("signs_are").toString()%>
    </select><img id="2session${client_dets.positioner}<%=i%>" src="images/tck.png" width="13px" height="12px">
               </td>
      <%} else {%> 
      <td style=" width: 50px;"> <select name="session${client_dets.positioner}<%=i%>"  id ="session${client_dets.positioner}<%=i%>" style=" width: 47px; " disabled="true">    
              <option value="5"></option>
    </select><img id="" src="images/warning.png" width="13px" height="12px">
               </td>
               <% }i++;
}%>

                        </tr>
       </c:forEach>

                       
                      <tr><td colspan="17"><input type="submit" name="sub" value="Save And Continue" class="textbox1" style="background: #cc99ff; color: #0000ff; width: 180px; height: 40px;"></td></tr>
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
