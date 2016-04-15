<%-- 
    Document   : markIndividualRegister
    Created on : Feb 1, 2015, 4:35:26 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server 
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


   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="prefix-free.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

 <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
	<!--<script src="js/jquery-1.9.1.js"></script>-->
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
             var splitter=source.split("_");
             var destinationID="img_"+splitter[1]+"_"+splitter[2];
             var data=src.value;
//             alert(source);
//             alert(data);
if(data=="2"){
document.getElementById(destinationID).src="images/x1.png"; 
}
else if(data=="1"){
document.getElementById(destinationID).src="images/tck.png"; 
}
else{
document.getElementById(destinationID).src="images/editor.jpg";    
}
            }
            </script>
            <script type="text/javascript"> 
                $(document).ready(function(){
                    
                      $.ajax({
        url:"loadMinDate",
        type:"post",
        dataType:"html",
        success:function(data){
        var minDate=data;
    
        var all_clients=$("#pos").val();
                var i=1;
               while(i<=all_clients){
//                alert("here")   
               var j=1;
               while(j<=13){
//                   $( "#datepicker1" ).datepicker();
                 $("#session_date_"+i+"_"+j).datepicker({minDate: minDate,maxDate: new Date()});  
//                 alert("session date : "+$("#session_date_"+i+"_"+j).val());  
                   j++;
               }
                 i++;  
               }
               
               } 
    });
        
        });
           
                </script>
                 <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
          <script>
