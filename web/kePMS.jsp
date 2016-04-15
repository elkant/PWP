<%-- 
    Document   : kePMS
    Created on : Dec 4, 2014, 3:56:30 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KePMS Report</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="prefix-free.js"></script>
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
       $(document).ready(function(){
           $("#achieved1").hide();
           $("#services1").hide();
           $("#reporter").hide();  
           $.ajax({
               url:"load_Partners",
               type:"post",

               dataType:"html",
               success:function(data){
               $("#partner").html(data); 
             } 
           });
//           Load pepfar years

            $.ajax({
               url:"loadYears",
               type:"post",

               dataType:"html",
               success:function(data){
               $("#year").html(data);
               $("#year").select2();
             } 
           });
          $("#nextpage").change(function(){
               $("#nextpage1").html("<option value=\"\">Choose report type</option><option value=\"kePMSnew\">Clients reached per Partner</option><option value=\"kePMSCounty\">Clients Reached per County</option><option value=\"kePMSDIC\">Reached per DIC</option><option value=\"kePMSGroup\">Reached per Group</option>");
               $("#nextpage2").html("<option value=\"\">Choose report</option><option value=\"kePMSServices\">Services Provided per Partner</option><option value=\"kePMSDICServices\">Services Provided per DIC</option><option value=\"kePMSGroupServices\">Services Provided per Group</option>") ;
           var reportType=$("#nextpage").val();   
           if(reportType==="achieved"){
         $("#reporter").hide();
          $("#partner").removeAttr("required");
           $("#achieved1").show();
           $("#services1").hide();
           $("#perioder").hide();
           $("#period").removeAttr("required");
           }
          else if(reportType==="services"){
             $("#reporter").hide();
            $("#partner").removeAttr("required");
           $("#services1").show();
           $("#achieved1").hide();
            $("#perioder").show();
             $("#period").prop("required",true);
           }
           else if(reportType==="Reached_OthersMessages"){
//            Other messages
           $("#services1").hide();
           $("#achieved1").hide();
           $("#reporter").hide();
           $("#perioder").show();
           $("#partner").removeAttr("required");
           $("#period").prop("required",true);
            $("#nextpage1").removeAttr("required");
            $("#nextpage2").removeAttr("required");
           }
          else{
             $("#perioder").hide();
             $("#reporter").hide();
             $("#services1").hide();
             $("#achieved1").hide();
             $("#partner").removeAttr("required");
           }
           });
         
         $("#nextpage1").change(function(){
           $("#services1").hide();
           $("#nextpage2").removeAttr("required");
           
           var reportType=$("#nextpage1").val();   
           if(reportType=="kePMSnew"){
         $("#reporter").hide();
          $("#partner").removeAttr("required");
           }
            else if(reportType=="kePMSCounty"){
         $("#reporter").hide();
          $("#partner").removeAttr("required");
           }
          else if(reportType=="kePMSDIC"){
             $("#reporter").show();
            $("#partner").prop("required",true);
           }
           else if(reportType=="kePMSGroup"){
            $("#reporter").show();
            $("#partner").prop("required",true);
           }
          else{
             $("#reporter").hide();
             $("#services1").hide();
             $("#partner").removeAttr("required");
           }
           });
           
          $("#nextpage2").change(function(){
           $("#achieved1").hide();
           $("#nextpage1").removeAttr("required");
           
           var reportType=$("#nextpage2").val();   
           if(reportType=="kePMSServices"){
         $("#reporter").hide();
          $("#partner").removeAttr("required");
           }
           
          else if(reportType=="kePMSDICServices"){
             $("#reporter").show();
            $("#partner").prop("required",true);
           }
           else if(reportType=="kePMSGroupServices"){
             $("#reporter").show();
            $("#partner").prop("required",true);
           }
          else{
             $("#reporter").hide();
             $("#achieved1").hide();
             $("#partner").removeAttr("required");
           }
           }); 
           $("#year").change(function(){
//               alert("here");
              var currentyear=parseInt($("#year").val()) ;
              var prevyear=currentyear-1;
                      $("#period").html("<option value=\"\">Choose period </option><option value=\"10-12\">Oct - Dec ("+prevyear+")</option><option value=\"01-03\">Jan - March ("+currentyear+")</option><option value=\"04-06\">April - June ("+currentyear+")</option><option value=\"07-09\">July - Sept ("+currentyear+")</option>");
           });
        }); 
       </script> 
        <link rel="stylesheet" href="select2/css/select2.css">   
    </head>
    <body>
        
    <div id="container" style="height: auto;">
        
   <%if(session.getAttribute("level")!=null){if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}else{}}%>
