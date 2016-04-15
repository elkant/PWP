<%-- 
    Document   : edit_sessions
    Created on : Dec 3, 2013, 11:41:50 AM
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

<html>
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
<title>Sessions Details.</title> 
  <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
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
    
function nullchecker(){ 
 var dateObject,day,month,year,current_date;
 var selected_date,selected_month,selected_year;
  //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
   var checker=document.getElementById("checkedValue").value;
   current_date=month+"/"+day+"/"+year;  
var i=1;
var all_sess="";
while(i<=13){ 
//alert("called"+checker);
if(checker.indexOf(","+i+",")>=0){
//    alert("checked ; "+i)
  if(document.getElementById("datepicker"+i)!=null || document.getElementById("datepicker"+i).value!=""){   
    var datepicker=document.getElementById("datepicker"+i).value;
//        var messages_given=document.getElementById("messages_given"+i).value;
        var method_used=document.getElementById("method"+i).value;
        var time_taken=document.getElementById("duration"+i).value;
//   alert("here------------"+i+"-------"+datepicker+"-------------"+method_used+"-------------"+time_taken)
//if(datepicker=="" || messages_given=="" || method_used=="" || time_taken==""){
    if((datepicker!="" &&(method_used=="" || time_taken=="0" || time_taken=="")) || ((time_taken!="0" || time_taken=="") &&(method_used=="" || datepicker==""))|| (method_used!="" &&(datepicker=="" || time_taken=="0" || time_taken==""))){
    alertify.alert ("Enter all Details For Session "+i);
        return false;
        break;
}
 if(new Date(datepicker) > new Date(current_date)){
     alertify.alert("This date cannot be greater than current date. Check session date at session "+i);   
    return false;
break;    
    }  
    all_sess+=datepicker;
}
}
   i++;

}
if(all_sess==""){
     alertify.alert ("Enter Data for atleast one session");
        return false;   
 }
//return false;
}
 
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
   
   
 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
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
<div style="margin-left: 320px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Step 3/5. Mark/Edit Sessions <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>
<div id="midcontent" style="margin-left:0px ; width: 1300px; height: auto">
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 17px;">
<p>The messages with blue headings are those whose attendance has been marked and can be edited.</p>
    <%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='19'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>
<p style="font-size: 15px;"><font color="red">NOTE:</font>The fields whose messages appear in blue color are for the message(s) which can be edited or added to the System.</p>
<!--<p id='mess' class='jBox-notice-text'></p>-->


<form action="save_edited_sessions" name="form" onsubmit="return nullchecker();" method="post" >
    <input type="hidden" name="checkedValue" id="checkedValue" value="<%=session.getAttribute("chosen_session_1").toString()%>">
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
<tr>
          
          <th colspan="1"> Session Details</th> 
          <th colspan="13"> Session Information and/or Counseling on the following  (Select Appropriately)<br>  Use The Codes Provided</th> 
     </tr>
<tr><th>Messages: </th>
    <th><%if(session.getAttribute("marked_sessions").toString().contains(",1,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">1. Knowledge Of HIV Status </font><%}else{%><font color="black">1. Knowledge Of HIV Status </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",2,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">2. Partner HIV Testing </font><%}else{%><font color="black">2. Partner HIV Testing </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",3,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">3. Child HIV Testing </font><%}else{%><font color="black">3. Child HIV Testing </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",4,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">4. Discordance </font><%}else{%><font color="black">4. Discordance </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",5,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">5. HIV Disclosure</font><%}else{%><font color="black">5. HIV Disclosure</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",6,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">6. Risk Factors / Reduction.</font><%}else{%><font color="black">6. Risk Factors / Reduction.</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",7,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">7. Condom Use </font><%}else{%><font color="black">7. Condom Use </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",8,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">8. Alcohol And Substance Abuse </font><%}else{%><font color="black">8. Alcohol And Substance Abuse </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",9,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">9. Adherence </font><%}else{%><font color="black">9. Adherence </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",10,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">10. STIs</font><%}else{%><font color="black">10. STIs</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",11,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">11. Family Planning</font><%}else{%><font color="black">11. Family Planning</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",12,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">12. PMTCT</font><%}else{%><font color="black">12. PMTCT </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",13,")){%><font color="blue" class='tooltip' title="<font color='red'>NOTE: </font><br>1.Date of session,method used and time taken are required fields. <br>2. Press and hold control button (CTRL) to select more than one method.">13. TB </font><%}else{%><font color="black">13. TB  </font><%}%></th>
</tr>

