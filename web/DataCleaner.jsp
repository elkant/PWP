<%-- 
    Document   : DataCleaner
    Created on : Dec 16, 2014, 8:16:32 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data cleaner</title>
        <link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link rel='stylesheet' type='text/css' href='css/main.css' />
       
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />
<!-- You can add more layouts if you want -->
 <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script>
       $(document).ready(function(){
//           UPDATE SESSIONS==============================
$("#reminder").html("Please wait while your data is being updated.");
     $.ajax({
          
               url:"updateSessionData",
               type:"post",
               dataType:"html",
               success:function(){
//                   alert("completed updating sessions");
               
 //         UPDATE TIMESTAMP=======================================  
                 $.ajax({
               url:"timestampUpdator",
               type:"post",
//               data:""
               dataType:"html",
               success:function(){
           //    UPDATE KEPMS================================================ 
            $.ajax({
               url:"updateKePMS",
               type:"post",

               dataType:"html",
               success:function(){
              $("#header").hide(10000);     
               } 
           });        
                   
               }
           });

           
         
               }
           });
 });
        </script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
    </head>
    <body>
        <div id="container" style=" height: auto;">
<div id="header">
                <div class="ui-widget" style="width:1200px; margin-left: -140px;" >
                    <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                        <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                        <p style="text-align: center" id="reminder"></p>
                    </div>


                </div>
            </div>
            <div id="content" style=" height:100px">
                
                <div id="midcontent" style=" height: 100px">
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
