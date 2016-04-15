<%-- 
    Document   : guest_menu
    Created on : Mar 15, 2014, 8:22:21 PM
    Author     : Geofrey Nyabuto
--%>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='css/styles.css' />
        <link rel='stylesheet' type='text/css' href='css/buttons.css' /><!--
         <script type="text/javascript" src="js/1.10.2.min.js"></script>-->
</head>
<body>
<div id='cssmenu'>
<ul>
 
<li class='has-sub'><a href='#'><span class="btn_header">Reports</span></a>
      <ul>
       <li class='has-sub'><a href='#'><span>1. Custom reports</span></a>
         <ul>
          <li class='has-sub'><a href='enrollments.jsp'><span>1.Enrollments Report</span></a></li>
          <li class='has-sub'><a href='CustomRawData.jsp'><span>2. Messages Given</span></a></li>
         <!--<li class='has-sub'><a href='servicesProvided.jsp'><span>3. Services Provided</span></a></li>-->
         </ul>
         </li> 
         <li class='has-sub'><a href='rawData.jsp'><span>2. Raw Data</span></a></li>
         <li class='has-sub'><a href='kePMS.jsp'><span>3. KePMS Report</span></a></li>
         <li class='has-sub'><a href='OtherReports.jsp'><span>4. Other Reports.</span></a>
<!--         <ul>
         <li class='has-sub'><a href='completed13Messages'><span>Attended 13 messages report.</span></a></li>
         <li class='has-sub'><a href='notReached'><span>Clients not reached report.</span></a></li>
         </ul>-->
         </li>
         
      </ul>
     
   </li>
  
    <li><a href='help/PWP Presentation Slides.pdf'>Help</a></li>
   <li class='last'><a href='logout.jsp'><span class="btn_header">Log Out</span></a></li>
</ul>
</div>
</body>
</html>