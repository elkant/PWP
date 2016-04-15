<%-- 
    Document   : add_providers2
    Created on : Nov 28, 2013, 9:58:55 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
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
<script type="text/javascript" src="js/numeric.js"></script>
<script type="text/javascript" src="js/jquery_min.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
  <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
  <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
	<script src="js/jquery-1.9.1.js"></script>
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
	</script> 
        
<title>Add Providers.</title>
 <!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script type="text/javascript">
  function nullchecker(){
      var no_of_clients=<%=session.getAttribute("no_of_providers")%>
      var i=1;
      var fname,mname,lname,phone,group_ids;
     var all_fnames=""
     while(i<=no_of_clients){
         
         
         
         fname=document.getElementById("fname"+i).value;
         lname=document.getElementById("lname"+i).value;
         phone=document.getElementById("phone"+i).value;
         mname=document.getElementById("mname"+i).value;
         group_ids=document.getElementById("groups"+i).value;
//         alert(group_ids+","+fname+","+mname+","+lname+","+phone)
         if(fname!="" && (lname=="" || phone=="" || group_ids=="" )){
             
                     alertify.alert(" First Name entered at position : "+i+" Enter Missing Details.") 
            return false;
            
         } if(mname!="" && (fname=="" || lname=="" || phone=="" || group_ids=="")) {
                      alertify.alert(" Middle Name entered at position : "+i+" Enter Missing Details.") 
            return false;    
         }
         if    (lname!="" && (fname=="" || phone=="" || group_ids=="") ) {
                   alertify.alert(" Last Name entered at position : "+i+" Enter Missing Details.")  
            return false;      
         }
         if (phone!="" && (fname=="" || lname=="" || group_ids=="")){
                 alertify.alert(" Phone entered at position : "+i+" Enter Missing Details.") 
            return false;      
             
         }
         if (group_ids!="" && (fname=="" || lname=="" || phone==""))
              {
             alertify.alert(" Groups Selected at position : "+i+" Enter Missing Details.") 
            return false;
         }
        i++; 
        all_fnames+=fname;
     }
            if (all_fnames=="")
              {
            alertify.alert("Register At least one Service provider") 
            return false;
         }
         
  }  
</script> 
<script type="text/javascript">
    function number_validator(){
    var aller =<%=session.getAttribute("no_of_providers")%>
    var f=1;
    while(f<=aller){
    $(".phone"+f+"").numeric(false, function() { alertify.alert("Integers only"); this.value = ""; this.focus(); });
    f++;
    }
    }
</script>
</head>
<body>

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
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Add New Service Providers.</div>
 
<div id="midcontent" style="margin-left:150px ; width: 900px; height: auto">
<div ><h3 style=" font-size: 23px;">Enter Service Provider Details.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h3></div> 

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
                        
<div id="dialog" title="Add Service Providers' Details Help." style=" font-size: 15px;">
<%  String message1="";
dbConn conn = new dbConn();
String message_selector="SELECT * FROM help WHERE help_id='8'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
conn.rs.close();
conn.st.close();
%>
</div>
<form action="add_providers" name="form" onsubmit="return nullchecker();" method="post" >
    <br>
   <h4 style="color: #330033; height: 25px; font-size: 18px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;District Name : <%=session.getAttribute("district_name").toString()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Partner Name  :  <%=session.getAttribute("partner_name").toString()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</h4>

   <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style"  style="background: #ccccff; width: 875px">
<tr><th>No.</th><th>Groups</th><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Phone Number</th></tr>
           <%=session.getAttribute("all_providers").toString()%>

                      <tr><td colspan="6"><input type="submit" name="sub" value="Add Providers" class="textbox1" style="background: #cc99ff; color: #0000ff"></td></tr>
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
