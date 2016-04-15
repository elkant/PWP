<%-- 
    Document   : DuplicateEntries
    Created on : Nov 18, 2014, 10:54:54 AM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.JPG"/>
        <title>Check Duplicates</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<!--<script src="menu/prefix-free.js"></script>-->  

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

         
<script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
         <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
          <script src="scripts/jquery-ui.js" type="text/javascript"></script>
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
    function loadStaff(){
var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("example").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","loadStaff",true);
xmlhttp.send();
    } 
    </script>
          
          
          <script type="text/javascript">
     $(document).ready( function () {
         $("#dataE").change(
           function () {  
               var datav=$("#dataE").val();
//          $("#example").html(""); 
//alert("here");
$("#demo").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='example'><tr><td>Loading Data...<img src='images/utube.gif' alt='.'></td></tr></table>");
//     alert("here2");
        $.ajax({
               url:"duplicateName?data="+datav,
               type:"post",
//               data:""
               dataType:"html",
               success:function(data){
               $("#example").html(data);
//               alert(data);
                $('#example').dataTable({
               sDeleteURL: "deletefacil", 
            "dom": 'T<"clear">Rlfrtip',
              
        "tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf",
            "aButtons": [ {
                    "sExtends": "csv",
                    "sButtonText": "Save to csv"
                },
                {
                    "sExtends": "xls",
                    "sButtonText": "Save to xls"
                },
                {
                    "sExtends": "pdf",
                    "sButtonText": "Save to pdf"
                } ],
             "sRowSelect": "multi"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true   
                    
                });
            
            }
                            
                            
                        }       
                );
    }
                );
                
            
            });
            
            function deleter(id){
//           alert("id  :   "+id)     
          var datav=document.getElementById("dataE").value;
    var retVal = confirm("Do you want to Delete this Client ?");
   if( retVal == true ){
       var xmlhttp;    
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
//    alert("deleted successfully.....");
//=======================================================================
$(function () {  
// alert("after success deleting============================"+datav)
$("#demo").html("<table cellpadding='4px' cellspacing='4px' style='padding-top: 1px;' border='0' class='display' id='example'><tr><td>Loading Data...<img src='images/utube.gif' alt='.'></td></tr></table>");
//     alert("here2");
        $.ajax({
               url:"duplicateName?data="+datav,
               type:"post",
//               data:""
               dataType:"html",
               success:function(data){
               $("#example").html(data);
//               alert(data);
                $('#example').dataTable({
               
            "dom": 'T<"clear">Rlfrtip',
              
        "tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf",
            "aButtons": [ {
                    "sExtends": "csv",
                    "sButtonText": "Save to csv"
                },
                {
                    "sExtends": "xls",
                    "sButtonText": "Save to xls"
                },
                {
                    "sExtends": "pdf",
                    "sButtonText": "Save to pdf"
                } ],
             "sRowSelect": "multi"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true   
                    
                });
            
            }
                            
                            
                        }       
                );
    }
);
//=======================================================================
}
}
xmlhttp.open("POST","ActionDelete?id="+id,true);
xmlhttp.send();

       return true;
   }
   else{
       return false;
   }
}
            
        </script>
   
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
      
    <link rel="stylesheet" href="select2/css/select2.css">  
      
    </head>
    <body>
       
     <div id="container" style="height:auto;" >
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
            
            
            <div id="content" style="height:auto; width: 100%;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
                 <div style="margin-left: 0px; margin-top: -10px; color: #000; background: #d6b4eb; width:100%; font-size: 24px; text-align: center;">Duplicate Entries.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></div>
             <br>
             <div id="dialog" title="Duplicate Entries Help." style=" font-size: 17px;">
<p>Users are required to select the parameters they intend to use to check if the client is a duplicate.</p>
<p>Users with administrative priviledges are also able to delete these duplicates.</p>
</div>
             
                <div id="midcontent" style="margin-left:0px ; height:auto;" >
                    <form action="add_clerk" method="post" style="margin-left:0px; width:1200px;">
                       <br>
                       <b>  Choose Parameter Here: </b>    <select name="dataE" id="dataE" class="textbox2" style="width:250px;">
                            <option value="">Choose Category</option>
                            <option value="1">Full name</option>
                            <option value="2">Full name + age</option>
                            <option value="3">Full name + Partner</option>
                            <option value="4">CCC NO</option>
                            <option value="5">NATIONAL ID</option>
                            <option value="6">MOBILE NUMBER</option>
                        </select>
                        <br/><br/>
 <div id="demo" style="width:100%;margin-left:3px; z-index:0%;">
                         <table cellpadding="4px" cellspacing="4px" style="padding-top: 1px;width:97%;"border="0" class="display" id="example">
<tr><td>SELECT PARAMETERS</td></tr>
               </table>
 </div>
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
            <script src="select2/js/select2.js"></script>
   <script type="text/javascript">
 $(document).ready(function(){
 $('select').select2();    
 });   
</script> 
    </body>
</html>

