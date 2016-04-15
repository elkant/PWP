<%-- 
    Document   : timestamp
    Created on : Jan 19, 2015, 8:48:56 AM
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
//out.println(session.getAttribute("level").toString());
    if (session.getAttribute("userid")==null) {

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
        <title>Change timestamp</title>
      <script type ="text/javascript" src="js/datepicker/min.js"></script>
	<!--<script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
 <script type="text/javascript" src="js/jquery-ui.js"></script>   
   
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
<script type ="text/javascript" src="js/datepicker/jquery.jdpicker.js"></script>
<link rel="stylesheet" href="js/datepicker/jdpicker.css" type="text/css" media="screen" /> 
 <script>
	$(function() {
                 var dateObject,day,month,year,current_date;
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=day+"/"+month+"/"+year; 
   
   
       $('#timestamp').jdPicker({
     date_format:"dd/mm/YYYY",
     show_week:1,
     week_label:"we",
     start_of_week:3,
     date_min:"01/01/2010",
     date_max:current_date,
     selectable_days:[1, 2, 3, 4, 5]
//     non_selectable:["May 24 2000"],
//     rec_non_selectable: ["Jan 01", "May 26"]
}); 
	});
	</script>
<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>
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
//         alert("loader");
         $.ajax({
          url:"loadTimestamp" ,
          type:"post",
          dataType:"html",
          success:function(data){
       $("#timestamp").val(data);
    }
         });
         $("#updateMail").click( function(){
          alert("update called");   
             
             
         });
        });
	</script>
           <!--<script src="jBox/jBox.js"></script>-->
    <script src="jBox/jBox.min.js"></script>
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('input').jBox('Tooltip', {
    position: {
       x: 'right'
    },
    width:300,
    outside: 'x'
});      
 
 
    $('.textbox2').jBox('Tooltip', {
    position: {
       x: 'right'
    },
    width:300,
    outside: 'x'
});


     });
    
        
   </script>
    </head>
    <body>
       
        <div id="container"  style="height: auto;">
       <%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}%>
            <h3 style="text-align: center; background-color: #d6b4eb;">CHANGE TIMESTAMP/LAST DATA BACK UP DATE.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h3>

   <div id="dialog" title="Change timestamp Help." style=" font-size: 17px;">
       <p>Incase you need to back up and send previously backed up data, click on the 
           last back up date as shown below and change as appropriate.</p>
</div>         
            
            <div id="content" style="width:auto;height: auto; margin-left: 100px; ">
               <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%> 
              
                <div id="midcontent" style="margin-left:30px ; width:auto; height: auto;">
                     <%if (session.getAttribute("updateTimestamp") != null) {
                         
    
    
    %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("updateTimestamp")%>. Click to close',
                        layout: 'center',
                        type: 'Success'});
                    
                </script> <%
                session.removeAttribute("updateTimestamp");
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
              
                    <form action="updateTimestamp" method="post" style="height: auto; width:900px;">
                        <br/>
                        Last BackUp Date : <input type="text" name="timestamp" required="true" id="timestamp" value="" class="textbox"><br><br>
                       <input type="submit" id="send" value="Update" style="height:40px;">
                       
                    </form>
                </div>
            </div>

<br><br>

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
