<%-- 
    Document   : viewEditIndServices
    Created on : Feb 5, 2015, 4:36:20 PM
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
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <script type="text/javascript">
  $(function() {
$( "#datepicker" ).datepicker({maxDate: new Date()});
$(document).tooltip();
});
    
    
</script>
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
	</script> 
         <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
          <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script> 
<title>Edit Services provided</title> 
</head>
<body>
    <div id="container" style=" width: 1200px;" >
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
</div><br>
<div style="margin-left:0px;width: 100%; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">
    STEP 5/5. Edit Services Provided.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></div>
 <%if (session.getAttribute("edit_success") != null) { %>
                                <script type="text/javascript"> 
                     var n = noty({text: '<%=session.getAttribute("edit_success")%> Click to close',
                        layout: 'center',
                        type: 'Success'});
                     </script> <%
                session.removeAttribute("edit_success");
                            }%>
                            
<div id="content" style="height: auto">
        <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>

<div id="midcontent" style="margin-left:0px ; width: 1100px; height: auto">
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 18px;">
<p>Individual who appear here are only those who were present for the message earlier selected in section 4/5.</p>
<p>Tick or un-tick appropriately and save these changes.</p>
<p>Also enter the number of male condoms that were given out.</p>
</div>
<form action="saveEditedServices" name="form" onsubmit="return nullchecker();" style="margin-left: 50px;" method="post" >
    <br>
    
    <div style=" font-size: 21px; height: 50px;">
    <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 100%;">
        <tr style="height: 40px;">    
      <th> District Name :  <%=session.getAttribute("district_name").toString()%>.</th>
      <th> Partner Name :  <%=session.getAttribute("partner_name").toString()%></th>
      <th> Group Name : INDIVIDUAL</th>
      <th> Message : <%if(session.getAttribute("messageName")!=null){out.println(session.getAttribute("messageName").toString());}%></th>
        </tr>
    </table>  
</div>
   <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 100%;">
     <tr>
         <th>Serial No.</th> 
         <th colspan="3"> Client Details  </th> 
         <th colspan="9" style="width: 20px">PWP Services Provided (Tick Where Applicable)</th> 
     </tr>
     <tr> 
       <th>Serial No</th>
       <th>Name Of Client</th>
       <th>Age in Years</th>
       <th>Sex</th>
       <th>Received Contraceptives</th>
       <th>Referred to Service Point</th>
       <th>No Of Condoms Given</th>
       <th>Screened<br> For TB</th>
       <th>Screened<br> For STIs</th>
       <th>Partner<br> Tested</th>
       <th>Children<br> Tested</th>
       <th>Disclosed<br> Status</th>
       </tr>
       <c:forEach items="${EditInd}" var="EditInd">
       <tr>

          <input type="hidden" name="client_id${EditInd.positioner}" value="${EditInd.client_id}" />
          <input type="hidden" name="reg_id${EditInd.positioner}" value="${EditInd.regid}" />
           <td style=" width: 30px;">${EditInd.positioner}</td>
            <td>${EditInd.client_name} </td>
             <td style=" width: 20px;">${EditInd.age} </td>
              <td style=" width: 40px;">${EditInd.sex}     </td>
                <td style=" width: 40px;"><input type="checkbox" ${EditInd.contraceptive_method} name="contraceptive${EditInd.positioner}" id="contraceptive${EditInd.positioner}" value="YES" style=" width: 60px;">
                    <img src="${EditInd.imageCM}" style="width:14px; height: 14px;" > </td>
               <td style=" width: 40px;"><input type="checkbox"  ${EditInd.rsp} name="service_point${EditInd.positioner}" id="service_point${EditInd.positioner}" value="YES" style=" width: 60px;">
               <img src="${EditInd.imageSP}"  style="width:14px; height: 14px;"></td>
               <td style=" width: 40px;"><input type="text" class="textbox1" style=" background-color: #d6b4eb" maxlength="6" onkeypress="return numbers(event)" name="cds${EditInd.positioner}" id="cds${EditInd.positioner}" value="${EditInd.condoms_given}" placeholder="No. of CDs" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${EditInd.screened_tb} name="tb${EditInd.positioner}" id="tb${EditInd.positioner}" value="YES" style=" width: 60px;">
              <img src="${EditInd.imageTB}"  style="width:14px; height: 14px;" > </td>
               <td style=" width: 40px;"><input type="checkbox"  ${EditInd.screened_stis} name="sti${EditInd.positioner}" id="sti${EditInd.positioner}" value="YES" style=" width: 60px;">
              <img src="${EditInd.imageSTI}"  style="width:14px; height: 14px;" > </td>
               <td style=" width: 40px;"><input type="checkbox"  ${EditInd.tested_partner} name="partner_testing${EditInd.positioner}" id="partner_testing${EditInd.positioner}" value="YES" style=" width: 60px;">
              <img src="${EditInd.imagePartner}"  style="width:14px; height: 14px;" > </td>
               <td style=" width: 40px;"><input type="checkbox"  ${EditInd.tested_children} name="children_testing${EditInd.positioner}" id="children_testing${EditInd.positioner}" value="YES" style=" width: 60px;">
              <img src="${EditInd.imageChildren}"  style="width:14px; height: 14px;" > </td>
               <td style=" width: 40px;"><input type="checkbox"  ${EditInd.disclosed_status} name="disclosed_status${EditInd.positioner}" id="disclosed_status${EditInd.positioner}" value="YES" style=" width: 60px;">
              <img src="${EditInd.imageStatus}"  style="width:14px; height: 14px;" > </td>
             
                        </tr>
                        
                         <tr style="background-color: black; height: 6px;">
       </tr>
       </c:forEach>
                        <tr style=" height: 15px;"></tr>
                        
                        <tr><td colspan="17"><input type="submit" name="sub" value="Save"  class="textbox1" style="background: #cc99ff; color: #0000ff"></td></tr>

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

