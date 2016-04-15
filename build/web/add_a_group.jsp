<%-- 
    Document   : add_a_group
    Created on : Apr 1, 2014, 8:12:53 AM
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

<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel='stylesheet' type='text/css' href='css/btn.css' />
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.9.1.js"></script>
<script src="prefix-free.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>

   <!-- ANIMATE HELP    -->
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
        
<title>Select session type.</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 $(function() {

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

load_partner();


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
 $("#district").select2();  
}
}
xmlhttp.open("POST","district_loader?county_id="+dist,true);
xmlhttp.send();
}//end of filter districts

//*******************filter healthy facility*************************


      
        function load_partner(){
var county=document.getElementById("county").value;
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
$('select').select2();
}
}
xmlhttp.open("POST","load_partners?county="+county,true);
xmlhttp.send();
}
//set the partner

 function load_no_of_groups(){
     
        var groups=document.getElementById("no_of_groups").value;
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
xmlhttp.open("POST","groups_maker?groups="+groups,true);
xmlhttp.send();
}
//set the partner
function filter_groups(){
//alert("called");
        var district_id=document.getElementById("district").value; 
        var partner_id=document.getElementById("partner").value; 
        var cate=document.getElementById("cat").value;
//        alert("district : "+district_id+"  partner : "+partner_id+"  cat "+cate);
        
        if(cate==1){
            $("#1").hide();
//          document.getElementById("1").style="display: none";
         document.getElementById("grp").value="";
          document.getElementById("grp").removeAttribute("required");
         
//          alert("here1")
        }
        if(cate==2){
             $("#1").show();
//           document.getElementById("1").style="display: compact";
          document.getElementById("grp").required=true;
        
//          alert(id"here2")
        }
      if(cate==3){
           $("#1").hide();
//           document.getElementById("1").style="display: none";
          document.getElementById("grp").removeAttribute("required");
        
//          alert(id"here2")
        }
        
     if(district_id!="" && partner_id!="" && cate==2) { 
//       var dis_part=partner_id+"-"+district_id;
//       alert("here loading data");
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
document.getElementById("grp").innerHTML=xmlhttp.responseText;
$("#grp").select2(); 
//alert("data........."+xmlhttp.responseText);
}
}
xmlhttp.open("POST","load_groups?district_id="+district_id+"&&partner_id="+partner_id,true);
xmlhttp.send();
}
else{
//    alert("failed");
}
}
function checkPending(){
 var xmlhttp; 
var output="";
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
   var output=xmlhttp.responseText;
   document.getElementById("str").value=output;
   output=document.getElementById("str").value;
    if(output!="NONE"){
        document.getElementById("headertitle").innerHTML="<font color=\"red\">NOTE </font>: Please Complete Step 5 as Shown below.";
   document.getElementById("originalcont").hidden=true;
    document.getElementById("newcont").hidden=false;
   var alldata=output.split("##");
//   alert("partner :   "+alldata[0])
//   alert("district :   "+alldata[1])
//   alert("group :   "+alldata[2])
//   alert("message :   "+alldata[3])
   document.getElementById("dis").innerHTML=alldata[1];
   document.getElementById("part").innerHTML=alldata[0];
   document.getElementById("gr").innerHTML=alldata[2];
   document.getElementById("messo").innerHTML=alldata[3];
    }
   if(output=="NONE"){
     document.getElementById("newcont").hidden=true;
     document.getElementById("originalcont").hidden=false;
    }

}
}
xmlhttp.open("POST","checkUnmarked",true);
xmlhttp.send();
}   
</script>

<script>
    $(document).ready(function(){
        var idis,grp;
        $("#1").hide();
//     $("#cat").change(function(){
//         idis=$("#cat").val();
////         alert("here"+$("#grp").html());
//       
//         
//      if(idis=="2") {
//        $("#1").show(); 
//         $("#grp").props("required",true);
////           $("#grp").select2(); 
//      } 
//      
//      if(idis=="3"){
//        $("#1").hide(); 
//        $("#grp").removeAttr("required");
//       }
//     });
     
//      $.ajax({
//        url:"checkRegisteredClients",
//        type:"post",
//       dataType:"html",
//        success:function(data){
//            if(parseInt(data.length)>3){
//           $("#midcontent") .html(data);
//        }
//        else{
//     
//        }
//        }
//    });
    
     });
    </script>
      <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
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
<body onload="checkPending(); load_partner();load_counties();">

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

<div id="content" style="height:auto">
    <%if (session.getAttribute("fullname")!=null){ %>
    <div style="margin-left: 20px; margin-top: 10px; font-size: 17px;">
    Hi, <u><%=session.getAttribute("fullname").toString()%>  </u>       
</div> <%}%>
<div style="margin-left: 300px;width: 550px; margin-top: -10px; color: #000; background: #d6b4eb; font-size: 24px; text-align: center;">STEP 1/5. Select Group/Individual Sessions <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;">.</div>
<br>
<div ><h4 style=" font-size: 23px;text-align: center;" id="headertitle"></h4></div>
<div id="midcontent" style="margin-left:230px ; height: auto">
<!--<p style="font-size: 15px;"><font color="red" >NOTE : </font>For Individual Sessions,In Choose category, Choose Existing group and then Select Individual. </p>--> 