function nullchecker(){
 var all=$("#pos").val();
 var rtn=true;
// alert(all);
 var i=1;
 while(i<=all){
  var dater,sessi;
  var j=1;
  while(j<=13){
  dater = $("#session_date_"+i+"_"+j).val();
  sessi = $("#s_"+i+"_"+j).val();
  
//  alert("dater : "+dater+"    session : "+sessi+" at person : "+i+" and message : "+j);
  if(dater=="" && sessi!=""){
    alertify.alert("Enter date at client no. "+i+" and message no. "+j) ;
   rtn=false;
   break;
  }
  else if(dater!="" && sessi==""){
    alertify.alert("Select attendance for client no. "+i+" and message no. "+j);
    rtn=false;
   break;
  }
  else{
      
  }
  
    j++;
       }
     i++;
 }
 return rtn;
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
<div style="margin-left: 270px;width: 850px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 3/4. Mark/Edit Attendance Register - Tick Sessions Attended.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></div>
<br><br>
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
<p>Only those individuals that were pre-selected in step 2/4 will be loaded here for marking.</p>
<p>As shown here, these are the possible states of attendance <img src="images/attendance_guide.JPG" style="width:250px; height:170px;" alt="guide"> </p>
<p>3. For each session, enter the date when the message was given out.</p>
<p></p>
<p></p>

</div>
<form action="saveIndividualRegister" name="form" method="post" onsubmit="return nullchecker();">
    <br>
    <div style=" font-size: 21px; height: 50px;">
    <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style2"  style="background: #ccccff; width: 1300px">
        <tr style="height: 40px;">    
    <th> District Name :  <%=session.getAttribute("district_name").toString()%></th>
      <th> Partner Name :  <%=session.getAttribute("partner_name").toString()%></th>
      <th> Group Name : INDIVIDUAL</th>
        </tr>
    </table>  
</div>
   <!--<input type="text" name="date" id="date">-->
   
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
      
      
       
       
       <input type="hidden" name="pos" id="pos" value="<%=session.getAttribute("total_clients")%>">
       <c:forEach items="${individual}" var="client_dets">
      <input class="textbox" style="border-color: green;width:50px; height: 13px;"  type="hidden" name="reg_id_${client_dets.positioner}_1" id="reg_id_${client_dets.positioner}_1" value="reg_id${client_dets.regid1}" />
      <input class="textbox" style="border-color: green;width:50px; height: 13px;"  type="hidden" name="reg_id_${client_dets.positioner}_2" id="reg_id_${client_dets.positioner}_2" value="reg_id${client_dets.regid2}" />
      <input class="textbox" style="border-color: green;width:50px; height: 13px;"  type="hidden" name="reg_id_${client_dets.positioner}_3" id="reg_id_${client_dets.positioner}_3" value="reg_id${client_dets.regid3}" />
      <input class="textbox" style="border-color: green;width:50px; height: 13px;"  type="hidden" name="reg_id_${client_dets.positioner}_4" id="reg_id_${client_dets.positioner}_4" value="reg_id${client_dets.regid4}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_5" id="reg_id_${client_dets.positioner}_5" value="reg_id${client_dets.regid5}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_6" id="reg_id_${client_dets.positioner}_6" value="reg_id${client_dets.regid6}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_7" id="reg_id_${client_dets.positioner}_7" value="reg_id${client_dets.regid7}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_8" id="reg_id_${client_dets.positioner}_8" value="reg_id${client_dets.regid8}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_9" id="reg_id_${client_dets.positioner}_9" value="reg_id${client_dets.regid9}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_10" id="reg_id_${client_dets.positioner}_10" value="reg_id${client_dets.regid10}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_11" id="reg_id_${client_dets.positioner}_11" value="reg_id${client_dets.regid11}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_12" id="reg_id_${client_dets.positioner}_12" value="reg_id${client_dets.regid12}" />
      <input class="textbox" style="border-color: green;width:50px; height: 14px;"  type="hidden" name="reg_id_${client_dets.positioner}_13" id="reg_id_${client_dets.positioner}_!3" value="reg_id${client_dets.regid13}" />
      
           
           
           <tr> 
            <td style=" width: 30px;" rowspan="2">${client_dets.positioner}</td>
            <td  rowspan="2">${client_dets.client_name} </td>
             <td style=" width: 20px;"  rowspan="2">${client_dets.age} </td>
              <td style=" width: 40px;"  rowspan="2">${client_dets.sex}     </td>
              <td><input class="textbox" style="border-color: green;width:50px; height: 13px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_1" ${client_dets.disSele1}  id="session_date_${client_dets.positioner}_1" readonly="true" value="${client_dets.ds1}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 13px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_2" ${client_dets.disSele2} id="session_date_${client_dets.positioner}_2" readonly="true" value="${client_dets.ds2}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 13px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_3" ${client_dets.disSele3} id="session_date_${client_dets.positioner}_3" readonly="true" value="${client_dets.ds3}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 13px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_4" ${client_dets.disSele4} id="session_date_${client_dets.positioner}_4" readonly="true" value="${client_dets.ds4}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_5" ${client_dets.disSele5} id="session_date_${client_dets.positioner}_5" readonly="true" value="${client_dets.ds5}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_6" ${client_dets.disSele6} id="session_date_${client_dets.positioner}_6" readonly="true" value="${client_dets.ds6}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_7" ${client_dets.disSele7} id="session_date_${client_dets.positioner}_7" readonly="true" value="${client_dets.ds7}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_8" ${client_dets.disSele8} id="session_date_${client_dets.positioner}_8" readonly="true" value="${client_dets.ds8}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_9" ${client_dets.disSele9} id="session_date_${client_dets.positioner}_9" readonly="true" value="${client_dets.ds9}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_10" ${client_dets.disSele10} id="session_date_${client_dets.positioner}_10" readonly="true" value="${client_dets.ds10}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_11" ${client_dets.disSele11} id="session_date_${client_dets.positioner}_11" readonly="true" value="${client_dets.ds11}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_12" ${client_dets.disSele12} id="session_date_${client_dets.positioner}_12" readonly="true" value="${client_dets.ds12}" /></td>
      <td><input class="textbox" style="border-color: green;width:50px; height: 14px;font-size: 12px;" placeholder="date of session" type="text" name="session_date_${client_dets.positioner}_13" ${client_dets.disSele13} id="session_date_${client_dets.positioner}_13" readonly="true" value="${client_dets.ds13}" /></td>
       </tr>
       <tr>
        <input type="hidden" name="client_id${client_dets.positioner}" value="${client_dets.client_id}" />
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_1" ${client_dets.disSele1} id ="s_${client_dets.positioner}_1" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
              ${client_dets.s1} 
    </select><img id="img_${client_dets.positioner}_1" src="${client_dets.img1}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_2" ${client_dets.disSele2}  id ="s_${client_dets.positioner}_2" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
               ${client_dets.s2} 
    </select><img id="img_${client_dets.positioner}_2" src="${client_dets.img2}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_3" ${client_dets.disSele3}  id ="s_${client_dets.positioner}_3" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
             ${client_dets.s3} 
    </select><img id="img_${client_dets.positioner}_3" src="${client_dets.img3}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_4" ${client_dets.disSele4}  id ="s_${client_dets.positioner}_4" style=" width: 47px; font-size: 10px;"onchange="return change_image(this);">    
              ${client_dets.s4} 
    </select><img id="img_${client_dets.positioner}_4" src="${client_dets.img4}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_5" ${client_dets.disSele5} id ="s_${client_dets.positioner}_5" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
               ${client_dets.s5} 
    </select><img id="img_${client_dets.positioner}_5" src="${client_dets.img5}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_6" ${client_dets.disSele6} id ="s_${client_dets.positioner}_6" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
               ${client_dets.s6} 
    </select><img id="img_${client_dets.positioner}_6" src="${client_dets.img6}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_7" ${client_dets.disSele7} id ="s_${client_dets.positioner}_7" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
               ${client_dets.s7} 
    </select><img id="img_${client_dets.positioner}_7" src="${client_dets.img7}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_8" ${client_dets.disSele8} id ="s_${client_dets.positioner}_8" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
              ${client_dets.s8} 
    </select><img id="img_${client_dets.positioner}_8" src="${client_dets.img8}" width="13px" height="12px">
               </td>
               
               <td style="width: 250px;"> <select name="s_${client_dets.positioner}_9" ${client_dets.disSele9} id ="s_${client_dets.positioner}_9" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
              ${client_dets.s9} 
    </select><img id="img_${client_dets.positioner}_9" src="${client_dets.img9}" width="13px" height="12px">
           
    ${client_dets.s9special} 
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_10" ${client_dets.disSele10} id ="s_${client_dets.positioner}_10" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
              ${client_dets.s10} 
    </select><img id="img_${client_dets.positioner}_10" src="${client_dets.img10}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_11" ${client_dets.disSele11} id ="s_${client_dets.positioner}_11" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
             ${client_dets.s11} 
    </select><img id="img_${client_dets.positioner}_11" src="${client_dets.img11}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_12" ${client_dets.disSele12} id ="s_${client_dets.positioner}_12" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
             ${client_dets.s12} 
    </select><img id="img_${client_dets.positioner}_12" src="${client_dets.img12}" width="13px" height="12px">
               </td>
               
               <td style=" width: 50px;"> <select name="s_${client_dets.positioner}_13" ${client_dets.disSele13} id ="s_${client_dets.positioner}_13" style=" width: 47px;font-size: 10px; "onchange="return change_image(this);">    
              ${client_dets.s13} 
    </select><img id="img_${client_dets.positioner}_13" src="${client_dets.img13}" width="13px" height="12px">
               </td>
               </tr>
    
</tr>
   <tr style="background-color: black; height: 10px;">
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

