<%-- 
    Document   : edit_services_provided
    Created on : Dec 4, 2013, 9:33:58 AM
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

$( document ).tooltip();
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
 function nullchecker(){
 var dateObject,day,month,year,current_date;
 var selected_date,selected_month,selected_year;
  //created the date object

  var total=0,value=0;
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();

//     alert("total entered    :   "+total+" and the total cds earlier is  :  "+prev_total)
//     if(prev_total!=total){
//
//           alertify.alert("The Number of Condoms Distributed should add up to the ones given during sessions.");
////         alertify.alert("The Number of Condoms Distributed should add up to the ones given during sessions."+prev_total+"  tot  : "+total);
//        return false;    
//     }
   current_date=month+"/"+day+"/"+year;  
    var date_picker=document.getElementById("datepicker").value;
    
    if(date_picker==""){
        alertify.alert("Enter Date of submission");
        document.getElementById("datepicker").focus();
        return false;
    }
 if(new Date(date_picker) > new Date(current_date)){
     alertify.alert("The date of submission can not be greater than the current date.");   
    return false;    
    }
   
}
    

   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>

<script type="text/javascript">
    $(document).ready(function(){
           $.ajax({
               url:"getPreparedBy",
               type:"post",
               dataType:"html",
               success:function(data){  
                $("#prep")  .html(data); 
                
               }
           });  
            $.ajax({
               url:"getReviewedBy",
               type:"post",
               dataType:"html",
               success:function(data){  
                $("#rev")  .html(data); 
                
               }
           }); 
           
            $.ajax({
               url:"getRemarks",
               type:"post",
               dataType:"html",
               success:function(data){  
                $("#rem")  .html(data); 
                
               }
           }); 
        
        
    });
    
    </script>
    
    
  <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('.tooltip').jBox('Tooltip', {
    position: {
       y: 'bottom'
    },
    width:300,
    outside: 'y'
});      
  
       $.ajax({
        url:"selectedMessages",
        type:"post",
        dataType:"html",
        success:function(data){
//        $("#mess").html(data); 
        new jBox('Notice', {
    content: data
});
        } 
    });
     });
    
        
   </script>

<title>Edit PWP Services Provided.</title> 

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
<div style="margin-left: 380px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Step 5/5. Edit Services Provided <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"> .</div>
    <div ><a href="edit_attendance.jsp" class="linkstyle" title="Go To The Previous Page." style="height: 30px; width: 150px; margin-left: 50px; background: #ff00cc;position: absolute; top: 150px;"><< Back</a></div> 
