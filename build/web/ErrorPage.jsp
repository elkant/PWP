<%-- 
    Document   : ErrorPage
    Created on : Mar 14, 2014, 3:51:35 PM
    Author     : Geofrey Nyabuto
--%>
<%-- 
    Document   : add_clients
    Created on : Nov 27, 2013, 10:28:26 AM
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
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

<script src="prefix-free.js"></script>
   
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

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
        
function get_years(){

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
document.getElementById("year").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","years_loader",true);
xmlhttp.send();
}
//set the partner
function get_period(){
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
document.getElementById("period").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","period_loader",true);
xmlhttp.send();
}

	</script>                
         <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>        
<title>Error Page</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 $(function() {
$( "#datepicker0" ).datepicker();
$( "#datepicker1" ).datepicker();
$( "#datepicker2" ).datepicker();
$( "#datepicker3" ).datepicker();
$( "#datepicker4" ).datepicker();
$( "#datepicker5" ).datepicker();
$( "#datepicker6" ).datepicker();
$( "#datepicker7" ).datepicker();
$( "#datepicker8" ).datepicker();
$( "#datepicker9" ).datepicker();
$( document ).tooltip();
$( "#accordion" ).accordion();

}); 




// a function that filters the districts in the passed county as per the county dropdown.
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




function filter_districts(district){

var dist=district.value;    

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (dist=="")
{
//filter the districts    



document.getElementById("district").innerHTML="<option value=\"\">Choose District</option>";
return;
}
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
document.getElementById("district").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","district_loader?county_id="+dist,true);
xmlhttp.send();
}//end of filter districts

        function load_partner(){
var xmlhttp="";
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

//*******************filter healthy facility*************************

function filter_groups(){

        var district_id=document.getElementById("district").value; 
        var partner_id=document.getElementById("partner").value; 
     if(district_id!="" && partner_id!="") {  
       var dis_part=partner_id+"-"+district_id;
//        alert("here  :  "+dis_part)
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
document.getElementById("group").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_groups?district_id="+district_id+"&&partner_id="+partner_id,true);
xmlhttp.send();
}
}
 function load_no_of_clients(){
     var clients=document.getElementById("no_of_clients").value;
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
}
}
xmlhttp.open("POST","load_no_clients?clients="+clients,true);
xmlhttp.send();
}
 function get_providers(){
     var group=document.getElementById("group").value;
     var partner=document.getElementById("partner").value;
     var district=document.getElementById("district").value;
//     alert("partner  : "+partner+"  distr  : "+district+"   group  : "+group)
     if(group!="" && partner!="" && district!=""){
         var xmlhttp=""
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
document.getElementById("provider").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","get_providers?group="+group+"&&partner="+partner+"&&district="+district,true);
xmlhttp.send();
if(group=="0"){
    
document.getElementById("n1").hidden=false;  
document.getElementById("n2").hidden=false;
document.getElementById("nhf").required=true;
get_nhfs();
}
else{
document.getElementById("n1").hidden=true;  
document.getElementById("n2").hidden=true;
document.getElementById("nhf").required=false;
}
}

 }
function get_nhfs(){

var dist=document.getElementById("district").value;;    
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
document.getElementById("nhf").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_health_facility?district_id="+dist,true);
xmlhttp.send();
}//end of filter healthy facility
</script>

</head>
<body onload=" load_partner();load_counties();get_years(); get_period(); ">

<div id="container" >
    <%if (session.getAttribute("level")=="2"){ %>
<%@include file="/menu/user.jsp" %>
<%} else if (session.getAttribute("level")=="1"){%>
<%@include file="/menu/super_admin.jsp" %>
<%} else if (session.getAttribute("level")=="5"){%>
<%@include file="/menu/guest_menu.jsp" %>
<%}%>
<div id="header" align="center">
<br/>
</div>
 
<div id="content" style="height:700px;">

<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">Add New Clients.</div>


<div id="midcontent" style="margin-left:230px ; height: 450px;">



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






<%@page import="pwp.dbConn"%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Date"%>

<%@ page import="java.util.*, javax.mail.*, javax.mail.internet.*" %> 
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SYSTEM ERROR</title>
        <link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->



        <!--clerk menu css-->
    



        <script type="text/javascript" src="js/noty/themes/default.js"></script>


        <!--tooltip-->
        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />



 


    <script type="text/javascript">
        $(function() {

            $( document ).tooltip();
            $( "#accordion" ).accordion();

        }); 
    </script>
</head>

<!-- Body page -->



