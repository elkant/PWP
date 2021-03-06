<%-- 
    Document   : moveToGroup
    Created on : Jan 7, 2015, 3:54:36 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <!-- To manage success, fail notifications  -->            
 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.JPG"/>
        <title>Move individual clients to group</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>

         
         <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
         <script src="scripts/jquery.validate.js" type="text/javascript"></script>
          <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
          <script src="scripts/dataTables.scroller.js" type="text/javascript"></script>
          <script src="scripts/dataTables.colReorder.js" type="text/javascript"></script>
          <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
          <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
          
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
        
        
      
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
        
<title>Edit Clients.</title>
<script type="text/javascript">
   
  $(document).ready( function (){

$("#demo").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='example'><tr><td>Loading Data...<img src='images/utube.gif' alt='.'></td></tr></table>");
 $.ajax({
               url:"loadMoveIndividuals",
               type:"post",
//               data:""
               dataType:"html",
               success:function(data){
               $("#example").html(data);
              $('#example').dataTable().makeEditable({
  sUpdateURL: "saveEditedProvider",
                  "aoColumns": [ null,null,null,null,
                 
                     {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback: function()
                            { 
                              alert("here 1");
                            },
                            fnOnCellUpdated: function(){
                            alert("here 2");
                        }
                                                                                              
                        },
                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback: function()
                            { 
                              alert("here 1");
                            },
                            fnOnCellUpdated: function(){
                            alert("here 2");
                        }
                                                                                              
                        },
                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback: function()
                            { 
                              alert("here 1");
                            },
                            fnOnCellUpdated: function(){
                            alert("here 2");
                        }
                                                                                              
                        },
                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback: function()
                            { 
                              alert("here 1");
                            },
                            fnOnCellUpdated: function(){
                            alert("here 2");
                        }
                                                                                              
                        }
            ]
//////                    
              });
//alert("data");           
            }
////                            
////                            
                        });
//    
});

</script>

</head>
<body onload="">

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
<br/>
</div>
<br>
<div id="content" style="height:auto; margin-left: 100px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: -100px; margin-top: 3px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <br><%}%>
<div style="margin-left: 0px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Edit Service Provider Details.</div>

<br>
<div id="midcontent" style="margin-left:100px ; height: auto">
<div id="dialog" title="Add Group Help." style=" font-size: 17px;">
</div>   
  <div id="demo" style="width:1000px;margin-left:-100px; z-index:0;">
                         <table cellpadding="4px" cellspacing="4px" style="padding-top: 1px;"border="0" class="display" id="example">
<tr><td>SELECT PARAMETERS</td></tr>
               </table>
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