<tr>
    
 <td>Date Of Session <font color='red'>*</font>  </td>
     <c:forEach items="${edit_reg1}" var="edit_reg1">
         <input type="hidden" name="sessions${edit_reg1.positioner}" value="${edit_reg1.sessions_id}">
      
     <c:set var="sessionNO" scope="page" value="${edit_reg1.positioner}"/>
        
        <td>
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(","+pageContext.getAttribute("sessionNO").toString()+",")){
        %>     
         <input type="text" value="" ${edit_reg1.disabled} name="session_date${edit_reg1.positioner}" id="datepicker${edit_reg1.positioner}" style="width: 80px" readonly>
         <%} else{%>
         <input type="text" value="${edit_reg1.session_date}" disabled="true" name="session_date${edit_reg1.positioner}" id="datepicker${edit_reg1.positioner}" style="width: 80px" readonly>
         <%}%>
        </td>
     </c:forEach>
     
     </tr>
     <tr>
 <td>Methods Used <font color='red'>*</font> </td>
     <c:forEach items="${edit_reg1}" var="edit_reg1">
     
      <c:set var="sessionNO1" scope="page" value="${edit_reg1.positioner}"/>
    
        <td>
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(","+pageContext.getAttribute("sessionNO1").toString()+",")){
        %>    
         <select name="method${edit_reg1.positioner}" id="method${edit_reg1.positioner}" style="width: 80px" multiple  >
     ${edit_reg1.method_used}
         </select>
        <%} else{%>
        <select name="method${edit_reg1.positioner}" disabled="true" id="method${edit_reg1.positioner}" style="width: 80px" multiple  >
    ${edit_reg1.method_used}
        </select>
            <%}%> 
         
     </td>
     </c:forEach>
     </tr>
     <tr>
 <td>Time Taken(Mins) <font color='red'>*</font></td>
     <c:forEach items="${edit_reg1}" var="edit_reg1">
      <c:set var="sessionNO2" scope="page" value="${edit_reg1.positioner}"/>
        
        <td>
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(","+pageContext.getAttribute("sessionNO2").toString()+",")){
        %>
         
        <input type="text" value="${edit_reg1.duration}" ${edit_reg1.disabled} maxlength="3" onkeypress="return numbers(event)" name="duration${edit_reg1.positioner}" id="duration${edit_reg1.positioner}" style="width: 80px"> 
        <%} else{%>
      <input type="text" value="${edit_reg1.duration}" disabled="true" maxlength="3" onkeypress="return numbers(event)" name="duration${edit_reg1.positioner}" id="duration${edit_reg1.positioner}" style="width: 80px"> 
          
        <%}%>
        </td>
     </c:forEach>
     </tr>
     <tr>



 <td>No. Of Male Condoms Distributed</td>
     <c:forEach items="${edit_reg1}" var="edit_reg1">
         <c:set var="sessionNO3" scope="page" value="${edit_reg1.positioner}"/>
        
        <td>
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(","+pageContext.getAttribute("sessionNO3").toString()+",")){
        %>

             <input type="text" value="${edit_reg1.male_cds}" ${edit_reg1.disabled} maxlength="5" onkeypress="return numbers(event)" name="male_cds${edit_reg1.positioner}" id="male_cds${edit_reg1.male_cds}" style="width: 80px">
       <%} else{%>
             <input type="text" value="${edit_reg1.male_cds}" disabled="true" maxlength="5" onkeypress="return numbers(event)" name="male_cds${edit_reg1.positioner}" id="male_cds${edit_reg1.male_cds}" style="width: 80px">
             <%}%>
        </td>
         </c:forEach>
     </tr>
     <tr>
 <td>No. Of Female Condoms Distributed </td>
     <c:forEach items="${edit_reg1}" var="edit_reg1">
        
          <c:set var="sessionNO4" scope="page" value="${edit_reg1.positioner}"/>
        
        <td>
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(","+pageContext.getAttribute("sessionNO4").toString()+",")){
        %>    
             <input type="text" value="${edit_reg1.female_cds}" ${edit_reg1.disabled} maxlength="5" onkeypress="return numbers(event)" name="female_cds${edit_reg1.positioner}" id="female_cds${edit_reg1.female_cds}" style="width: 80px">
      <%} else{%>
              <input type="text" value="${edit_reg1.female_cds}" disabled="true" maxlength="5" onkeypress="return numbers(event)" name="female_cds${edit_reg1.positioner}" id="female_cds${edit_reg1.female_cds}" style="width: 80px">
            
             <%}%>
        </td>
     </c:forEach>
     </tr>
     
     <tr>
 <td>No. Of IEC Materials Distributed </td>
     <c:forEach items="${edit_reg1}" var="edit_reg1">
       
       <c:set var="sessionNO5" scope="page" value="${edit_reg1.positioner}"/>
     
        <td>
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(","+pageContext.getAttribute("sessionNO5").toString()+",")){
        %>       
             <input type="text" value="${edit_reg1.iec_materials}" ${edit_reg1.disabled} maxlength="5" onkeypress="return numbers(event)" name="iec_no${edit_reg1.positioner}" id="iec_no${edit_reg1.iec_materials}" style="width: 80px">
       <%} else{%>
              <input type="text" value="${edit_reg1.iec_materials}" disabled="true" maxlength="5" onkeypress="return numbers(event)" name="iec_no${edit_reg1.positioner}" id="iec_no${edit_reg1.iec_materials}" style="width: 80px">
           
             <%}%>
        </td>
     </c:forEach>
</tr>

<tr><td colspan="15"><input type="submit" name="sub" value="Save And Continue" style="background: orangered; width: 170px; height: 40px;"></td></tr>
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

