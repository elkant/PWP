<%-- 
    Document   : edit_attendance
    Created on : Dec 3, 2013, 3:24:05 PM
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
    
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery_min.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

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
                <script type="text/javascript" >
            function change_image(src){
             var source=src.id;
             var data=src.value;
//             alert(source+"-------"+data);
if(data=="2"){
document.getElementById("2-"+source).src="images/absent.JPG"; 
}
else{
document.getElementById("2-"+source).src="images/present.JPG"; 
}
            }
     
              <%if(session.getAttribute("chosen_session_1").toString().contains(",9,")){%>
            <%if(session.getAttribute("second")!=null){%>
        $(document).ready(function() {
$( "#dialog-confirm" ).dialog({
resizable: false,
width:500,
height:290,
modal: true,
buttons: {
"YES": function() {
// $("#subsequent").prop('checked', true);  
$( this ).dialog( "close" );
},
NO: function() {
//$("#subsequent").prop('checked', true); 
$( this ).dialog( "close" );
}
}
});
});
<%}}%>       
       
            </script>
             	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
            <script type="text/javascript">
                $(document).ready(function(){
                 $('#checkall').change(function() {
             var all = <%out.println(session.getAttribute("total_client").toString());%>
             var statusx= $('#checkall').is(':checked');
//             alert(statusx+" "+all);
var i=1;
if(statusx==true){
while(i<=all){
   $("#client_"+i).prop('checked', true);
    i++;
}
}
i=1;
if(statusx==false){
while(i<=all){
   $("#client_"+i).prop('checked', false);
    i++;
}
}
 });     
                    
                });
            function nullchecker(){
            var returned="";
         var all=<%out.println(session.getAttribute("total_client").toString());%>
           var i=1;
           while(i<=all){
  var statusx=$("#client_"+i).is(':checked');
  if(statusx==true){
      alert("true");
      returned=true;
      break;
  }
  else{
      returned=false;
  }
        i++;
}

if(returned==false){
    alertify.alert("Please select clients whom you want to mark/edit their attendance.");
    } 
      return returned; 
     
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
<title>Edit Attendance.</title> 

</head>
<body onload="">
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
<div style="margin-left: 370px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 4/5. Mark/Edit Attendance <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>
<div id="dialog-confirm" hidden="true" title="Confirm Marking or editing for adherence">
    <p><font color="red"><b>NOTE :</b> </font><font color="black">Adherence message has been marked.</font><br>
    <br>1. Click <b>YES</b> if you want to mark adherence for the second or subsequent times. 
    <br>2.Click <b>NO</b> if want to edit the already marked data for adherence.</p>
</div>
<div id="midcontent" style="margin-left:0px ; width: 1300px; height: auto">
  
    
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
  
                        
<div id="dialog" title="Mark/Edit Attendance Help." style=" font-size: 17px;">
<p>1. The messages with blue headings are those messages whose attendance has been marked.</p>
<p>2.The messages whose markings are proceeded with a yellow (warning) mark, are the messages whose attendance cannot be marked.</p>
<p>3.<font color="red">NOTE: </font>You only need to mark the attendance for the message(s) that you selected in step 2. </p>
</div>
<p style="font-size: 15px;"><font color="red">NOTE:</font>The fields whose messages appear in <b>blue</b> color are for the message(s) whose attendance can be marked or Edited.</p>

<form action="save_edited_register" name="form" method="post" >
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
          <th colspan="13"> Clients Who received Information and/or Counseling on the following  (Select Appropriately)<br>  Use The Codes Provided</th> 
     </tr>
       <tr> 
       <th style="width: 20px;">Serial No</th>
       <th style="width: 200px;">Name Of Client</th>
       <th style="width: 20px;">Age in Years</th>
       <!--<th style="width: 20px; background-color: #d6b4eb;">Click here to tick all.<input type="checkbox" name="checkall" id="checkall"></th>-->
       <th style="width: 40px;">Sex</th>
       
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",1,")){%><font color="blue">1. Knowledge Of HIV Status </font><%}else{%><font color="black">1. Knowledge Of HIV Status </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",2,")){%><font color="blue">2. Partner HIV Testing </font><%}else{%><font color="black">2. Partner HIV Testing </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",3,")){%><font color="blue">3. Child HIV Testing </font><%}else{%><font color="black">3. Child HIV Testing </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",4,")){%><font color="blue">4. Discordance </font><%}else{%><font color="black">4. Discordance </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",5,")){%><font color="blue">5. HIV Disclosure</font><%}else{%><font color="black">5. HIV Disclosure</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",6,")){%><font color="blue">6. Risk Factors / Reduction.</font><%}else{%><font color="black">6. Risk Factors / Reduction.</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",7,")){%><font color="blue">7. Condom Use </font><%}else{%><font color="black">7. Condom Use </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",8,")){%><font color="blue">8. Alcohol And Substance Abuse </font><%}else{%><font color="black">8. Alcohol And Substance Abuse </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",9,")){%><font color="blue">9. Adherence </font><%}else{%><font color="black">9. Adherence </font><%}%><br><%if(session.getAttribute("chosen_session_1").toString().contains(",9,")){%><%if(session.getAttribute("second")!=null){out.println(session.getAttribute("second").toString());}}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",10,")){%><font color="blue">10. STIs</font><%}else{%><font color="black">10. STIs</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",11,")){%><font color="blue">11. Family Planning</font><%}else{%><font color="black">11. Family Planning</font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",12,")){%><font color="blue">12. PMTCT</font><%}else{%><font color="black">12. PMTCT </font><%}%></th>
       <th><%if(session.getAttribute("marked_sessions").toString().contains(",13,")){%><font color="blue">13. TB </font><%}else{%><font color="black">13. TB  </font><%}%></th>