<div id="header" align="center">
<br/>
</div>
<div id="content" style="height:auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">kePMS report <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>

                       <% if (session.getAttribute("kePMSError") != null)  { %>
                                <script type="text/javascript"> 
                    var n = noty({text: '<%=session.getAttribute("kePMSError")%> .<br> Click here to close.',
                        layout: 'center',
                        type: 'Success'});
                 </script> <%session.removeAttribute("kePMSError");} %>
           
                 
                 
<div id="midcontent" style="margin-left:230px ; height: auto">
<div id="dialog" title="KePMS Report Help." style=" font-size: 17px;">
<p></p>
</div>
<p style=" font-size: 20px;"><font color="red" >*</font> indicates must select fields</p>
<form action="kePMSDecider" method="post"><br><br>
<table cellpadding="2px" cellspacing="3px" border="0px">

<tr> <td class="align_button_right" style=" font-size: 18px;">Select Report Type<font color="red">*</font></td>
    <td>  
    <select name="nextpage" id="nextpage" style="width:450px;" required class="textbox2">
        <option value="">Choose report type</option>
        <option value="achieved">Clients Reached Report(New reached clients with minimum package)</option>
        <option value="Reached_OthersMessages">Clients Reached with Other Messages (Already reached with minimum package)</option>
        <option value="services">Services Provided Report</option>

    </select>  
    </td>
</tr>
 
<tr> <td class="align_button_right" style=" font-size: 18px;">Choose Pepfar Year<font color="red">*</font></td>
    <td>  
    <select name="year" id="year" required style="width:450px;" class="textbox2">
        <option value="">Choose Year </option>
        <option value="2014">2014</option>
        <option value="2015">2015</option>
    </select>  
    </td>
</tr>

<tr id="perioder"> <td class="align_button_right" style=" font-size: 18px;">Choose Period<font color="red">*</font></td>
    <td>  
    <select name="period" id="period" style="width:450px;" class="textbox2">
        <option value="">Choose period </option>

    </select>  
    </td>
</tr>

<tr id="achieved1"> <td class="align_button_right" style=" font-size: 18px;">Client reached Report<font color="red">*</font></td>
    <td>  
    <select name="nextpage1" id="nextpage1" style="width:450px;" required class="textbox2">
        <option value="">Choose report type</option>
        <option value="kePMSnew">Clients reached per Partner</option>
        <option value="kePMSCounty">Clients reached per Partner</option>
        <option value="kePMSDIC">Clients reached per DIC</option>
        <option value="kePMSGroup">Clients reached per Group</option>
    </select>  
    </td>
</tr>

<tr id="services1"> <td class="align_button_right" style=" font-size: 18px;">Services provided Report: <font color="red">*</font></td>
    <td>  
    <select name="nextpage2" id="nextpage2" style="width:450px;" required class="textbox2">
        <option value="">Choose report</option>
        <option value="kePMSServices">Services Provided per Partner</option>
        <option value="kePMSDICServices">Services Provided per DIC</option>
        <option value="kePMSGroupServices">Services Provided per Group</option>
    </select>  
    </td>
</tr>

<tr id="reporter"> <td  class="align_button_right" style=" font-size: 18px;">Select Partner<font color="red">*</font></td>
    <td>  
    <select name="partner" id="partner" style="width:450px;" required class="textbox2">
        <option value="">Choose Partner </option>
    </select>  
    </td>
</tr>

</td><td></td></tr>
<tr><td></td><td><input type="submit" value="Generate" class="submit"/></td></tr>
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
 $('select').select2();    
 });
 
</script> 
            
    </body>
</html>