<div id="dialog" title="Select session type Help." style=" font-size: 17px;">
<p>Select county,partner,district as shown in the PWP Form.</p>
<p>On Choose Category Section : 
    <br>1. <b>Choose group sessions</b>, for those clients who attended sessions in a group.
    <br>2. <b>Choose Individual Sessions</b>, for those clients who attend sessions individually.
<p>
<p>Groups are always associated to a specific partner and district.</p>

</div>
<form action="save_a_group" method="post">
    <table cellpadding="2px" cellspacing="3px" border="0px" id="newcont" hidden="true">
    <tr style="font-size: 25px;"> 
        <td><font color="red">N/B :</font> You did not complete all the steps when you last marked attendance for : <br><br> 
            Group Name :<b id="gr" style="color:red;">group</b><br>
            Partner Name : <b id="part" style="color:red;">partner</b> <br>
            District : <b id="dis" style="color:red;">dist</b> <br>
            Message (s) given:<b id="messo" style="color:red;">meso</b>
            Please  <a href="directPage" style="-webkit-border-radius: 2px;
    -moz-border-radius: 2px;
    border-radius: 2px;
    border: solid 1px #20538D;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
    -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);
    -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);
    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);
    background: #4479BA;
    color: #FFF;
    padding: 1px 2px;
    text-decoration: none;">Click here</a> to complete step 5.</td>
        </tr>
<table cellpadding="2px" cellspacing="3px" border="0px" id="originalcont" hidden="true">
  <%
                            if (session.getAttribute("error_group") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("error_group")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2800});
                    
                </script> <%
                
                session.removeAttribute("error_group");
                            }

                        %>



<tr><td></td></tr>
<tr style="display: compact">
<td class="align_button_right" style=" font-size: 20px;">County<font color="red">*</font></td>
<td><select id="county" class="textbox2" style="width:400px;" title="Please click here to select county.<br> (<font color='red'>This field is required</font>)" onchange="filter_districts(this);"  required ="true" name="county_id" >
<option value="">Choose County</option> 
</select></td>

<input type="text" name="str" id="str" hidden="true" value="">
</tr>   
<tr><td></td></tr>
<tr><td></td></tr>
<tr style="display: compact">  
<td class="align_button_right" style=" font-size: 20px;">Implementing Partner<font color="red">*</font></td>
<td><select id="partner" class="textbox2" style="width:400px;" title=" Click here to select the implementing partner.<br>(<font color='red'>This field is required</font>)"  required ="true" onchange="filter_groups();" name="partner" >
  
<option value="">Choose partner</option>  
</select></td><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr style="display: compact">  
<td class="align_button_right" style=" font-size: 20px;">District<font color="red">*</font></td>
<td><select id="district" class="textbox2" style="width:400px;" title="Click here to select district here. <br>(<font color='red'>This field is required.</font>)"  required="true" name="district" onchange="filter_groups();" >

<option value="">Choose District</option>  
</select></td>
        <%if(session.getAttribute("district_id")!=null && session.getAttribute("partner_id")!=null && session.getAttribute("group_name_s")!=null && session.getAttribute("complete")!=null) {%>
      <script type="text/javascript"> 
                    
                    var n = noty({text: '<font color="green"><b>Data Saved Successfully.</b></font>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script>
                
    <%}%>
    
        <%
      session.removeAttribute("complete"); 

%>
 
</tr>
<tr><td></td></tr><tr >
<td class="align_button_right" style=" font-size: 20px;">Choose Session type: <font color="red">*</font></td>
<td>
    <select name="cat" id="cat" class="textbox2" title="Click here to select session type i.e Group session or individual session.<br> (<font color='red'>This field is required.</font>)" onchange="filter_groups();" style="width:400px;" required="true">
        <option value="">Session type </option>
        <!--<option value="1">New Group</option>-->
        <option value="2">Group Session</option>
        <option value="3">Individual Sessions</option>
   </select>
    
</td><td></td></tr>
<tr ><td></td></tr><tr id="1">
<td class="align_button_right" style=" font-size: 20px;">Choose A Group<font color="red">*</font></td>
<td>
    <select name="grp" id="grp" class="textbox2" title="Click here to select group. <br>This is the group that you want to mark/edit its attendance.<br>(<font color='red'>This field is required.</font>)" style="width:400px;" onchange="">
        <option value="">Choose Group</option>
   </select>
    
</td><td></td></tr>

<tr><td></td><td><input type="submit" value="Next >>" class="submit"/></td></tr>

</table>
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
