<%-- 
    Document   : ClientRegistrationForm
    Created on : Jan 22, 2015, 9:55:39 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>

<link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/header.PNG"/>
        <title>Client Enrollment Form</title>
    
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="tooltip/css/tooltipster.css">
<link href="styles_wiz/demo_style.css" rel="stylesheet" type="text/css">
<link href="styles_wiz/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<!--<script type="text/javascript" src="js_wiz/jquery-2.0.0.min.js"></script>-->


 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
 <script src="menu/prefix-free.js"></script>  
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<!--WIZARD-->
<script type="text/javascript" src="js_wiz/jquery.smartWizard.js"></script>
<script type="text/javascript" src="js_wiz/validator.js"></script>

<script type="text/javascript">
   
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>
<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript"> 

            
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
 <!--calendar-->
 <script src="tooltip/js/jquery.tooltipster.min.js"></script> 

<!--<script type ="text/javascript" src="js/datepicker/min.js"></script>-->
<script>
        $(document).ready(function() {
            $('.tooltip').tooltipster();
        });
        $('#step1').tooltipster({
                content: $('step 1 here')
            });
    </script>
         <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
   <link rel="stylesheet" href="ui-essentials/demos.css">
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
       <script type="text/javascript">
  $(document).ready(function() {
      var currentYear = (new Date).getFullYear();
      var prev_year=parseInt(currentYear)-5;
$( "#dob" ).datepicker({changeMonth: true, changeYear: true, yearRange: '1900:'+currentYear+'', dateFormat: 'yy-mm-dd', maxDate: new Date()});
$( "#approval_date" ).datepicker({changeMonth: true, changeYear: true, yearRange: '1900:'+currentYear+'', dateFormat: 'yy-mm-dd', maxDate: new Date()});
$( "#registration_date" ).datepicker({changeMonth: true, changeYear: true, yearRange: '1900:'+currentYear+'', dateFormat: 'yy-mm-dd', maxDate: new Date()});
       
         $.ajax({
        url:"loadMinDate",
        type:"post",
        dataType:"html",
        success:function(data){
        var minDate=data.split("/");
        var year,month,dater;
        year=minDate[2];
        month=minDate[0];
        dater=minDate[1];
        
      var OrderedMinDate=year.trim()+"-"+month.trim()+"-"+dater.trim();
//      alert(" cal start date : "+OrderedMinDate+"dob : "+dob);
       
         
        
      // $( "#registration_date" ).datepicker({changeMonth: true, changeYear: true, yearRange: ''+prev_year+':'+currentYear+'', dateFormat: 'yy-mm-dd',minDate: OrderedMinDate,maxDate: new Date()});
      }
    });

//$( document ).tooltip();
});
    
    
</script>
<script>
    $(document).ready(function(){
   //    UPDATE KEPMS================================================ 
   var county_id,district_id;
      var currentYear = (new Date).getFullYear();
      var prev_year=parseInt(currentYear)-5;
      
      $("#grouper").hide();
      $("#new_group").hide();
      $("#existing_group").hide();
      $("#new_provider").hide();
      $("#existing_provider").hide();
      $("#receive_message").hide();
            $.ajax({
               url:"load_County",
               type:"post",

               dataType:"html",
               success:function(data){
              $("#county").html(data);     
               } 
           });
//          LOAD ALL DISTRICTS===================================== 
           $("#county").change(function(){
             load_partner();
        var partner_id=$("#partner_name") .val();
              county_id= $("#county").val();
              $.ajax({
               url:"load_District?county_id="+county_id,
               type:"post",

               dataType:"html",
               success:function(data){
              $("#district").html(data);  
              $("#district").select2();
               } 
           });
           
       
        if(partner_id!="" && county_id!=""){
        $.ajax({
               url:"loadWards?partner_id="+partner_id+"&&county_id="+county_id,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#ward").html(data); 
               $("#ward").select2(); 
             } 
           });
        }
           
           });
//           LOAD ALL GROUPS======================================
                $("#district").change(function(){
              district_id= $("#district").val();
              $.ajax({
               url:"load_Group?district_id="+district_id,
               type:"post",

               dataType:"html",
               success:function(data){
              $("#group_name").html(data);
              $("#group_name").select2();
               } 
           });   
           }); 
          //LOAD ALL PROVIDERS===================================== 
             $("#district").change(function(){
              district_id= $("#district").val();

              $.ajax({
               url:"load_Providers?district_id="+district_id,
               type:"post",

               dataType:"html",
               success:function(data){
              $("#service_provider").html(data);  
              $("#service_provider").select2();  
               } 
           });   
           });
           
       $.ajax({
               url:"load_Years",
               type:"post",

               dataType:"html",
               success:function(data){
              $("#year_confirmed").html(data); 
              $("#year_confirmed").select2(); 
               } 
           });  
           
             $.ajax({
               url:"load_Facilities",
               type:"post",

               dataType:"html",
               success:function(data){
              $("#health_facility").html(data);  
              $("#health_facility").select2();  
               } 
           });
           
       
        $.ajax({
               url:"load_Partners",
               type:"post",

               dataType:"html",
               success:function(data){
               $("#partner_name").html(data); 
             } 
           });
            
         $("#linked_to_group").change(function(){
               var ed_status;
              ed_status= $("#linked_to_group").val();
              
              if(ed_status=="yes"){
              $("#grouper").show();
              $("#receive_message").show();
              }
              else if (ed_status=="no"){
              $("#grouper").hide();
              $("#receive_message").hide();
              $("#new_group").hide(); 
               $("#existing_group").hide();  
              }
              else{
               $("#grouper").hide();
                $("#new_group").hide();
                $("#existing_group").hide();  
              }
          });
          
          
           $("#group_status").change(function(){
               var ed_status;
              ed_status= $("#group_status").val();
              
              if(ed_status=="yes"){
              $("#new_group").hide();
               $("#existing_group").show();p
              }
              else if (ed_status=="no"){
                $("#new_group").show();
               $("#existing_group").hide();
              }
              else{
               $("#new_group").hide();
               $("#existing_group").hide();   
              }
          });
   
      $("#provider_status").change(function(){
               var ed_status;
              ed_status= $("#provider_status").val();
              
              if(ed_status=="yes"){
              $("#new_provider").hide();
               $("#existing_provider").show();
              }
              else if (ed_status=="no"){
                $("#new_provider").show();
               $("#existing_provider").hide();
              }
              else{
               $("#new_provider").hide();
               $("#existing_provider").hide();   
              }
          }); 
    
               $.ajax({
               url:"getFname",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#dfname").html(data);
            
             } 
           });
                   $.ajax({
               url:"getMname",
               type:"post",
               dataType:"html",
               success:function(data){
                
               $("#dmname").val(data); 
             } 
           });
                   $.ajax({
               url:"getLname",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#dlname").html(data); 
             } 
           });
                           $.ajax({
               url:"getLocation",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#loc").html(data); 
             } 
           });
           
               $.ajax({
               url:"getCnationaID",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#nationaID").html(data); 
             } 
           });
           
       $.ajax({
               url:"getCPhone",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#phone").html(data); 
             } 
           });        
       
   $.ajax({
               url:"getGroupName",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#new_group_data").html(data); 
             } 
           });      
      
        $.ajax({
               url:"getProviderName",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#pro_fname").html(data); 
             } 
           }); 
      
          $.ajax({
               url:"getPnationalID",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#pro_nationalID").html(data); 
             } 
           }); 
       
           $.ajax({
               url:"getPphone",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#pro_phone").html(data); 
             } 
           }); 
              $.ajax({
               url:"getCCCNO",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#ccc").html(data); 
             } 
           }); 
           
            $.ajax({
               url:"getApprover",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#approv").html(data); 
             } 
           }); 
              $.ajax({
               url:"getDesignation",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#design").html(data); 
             } 
           });
       
        $("#dob").change(function(){
            var dob=$("#dob").val();
      $( "#registration_date" ).val("");
       // $("#registration_date").datepicker("destroy");
        
         $.ajax({
        url:"loadMinDate",
        type:"post",
        dataType:"html",
        success:function(data){
        var minDate=data.split("/");
        var year,month,dater;
        year=minDate[2];
        month=minDate[0];
        dater=minDate[1];
        
      var OrderedMinDate=year.trim()+"-"+month.trim()+"-"+dater.trim();
//      alert(" cal start date : "+OrderedMinDate+"dob : "+dob);
       
         
        
    //   $( "#registration_date" ).datepicker({changeMonth: true, changeYear: true, yearRange: ''+prev_year+':'+currentYear+'', dateFormat: 'yy-mm-dd',minDate: OrderedMinDate,maxDate: new Date()});
      }
    });
    
      
    });
       
        $("#registration_date").change(function(){
            var registration_date=$("#registration_date").val();
     $("#approval_date").val("");
      $("#approval_date").datepicker("destroy");
$("#approval_date").datepicker({changeMonth: true, changeYear: true, yearRange: ''+prev_year+':'+currentYear+'', dateFormat: 'yy-mm-dd',minDate: registration_date,maxDate: new Date()});
//alert("approval date should show calender now");
        });
     
    $("#partner_name") .change(function(){
        var partner_id=$("#partner_name") .val();
        county_id= $("#county").val();
        $.ajax({
               url:"getDIC?partner_id="+partner_id,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#dic").html(data); 
               $("#dic").select2(); 
             } 
           });
        if(partner_id!="" && county_id!=""){
         
         $.ajax({
               url:"loadWards?partner_id="+partner_id+"&&county_id="+county_id,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#ward").html(data); 
               $("#ward").select2(); 
             } 
           });
        } 
    });   
