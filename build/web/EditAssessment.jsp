<%-- 
    Document   : EditAssessment
    Created on : Feb 26, 2015, 3:23:34 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>

<link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Edit Assessment form</title>
         <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
     <!--<script type ="text/javascript" src="js/datepicker/min.js"></script>--> 
	<!--<script type="text/javascript" src="js/jquery.js"></script>-->
    <script type="text/javascript" src="js/jquery-ui.js"></script>
 <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <script src="menu/prefix-free.js"></script>  
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<!--WIZARD-->

<link href="styles_wiz/demo_style.css" rel="stylesheet" type="text/css">

<link href="styles_wiz/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="js_wiz/jquery-2.0.0.min.js"></script>-->
<script type="text/javascript" src="js_wiz/jquery.smartWizard.js"></script>

<script type="text/javascript" src="js_wiz/validateAssessment.js"></script>
<script type="text/javascript">
   
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript"> 

            
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
 <!--calendar-->
 
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->

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
                        width:700,
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
  $(function() {
      var currentYear = (new Date).getFullYear();
      var prev_year=parseInt(currentYear)-5;
$( "#date_of_assessment" ).datepicker({changeMonth: true, changeYear: true, yearRange: ''+prev_year+':'+currentYear+'', dateFormat: 'yy-mm-dd', maxDate: new Date()});
//$( document ).tooltip();
});
    
    
</script>
    <style>
legend { 
    display: block;
    padding-left: 2px;
    padding-right: 2px;
    border: none;
}
</style>

