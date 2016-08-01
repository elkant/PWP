<%-- 
    Document   : backupdata
    Created on : Mar 13, 2014, 9:38:46 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null || (session.getAttribute("level").toString().equals("2") || session.getAttribute("level").toString().equals("5"))) {
      
//        response.sendRedirect("index.jsp");
           } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <title>Backup data</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>   
  <script src="prefix-free.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

<!--clerk menu-->

<!--<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>-->
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
        $(document).ready(function(){
         $.ajax({
          url:"loadEmail" ,
          type:"post",
          dataType:"html",
          success:function(data){
        var str1 = $.trim("SET M&E EMAIL ADDRESS.".toUpperCase());     
        var str2 = $.trim(data.toUpperCase()); 
//        alert(str1+":::    "+str2);
          if(str1===str2){
              $("#notification").html("SORRY: M&E email is not set. Please<a href='set_email.jsp' target='blank'>Click Here</a> to set the mail.");
//            $("#send").val("No Mail Set");
////            $("#send").hide();
//           $("#send").prop("disabled","true");
           $("#notification").prop("style","background-color:red;");  
          }
          else{
          $("#notification").html(data);
//             $("#send").val("Create Backup");
//             $("#send").show(); 
//           $("#send").removeprop("disabled","true");
           $("#notification").removeprop("style","");
          }
         
          }
         });
//         
//         $.ajax({
//          url:"checkConnections" ,
//          type:"post",
//          dataType:"html",
//          success:function(data){
//         $("#connections").html(data);  
//          }
//      });
         
          $("#updateMail").click(function(){
//          alert("update called");  
          var partner,county,mail,urmail;
          
          partner=$("#partner").val();
          county=$("#partner").val();
          mail=$("#mail").val();
          urmail=$("#urmail").val();
//          alert("partner : "+partner+"   county:   "+county+" mail : "+mail+" ur mail : "+urmail);
          
          $.ajax({
          url:"save_me_mail?partner="+partner+"&&county="+county+"&&mail="+mail+"&&urmail="+urmail,
          type:"post",
          dataType:"html",
          success:function(){
     var n = noty({text: '<font color=\"green\">Mail updated successfully.</font>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
          }
      });
      
         });
        });
    
	</script>
        <script type="text/javascript">
            function loader(){
              setTimeout(function(){ 
          loaderDataConnection();
      }, 5000);
            }
            
            function loaderDataConnection(){
                
             $.ajax({
      url:"checkConnections" ,
      type:"post",
      dataType:"html",
      success:function(data){
      $("#connections").html(data);  
          if(data.indexOf("red")!==-1){
//          there is net
$("#send").prop("disabled",true);
$("#send").val("Error : No internet !");
$("#send").css("color","black");
$("#send").css("font-size","30px");
$("#send").css("background","red");
       }
       else{

$("#send").val("Create Back up");
$("#send").css("background","white");
$("#send").css("color","black");
$("#send").css("font-size","30px");
$("#send").removeAttr("disabled");
       }
    }
      }); 
     
      loader();
            }
            </script>
            
            <script src="jBox/jBox.js"></script>
       <script src="jBox/jBox.min.js"></script>
        <link rel="stylesheet" href="jBox/jBox.css">
<script type="text/javascript">
    $(document).ready(function(){
  $('.tooltip').jBox('Tooltip', {
      content: $('#notifier'),
    position: {y: 'bottom' },
    width:600,
    outside: 'y'
});      
        
// $('#delete_form').jBox('Tooltip', {content: $('#deleteForm'),position: {x: 'right'},width:200,outside: 'x'});
       
    });
    </script>
    
    </head>
    <body onload="loader();">
       
        
        
        
        <div id="container"  style="height: auto;">
 <% if (session.getAttribute("level")!=null){%>
       <%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}} else{%>
<%@include file="/menu/guest_menu.jsp" %>
<br><br>
After creating data back up, Click <a href="index.jsp">here to Log in</a> and access all system resources.
<br>
<%}%>
            <h3 style="text-align: center; background-color: #d6b4eb;">SEND DATA BACK UP VIA MAIL.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h3>

            
            
            <div id="content" style="width:1000px;height: auto; margin-left: 100px; ">
                <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
              
                <div id="midcontent" style="margin-left:10px ; width:950px; height: auto;">
                  
                         <%if (session.getAttribute("datasend") != null) {
                         
    
    
    %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend")%>',
                        layout: 'center',
                        type: 'Success'});
                    
                </script> <%
                session.removeAttribute("datasend");
                            }

                        %>
                        <!--creating random user id-->
                         <%!
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
%>  
<div id="dialog" title="Back up data help." style=" font-size: 17px;">
    <p>Users are able to confirm and change their details before backing up data. </p>
    
    <p>
        <img src="images/connection.JPG" style="width:250px; height: 200px;">  
        Users are also notified on the status of internet connectivity before backing up their data.</p>
   
</div>
                        
              <form name="forms" id="forms" action="#" method="post">          
              <p id="notification">Loading data...</p>
              <input type="button" name="update" id="updateMail" value="update">
              </form>
              <p id="connections">Checking for internet connections...</p>
            
                    <form action="BackUpData" method="post" style="height:auto;">
                        <br/>
                        <input type="hidden" name="src" value="backupdata.jsp">
                       <input type="submit" id="send" value="Checking ..." disabled="true" class="tooltip" style="height:80px;width:300px;" >
                   <br><br>
                    </form>
              
              <p id="notifier" hidden="true"><font color="red" style="text-align:center;"><b>Before Creating any data back up ensure :</b></font> <br>
              1. The M&E email address has been set up correctly.<br>
              2. There is internet connection, because our system relies on internet connection to send data.<br>
              3. Users are able to change their M&E email addresses before sending data.<br>
              4. Click on the "Send Back Up" button to create and send data back up.<br>
              5. Wait for a success/failure message to confirm if data was sent successfully.<br>
              </p>
                </div>
            </div>

            

             <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">&copyPWP System Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>
