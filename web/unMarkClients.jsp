<%-- 
    Document   : viewIndividuals
    Created on : Feb 1, 2015, 4:03:42 PM
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
        <title>View unmarked clients</title>
        <!--<link href="media/js/jquery.js" rel="stylesheet" type="text/css" />-->
	<!--<script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
         <script src="dataTable/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <script src="prefix-free.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>

         
         <script src="dataTable/jquery.dataTables.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="dataTable/jquery.jeditable.js" type="text/javascript"></script>
        <script src="scripts/jquery_ui.js" type="text/javascript"></script>
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
        <script src="dataTable/dataTables.fnGetFilteredNodes.js" type="text/javascript"></script>
        <script src="dataTable/dataTables.fnGetHiddenNodes.js" type="text/javascript"></script>
                <script>
	$(document).ready(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
                        width:700,
                        height:400,
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
        $("#message").show();
           $.ajax({
               url:"loadMessages",
               type:"post",
               dataType:"html",
               success:function(data){  
                $("#message")  .html(data); 
                
               }
           });
           
     $("#message").change(function(){
       loadUnMarked();  
     });      

 });
    function loadUnMarked(){  
   var message=$("#message").val();  
   
$("#demo").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='example'><tr><td>Loading Data...<img src='images/utube.gif' alt='.'></td></tr></table>");
 $.ajax({
    
               url:"loadUnMarkClients?message="+message,
               type:"post",
               dataType:"html",
               success:function(data){
          
               $("#example").html(data);
      $.ajax({
               url:"getTotalUnMarked?message="+message,
               type:"post",
               dataType:"html",
               success:function(data){

                $("#all") .val(data);
                if(data==0){
                 $("#submit").prop("disabled",true);
                 $("#submit").val("No Client");
                }
                else{
               $("#submit").removeAttr("disabled");
                 $("#submit").val("Mark");     
                }
             $('#checkall').change(function() {
             var all = $("#all") .val();
             var statusx= $('#checkall').is(':checked');
var i=1;
if(statusx==true){
while(i<=all){
   $("#checker_"+i).prop('checked', true);
    i++;
}
}
i=1;
if(statusx==false){
while(i<=all){
   $("#checker_"+i).prop('checked', false);
    i++;
}
}
 });         
 }
 });
 oTable=$('#example').dataTable().makeEditable({
 "aoColumns": [ null,null,null,null,null,null,null]
        });  
  $('form').submit(function(){
  $(oTable.fnGetHiddenNodes()).attr('checked',this.checked).appendTo(this).css("visibility","hidden");

} );
 
$('#checkall').click( function() { //this is the function that will mark all your checkboxes when the input with the .checkall class is clicked
    $('input', oTable.fnGetFilteredNodes()).attr('checked',this.checked); //note it's calling fnGetFilteredNodes() - this is so it will mark all nodes whether they are filtered or not
} );
       
 }
  });
  }
  
</script><!--ANIMATTED ALERTS FROM THE SYSTEM-->   
   	<script src="Alerts/alertify.js"></script>
	<script src="Alerts/alertify.min.js"></script>   
       <link rel="stylesheet" href="Alerts/alertify.core.css">
       <link rel="stylesheet" href="Alerts/alertify.default.css">
<script>
    $(document).ready(function(){
      $("form").submit(function(){
        var  rtn=false;
        var all = $("#all") .val();
var i=1;
while(i<=all){
  var values= $("#checker_"+i).is(':checked');
  if(values==true){
      rtn=true;
break;
  }
    i++;
}
if(rtn==false){
    alertify.alert("PLEASE SELECT ATLEAST ONE CLIENT.");
}
          return rtn;
      }); 
        
    });
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
<br/>
</div>
<br>
<div id="content" style="height:auto; margin-left: 30px;">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: -20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 100px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 2/4. Mark Individual Sessions.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>


    <%if (session.getAttribute("unMarked") != null) { %>
                                <script type="text/javascript"> 
                     var n = noty({text: '<%=session.getAttribute("unMarked")%> Click to close',
                        layout: 'center',
                        type: 'Success'});
                     </script> <%
                session.removeAttribute("unMarked");
                            }%>
   
<br>
<div id="midcontent" style="margin-left:10px ; height: auto">

<!--<p style=" font-size: 20px;"><font color="red">*</font> indicates must fill fields</p>-->
<form action="unMarkClients" method="post" style="width:1150px;"> 
    
     <select name="message" id="message" class="textbox2" required style="border-color: green; width:200px;">
        <option value="">Choose message</option>

    </select>
    <div id="dialog" title="Choose individual client to mark or edit their attendance ." style=" font-size: 17px; height:200px;">
<p><font color="red"><b>NOTE: </b></font>By default, the system is able to load 10 clients per page.<br>
   Follow the following steps to select the clients you want to edit/add their attendance:<br>
    1. Click here and select 100 clients per page as shown.<br>
    <img src="images/100.JPG" style="width:350px; height: 100px;"><br>
    2. Use the search box to select the clients you want to tick for marking or editing their attendance.
    It is recommended you use the <b>service provider name</b> to view all individual clients under this service provider.<br>
    3. Tick this client.<br>
    4. Search for the next client and the process repeats itself till all required clients are selected.<br>
    5. Upon selecting all the individuals, 
    select the action you want to take on this individuals on the lower part of the page then click <b>mark</b>.<br>
    <img src="images/indaction.JPG" style="width:400px; height: 100px;">
</div>
  <div id="demo" style="width:100%;margin-left:3px; z-index:0;">
                         <table cellpadding="4px" cellspacing="4px" style="padding-top: 1px;"border="0" class="display" id="example">
<!--<tr><td>SELECT PARAMETERS</td></tr>-->
               </table>
     
 </div>   
    <input type="hidden" name="all" value="" id="all">
    <input type="submit" value="Update" style="background-color: orangered;"> 
   
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


