<%@page import="pwp.dbConn"%>
<%@page import="java.util.Calendar"%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if ((!session.getAttribute("level").equals("2")) || (session.getAttribute("userid")==null)) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="shortcut icon" href="images/header.JPG"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

<!-- IMPORT THE LIBRARIES FOR THE ACCORDION    -->
                <link rel="stylesheet" type="text/css" href="nested2/css/default.css" />
		<link rel="stylesheet" type="text/css" href="nested2/css/component.css" />
		<script src="nested2/js/modernizr.custom.js"></script>
                <script src="nested2/jq.1.9.1.min.js"></script>
		<script src="nested2/js/jquery.cbpNTAccordion.min.js"></script>
		<script>
			$( function() {
				$( '#cbp-ntaccordion' ).cbpNTAccordion();

			} );
		</script>
</head>
<body>

    <div id="container" style=" height: auto;" >
<%@include file="/menu/clerk_menu.html" %>
<div id="header" align="center">
<br/>
</div>                
<div id="content" style="height:auto; width: 950px;">


<div id="midcontent" style="margin-left:30px ; height: auto;">
    <div class="container" style=" width: 950px;">
			<header class="clearfix">
                            <h1 style=" margin-left: 300px;">PWP System Help.</h1>
				
			</header>	
        <div class="main" style="margin-left: 10px; width: 900px;">
				<ul id="cbp-ntaccordion" class="cbp-ntaccordion">
					<li>
						<h3 class="cbp-nttrigger">Healthy Facility.</h3>
						<div class="cbp-ntcontent">
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Add Healthy Facility</h4>
									<div class="cbp-ntcontent">
                                                                            <p>   </p>
                                                                        </div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Edit Healthy Facility</h4>
									<div class="cbp-ntcontent">
                                                                            <p> </p>
                                                                        </div>
								</li>
								
							</ul>
						</div>
					</li>
					
                                        <li>
						<h3 class="cbp-nttrigger">Groups.</h3>
						<div class="cbp-ntcontent">
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Add Groups</h4>
									<div class="cbp-ntcontent">
                                                                            <p>   </p>
                                                                        </div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Edit Groups</h4>
									<div class="cbp-ntcontent">
                                                                            <p> </p>
                                                                        </div>
								</li>
								
							</ul>
						</div>
					</li>
                                        
                                        <li>
						<h3 class="cbp-nttrigger">Providers.</h3>
						<div class="cbp-ntcontent">
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Add Providers</h4>
									<div class="cbp-ntcontent">
                                                                            <p>   </p>
                                                                        </div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Edit Providers</h4>
									<div class="cbp-ntcontent">
                                                                            <p> </p>
                                                                        </div>
								</li>
								
							</ul>
						</div>
					</li>
                                        
                                        <li>
						<h3 class="cbp-nttrigger">Clients.</h3>
						<div class="cbp-ntcontent">
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Add Clients</h4>
									<div class="cbp-ntcontent">
                                                                            <p>   </p>
                                                                        </div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Edit Clients</h4>
									<div class="cbp-ntcontent">
                                                                            <p> </p>
                                                                        </div>
								</li>
								
							</ul>
						</div>
					</li>
                                        
                                        <li>
						<h3 class="cbp-nttrigger">Attendance.</h3>
						<div class="cbp-ntcontent">
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Mark Attendance Register</h4>
									<div class="cbp-ntcontent">
                                                                            <p>   </p>
                                                                        </div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Edit Attendance Register</h4>
									<div class="cbp-ntcontent">
                                                                            <p> </p>
                                                                        </div>
								</li>
								
							</ul>
						</div>
					</li>
                                        
                                        <li>
						<h3 class="cbp-nttrigger">Healthy Facility.</h3>
						<div class="cbp-ntcontent">
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Add Healthy Facility</h4>
									<div class="cbp-ntcontent">
                                                                            <p>   </p>
                                                                        </div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Edit Healthy Facility</h4>
									<div class="cbp-ntcontent">
                                                                            <p> </p>
                                                                        </div>
								</li>
								
							</ul>
						</div>
					</li>
					
					
				</ul>
			</div>
		</div>
</div>
</div>

<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center" style=" font-size: 20px; color: #000"> &copy Aphia Plus | USAID <%=year%></p>
            </div>