<body>
    <div id="container" style="width: 1200px; margin-left: 150px;">
          
        <%
            if (session.getAttribute("level") != null) {

                if (session.getAttribute("level").equals("0")) {%>
        <%@include file="menu/super_admin.jsp" %>
        <%} else {%>

        <%@include file="menu/clerk_menu.html" %>

        <%}

        } else {%>

        <%@include file="menu/clerk_menu.html" %>

        <%}%>
 <div id="header" style="text-align: center;">
           </div>
        <div id="content" style="height:500px ; ">
           <div id="midcontent">
            <div class="ui-widget">
                <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                    <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                        <strong></strong> PWP System  ERROR!! <br/> 
                        An error has occured.
                        <br/>
                        <%String subj = "System Error";


//                            StringWriter stringWriter = new StringWriter();
//                            PrintWriter printWriter = new PrintWriter(stringWriter);
//                            exception.printStackTrace(printWriter);
//                            //____________________COMPUTER NAME____________________________________
//                            String computername = InetAddress.getLocalHost().getHostName();
//                            String myerror = stringWriter.toString().toLowerCase();
//                            //out.println(stringWriter);
                            //printWriter.close();
                            //stringWriter.close();

                        %>
                         </p>
                    An error  notice has been send to Biometric System developers for immediate action.<br/>Press Browser back button to continue with other activities if the error persists.
                    <%




//                        Calendar cal = Calendar.getInstance();
//                        int year = cal.get(Calendar.YEAR);
//                        int month = cal.get(Calendar.MONTH) + 1;
//                        int date = cal.get(Calendar.DATE);
//                        int hour = cal.get(Calendar.HOUR_OF_DAY);
//                        int min = cal.get(Calendar.MINUTE);
//                        int sec = cal.get(Calendar.SECOND);
//                        String yr, mnth, dater, hr, mn, sc, action = "";
//
//                        Date dat = new Date();
//                        dbConn conn = new dbConn();
//
//                        String inserter = "insert into error_log set error_name='" + stringWriter + "' , time='" + dat + "', actor_id='" + session.getAttribute("userid") + "'";
//                        conn.st3.executeUpdate(inserter);
//

                    %>


                    <% String host = "smtp.gmail.com";

//                          String user = "mwambageofrey@gmail.com";
//                        String pass = "27798006";
//                        String to = "mwambage@gmail.com";
//                        String from = "mwambageofrey@gmail.com";
//                        String subject = computername + " PWP SYSTEM ERROR";
//                        String messageText = myerror;
//                        boolean sessionDebug = false;
//                        Properties props = System.getProperties();
//                        props.put("mail.host", host);
                        //  props.put("mail.transport.protocol", "smtp");
                        //props.put("mail.smtp.auth", "true"); 
                        //props.put("mail.smtp.port", 465); 
            // Uncomment 5 SMTPS-related lines below and comment out 2 SMTP-related lines above and 1 below if you prefer to use SSL
//                        props.put("mail.transport.protocol", "smtps");
//                        props.put("mail.smtps.auth", "true");
//                        props.put("mail.smtps.port", "465");
//                        props.put("mail.smtps.ssl.trust", host);
//                        Session mailSession = Session.getDefaultInstance(props, null);
//                        mailSession.setDebug(sessionDebug);
//                        Message msg = new MimeMessage(mailSession);
//                        msg.setFrom(new InternetAddress(from));
//                        InternetAddress[] address = {new InternetAddress(to)};
//                        msg.setRecipients(Message.RecipientType.TO, address);
//                        msg.setSubject(subject);
//                        msg.setSentDate(new Date());
//                        msg.setText(messageText);
//            //Transport transport = mailSession.getTransport("smtp");
//                        Transport transport = mailSession.getTransport("smtps");
//                        transport.connect(host, user, pass);
//                        transport.sendMessage(msg, msg.getAllRecipients());
//                        transport.close();
//
//                    %> 

                </div>
                   
        </div>

        

        


            



                <%//=exception.getMessage()%>


                <%
                    //StringWriter stringWriter = new StringWriter();
                    //PrintWriter printWriter = new PrintWriter(stringWriter);
                    //exception.printStackTrace(printWriter);
                    //out.println(stringWriter);
//                    printWriter.close();
//                    stringWriter.close();
                %>





                <br/><br/>

            </div>
        </div>

      

        <div id="footer">
            <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
           
            <p align="center"> &copy <a href="#" title="">CHWs Biometric System</a> Aphia Plus | USAID <%=""%></p>
        </div>
    </div>
</body>


</html>
