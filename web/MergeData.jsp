<%-- 
    Document   : MergeData
    Created on : Mar 24, 2014, 9:31:15 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null || (session.getAttribute("level").toString().equals("2") || session.getAttribute("level").toString().equals("5"))) {
      
        response.sendRedirect("index.jsp");
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

<script src="prefix-free.js"></script>
   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

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
       <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
      <script type="text/javascript">
      function hasExtension(inputID, exts) {
    var fileName = document.getElementById(inputID).value;
//    document.getElementById("loads").hidden=true;
    return (new RegExp('(' + exts.join('|').replace(/\./g, '\\.') + ')$')).test(fileName);
}
function checker_validity(){
    var va=false;
    if (!hasExtension('upload', ['.sql','.SQL'])) {
    // ... block upload
   alertify.alert("Incorrect file. Please upload the correct Data File.")
  if(document.getElementById("loads")!=null){
   document.getElementById("loads").hidden=true;
  }
    va=false;
}
   if (hasExtension('upload', ['.sql'])) {
       document.getElementById("namer").hidden=true;
       document.getElementById("loader").hidden=false;
       if(document.getElementById("loads")!=null){
   document.getElementById("loads").hidden=true;
  }
//       document.getElementById("loads").hidden=true;
//       document.getElementById("load").hidden=true;
   va=true; 
   loader();
}
return va;
//return false;
}
function filename(){
    
} </script>
      
       <script type="text/javascript">
            function loader(){
              setTimeout(function(){ 
          loaderDataConnection();
      }, 200);
            }
            
            function loaderDataConnection(){
             $.ajax({
      url:"mergeDone" ,
      type:"post",
      dataType:"html",
      success:function(data){
      $("#mergestatus").html(data);  
      }
      }); 
//      alert("loader called");
      loader();
            }
            </script>
            
       
  <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('.textbox').jBox('Tooltip', {
    position: {
       y: 'bottom'
    },
    width:600,
    outside: 'y'
});      

     });
    
        
   </script>          
                
                
                
                
        
<title>Merge Data</title>


</head>
<body>

    <div id="container" style=" height: auto">
<% if (session.getAttribute("level").toString().equals("1") || session.getAttribute("level").toString().equals("2")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%}%>
<div id="header" align="center">
<br/>
</div>
 <%if (session.getAttribute("datasend1") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend1")%>. click to close.',
                        layout: 'center',
                        type: 'Success'
                       } );
                    
                </script> <%
                session.removeAttribute("datasend1");
                            }

                        %>
                         <%if (session.getAttribute("already_clients") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("already_clients")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 6800});
                    
                </script> <%
                session.removeAttribute("already_clients");
                            }

                        %>

<div id="content" style="height:500px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<table style="width:1200px;height: 20px; background: #d6b4eb; font-size: 24px; "><tr><td>
<div style="text-align: center;">Merge PWP Data <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div></td>
        <!--<td><div style="text-align: center;"><p id="mergestatus"><br></p></div></td>-->
    </tr></table>
<br><br>
<div id="midcontent" style="margin-left:230px ; height: 350px;">
<div id="dialog" title="Merge Data Help." style=" font-size: 17px;">
<p>1. Click on Browse to select the file that you had downloaded.</p>
<p>2. Choose the file, the file must be a .sql file.</p>
<p>3. Click on merge to merge the data.</p>

</div>
    <br>
    
<form style="height: 250px;" action="FileUploadServlet" method="post" enctype="multipart/form-data" onsubmit="return checker_validity();">
    
    <br><br>
    
    <div id="loader" hidden="true" style="left: 300px; height: 10px;">
         <br>
         <p><img src='images/utube.gif' width='50px' height='20px'> &nbsp;&nbsp;&nbsp;&nbsp;<i  id="mergestatus"></i></p><br>
         <!--<p><img src='images/utube.gif' width='50px' height='20px'> Loading data ...</p>-->
         <!--<img src="images/loads.PNG" width='200px' height='20px'>-->
    </div>
    
    <div id="namer">
        <input type="file" name="file_name" id="upload" value="" title='<font color="red">NOTE: </font><br> Follow the following steps to merge data : <br>1. Download the data file from your mail.<br> 2. Click on browse<br>3. Locate the file data file you had earlier downloaded. <br> Select the file and then click <b>OPEN</b>. <br>4. Lastly click on Merge button and wait for a response from the system. <br><br> If the data file is large, it may takes more time to merge this data. ' class="textbox" required>   
    <br><br><br><br>
    <input type="submit" value="Merge" class="submit"/>
    <br><br></div>
      <% if (session.getAttribute("saved_success") != null) {
      if (session.getAttribute("saved_success").toString().equals("success")) {
    %>
    <p id="loads" style="font-size: 22px;"><img  src="images/suc1.png" height="60px" width="60px" style="margin-left:550px; margin-top: -70px;">Success.</p>
     
     <%}else if (session.getAttribute("saved_success").toString().equals("fail")){%>
  <p id="loads" style="font-size: 22px;"><img  src="images/warning.png" height="60px" width="60px" style="margin-left:550px; margin-top: -70px;">Failed.</p>
  <%}  session.removeAttribute("saved_success");  }%>
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