</div>    
<!--
            <li>
						<h3 class="cbp-nttrigger">Jujubes bear claw topping</h3>
						<div class="cbp-ntcontent">
							<p>Cotton candy chocolate cake macaroon applicake I love. Tart soufflé I love powder. I love tiramisu lollipop pie. Pudding gummi bears sweet. Candy chocolate I love jujubes pie donut. Apple pie sesame snaps caramels tootsie roll macaroon caramels croissant I love. Sweet roll chocolate lemon drops. I love I love muffin gummi bears I love cotton candy.</p>
							<ul class="cbp-ntsubaccordion">
								<li>
									<h4 class="cbp-nttrigger">Faworki jelly pastry</h4>
									<div class="cbp-ntcontent">
										<p>Liquorice jelly-o oat cake gingerbread chocolate caramels chupa chups muffin candy. I love dessert biscuit halvah lemon drops. Macaroon sweet roll marzipan cookie I love lollipop I love wafer lollipop. Caramels candy pudding brownie. Oat cake cake gingerbread bear claw croissant dessert. Lemon drops oat cake sugar plum wypas cupcake fruitcake liquorice. Muffin wafer tootsie roll caramels. I love brownie powder icing marzipan dragée. Pie fruitcake soufflé sweet roll faworki candy canes I love. Jelly-o carrot cake donut cheesecake.</p>
										<ul class="cbp-ntsubaccordion">
											<li>
												<h5 class="cbp-nttrigger">Donut pastry</h5>
												<div class="cbp-ntcontent">
													<p>Gingerbread cotton candy halvah gingerbread. Apple pie wypas liquorice I love chocolate cake I love. Jelly cotton candy wypas lemon drops. Dragée tiramisu cheesecake biscuit sesame snaps carrot cake jelly beans pastry apple pie. Chocolate cake cotton candy candy canes brownie ice cream. Muffin chocolate cake jelly-o cake pudding. Jujubes I love cookie. I love cupcake I love bear claw sweet croissant. Wypas bonbon chocolate cake bonbon bear claw gummies. Liquorice danish jelly tootsie roll. I love danish icing lemon drops dessert pie jujubes. Fruitcake wafer I love biscuit. Donut pastry apple pie sugar plum soufflé ice cream tart bonbon candy.</p>
												</div>
											</li>
											<li>
												<h5 class="cbp-nttrigger">Carrot cake</h5>
												<div class="cbp-ntcontent">
													<p>Wafer muffin cupcake apple pie tootsie roll I love. Carrot cake apple pie I love dessert. I love carrot cake lollipop jelly jelly-o brownie cake. Croissant brownie donut gingerbread dessert icing. Sugar plum jelly candy pudding liquorice liquorice cotton candy pie. Powder sesame snaps I love chocolate bar bonbon. Pudding gummi bears donut applicake carrot cake I love I love icing cake.</p>
												</div>
											</li>
											<li>
												<h5 class="cbp-nttrigger">Tootsie roll marshmallow</h5>
												<div class="cbp-ntcontent">
													<p>I love tootsie roll marshmallow. Halvah jelly bear claw lemon drops lollipop. Brownie tiramisu I love I love halvah wafer. Powder jelly beans sesame snaps. Powder biscuit I love wypas soufflé apple pie marzipan. Cheesecake apple pie halvah croissant jelly I love.</p>
												</div>
											</li>
										</ul>
									</div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Chocolate caramels</h4>
									<div class="cbp-ntcontent">
										<p>Faworki oat cake cotton candy cookie ice cream gummi bears. Ice cream bear claw icing macaroon apple pie caramels. Sugar plum applicake candy canes sesame snaps I love. Sugar plum brownie biscuit tiramisu marzipan. Ice cream I love sweet bear claw I love sweet cake tart. Danish marshmallow I love wypas pastry. Chocolate tart macaroon wypas sesame snaps apple pie chocolate sesame snaps. Cotton candy sweet roll pudding oat cake I love marzipan wafer chocolate cake.</p>
									</div>
								</li>
								<li>
									<h4 class="cbp-nttrigger">Lollipop liquorice</h4>
									<div class="cbp-ntcontent">
										<p>Cookie ice cream sweet I love cupcake. Fruitcake topping wafer. Lollipop liquorice I love tart wypas biscuit. Danish jelly-o gingerbread. Faworki jelly pastry. Wypas wypas topping I love I love candy canes liquorice. I love sweet roll fruitcake dragée wafer icing bonbon.</p>
									</div>
								</li>
							</ul>
						</div>
					</li>-->
</body>
</html>
