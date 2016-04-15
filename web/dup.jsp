<%-- 
    Document   : viewClients
    Created on : Oct 13, 2014, 10:48:11 PM
    Author     : Geofrey Nyabuto
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.UUID"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
   String uuid = UUID.randomUUID().toString();
   String uuid2 = UUID.randomUUID().toString();
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
 <title>Delete Clients</title>
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
<link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
<link rel="shortcut icon" href="images/header.PNG"/>
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>
          
  <!----datatable--Maureens---->
  
<!--
        <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>-->
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
        <!--<script src="media/js/jquery-ui.js" type="text/javascript"></script>-->
        <!--   <script src="media/js/jquery.validate.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-ui.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.js" type="text/javascript"></script>
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
  
  
 <script type="text/javascript">
    
            $(document).ready( function () {
                $('#example').dataTable().makeEditable({
//                                  
//									
//                    sUpdateURL: "editfacil",
//                    //                                                                        sAddURL: "AddCounty",
//                    sDeleteURL: "deletefacil",
//                    "aoColumns": [ null,null,null                   									
//                    ]									

                });
				
            } );
            
           
            
        </script>
        <script type="text/javascript">

            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-17838786-2']);
            _gaq.push(['_trackPageview']);

//            (function() {
//                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
//                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
//                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
//            })();

function deleter(){
    var retVal = confirm("Do you want to Delete this Client ?");
   if( retVal == true ){
       return true;
   }
   else{
       return false;
   }
}


        </script>
<!--clerk menu-->



  <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<script src="js/jquery-ui-1.10.3.custom.js"></script>

</head>   
<body onload="">
   
    
 <div id="container" style="height:auto;" > 
  <!--menu-->   
   <%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("5")){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}%>  
      
<div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #CFDDEE; font-size: 24px; text-align: center;">Delete Clients.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>    
           
      <div id="content" style="height:auto;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>   
                    
                </div> <%}%>
    <div id="dialog" title="Add User Help." style=" font-size: 17px;">
        </div>
                 
                 <% if (session.getAttribute("deleted") != null) { %>
                                <script type="text/javascript"> 
                    var n = noty({text: '<%=session.getAttribute("deleted")%>',
                        
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 8800});
                    
                </script> <%
                session.removeAttribute("deleted");
                            }
%>
    
 <div id="demo" style="width:109%;margin-left:3px; z-index:0%;">
           
           <p id="msg"><p>
                        <table cellpadding="4px" cellspacing="4px" style="padding-top: 100px;"
                               border="0" class="display" id="example">
                            <thead>
                                <tr  >
    <th>COUNTY</th>
  <th>PARTNER</th>   
   <th>DISTRICT</th> 
   <th>GROUP</th>
   <th>CLIENT NAME</th>
   <th>AGE</th>
   <th>GENDER</th>
   <th>ATTENDED</th>
   <th>ACTION</th>
     
</tr>
    </thead>
 <%--<c:forEach items="${AList}" var="AList">--%>
<tr id="1">
    <td  class="sorting_1">i</td>
    <td  class="sorting_1">i</td>
    <td  class="sorting_1">i</td>  
    <td  class="sorting_1">i</td> 
    <td  class="sorting_1">i</td>
    <td  class="sorting_1">i</td>
    <td  class="sorting_1">i</td>
    <td  class="sorting_1">i</td>  
    <td  class="sorting_1">a</td>
    
</tr>
 <%--</c:forEach>--%>
</table>
</div>   
    
    
</div>
                        
 
        
 <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
 <div id="footer">
  <p align="center" style=" font-size: 18px;"> &copy PWP System Aphia Plus | USAID <%=year%></p>
            </div>                             
</div>
</body>
</html>