//        
//     $.ajax({
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
document.getElementById("partner_name").innerHTML=xmlhttp.responseText;
$('select').select2();
}
}
xmlhttp.open("POST","load_partners?county="+county,true);
xmlhttp.send();
}

    </script>
      <script src="jBox/jBox.js"></script>
    <!--<script src="jBox/jBox.min.js"></script>-->
   <link rel="stylesheet" href="jBox/jBox.css">
        <script type="text/javascript">
     $(document).ready(function(){
     $('.textbox').jBox('Tooltip', {
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
    <style>
legend { 
    display: block;
    padding-left: 2px;
    padding-right: 2px;
    border: none;
}
</style>
<link rel="stylesheet" href="select2/css/select2.css">

<!--<link rel="stylesheet" type="text/css" href="jnotify/jquery/jNotify.jquery.css" media="screen" />-->
 
<!-- jQuery files -->
<!--<script type="text/javascript" src="jnotify/jquery/jquery.js"></script>-->
<!--<script type="text/javascript" src="jnotify/jquery/jNotify.jquery.js"></script>-->
<script type="text/javascript">
//  $(document).ready(function(){
//  // simple Notify box call
////  jNotify();
// 
//  // simple Error box call
////  jError();
// 
//  // simple Success box call
////  jSuccess();
// 
//  // more complex Notify box call
//  jNotify(
//    'Here the message !!<br />You can write HTML code, <strong>bold</strong>,...',
//    {
//      autoHide : true, // added in v2.0
//      TimeShown : 7000,
//      HorizontalPosition : 'center',
//      VerticalPosition:'center'
////      onCompleted : function(){ // added in v2.0
////      alert('jNofity is completed !');
////    }
//  }
//  ); // close jNotify
//}); // close document.ready


$(document).ready(function(){
//        new jBox('Notice',
//        {autoClose: 3900},			// Time when jBox should close automatically
//		{content: "Please confirm"},
//        {color: 'white'},				// Makes your notices colorful, use 'black', 'red', 'green', 'blue', 'yellow'
//		{stack: true},				// Set to false to disable notice-stacking
//		{audio: false},				// Set the url to an audio file without extention, e.g. '/url/filename'. jBox will look for an .mp3 and an .ogg file
//		{volume: 100},
//              
//{fixed: true,
//			position: {x: 620, y: 300},
//			attributes: {x: 'right', y: 'top'},
//			animation: 'zoomIn',
//			closeOnClick: 'box'}
//
//);        
});
</script>
<style>
 .button-link {
-webkit-border-radius: 2px;
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
    text-decoration: none;
}   
    </style>

<link rel="stylesheet" href="pnotify/pnotify.custom.css">
<script type="text/javascript" src="pnotify/pnotify.custom.js"></script>
<script type="text/javascript">
$(document).ready(function(){
//new PNotify({
//    title: "<font color='red' style='text-align:center'>NOTE : </font>",
//    text: "Please ensure this client does not exist in the system before registering him/her."
////   text: "Please ensure this client does not exist in the system before registering him/her <a href='EditClientRegistrationForm.jsp' class='button-link'>Registered Forms.</a>." 
////    animation: 'fade'
//});
});
</script>
<!--<link rel="stylesheet" href="pnotify/buttonlink.css">-->
    
<!--<link rel="stylesheet" type="text/css" href="AlertJU/jquery-ui.css">-->
<link rel="stylesheet" type="text/css" href="AlertJU/jquery.jui_alert.css">

<!--<script type="text/javascript" src="AlertJU/jquery.js"></script>-->
<script type="text/javascript" src="AlertJU/jquery-ui.js"></script>
<script type="text/javascript" src="AlertJU/jquery.jui_alert.min.js"></script>
<script type="text/javascript" src="AlertJU/en.js"></script>

<script>
    $(document).ready(function(){
    $("#maritalStatus").change(function(){
         var fname=$("#fname").val();
         var lname=$("#lname").val();
           $.ajax({
        url:"ifRegistered?fname="+fname+"&&lname="+lname,
        type:"post",
       dataType:"html",
        success:function(data){
            if(data.trim()==""){
 $("#ff").hide();
           
            }
            else{
                 $("#ff").show();
  $("#ff").jui_alert({
  message: data,
  timeout: 600000,
   messageAreaClass: "jui_alert_message_area ui-state-error ui-corner-all",
    messageIconClass: "jui_alert_icon ui-icon ui-icon-alert",
    use_effect: {effect_name: "slide", effect_options: {"direction": "left"}, effect_duration: "500"}
});
 $( "#shaker" ).effect( "shake" );
            }   }
    });   
         
     });
     
     $("#gender").change(function(){
         var fname=$("#fname").val();
         var lname=$("#lname").val();
           $.ajax({
        url:"ifRegistered?fname="+fname+"&&lname="+lname,
        type:"post",
       dataType:"html",
        success:function(data){
            if(data.trim()==""){
 $("#ff").hide();
            }
            else{
                 $("#ff").show();
  $("#ff").jui_alert({
  message: data,
  timeout: 600000,
   messageAreaClass: "jui_alert_message_area ui-state-error ui-corner-all",
    messageIconClass: "jui_alert_icon ui-icon ui-icon-alert",
   use_effect: "shake"
});

            }  }
    });   
         
     });
     
  $("#fname").on('input',function(){
         var fname=$("#fname").val();
         var lname=$("#lname").val();
           $.ajax({
        url:"ifRegistered?fname="+fname+"&&lname="+lname,
        type:"post",
       dataType:"html",
        success:function(data){
            if(data.trim()==""){
 $("#ff").hide();
           
            }
            else{
                 $("#ff").show();
  $("#ff").jui_alert({
  message: data,
  timeout: 600000,
   messageAreaClass: "jui_alert_message_area ui-state-error ui-corner-all",
    messageIconClass: "jui_alert_icon ui-icon ui-icon-alert",
   use_effect: "shake"
});
            }   }
    });   
         
     });   
     
       $("#lname").on('input',function(){
         var fname=$("#fname").val();
         var lname=$("#lname").val();
           $.ajax({
        url:"ifRegistered?fname="+fname+"&&lname="+lname,
        type:"post",
       dataType:"html",
        success:function(data){
            if(data.trim()==""){
 $("#ff").hide();
           
            }
            else{
                 $("#ff").show();
  $("#ff").jui_alert({
  message: data,
  timeout: 600000,
   messageAreaClass: "jui_alert_message_area ui-state-error ui-corner-all",
    messageIconClass: "jui_alert_icon ui-icon ui-icon-alert",
   use_effect: "shake"
});
            }   }
    });   
         
     }); 
    
    });
    </script>


<script>
    $(document).ready(function(){
     $("#ccc_no").on('input',function(){
         var ccc_no=$("#ccc_no").val();
       if(ccc_no!=""){
           $.ajax({
        url:"cccChecker?ccc_no="+ccc_no,
        type:"post",
       dataType:"html",
        success:function(data){
            if(data.trim()==""){
  $("#ff").hide();
  $("#existccc").val("");
           }
            else{
                $("#existccc").val("1");
                 $("#ff").show();
  $("#ff").jui_alert({
  message: data,
  timeout: 600000,
   messageAreaClass: "jui_alert_message_area ui-state-error ui-corner-all",
    messageIconClass: "jui_alert_icon ui-icon ui-icon-alert",
    use_effect: {effect_name: "slide", effect_options: {"direction": "left"}, effect_duration: "500"}
});
 $( "#shaker" ).effect( "shake" );
            }   }
    });   
     }     
     });  
    });
    </script>
    </head>
    <body onload="">
       
     <div id="container" style="height:auto;">
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
  
  <div id="ff"></div>
<div style="margin-left:0px; width: 100%; margin-top: 20px; color: #000; background: #c4c4ff; font-size: 24px; text-align: center;">PWP Client Registration Form.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4></div>    
            <div id="content" style="height:auto; width: 1000px;">
                <%if (session.getAttribute("fullname")!=null){ %>
                <div style="margin-left: 20px; margin-top: 10px">
                 Hi, <u><%=session.getAttribute("fullname").toString()%></u>  
                    
                </div> <%}%>
                <div id="midcontent" style="margin-left:50px ; height:auto; width: 950px;" >
                 <%if (session.getAttribute("addedClient") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("addedClient")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("addedClient");
                            }
%>
<p id="showMessage" style="background-color: black"></p>
<!--<br>-->
<div id="dialog" title="Client Enrollment form." style=" font-size: 17px;">
    <p>Fields marked <font color="red">*</font> are required fields.</p>
    <p>On typing the client's details there is a list of already existing details from the system. e.g
        <img src='images/name.jpg' alt='image' style='width:200px; height: 150px;'> 
        shows all clients with first name <b>JANE</b> who already existing in the system. This can assist users to know those clients who already exist in their system.</p>
    <p>All fields that have inputs have that option to assist users know who exist in the system for quick decision making.</p>
</div>
                    <form action="saveClient" method="post" style="width: 1000px;">
                      <table align="center" border="0" cellpadding="0" cellspacing="0">
                          
<tr><td> 
<!-- Tabs -->
<div id="wizard" class="swMain" style="height: auto;">
   
        <ul>
                            <li style="font-size: 25px;font-family: sans-serif; color: black; margin-left: 10px;">SECTION.</li>
                            <li id="step1" style="height: auto;"><a href="#step-1">
                <label class="stepNumber">1</label>
                <span class="stepDesc">
<!--                   Step 1<br />-->
<small ><b style="font-size: 10px;">PERSONAL INFORMATION OF THE CARE-GIVER</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-2">
                <label class="stepNumber">2</label>
                <span class="stepDesc">
<!--                   Step 2<br />-->
            <small><b style="font-size: 10px;">EDUCATION & HOUSEHOLD DETAILS OF CARE-GIVER</b></small>
                </span>
            </a></li>
  				<li style="height: auto;"><a href="#step-3">
                <label class="stepNumber">3</label>
                <span class="stepDesc">
<!--                   Step 3<br />-->
                   <small><b style="font-size: 10px;">SUPPORT GROUP DETAILS</b></small>
                </span>                   
             </a></li>
  				<li style="height: auto;"><a href="#step-4">
                <label class="stepNumber">4</label>
                <span class="stepDesc">
<!--                   Step 4<br />-->
                  <small><b style="font-size: 10px;">CARE-GIVER HEALTH DETAILS</b></small>
                </span>                   
            </a></li>
            
            	<li style="height: auto;"><a href="#step-5">
                <label class="stepNumber">5</label>
                <span class="stepDesc">
<!--                   Step 4<br />-->
                  <small><b style="font-size: 10px;">SIGNATURE AND APPROVAL SECTION</b></small>
                </span>                   
            </a></li>
            
            
  			</ul>
  		
  		                     
  	<input type="hidden" name="existccc" id="existccc" value="">
  			<div id="step-1">
                            <h2 class="StepTitle"><font color="green">A.  </font> Personal Information of the caregiver.<i style="font-size: 10px;">(Fields marked <font color="red">*</font> are required.)</i></h2>
            <table style="width: 100%">
           <tr>
            <td>First Name <font color="red">*</font> : </td>
            <td><input type="text" name="fname" id="fname" list="dfname" title="<font color='red'>NOTE : </font> First name must be atleast 3 characters." autocomplete="off" class="textbox" style="border-color: green; width:120px;"></td>
            <datalist id="dfname">
                <option value="mwas">
             </datalist>
            <td>Middle Name : </td>
            <td><input type="text" name="mname" id="mname" title="<font color='red'>NOTE : </font> Middle name is optional." list="dmname" autocomplete="off" class="textbox" style="border-color: green; width:120px;"></td>
            <datalist id="dmname">
             </datalist>
            <td>Last Name <font color="red">*</font> : </td>
            <td><input type="text" name="lname" id="lname" title="<font color='red'>NOTE : </font> Last name must be atleast 3 characters." list="dlname" autocomplete="off" class="textbox" style="border-color: green; width:120px;"></td>
            <datalist id="dlname">
             </datalist>
            </tr>
            <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
             <tr>
            <td>County Name <font color="red">*</font> : </td>
            <td><select name="county" id="county"  class="textbox2" title="Click here to select county name.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:140px;">
                    <option value="">Choose County</option>
                </select></td>
            
            <td>District Name <font color="red">*</font> : </td>
           <td><select name="district" id="district"  class="textbox2" title="Click here to select district name.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:140px;">
                    <option value="">Choose District</option>
                </select></td> 
            <td>Location: </td>
            <td><input type="text" name="location" id="location" title="<font color='red'>NOTE : </font> Client's location is optional." autocomplete="off" list="loc" class="textbox" style="border-color: green; width:120px;"></td>
            <datalist id="loc">
             </datalist>
            </tr>
           <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
           <tr>
               <td >National ID: </td>
            <td><input type="text" name="nationalID" class="textbox" title="<font color='red'>NOTE : </font> National ID number is optional.<br>1. ID numbers are unique (two or more clients cannot share the same ID number) <br>2. If entered it must be in the correct format.<br>3. Clients with the same National ID Number will not be saved" autocomplete="off" id="nationalID" list="nationaID" maxlength="8" onkeypress="return numbers(event)"  class="textbox" style="border-color: green; width:120px;"></td>
           <datalist id="nationaID">
             </datalist>
            <td></td><td></td>
            <td >Mobile No: </td>
          <td><input type="text" name="mobileNO" autocomplete="off" id="mobileNO" list="phone" maxlength="10" onkeypress="return numbers(event)" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> Mobile phone nmber is option. If entered, it must be 10 digits.eg 07********"></td>
            <datalist id="phone">
             </datalist>
                 </tr>
             <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
            <tr>
            <td>Gender <font color="red">*</font>  : </td>
             <td><select name="gender" id="gender" class="textbox2" title="Click here to select client's gender.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:140px;">
                    <option value="">Choose Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select></td>
            <td>Date of Birth <font color="red">* (yyyy-mm-dd)</font> : </td>
            <td><input type="text" name="dob" readonly="true" id="dob" title="<font color='red'>NOTE : </font> Date of birth must be entered." class="textbox" style="border-color: green; width:120px;"></td>
            
            <td>Marital status <font color="red">*</font> : </td>
             <td><select name="maritalStatus" id="maritalStatus" title="Click here to select client's marital status.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:140px;">
                    <option value="">Choose Status</option>
                    <option value="1">Single</option>
                    <option value="2">Married</option>
                    <option value="3">Separated</option>
                    <option value="4">Widowed</option>
                    <option value="5">Divorced</option>
                    
                </select></td>
            </tr>
            </table>                        			
        </div>
    
    <div id="step-2">
            <h2 class="StepTitle">B. <font color="green">Education & household details of caregiver. <i style="font-size: 10px;">(Fields marked <font color="red">*</font> are required.)</i></font></h2>
          
          
            <table>
            <tr >
            <td> Type of employment <font color="red">*</font> :</td>
           <td><select name="employment" id="employment" class="textbox2" title="Click here to select client's employment status.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:300px;">
                    <option value="">Choose employment</option>
                    <option value="1">Formal Employment</option>
                    <option value="2">Self employment</option>
                    <option value="3">Casual Labour</option>
                    <option value="4">Family Support</option>
                </select>
           
           </td>
           </tr>
          <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
           <tr>
           
           <td>Completed Level of education <font color="red">*</font>  :</td>
           <td><select name="education" id="education" class="textbox2" title="Click here to select client's highest education level.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:300px;">
                    <option value="">Choose education level</option>
                    <option value="1">Pre-Primary</option>
                    <option value="2">Primary</option>
                    <option value="3">Secondary</option>
                    <option value="4">Tertiary</option>
                    <option value="5">Vocational</option>
                    <option value="6">None</option>
                </select>
           
           </td>
           </tr>
          <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
            <tr>
                <td colspan="2">Number of children living in the household below 18 years: </td>
 <td><input type="text" name="under_18" id="under_18"  autocomplete="off" maxlength="3" onkeypress="return numbers(event)" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> This field is optional, if there is no data, leave it blank or write 0."></td>
  </tr>  
  <tr>
 <td colspan="2">Number of children in APHIA OVC program</td>
 <td><input type="text" name="ovc_children" autocomplete="off" id="ovc_children" maxlength="3" onkeypress="return numbers(event)" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> This field is optional, if there is no data, leave it blank or write 0."><font color="grey" style="font-size: 10px;"><br>(For APHIA enrolled beneficiaries).</font></td>
               
            
            </tr>
            
            </table>                        			
        </div>
    
    <div id="step-3" style="height:400px;">
            <h2 class="StepTitle">C. <font color="green">Support group details. <i style="font-size: 10px;">(Fields marked <font color="red">*</font> are required.)</i></font></h2>
            <table style="width: 100%">
            
                 <tr>
            <td>Choose partner Name <font color="red">*</font>  </td>
           <td><select name="partner_name" id="partner_name" title="Click here to select client's implementing partner.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:300px;">
                    <option value="">Choose Partner</option>
                    </select></td>
            </tr>
           
                 <tr>
            <td>Choose DIC </td>
           <td><select name="dic" id="dic" required class="textbox2" title="Click here to select client's DIC if available.<br>(<font color='red'>This field is optional.</font>)" style="border-color: green; width:300px;">
                    <option value="">Choose DIC</option>
                    </select></td>
            </tr> 
            
              <tr>
            <td>Choose Ward </td>
           <td><select name="ward" id="ward" required class="textbox2" title="Click here to select ward." style="border-color: green; width:300px;">
                    <option value="">Choose ward</option>
                    </select></td>
            </tr>
            
            <tr>
            <td>Linked to support group? <font color="red">*</font>  </td>
           <td><select name="linked_to_group" id="linked_to_group" title="Click here to select if the client is linked to a support group.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:300px;">
                    <option value="">Choose Status</option>
                    <option value="yes">YES</option>
                    <option value="no">NO</option>
                </select></td>
            </tr>
            
            <tr id="receive_message">
            <td><b>How does this client receive messages?</b> </td>
           <td><select name="client_messages" id="client_messages" required class="textbox2" title="Click to choose if the client receives messages either in a group or as individual." style="border-color: green; width:300px;">
                   <option value=""></option>
                   <option value="yes">Receive message in a group.</option>
                    <option value="no">Receive message as an individual.</option>
                    </select></td>
            </tr>
            
            <tr id="grouper">
            <td>Does the group exist in the system or is it a new group? <font color="red">*</font>  </td>
            <td><select name="group_status" id="group_status" title="Click here to select if it is <b>an existing group</b> or <b>a new group</b>.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:300px;">
                    <option value="">Choose Status</option>
                    <option value="yes">Existing Group</option>
                    <option value="no">New group</option>
                </select></td>
            </tr>
            
            </table>
            
            <fieldset id="new_group"><legend>Add a new Group:</legend>
            <table>
            <tr >
            <td>Group Name <font color="red">*</font>  : </td>
            <td><input type="text" name="new_group_name"  title="<font color='red'>NOTE : </font> This field is required. <br> If the group had already been registered, in the prior field, select existing group then you will be able to select that group from the drop down." autocomplete="off" list="new_group_data" style="border-color:green;" id="new_group_name" value="" class="textbox"></td>
           
            </tr>
          </table></fieldset>
         <datalist id="new_group_data">
             </datalist>
            <fieldset id="existing_group"><legend>Choose Existing Group :</legend>
            <table>
            <tr>
            <td>Name of support group <font color="red">*</font> : </td>
           <td><select name="group_name" id="group_name" class="textbox2" title="Click here to select a group that this client is linked to.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:300px;">
                    <option value="">Choose Group</option>
                </select></td>
            </tr></table>
            </fieldset>
            
            <table  style="width: 100%"> <tr>
            <td>Does the service provider exist in the system? <font color="red">*</font> </td>
            <td><select name="provider_status" id="provider_status" title="Click here to select wether the service provider <b>exist</b> in the system or it is a <b>new service provider</b>.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:300px;">
                    <option value="">Choose status</option>
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                    
                </select></td>
            </tr>
            
            </table>  
            <fieldset id="existing_provider"><legend>Choose an existing service provider</legend>
            <table> <tr>
            <td>Choose service provider <font color="red">*</font> </td>
           <td><select name="service_provider" id="service_provider" title="Click here to select an existing/already registered service provider.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:300px;">
                    <option value="">Loading providers ...</option>
                </select></td>
            </tr>
            </table>     
            </fieldset>    
            
            <fieldset id="new_provider"><legend>Add new service Provider</legend>
            <table>
                <tr>
            <td>First Name <font color="red">*</font> : </td>
            <td><input type="text" name="pfname" id="pfname" title="<font color='red'>NOTE : </font> service provide's first name must be atleast 3 characters." autocomplete="off" list="pro_fname" class="textbox" style="border-color: green; width:120px;"></td>
            <datalist id="pro_fname">
             </datalist>
            <td>Middle Name : </td>
            <td><input type="text" name="pmname" id="pmname" title="<font color='red'>NOTE : </font> service provider's middle name is optional." autocomplete="off" class="textbox" style="border-color: green; width:120px;"></td>
            
            <td>Last Name <font color="red">*</font> : </td>
            <td><input type="text" name="plname" id="plname" title="<font color='red'>NOTE : </font> service provider's last name must be atleast 3 characters." autocomplete="off" class="textbox" style="border-color: green; width:120px;"></td>
            
            </tr>
           <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
           <tr>
               <td >National ID: </td>
               <td><input type="text" name="pnationalID" autocomplete="off" id="pnationalID" list="pro_nationalID" maxlength="8" onkeypress="return numbers(event)"  class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> National ID number is optional. if entered it must be atmost 8 digits long."></td>
            <datalist id="pro_nationalID">
             </datalist>
               <td></td><td></td>
            <td >Mobile No: </td>
          <td><input type="text" name="pmobileNO" id="pmobileNO" autocomplete="off" list="pro_phone" maxlength="10" onkeypress="return numbers(event)" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> service provider's phone number is optional, if vailable it must be 10 digits eg 0721****00."></td>
           <datalist id="pro_phone">
             </datalist>
          
                 </tr>
            </table>     
            </fieldset>  
            
            
            
            
            
        </div>
    
    <div id="step-4">
            <h2 class="StepTitle">D. <font color="green">Care-giver health details. <i style="font-size: 10px;">(Fields marked <font color="red">*</font> are required.)</i></font></h2>
            <table style="width: 100%" >
                  
            <tr>
                <td>Year confirmed HIV+ <font color="red">*</font> </td>
               <td><select name="year_confirmed" id="year_confirmed" title="Click here to select year this client was confirmed HIV+.<br>(<font color='red'>This field is required.</font>)" class="textbox2" style="border-color: green; width:300px;">
                    <option value="">Loading years ...</option>
                </select></td>
            </tr>
           <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr> 
                      
 <tr>
            <td>Client ART Status <font color="red">*</font> : </td>
             <td><select name="art_status" id="art_status" class="textbox2" title="Click here to select client's ART status.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:300px;">
                    <option value="">Choose Status</option>
                    <option value="1">ARV Treatment</option>
                    <option value="2">Prophylaxis (Care)</option>
                </select></td>
            </tr>
              <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr> 
            <tr>
                <td>Health facility linked to <font color="red">*</font>  : </td>
               <td><select name="health_facility" id="health_facility" class="textbox2" title="Click here to select a health facility this client is linked to.<br>(<font color='red'>This field is required.</font>)" style="border-color: green; width:300px;">
               <option value="">Loading facilities...</option>
                </select><i style="font-size: 10px; color: black">(Click and wait for facilities to be loaded...)</i></td>
            </tr> 
         <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>   
             <tr>
         <td>CCC NO <font color="red">*</font> : </td>
         <td><input type="text" name="ccc_no" id="ccc_no" autocomplete="off" onkeypress="return numbers(event)" list="ccc" maxlength="11" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> CCC NO is required.<br>1. CCC Number is unique(two or more clients cannot share the same CCC Number).<br>2. CCC Number must be between 6 and 11 digits long. <br>3. CCC Numbers already registered in the system will pop out when the user starts typing.<br>4. Client with the same CCC Number will not be saved."></td>
         <datalist id="ccc">
             </datalist>      
            
            </tr>
            </table>                        			
        </div>
    
    <div id="step-5">
            <h2 class="StepTitle">E. <font color="green">Signature and approval section. <i style="font-size: 10px;">(Fields marked <font color="red">*</font> are required.)</i></font></h2>
          
            
            <table style="width: 100%">
           <tr>
           <td>Registration date <font color="red">*</font>  <font color="red">(mm/dd/yyyy)</font> :  </td>
         <td><input type="text" name="registration_date" readonly="true" title="<font color='red'>NOTE : </font> This field is required.<br> Click to select the date when the client was registered." autocomplete="off" id="registration_date" class="textbox" style="border-color: green; width:120px;"></td>
            </tr>
           <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr> 
            <tr>
           <td>Approved by <font color="red">*</font>  :  </td>
         <td><input type="text" name="approved_by" list="approv" autocomplete="off" title="<font color='red'>NOTE : </font> This field is required.<br> When you start typing, a list of saved approvers will pop up. Users can select from this list." id="approved_by" class="textbox" style="border-color: green; width:120px;"></td>
          <datalist id="approv">
             </datalist> 
            </tr>
          <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
             <tr>
           <td>Designation <font color="red">*</font>  :  </td>
         <td><input type="text" name="designation" list="design" autocomplete="off" id="designation" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> This field is required.<br> When you start typing, alist of already existing designation will pop out, users can select from this list."></td>
           <datalist id="design">
             </datalist> 
             </tr>
            <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
            <tr>i
                <td>Approval Date <font color="red">*</font>  <font color="red">(mm/dd/yyyy)</font>  :  </td>
         <td><input type="text" name="approval_date" readonly="true" autocomplete="off" id="approval_date" class="textbox" style="border-color: green; width:120px;" title="<font color='red'>NOTE : </font> This field is required.<br>Enter the date when this registration form was approved."></td>
            </tr>
            
            </table>                        			
        </div>
  		</div>
  		
<!-- End SmartWizard Content -->  		
  		
</td></tr>
</table>  
                       
                            
                           
                                      <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
                           
                        
                  
                    </form>
                </div>
          
            </div>
<div id="footer">
               <p align="center" style=" font-size: 18px;"> &copy M&E Checklist System Aphia Plus | USAID <%=year%></p>
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