</tr>
       
       
       
       <c:forEach items="${edit_reg2}" var="edit_reg2">
           <tr style="height: 40px">
    
         <input type="hidden" name="register_id${edit_reg2.positioner}" value="${edit_reg2.reg_id}">
         <input type="hidden" name="client_id${edit_reg2.positioner}" value="${edit_reg2.client_id}">   
      <td style="width: 20px;">${edit_reg2.positioner}</td>
      <td style=" width: 200px">${edit_reg2.full_names}</td>
      <td style="width: 20px;">${edit_reg2.age}</td>
      <!--<td style="width: 20px;"><input type="checkbox" name="client_${edit_reg2.positioner}" id="client_${edit_reg2.positioner}"></td>-->
      <td style="width: 40px;">${edit_reg2.sex}</td>
       
       <td style="width: 60px;">
        
         <%
       if(session.getAttribute("chosen_session_1").toString().contains(",1,")){
        %> 
        <c:if test="${edit_reg2.disabled1== 'NO'}">
           <input type="hidden" name="active_1_${edit_reg2.positioner}" value="">
      <select name="session1-${edit_reg2.positioner}"
           id="session1-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s1}
           </select> 
        </c:if>
        
        <img id="2-session1-${edit_reg2.positioner}" src="${edit_reg2.s1_image}" width="15px" height="15" title="Image1">
       <%}else{%>
       <img id="2-session1-${edit_reg2.positioner}" src="${edit_reg2.s1_image}" width="15px" height="15" title="Image1">
       <%}%>
       </td>
           
           <td style="width: 60px;">
               
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",2,")){
        %>   
        <c:if test="${edit_reg2.disabled2== 'NO'}">
           <input type="hidden" name="active_2_${edit_reg2.positioner}" value="">
      <select name="session2-${edit_reg2.positioner}"
           id="session2-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s2}
           </select> 
        </c:if>
           
           <img id="2-session2-${edit_reg2.positioner}" src="${edit_reg2.s2_image}" width="15px" height="15" title="Image2">
          <%}else{%>
<img id="2-session2-${edit_reg2.positioner}" src="${edit_reg2.s2_image}" width="15px" height="15" title="Image2">           
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                  <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",3,")){
        %> 
       <c:if test="${edit_reg2.disabled3== 'NO'}">
           <input type="hidden" name="active_3_${edit_reg2.positioner}" value="">
      <select name="session3-${edit_reg2.positioner}"
           id="session3-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s3}
           </select> 
        </c:if> <img id="2-session3-${edit_reg2.positioner}" src="${edit_reg2.s3_image}" width="15px" height="15" title="Image3">
            <%}else{%>
 <img id="2-session3-${edit_reg2.positioner}" src="${edit_reg2.s3_image}" width="15px" height="15" title="Image3">          
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                   <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",4,")){
        %>
        <c:if test="${edit_reg2.disabled4== 'NO'}">
           <input type="hidden" name="active_4_${edit_reg2.positioner}" value="">
      <select name="session4-${edit_reg2.positioner}"
           id="session4-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s4}
           </select> 
        </c:if>
           
           <img id="2-session4-${edit_reg2.positioner}" src="${edit_reg2.s4_image}" width="15px" height="15" title="Image4">
            <%}else{%>
  <img id="2-session4-${edit_reg2.positioner}" src="${edit_reg2.s4_image}" width="15px" height="15" title="Image4">         
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                 <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",5,")){
        %>  
        <c:if test="${edit_reg2.disabled5== 'NO'}">
           <input type="hidden" name="active_5_${edit_reg2.positioner}" value="">
      <select name="session5-${edit_reg2.positioner}"
           id="session5-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s5}
           </select> 
        </c:if>
           
           <img id="2-session5-${edit_reg2.positioner}" src="${edit_reg2.s5_image}" width="15px" height="15" title="Image5">
           <%}else{%>
        <img id="2-session5-${edit_reg2.positioner}" src="${edit_reg2.s5_image}" width="15px" height="15" title="Image5">   
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                 <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",6,")){
        %>  
        
        <c:if test="${edit_reg2.disabled6== 'NO'}">
           <input type="hidden" name="active_6_${edit_reg2.positioner}" value="">
      <select name="session6-${edit_reg2.positioner}"
           id="session6-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s6}
           </select> 
        </c:if>
        <img id="2-session6-${edit_reg2.positioner}" src="${edit_reg2.s6_image}" width="15px" height="15" title="Image6">
            <%}else{%>
          <img id="2-session6-${edit_reg2.positioner}" src="${edit_reg2.s6_image}" width="15px" height="15" title="Image6"> 
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                  <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",7,")){
        %> 
        <c:if test="${edit_reg2.disabled7== 'NO'}">
           <input type="hidden" name="active_7_${edit_reg2.positioner}" value="">
      <select name="session7-${edit_reg2.positioner}"
           id="session7-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s7}
           </select> 
        </c:if>
           
           <img id="2-session7-${edit_reg2.positioner}" src="${edit_reg2.s7_image}" width="15px" height="15" title="Image7">
            <%}else{%>
          <img id="2-session7-${edit_reg2.positioner}" src="${edit_reg2.s7_image}" width="15px" height="15" title="Image7"> 
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                  <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",8,")){
        %> 
        
        <c:if test="${edit_reg2.disabled8== 'NO'}">
           <input type="hidden" name="active_8_${edit_reg2.positioner}" value="">
      <select name="session8-${edit_reg2.positioner}"
           id="session8-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s8}
           </select> 
        </c:if>
           
        <img id="2-session8-${edit_reg2.positioner}" src="${edit_reg2.s8_image}" width="15px" height="15" title="Image8">
            <%}else{%>
         <img id="2-session8-${edit_reg2.positioner}" src="${edit_reg2.s8_image}" width="15px" height="15" title="Image8">  
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                   <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",9,")){
        %>
        <%--<c:if test="${edit_reg2.disabled9== 'NO'}">--%>
           <input type="hidden" name="active_9_${edit_reg2.positioner}" value="">
      <select name="session9-${edit_reg2.positioner}"
           id="session9-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s9}
           </select> 
        <%--</c:if>--%>
           
           <img id="2-session9-${edit_reg2.positioner}" src="${edit_reg2.s9_image}" width="15px" height="15" title="Image9">
            <%}else{%>
       <img id="2-session9-${edit_reg2.positioner}" src="${edit_reg2.s9_image}" width="15px" height="15" title="Image9">    
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                   <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",10,")){
        %>
        <c:if test="${edit_reg2.disabled10== 'NO'}">
           <input type="hidden" name="active_10_${edit_reg2.positioner}" value="">
      <select name="session10-${edit_reg2.positioner}"
           id="session10-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s10}
           </select> 
        </c:if>
           
           <img id="2-session10-${edit_reg2.positioner}" src="${edit_reg2.s10_image}" width="15px" height="15" title="Image10">
            <%}else{%>
           <img id="2-session10-${edit_reg2.positioner}" src="${edit_reg2.s10_image}" width="15px" height="15" title="Image10">
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",11,")){
        %>  
       <c:if test="${edit_reg2.disabled11== 'NO'}">
           <input type="hidden" name="active_11_${edit_reg2.positioner}" value="">
      <select name="session11-${edit_reg2.positioner}"
           id="session11-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s11}
           </select> 
        </c:if>
           <img id="2-session11-${edit_reg2.positioner}" src="${edit_reg2.s11_image}" width="15px" height="15" title="Image11">
          <%}else{%>
          <img id="2-session11-${edit_reg2.positioner}" src="${edit_reg2.s11_image}" width="15px" height="15" title="Image11"> 
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                  <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",12,")){
        %> 
       <c:if test="${edit_reg2.disabled12== 'NO'}">
           <input type="hidden" name="active_12_${edit_reg2.positioner}" value="">
      <select name="session12-${edit_reg2.positioner}"
           id="session12-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s12}
           </select> 
        </c:if>
           
           <img id="2-session12-${edit_reg2.positioner}" src="${edit_reg2.s12_image}" width="15px" height="15" title="Image12">
           <%}else{%>
           <img id="2-session12-${edit_reg2.positioner}" src="${edit_reg2.s12_image}" width="15px" height="15" title="Image12">
           <%}%> 
           </td>
           
           <td style="width: 60px;">
                 <c:set var="sessionNO" scope="page" value="${edit_reg2.positioner}"/>
               <%
       if(session.getAttribute("chosen_session_1").toString().contains(",13,")){
        %>  
      <c:if test="${edit_reg2.disabled13== 'NO'}">
           <input type="hidden" name="active_13_${edit_reg2.positioner}" value="">
      <select name="session13-${edit_reg2.positioner}"
           id="session13-${edit_reg2.positioner}" style=" width: 45px; font-size:11px" onchange="return change_image(this);">
           ${edit_reg2.s13}
           </select> 
        </c:if>
           
           <img id="2-session13-${edit_reg2.positioner}" src="${edit_reg2.s13_image}" width="15px" height="15" title="Image13">
           <%}else{%>
           <img id="2-session13-${edit_reg2.positioner}" src="${edit_reg2.s13_image}" width="15px" height="15" title="Image13">
           <%}%> 
           </td>
             
               
           
         
         
    
     </tr>
    
</c:forEach>
                      <tr><td colspan="17"><input type="submit" name="sub" value="Save And Continue" style="background: orangered; width: 170px; height: 40px;"></td></tr>
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