<script type="text/javascript">
    $(document).ready(function(){
          
    var referral_point,other_referrals;
//        $("#other_referrals").change(function(){
          other_referrals=$("#other_referrals").val();
          if(other_referrals=="1"){
          $("#sp").show();
//          $("#referral_point").props("required",true);
          }
          else{
           $("#sp").hide();
          $("#referral_point").val("");   
          }
//        });

        $("#other_referrals").change(function(){
          other_referrals=$("#other_referrals").val();
          if(other_referrals=="1"){
          $("#sp").show();
//          $("#referral_point").props("required",true);
          }
          else{
           $("#sp").hide();
          $("#referral_point").val("");   
          }
        });
        
    });
    </script>
       
  <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('.textbox').jBox('Tooltip', {
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
    <body onload="">
       
     <div id="container" style="height:auto;">
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
  
  
<div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">Edit PWP Client Assessment Form.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>    
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>  
                    
                </div> <%}%>
                <div id="midcontent" style="margin-left:50px ; height:auto; width: 950px;" >
                 <%if (session.getAttribute("EditedAssessment") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("EditedAssessment")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("EditedAssessment");
                            }
%>
<p id="showMessage" style="background-color: black"></p>
<!--<br>-->
<div id="dialog" title="Client Assessment form." style=" font-size: 17px;">
    <p>Make or the necessary changes as required.</p>
    <p>Click on save to save the changes made on the assessment form.</p>
    <p><font color="red">NOTE : </font> Users will be unable to navigate 
        from one section to another if they have not completed filling the current section correctly.</p>
</div>
                    <form action="saveEditedAssessment" method="post" style="width: 1050px;">
                    		
   <c:forEach items="${Assess}" var="Assess">	
                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                          
<tr><td> 
<!-- Tabs -->
<div id="wizard" class="swMain" style="height: 500px;">
   
        <ul>
                            <li style="font-size: 25px;font-family: sans-serif; color: black; margin-left: 10px;">SECTION.</li>
                           
                              <li style="height: auto;"><a href="#step-1">
                <label class="stepNumber" style="font-size: 30px; color: black"></label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small ><b style="font-size: 10px;">PERSONAL DETAILS.</b></small><br>
<b style='font-size: 10px; color: black;'>${Assess.client_name}</b>
                </span>
            </a></li>
                            
                            <li style="height: auto;"><a href="#step-2">
                <label class="stepNumber" style="font-size: 30px; color: black">A<br>B</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small ><b style="font-size: 10px;">DATE OF ASSESSMENT <br> PREVENTION MESSAGES.</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-3">
                <label class="stepNumber" style="font-size: 30px; color: black">C<br>D</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small ><b style="font-size: 10px;">PREVENTION COUNSELING <br> HIV TESTING.</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-4">
                <label class="stepNumber" style="font-size: 30px; color: black">E<br>F</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small ><b style="font-size: 10px;">STIs <br> FAMILY PLANNING.</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-5">
                 <label class="stepNumber" style="font-size: 30px; color: black">G,H<br>I</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small ><b style="font-size: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TB,PMTCT  <br> OTHER REFERRALS.</b></small>
                </span>
            </a></li>
            </ul>
  
        
     <div id="step-1" style="height:430px;">
             <h2 class="StepTitle" style="width:98%;height:50px;"> <font color="blue" style="margin-left:30%; ">Personal Details</font><i style="font-size:12px; color: green;">(Confirm details here)</i></font> .</h2>
            <br><br>
             <table style="width: 100%; font-size: 18px;">
             	<input type='hidden' name='client_id' id='client_id' value='${Assess.client_id}'>
              <tr><td></td><td></td></tr>
                  <tr><td>Client's Full name :</td>
                   <td>${Assess.client_name}</td>
               </tr>  
               <tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr>
               <tr><td>Client's Age :</td>
                   <td>${Assess.dob}</td>
               </tr> 
               <tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr>
               <tr><td>Client's Sex :</td>
                   <td>${Assess.sex}</td>
               </tr> 
               <tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr>
               <tr><td>Name of support group :</td>
                   <td>${Assess.group_name}</td>
               </tr> 
               <tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr>
               <tr><td>Name of PWP service provider :</td>
                   <td>${Assess.provider_name}</td>
               </tr> 
               <tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr>
               <tr><td>District :</td>
                   <td>${Assess.district_name}</td>
               </tr> 
               <tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr><tr><td></td><td></td></tr>
               <tr><td>Location :</td>
                   <td>${Assess.location}</td>
               </tr> 
                 
              
             </table>
         </div>    
        
        
        
    <div id="step-2" style="height:430px;">
                            <h2 class="StepTitle" style="width:98%;">A-B.  <font color="blue">Date of Assessment and Prevention Messages</font><i style="font-size:12px; color: green;">(PWP service provider)</i></font> .<br><i style="font-size: 10px; margin-left: 40%;">(Fields marked <font color="red">*</font> are required.)</i></h2>
                            <br>
                            <!--<br> <br><br>-->
                            <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">A. Date of Assessment:</b></legend>
                            <table style="width: 100%">
                            <tr>
                            <input type="hidden" name="id" id="id" value="${Assess.id}"> 
                            <input type="hidden" name="prevention_counseling_id" id="prevention_counseling_id" value="${Assess.prevention_counseling_id}"> 
                            <input type="hidden" name="hiv_testing_stis_id" id="hiv_testing_stis_id" value="${Assess.hiv_testing_stis_id}"> 
                            <input type="hidden" name="family_planning_tb_pmtct_id" id="family_planning_tb_pmtct_id" value="${Assess.family_planning_tb_pmtct_id}"> 
                            
            <td>Date of Assessment <font color="red">*</font> : </td>
            <td><input type="text" name="date_of_assessment" title="Please clicck here to change date of assessment." readonly="true" autocomplete="off" id="date_of_assessment" value="${Assess.date_of_assessment}" autocomplete="off" required class="textbox" style="border-color: green; width:120px;"></td>
            </tr>     
                                
                            </table></fieldset><br>
                            
                             <fieldset id="new_group"><legend><b style="margin:0 auto;  background-color: #acdd4a;">B. PREVENTION MESSAGES(PWP Service Provider):</b></legend>
                                 <i style="font-size:10px;color:black; margin-left: 20%;">Assess and provide recommendations (as indicated by assessment) on  :</i><br><br>
                                 <table style="width: 100%">
           <tr>
            <td>1. Knowledge of HIV status? <font color="red">*</font> : </td>
            <td><select name="knowledge_of_hiv" id="knowledge_of_hiv" required class="textbox2" style="border-color: green; width:140px;">
                ${Assess.knowledge_of_hiv}
                </select>
           </td>
           
           </tr>
            <tr>
            <td>2. Partner HIV testing? <font color="red">*</font> : </td>
             <td><select name="partner_hiv_testing" id="partner_hiv_testing" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.partner_hiv_testing} 
                </select>
           </td>
            
            </tr>
            </table>
            <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">3. child HIV testing. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>3 a). Any children tested ? <font color="red">*</font> : </td>
             <td><select name="any_child_tested" id="any_child_tested" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.any_child_tested} 
                </select>
           </td></tr>
           
           <tr>
            <td>3 b). Any children not tested ? <font color="red">*</font> : </td>
             <td><select name="any_child_not_tested" id="any_child_not_tested" required class="textbox2" style="border-color: green; width:140px;">
                 ${Assess.any_child_not_tested} 
                </select>
           </td></tr>
           
            </table>
            </fieldset>
                 <table style="width: 100%">
            <tr>
            <td>4. Discordance? <font color="red">*</font> : </td>
             <td><select name="discordance" id="discordance" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.discordance}
                </select>
           </td></tr>
            <tr>
            <td>5. HIV Disclosure? <font color="red">*</font> : </td>
             <td><select name="hiv_disclosure" id="hiv_disclosure" required class="textbox2" style="border-color: green; width:140px;">
                    ${Assess.hiv_disclosure}
                </select>
           </td></tr>
                 </table>
           <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">6. Risk factors/reduction. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>6 a). Abstinence ? <font color="red">*</font> : </td>
             <td><select name="abstinence" id="abstinence" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.abstinence}
                </select>
           </td></tr>
           
           <tr>
            <td>6 b). Faithful to one partner ? <font color="red">*</font> : </td>
             <td><select name="faithful_to_one_partner" id="faithful_to_one_partner" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.faithful_to_one_partner} 
                </select>
           </td></tr>
           
            <tr>
            <td>6 c). Safer sex methods ? <font color="red">*</font> : </td>
             <td><select name="safer_sex_methods" id="safer_sex_methods" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.safer_sex_methods} 
                </select>
           </td></tr>
           
           <tr>
            <td>6 d). Multiple sex partners ? <font color="red">*</font> : </td>
             <td><select name="multiple_sex_partner" id="multiple_sex_partner" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.multiple_sex_partner}
                </select>
           </td></tr>
           </table>
            </fieldset>
                                 
           <table style="width: 100%">                      
            
            <tr>
            <td>7. Condom use? <font color="red">*</font> : </td>
            <td><select name="condom_use" id="condom_use" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.condom_use} 
                </select>
           </td>
            </tr>
         <tr>
            <td>8. Alcohol and substance abuse? <font color="red">*</font> : </td>
             <td><select name="alcohol_substance_abuse" id="alcohol_substance_abuse" required class="textbox2" style="border-color: green; width:140px;">
                    ${Assess.alcohol_substance_abuse}
                </select>
           </td>
            
            </tr>
         
               </table>
             
            <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">9. Adherehence. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>9 a). Adherence to ARV's ? <font color="red">*</font> : </td>
             <td><select name="adherence_to_arv" id="adherence_to_arv" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.adherence_to_arv}
                </select>
           </td></tr>
           
           <tr>
            <td>9 b). Adherence to other medications ? <font color="red">*</font> : </td>
             <td><select name="adherence_to_others" id="adherence_to_others" required class="textbox2" style="border-color: green; width:140px;">
                    ${Assess.adherence_to_others}
                </select>
           </td></tr>     
               </table> 
            </fieldset>
          
          
                <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">10. STIs. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>10 a). Asking Assessment questions ? <font color="red">*</font> : </td>
             <td><select name="asking_stis_questions" id="asking_stis_questions" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.asking_stis_questions}
                </select>
           </td></tr>
             </table> 
            </fieldset>
          
           <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">11. Family planning. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>11 a). Assessing pregnancy intentions ? <font color="red">*</font> : </td>
             <td><select name="family_planning" id="family_planning" required class="textbox2" style="border-color: green; width:140px;">
                 ${Assess.family_planning} 
                </select>
           </td></tr>
             </table> 
            </fieldset>
            
       <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">12. PMTCT. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>12 a). Planning to have children ? <font color="red">*</font> : </td>
             <td><select name="planning_to_have_children" id="planning_to_have_children" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.planning_to_have_children}
                </select>
           </td></tr>
             </table> 
            </fieldset>
                                 
    <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #c4c4ff;">13. TB. </b></legend>
                            <table style="width: 100%">
           <tr>
            <td>13 a). Screend for TB ? <font color="red">*</font> : </td>
             <td><select name="screened_for_TB" id="screened_for_TB" required class="textbox2" style="border-color: green; width:140px;">
                    ${Assess.screened_for_TB}
                </select>
           </td></tr>
             </table> 
            </fieldset>

                             </fieldset>                                 
        </div>
    
    <div id="step-3" style="height:430px;">
            <h2 class="StepTitle" style=" width:98%;">C-D. <font color="blue">Prevention counseling</font><i style="font-size:12px; color: green;">(counselor/peer educator)</i><font color="blue"> and HIV Testing.</font> <br> <i style="font-size: 10px; margin-left: 40%">(Fields marked <font color="red">*</font> are required.)</i></h2>
          <br>
          <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">C. PREVENTION COUNSELING(Counselor/Peer Educator):</b></legend>
                               
            <table>
            <tr >
            <td>1. HIV Disclosure? <font color="red">*</font> :</td>
           <td><select name="hiv_disclosure2" id="hiv_disclosure2" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.hiv_disclosure2}  
                </select>
           </td>
           </tr>
          
           <tr >
            <td>2. Safer sex methods? <font color="red">*</font> :</td>
           <td><select name="safer_sex_methods2" id="safer_sex_methods2" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.safer_sex_methods2}
                </select>
           </td>
           </tr>
           
           <tr >
            <td>3 Alcohol Use? <font color="red">*</font> :</td>
           <td><select name="alcohol_use" id="alcohol_use" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.alcohol_use} 
                </select>
           </td>
           </tr>
           
           <tr >
            <td>4. Adherence to ARV's ? <font color="red">*</font> :</td>
           <td><select name="adherence_to_arvs" id="adherence_to_arvs" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.adherence_to_arvs} 
                </select>
           </td>
           </tr>
           
           <tr >
            <td>5. Adherence to other medications ?(TB,Prophylaxis) <font color="red">*</font> :</td>
           <td><select name="adherence_other_medications" id="adherence_other_medications" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.adherence_other_medications}
                </select>
           </td>
           </tr>
           
           <tr >
            <td>6. Couples counseling? (discordant and concordant) <font color="red">*</font> :</td>
           <td><select name="couples_counseling" id="couples_counseling" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.couples_counseling} 
                </select>
           </td>
           </tr>
            </table> 
          </fieldset>
            
         
          <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">D. HIV TESTING:</b></legend>
                               
            <table>
            <tr >
                <td colspan="2">1. Refer for HIV testing: <font color="red">*</font> :</td>
           
           </tr>
          
           <tr >
            <td>1a). Partner ? <font color="red">*</font> :</td>
           <td><select name="partner_tested" id="partner_tested" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.partner_tested} 
                </select>
           </td>
           </tr>
           
           <tr >
            <td>1b). Children ? <font color="red">*</font> :</td>
           <td><select name="children_tested" id="children_tested" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.children_tested}  
                </select>
           </td>
           </tr>
         
            </table> 
          </fieldset>   
            
        </div>
    
    
    
    
    
    
    <div id="step-4" style="height:430px;">
            <h2 class="StepTitle" style=" width:98%;">E-F. <font color="blue" style="margin-left:30%">STIs and Family Planning.</font> <br> <i style="font-size: 10px;margin-left:40%;">(Fields marked <font color="red">*</font> are required.)</i></font></h2>
         <br>
          <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">E. STIs:</b></legend>
                               
            <table>
            <tr >
            <td>1. Providing referral for STI management at a health facility ? <font color="red">*</font> :</td>
            <td><select name="referral_for_sti" id="referral_for_sti" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.referral_for_sti}  
                </select>
           </td>
           </tr>
          
           <tr >
            <td>2. Providing targeted risk reduction information counseling ? <font color="red">*</font> :</td>
           <td><select name="risk_reduction_info" id="risk_reduction_info" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.risk_reduction_info} 
                </select>
           </td>
           </tr>
           
           <tr >
            <td>3. Follow up to ensure treatment adherence? <font color="red">*</font> :</td>
           <td><select name="treatment_adherence" id="treatment_adherence" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.treatment_adherence} 
                </select>
           </td>
           </tr>
           
           <tr >
            <td>4. Condoms provided ? <font color="red">*</font> :</td>
            <td><select name="condoms_provided" id="condoms_provided" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.condoms_provided}  
                </select>
           </td>
           </tr>
       
            </table> 
          </fieldset>
            
         
          <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">F. FAMILY PLANNING:</b></legend>
                               
            <table>
           
           <tr >
            <td>1. Assessing pregnancy status and intentions ? <font color="red">*</font> :</td>
            <td><select name="pregnancy_status" id="pregnancy_status" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.pregnancy_status} 
                </select>
           </td>
           </tr>
           
           <tr >
            <td>2. Referral for hormonal contraceptives ? <font color="red">*</font> :</td>
           <td><select name="hormonal_contraceptive" id="hormonal_contraceptive" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.hormonal_contraceptive} 
                </select>
           </td>
           </tr>
           
            <tr >
            <td>3. Provision or referral for condoms ? <font color="red">*</font> :</td>
           <td><select name="condoms" id="condoms" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.condoms}   
                </select>
           </td>
           </tr>
         
            <tr >
            <td>4. Conducting safer pregnancy counseling ? <font color="red">*</font> :</td>
           <td><select name="pregnancy_counseling" id="pregnancy_counseling" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.pregnancy_counseling} 
                </select>
           </td>
           </tr>
           
            <tr >
            <td>5. Provide information on transmission risks ? <font color="red">*</font> :</td>
            <td><select name="transmission_risks" id="transmission_risks" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.transmission_risks} 
                </select>
           </td>
           </tr>
           
            </table> 
          </fieldset>     
        </div>
    
    <div id="step-5" style="height:430px;" >
            <h2 class="StepTitle" style=" width:98%;">G-I. <font color="blue" style="margin-left:30%" >TB, PMTCT and other referrals.</font> <br> <i style="font-size: 10px; margin-left: 40%">(Fields marked <font color="red">*</font> are required.)</i></font></h2>
         <br>
            <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">G. TB:</b></legend>
            <table>
           <tr >
            <td>1. TB Screening ? <font color="red">*</font> :</td>
            <td><select name="tb_screening" id="tb_screening" required class="textbox2" style="border-color: green; width:140px;">
                 ${Assess.tb_screening}  
                </select>
           </td>
           </tr>
           
           <tr >
            <td>2. Reffered for TB diagnosis ? <font color="red">*</font> :</td>
           <td><select name="referred_tb_diagnosis" id="referred_tb_diagnosis" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.referred_tb_diagnosis} 
                </select>
           </td>
           </tr>
         
            </table> 
          </fieldset>   
            
             <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">H. PMTCT:</b></legend>
            <table>
           <tr >
            <td>1. Referred for PMTCT services ? <font color="red">*</font> :</td>
            <td><select name="referred_pmtct_services" id="referred_pmtct_services" required class="textbox2" style="border-color: green; width:140px;">
                  ${Assess.referred_pmtct_services} 
                </select>
           </td>
           </td>
           </tr>
            </table> 
          </fieldset>
            
             <fieldset id="new_group"><legend><b style="margin:0 auto; background-color: #acdd4a;">I. OTHER REFERRALS:</b></legend>
            <table>
          
           
           <tr >
            <td>1. Other referrals (Specify the referral point) ? <font color="red">*</font> :</td>
           <td><select name="other_referrals" id="other_referrals" required class="textbox2" style="border-color: green; width:140px;">
                   ${Assess.other_referrals}  
                </select>
           </td>
           </tr>
         
            <tr id="sp">
            <td>Specify referral point here. <font color="red">*</font> :</td>
            <td><input type="text" name="referral_point" id="referral_point" value="${Assess.referral_point}" required="true" class="textbox" style="border-color: green;">
           </td>
           </tr>
            </table> 
          </fieldset>
            
        </div>
    
    
  		</div>
  		
<!-- End SmartWizard Content -->  		
  		
</td></tr>
</table>  
 </c:forEach>                      
                            
                           
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                        
                  
                    </form>
                </div>
          
            </div>
<div id="footer">
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
       <script src="select2/js/select2.js"></script>
   <script type="text/javascript">
 $(document).ready(function(){
 $('select').select2();    
 });   
</script>    
    </body>
</html>