<div id="midcontent" style="margin-left:0px ; width: 1300px; height: auto">
<br><br>
    <%if (session.getAttribute("group_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("group_success")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("group_success");
                            }

                        %>
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 15px;">
<p>Check/tick or uncheck/untick to edit each clients details appropriately.</p>

<p>Enter the correct remarks, name of the person who prepared the document, reviewer and the correct date of submission.</p>
<p>Clients who were absent cannot be given services hence are disabled.</p>
<p>Press Save to Save and exit.</p>

</div>
<form action="save_edited_services_provided" name="form" onsubmit="return nullchecker();" method="post" >
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
         <th colspan="3"> Client Details  </th> 
         <th colspan="8" style="width: 20px">Edit PWP Services Provided (Tick/Untick Where Applicable)</th> 
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
       
       <c:forEach items="${edit_reg3}" var="edit_reg3">
<tr>
    
         <input type="hidden" name="service_id${edit_reg3.positioner}" value="${edit_reg3.service_id}">
       <input type="hidden" name="clientID${edit_reg3.positioner}" id="clientID${edit_reg3.positioner}" value="${edit_reg3.clientId}" style="width: 120px;"></td>
     <td style="width: 20px;">${edit_reg3.positioner}
     <td style=" width: 300px">${edit_reg3.full_name}</td>
      <td style="width: 20px;">${edit_reg3.age}</td>
       <td style="width: 40px;">${edit_reg3.sex}</td>
       
       <td><input type="checkbox" name="contraceptive${edit_reg3.positioner}" ${edit_reg3.disabled} value="YES" ${edit_reg3.contraceptive} style="width: 40px;"> <img src="${edit_reg3.contraceptive_image}" width="15px" height="15"></td>
      <input type="hidden" name="disabler_${edit_reg3.positioner}" value="${edit_reg3.disabled}" style="width: 40px;">
       <td style="height: 25px"><input type="checkbox" ${edit_reg3.disabled} name="service_point${edit_reg3.positioner}" value="YES" ${edit_reg3.service_point} style="width: 40px;"><img src="${edit_reg3.service_point_image}" width="15px" height="15"></td>
       <td><input type="text" name="cds${edit_reg3.positioner}" id="cds${edit_reg3.positioner}" ${edit_reg3.disabled} value="${edit_reg3.cds}" maxlength="6" onkeypress="return numbers(event)" style="width: 120px;"></td>
       <td><input type="checkbox" name="tb${edit_reg3.positioner}" ${edit_reg3.disabled} value="YES" ${edit_reg3.tb} style="width: 40px;"><img src="${edit_reg3.tb_image}" width="15px" height="15"></td>
       <td><input type="checkbox" name="sti${edit_reg3.positioner}" ${edit_reg3.disabled} value="YES" ${edit_reg3.sti} style="width: 40px;"><img src="${edit_reg3.sti_image}" width="15px" height="15"></td>
       <td><input type="checkbox" name="partner_testing${edit_reg3.positioner}" ${edit_reg3.disabled} value="YES" ${edit_reg3.partner_testing} style="width: 40px;"><img src="${edit_reg3.partner_testing_image}" width="15px" height="15"></td>
       <td><input type="checkbox" name="children_testing${edit_reg3.positioner}" ${edit_reg3.disabled} value="YES" ${edit_reg3.children_testing} style="width: 40px;"><img src="${edit_reg3.children_testing_image}" width="15px" height="15"></td>
       <td><input type="checkbox" name="disclosed_status${edit_reg3.positioner}" ${edit_reg3.disabled} value="YES" ${edit_reg3.disclosed_status} style="width: 40px;"><img src="${edit_reg3.disclosed_status_image}" width="15px" height="15"></td>
     </tr>
    
</c:forEach>
     
     <tr style=" height: 15px;"></tr>
     
     <c:forEach items="${edit_reg3_general}" var="edit_reg3_general">
                        <tr style=" height: 50px;">
                            <td colspan="12">Remarks:  <input type="text" name="remarks" list="rem" value="${edit_reg3_general.remarks}" id ="remarks" class="textbox1" style=" background-color: #d6b4eb; width: 180px;" placeholder="Remarks" required> ||
                            Prepared By  : <input type="text" name="prepared_by" list="prep" value="${edit_reg3_general.prepared_by}" id ="prepared_by" class="textbox1" style=" background-color: #d6b4eb; width: 180px" placeholder="Prepared By" required> ||
                           Reviewed By :  <input type="text" name="reviewer" list="rev" value="${edit_reg3_general.reviewed_by}" id ="reviewer" class="textbox1" style=" background-color: #d6b4eb; width: 180px" placeholder="Reviewed By" required> ||
                           Submission Date (MM/DD/YYYY)  : <input type="text" name="submission" value="${edit_reg3_general.submission_date}" class="textbox1" style=" background-color: #d6b4eb; width: 180px" id ="datepicker" placeholder="Date Of Submission" required="true" readonly>
                            
                            </td> 
                        </tr>
                        </c:forEach>
                      <tr><td colspan="17"><input type="submit" name="sub" value="Save" style="background: orangered; width: 170px; height: 40px;"></td></tr>
</table>
      
    <datalist id="rem">

        </datalist>
    
    <datalist id="prep">

        </datalist>
    
    <datalist id="rev">

        </datalist>  
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

