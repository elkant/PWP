<%-- 
    Document   : add_groups2
    Created on : Nov 27, 2013, 7:40:41 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((session.getAttribute("partner_name")==null) || (session.getAttribute("userid")==null) || (session.getAttribute("district_name")==null)) {
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
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
<title>Add Groups.</title>
 <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script type="text/javascript">
  function nullchecker(){
      var no_of_groups=<%=session.getAttribute("no_of_groups")%>-1
      var i=1;
      var aller="";
      var group_name,location,year_formed;
     while(i<=no_of_groups){
         group_name=document.getElementById("group"+i).value;
         location=document.getElementById("location"+i).value;
         year_formed=document.getElementById("year_formed"+i).value;
         
         if((group_name!="" && (location=="" || year_formed=="")) || (location!="" && (group_name=="" || year_formed=="")) || (year_formed!="" && (group_name=="" || location==""))){
            alertify.alert("Enter all details at position : "+i) 
            return false;
         }
        i++; 
        if(group_name!="" && location!="" && year_formed!=""){
        aller+=group_name;}
     }
     if(aller==""){
       alertify.alert("Rgister atleast one Group") 
            return false;   
     }
  }  
</script> 
</head>
<body>

<div id="container" >
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
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Add New Groups.</div>

<div id="midcontent" style="margin-left:150px ; width: 900px; height: auto">

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
                        
<h3>Enter Group Names.</h3>
<form action="add_groups" name="form" onsubmit="return nullchecker();" method="post" >
    <br>
   <h4 style="color: #330033; height: 25px;">District Name: <%=session.getAttribute("district_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; || &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Partner Name: <%=session.getAttribute("partner_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ||&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Health Facility  <%=session.getAttribute("health_facility_name").toString()%>  </h4>
     
   <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style"  style="background: #ccccff; width: 875px">
<tr><th>No.</th><th>Group Name</th><th>Location</th><th>Year Formed</th></tr>
           <%=session.getAttribute("groups").toString()%>
    
                      <tr><td colspan="4"><input type="submit" name="sub" value="Add Groups" class="textbox1" style="background: #cc99ff; color: #0000ff"></td></tr>
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
