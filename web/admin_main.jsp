<%-- 
    Document   : admin_main
    Created on : Nov 26, 2013, 4:35:12 PM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
              <title>Admin Main Page</title>
        <link rel="shortcut icon" href="images/header.JPG"/>
        <link rel='stylesheet' type='text/css' href='css/main.css' />
    </head>
    <body>
        <div id="container" style="height: 700px;">
            <div id="content" style="height: 600px;">
               <%if (session.getAttribute("level").toString().equals("1")){%>
               <%@include file="/menu/Officer.jsp" %>
              <%} else if (session.getAttribute("level").toString().equals("3")){%>
                 <%@include file="menu/super_admin.jsp" %>
              <%}%>
                 <div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 18px;"> &copyPWP System Aphia Plus | USAID <%=year%></p>
            </div>
            </div>
            
        </div>
    </body>
</html>