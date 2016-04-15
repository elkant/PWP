<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
--%>

<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null || (session.getAttribute("level").toString().equals("5"))) {
      
        response.sendRedirect("index.jsp");
           }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
       <link rel="shortcut icon" href="images/header.JPG"/>
        <!--        <link rel="stylesheet"  type="text/css" href="menu/adminmenu_files/css3menu1/style.css" />-->
        <!--menu-->
        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="prefix-free.js"></script>
        <title>MandE_email</title>

       
        
        
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <link href="js/css/flick/jquery-ui-1.10.4.custom.css" rel="stylesheet"/>	
   
        <script type="text/javascript" src="js/jquery-ui.js"></script>   


        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->

        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        <!--clerk menu-->
         

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>
        <script type="text/javascript">
//      $(function() {
//
//$( document ).tooltip();
//
//
//}); 
function load_counties(){    

// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("county").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","county_loader",true);
xmlhttp.send();
}//county loader

     function load_partner(){

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
document.getElementById("partner").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_partners",true);
xmlhttp.send();
}
//set the partner

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
     <link rel="stylesheet" href="select2/css/select2.css">    
    </head>
    <body onload="">
        <div id="container"  style="height: 600px;">
<%if (session.getAttribute("level").toString().equals("2")){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("1")){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level").toString().equals("3")){%>
<%@include file="/menu/Officer.jsp" %>
<%}%>
            <div id="content" style="width:700px; margin-left: 150px; height: 450px;">
<h3 style="text-align: center; background: #d6b4eb; margin-left: 100px;">Set/ Edit M&E Officer's email.  <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</h3>
            

                <div id="midcontent" style="margin-left:130px ; height: 300px;">






                    <%if (session.getAttribute("mail") != null) {



                    %>
                    <script type="text/javascript"> 
                    
                                       var n = noty({
                                           text: '<%=session.getAttribute("mail")%>',
                                           layout: 'center',
                                           type: 'Success',
                                           timeout:3000});
                    
                    </script>
                    <%
                            session.removeAttribute("mail");
                        }

                    %>
                    <!--creating random user id-->

<div id="dialog" title="Merge Data Help." style=" font-size: 17px;">
<p>This page enables users to set/edit County M&E officer email.</p>
<p>Please ensure you have correctly set up this mail.</p>
<p>Whenever there is a data back up, it will be sent to head office and also a copy of this data will be sent to the county M&E office email you will set here.</p>
<p>After entering all details correctly, Click on update to save the changes you have made.</p>
</div>




                    <br/><br/>

                    <form action="save_me_mail" method="post" style="height:auto; width: 600px;">
                        <br/>

                        <% dbConn conn = new dbConn();
//CREATE A MAIL TABLE FROM CODE IF IT DOES NOT EXIST
//                        String executer="CREATE TABLE IF NOT EXISTS mail(`mail_id` int(11) NOT NULL, `mail` varchar(50) NOT NULL, PRIMARY KEY(mail_id))";
//                                conn.st.executeUpdate(executer);
       String selectedcounty="",selectedpartner="",urmail="no email";
      String counties="<option value=\"\">Select County</option>",partners="<option value=\"\">Select Partner</option>";
                            conn.rs = conn.st.executeQuery("select mail,county,partner,urmail from mail");

                            String mandemail = "enter mail";
                            if (conn.rs.next()) {

                                mandemail = conn.rs.getString(1);
                                selectedcounty=conn.rs.getString(2);
                                selectedpartner=conn.rs.getString(3);
                                urmail=conn.rs.getString(4);

                            } else {
                                mandemail = "no email";

                            }
                            String count=" select * from county "; 
                            conn.rs=conn.st.executeQuery(count);
                            while(conn.rs.next()){
                            if(conn.rs.getString(2).equals(selectedcounty)){
                             counties+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
                            }
                            else{ counties+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";   
                           
                                
                            }
                            }
                            String part=" select * from partner ORDER BY partner_name ASC"; 
                            conn.rs=conn.st.executeQuery(part);
                            while(conn.rs.next()){
                            if(conn.rs.getString(2).equals(selectedpartner)){
                             partners+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
                            }
                            else{
                             partners+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";   
                               
                            }
                            }
                          conn.rs.close();
                          conn.st.close();

                        %>
                        <table> 
                            <tr>          
                                <td>
                                Select County
                                </td>
                                <td>

                                </td>
                                <td>
                                  <Select id="county" class="textbox2" onchange="filter_districts(this);"  required ="true" name="county" >
<%out.println(counties);%>
</select> 
                                </td>
                            </tr>
                            <tr>          
                                <td>
                                Select Partner
                                </td>
                                <td>

                                </td>
                                <td>
                                  <Select id="partner" class="textbox2"  required ="true" name="partner" >

<%out.println(partners);%>
</select>
                                </td>
                            </tr>
                            <tr> <td>
Enter County M&E Email address:
                                </td> <td>  </td>      
                                <td><input type="email" name="mail" title="<font color='red'>NOTE : </font> Data back up will be sent to this email address. <br> It must be your county M&E Officer email address." required  value="<%=mandemail%>" class="textbox1" style="height:30px; width: 200px;" /></td>
                                <td>

                                </td></tr>
                            <tr> <td>
Enter Your Email address:
                                </td> <td>  </td>      
                                <td><input type="email" name="urmail" title="<font color='red'>NOTE : </font> This is your email address, please ensure you have entered your email address correctly. This will be essential incase your data has a problem we can reach you easily." required  value="<%=urmail%>" class="textbox1" style="height:30px; width: 200px;" /></td>
                                <td>

                                </td></tr>
                            
                            <tr><td>  </td>  <td>  </td>  
                                <td>
                                    <input type="submit"  title='Click to update.' value="Update" style="height:40px;"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>



            <div id="footer">
                <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->

                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center">&copyPWP System Aphia Plus | USAID <%=year%></p>
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
