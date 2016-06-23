<%-- 
    Document   : index
    Created on : Nov 26, 2013, 9:42:46 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PWP Session Tool</title>
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
function BrowserChecker() {
         var returned;
            if (navigator.userAgent.indexOf("Firefox") > 0) {
returned=true;
            }
//            if ($.browser.mozilla) { 
//            alert("mozilla in code2")
//            }
            else{
                alertify.alert("<image src=\"images/warning.png\" width=\"90px\" height=\"70px\"><b>Failed: Please Ensure You are using Mozilla FireFox.</b>");
             returned=false;
           
            }
            return returned;
        }
       $(document).ready(function(){
           $("#header").hide();
            $("#form").hide();
    $.ajax({
        url:"BackUpReminder",
        type:"post",
        dataType:"html",
        success:function(data){
             $("#form").hide();
            var res=data;
            var len=parseInt(res.length);
            if(res<=2){
                 $("#header").hide();
                 $("#ifdata").val("");
            }
            else{
                $("#ifdata").val("available");
                $("#header").show();
             $("#reminder").html(data);
            }
        }
    });
    
          $.ajax({
        url:"checkLock",
        type:"post",
        dataType:"html"
    });
    
       $.ajax({
        url:"ManualBackUp",
        type:"post",
        dataType:"html"
    });
    
//      $.ajax({
//        url:"UnEditedClients",
//        type:"post",
//               data:"",
//        dataType:"html"
//    });
//      $.ajax({
//               url:"updateKePMS",
//               type:"post",
////               data:""
//               dataType:"html"
//           });
           
           
       });
        </script>
        
        <script type="text/javascript">
            function loader(){
              setTimeout(function(){ 
          loaderDataConnection();
      }, 3000);
            }
            
            function loaderDataConnection(){
             $.ajax({
      url:"checkConnections" ,
      type:"post",
      dataType:"html",
      success:function(data){
      $("#connections").html(data);  
//       alert("loader called "+data);
         if(data.contains("red")){
$("#network").val("");
       }
       else{
           $("#network").val("available");
}
      }
      });
      var network,ifdata;
     network=$("#network").val().trim(); 
     ifdata=$("#ifdata").val().trim();
//     alert("if"+ifdata+"data");
      if(ifdata.trim()==="available"){
 if(network==="available"){
    $("#backupNotifier").show(); 
   $("#backupNotifier").html("<font color=\"red\"><b>Sorry:</b> You have not created data back up hence no access to the system.</font><br><br> Follow these steps to back up your data. <br><br> Step 1. Click <a href=\"ExportData.jsp\">here to access data back up module</a>.<br><br> Step 2. Create data back up.<br><br>")  ;
 $("#form").hide();
                }
                else{
    $("#backupNotifier").show(); 
   $("#backupNotifier").html("<font color=\"red\"><b>Sorry:</b> You have not created data back up hence no access to the system.</font><br><br> Follow these steps to back up your data. <br><br> Step 1. Please connect to the internet.<br><br> Step 2. Click <a href=\"ExportData.jsp\">here to access data back up module</a>.<br><br> Step 3. Create data back up.<br><br>")  ;
 $("#form").hide();
                }
}
else{
//    alert("called");
 $("#form").show();
$("#backupNotifier").hide();
}

      loader();
            }
            </script>
            
            
            
<script type="text/javascript" src="js/noty/themes/default.js"></script>
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
   <script>
       function update(){
          
    if(document.readyState !== 'complete' ){ 
//     location.reload(); 
//       checker();
//        alert("not loaded");
        } // don't do unless document loaded
      else{
//          alert("full loaded");
//          break;
      } 
}
function checker(){
setInterval(function(){update();}, 100);

}
       </script>
    </head>
    <body onload="checker();loaderDataConnection();BrowserChecker();">
        <div id="container" style=" height: auto;">
<div id="header">
                <div class="ui-widget" style="width:1200px; margin-left: -140px;" >
                    <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                        <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                        <p style="text-align: center" id="reminder"></p>
                    </div>


                </div>
            </div>
            <div id="content" style=" height:530px">
                
                <div id="midcontent" style=" height: 530px">
                      
                    <img src="images/index_img1.JPG" width="700px" alt="PWP System" align="centre"
                                               style="position: relative; top:50px; left: 250px; height: 130px;"/>
                                              
                       <div style="position: relative; top: 100px; left: 250px;"> 
                           <div id="backupNotifier"></div>
                       <form action="login" method="post" id="form" onsubmit="return BrowserChecker();">
                       <p align="center">Login</p>
                        <table style="margin-left: 190px; width: 100px;" >
                           
                            <tr>
                                <td></td>
                                <td style="text-align: right">
                                     <!--  username -->
                                     <input id="username"  type=text required name=uname title="<p style='text-align: center'>Please enter your PWP username here.</p><p ><font color='red'>NOTE : </font>Please ensure you are using your own username and password to log in to the system. </p>" placeholder="Username" autofocus class="textbox"/></td>
                                 </tr>
                            <tr><td></td> </tr>
                            <tr><td></td> </tr> <tr><td></td> </tr>
                            <tr><td></td>
                                <!--password-->
                                <td><input id="password"  type=password required title="Please enter your PWP password here." name=pass placeholder="Password" class="textbox"></td>
                            </tr>
                              <tr><td></td> </tr> <tr><td></td> </tr> <tr><td></td> </tr>
                            <tr>
                            <input type="text" name="ifdata" hidden="true" id="ifdata" value="">
                            <input type="text" name="network" hidden="true" id="network" value="">
                                <td style="text-align: right"> </td>
                                <td style="text-align: center"><input type="submit" class="submit" value="Log In"/>
                                   
                                    
                                </td>
                            </tr>
                             <tr><td style="text-align: left" colspan="2"  class="linkstyle"></td> </tr>
                             <tr>
                                   <td style="text-align: left" colspan="2"  class="linkstyle">
                                    <!--<a href="add_guest.jsp" style="">Register as a Guest</a>-->
                                </td> 
                            </tr>
                            
                        </table>
                       <p style="color: #0000ff">PWP System. Version 1.7 Last Updated 14th June 2016.</p>
                       <h4>
                        <%
                            if (session.getAttribute("error_login") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("error_login")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2800});
                    
                </script> <%
                
                session.removeAttribute("error_login");
                            }

                        %>
                        </h4>
                    </form>
                     </div>  
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
