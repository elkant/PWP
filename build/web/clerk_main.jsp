<%-- 
    Document   : clerk_main
    Created on : Nov 26, 2013, 3:55:23 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((!session.getAttribute("level").equals("2")) || (session.getAttribute("userid")==null)) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
              <title>Clerk Main Page</title>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.9.1.js"></script>
<script src="prefix-free.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
    </head>
    <body>
        <div id="container" style="height: 500px;">
   <%@include file="/menu/user.jsp" %>
            <div id="content" style="height: 400px; top: 20px;">
                <div id="midcontent" style="height:0px;">   
                 
                 <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: -320px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                </div>
                <%}%>
                <script type="text/javascript"> 
                    
                    var n = noty({text: 'NOTE: <br><br><font color=\"blue\">Always Remember to log out. This improves Security.</font>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 6800});
                    
                </script>
                </div> </div>
                <div id="footer" style="">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copyPWP System Aphia Plus | USAID <%=year%></p>
           
            </div>
            
        </div>
    </body>
</html>
