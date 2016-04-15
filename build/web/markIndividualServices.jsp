<%-- 
    Document   : markIndividualServices
    Created on : Feb 3, 2015, 12:03:06 PM
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
  $(document).ready(function() {
                 
                      $.ajax({
        url:"loadMinDate",
        type:"post",
        dataType:"html",
        success:function(data){
        var minDate=data;
    
$( "#datepicker" ).datepicker({minDate: minDate,maxDate: new Date()});
        }
    });

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
 function nullchecker(){
 var dateObject,day,month,year,current_date;
 var selected_date,selected_month,selected_year;
  //created the date object

  var total=0,value=0;
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    var tot=<%out.println(session.getAttribute("clients"));%>
    if(tot==null){
          var tot=<%out.println(session.getAttribute("total_clients"));%>
    }
    for (var j=1;j<=tot;j++){
        if(document.getElementById("cds"+j)!=null){
        value=parseFloat(document.getElementById("cds"+j).value);
       if(isNaN(value)){value=0}
       total+=value;
//        alert("here"+value)
     }}
    var prev_total=parseFloat(<%out.println(session.getAttribute("total_cds"));%>) 
//     alert("total entered    :   "+total+" and the total cds earlier is  :  "+prev_total)
//     if(prev_total!=total){
//     alertify.alert("The Number of Condoms Distributed should add up to the ones given during sessions.");
////           alertify.alert("The Number of Condoms Distributed should add up to the ones given during sessions."+prev_total+"  tot  : "+total);
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
<title>Enter/Tick services provided.</title> 
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
<div style="margin-left:0px;width: 100%; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 4/4. Tick/Enter Services Provided.
<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></div>

<div id="content" style="height: auto">
        <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>

<div id="midcontent" style="margin-left:0px ; width: 1100px; height: auto"> 

<div id="dialog" title="Tick/Enter services Provided Help." style=" font-size: 18px;">
<p><font color="red">NOTE:</font> Only those individual who attended a given message but were not given services will be loaded in this section.</p>
<p>Messages with an aesterick (*) before them are disabled because there services have already been saved to the system.</p>
<p></p>
<p>Users are supposed to tick or enter services that were provided only for the message(s) that are selected per client.</p>
<p></p>
<p></p>
</div>
<form action="saveIndividuaServices" name="form" style="margin-left: 80px;" onsubmit="return nullchecker();" method="post" >
    <br>
    
    <div style=" font-size: 21px; height: 50px;">
    <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 100%;">
        <tr style="height: 40px;">    
      <th> District Name :  <%=session.getAttribute("district_name").toString()%>.</th>
      <th> Partner Name :  <%=session.getAttribute("partner_name").toString()%></th>
      <th> Group Name : INDIVIDUAL</th>
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
       <th>Message Given<font color="red">*</font></th>
       <th>Received Contraceptives</th>
       <th>Referred to Service Point</th>
       <th>No Of Condoms Given</th>
       <th>Screened<br> For TB</th>
       <th>Screened<br> For STIs</th>
       <th>Partner<br> Tested</th>
       <th>Children<br> Tested</th>
       <th>Disclosed<br> Status</th>
       </tr>
       <c:forEach items="${clientale}" var="client_dets1">
       <tr>

          <input type="hidden" name="client_id${client_dets1.positioner}" value="${client_dets1.client_id}" />
          
           <td style=" width: 30px;">${client_dets1.positioner}</td>
            <td>${client_dets1.client_name} </td>
             <td style=" width: 20px;">${client_dets1.age} </td>
              <td style=" width: 40px;">${client_dets1.sex}     </td>
              <td style=" width: 40px;"><select name="message_${client_dets1.positioner}" required="true" multiple="true" id="message_${client_dets1.positioner}" class="textbox2" style="border-color: green; width: 100px; height: 80px;">${client_dets1.message}</select></td>
               <td style=" width: 40px;"><input type="checkbox" ${client_dets1.disabled} name="contraceptive${client_dets1.positioner}" id="contraceptive${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${client_dets1.disabled} name="service_point${client_dets1.positioner}" id="service_point${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="text" class="textbox1" ${client_dets1.disabled} style=" background-color: #d6b4eb" maxlength="6" onkeypress="return numbers(event)" name="cds${client_dets1.positioner}" id="cds${client_dets1.positioner}" value="" placeholder="No. of CDs" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${client_dets1.disabled} name="tb${client_dets1.positioner}" id="tb${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${client_dets1.disabled} name="sti${client_dets1.positioner}" id="sti${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${client_dets1.disabled} name="partner_testing${client_dets1.positioner}" id="partner_testing${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${client_dets1.disabled} name="children_testing${client_dets1.positioner}" id="children_testing${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
               <td style=" width: 40px;"><input type="checkbox"  ${client_dets1.disabled} name="disclosed_status${client_dets1.positioner}" id="disclosed_status${client_dets1.positioner}" value="YES" style=" width: 60px;"></td>
             
                        </tr>
                        
                         <tr style="background-color: black; height: 6px;">
       </tr>
       </c:forEach>
                        <tr style=" height: 15px;"></tr>
                        <tr>
                            <td colspan="3">Remarks <font color="red">*</font>:  <input type="text" name="remarks" id ="remarks" list="rem" autocomplete="off" class="textbox1" style=" border-color: green;" placeholder="Remarks" required></td> 
                            <td colspan="3">Prepared By <font color="red">*</font> : <input type="text" name="prepared_by" id ="prepared_by"  list="prep" autocomplete="off" class="textbox1" style="  border-color: green;" placeholder="Prepared By" required></td> 
                          <td colspan="3"> Reviewed By <font color="red">*</font> :  <input type="text" name="reviewer" id ="reviewer" list="rev" class="textbox1" autocomplete="off" style=" border-color: green;" placeholder="Reviewed By" required> </td> 
                          <td colspan="4"> Date Of Submission(MM/DD/YYYY)<font color="red">*</font>  : <input type="text" name="submission" class="textbox1" style=" border-color: green;" id ="datepicker" placeholder="Date Of Submission" required="true" readonly>
                            
                            </td> 
                        </tr>
                        
                        <tr><td colspan="17"><input type="submit" name="sub" value="Save"  class="textbox1" style="background: #cc99ff; color: #0000ff"></td></tr>

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

